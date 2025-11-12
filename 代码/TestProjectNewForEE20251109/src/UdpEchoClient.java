import java.io.IOException;
import java.net.*;
import java.util.Scanner;

/**
 * @author pluchon
 * @create 2025-11-09-15:44
 * 作者代码水平一般，难免难看，请见谅
 */
public class UdpEchoClient {
    private DatagramSocket socket;
    private String serverIp;//服务器IP地址
    private int serverPort;//服务器端口号

    //我们在构建客户端的时候，把服务器IP和端口号只指定下
    public UdpEchoClient(String serverIp,int serverPort) throws SocketException {
        socket = new DatagramSocket();
        this.serverIp = serverIp;
        this.serverPort = serverPort;
    }

    public void start() throws IOException {
        System.out.println("客户端启动！！");
        while(true) {
            //客户端用户开始输入请求
            Scanner sc = new Scanner(System.in);
            String request = sc.next();
            //我们以字节为单位生成数据报，同时我们也可以来判断客户端是否要进行退出操作
            if (request.equals("0")) {
                break;
            }
            //同时也要明确服务器的IP以及端口号，我们上面已经指定好了
            DatagramPacket requestPacket = new DatagramPacket(request.getBytes(), request.getBytes().length, InetAddress.getByName(serverIp), this.serverPort);
            socket.send(requestPacket);//发送
            DatagramPacket responsePacket = new DatagramPacket(new byte[1024],1024);
            //接收返回的结果，并将结果放入输出线参数中
            socket.receive(responsePacket);
            String response = new String(responsePacket.getData(),0,responsePacket.getLength());
            //显示结果
            System.out.println("服务器返回结果是："+response);
        }
    }

    public static void main(String[] args) throws IOException {
        UdpEchoClient client = new UdpEchoClient("127.0.0.1",9009);
        client.start();
    }
}
