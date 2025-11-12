import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * @author pluchon
 * @create 2025-11-09-15:18
 * 作者代码水平一般，难免难看，请见谅
 */
public class UdpEchoServer {
    //创建数据报
    private DatagramSocket socket;

    //构造方法制定端口号
    public UdpEchoServer(int port) throws SocketException {
        socket = new DatagramSocket(port);
    }

    //我们继续启动服务器
    public void start() throws IOException {
        //保证服务器长期运行，因此使用死循环
        while(true){
            //我们手动分配一个缓冲区的内存空间数组，并且指定缓冲区的长度
            //本质上还是一个输出型参数，相当于白纸，等待数据来填充
            DatagramPacket requestPacket = new DatagramPacket(new byte[1024],1024);
            socket.receive(requestPacket);
            //我们是面向数据报的，因此我们使用字符串进行传输
            //获取之前创建的字节数组，以及对应的偏移量和长度
            String request = new String(requestPacket.getData(),0,requestPacket.getLength());
            //我们再把上述请求发给回显服务器，再接收结果
            String response = process(request);
            //我们不可以使用size代替length，因为size记录的是字符数，而我们length求的是字节数，最后再明确把这个响应发送给谁
            DatagramPacket responsePacket = new DatagramPacket(response.getBytes(),response.getBytes().length,requestPacket.getSocketAddress());
            socket.send(responsePacket);
            //再打印发送日志
            System.out.printf("[%s:%d] request:%s,response:%s\n",requestPacket.getAddress().toString(),requestPacket.getPort(),request,response);
        }
    }

    public String process(String request) {
        return "哇酷哇酷" + request;
    }

    public static void main(String[] args) throws IOException {
        UdpEchoServer server = new UdpEchoServer(9009);
        server.start();
    }
}
