import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author pluchon
 * @create 2025-11-09-16:16
 * 作者代码水平一般，难免难看，请见谅
 */
public class TcpEchoServer {
    private ServerSocket serverSocket;

    public TcpEchoServer(int port) throws IOException{
        serverSocket = new ServerSocket(port);
    }

    public void start() throws IOException {
        while(true){
            ExecutorService executors = Executors.newCachedThreadPool();
            //接受客户端请求并且连接，具体内部已经由操作系统实现
            //我们接下来就要把这个操作系统连接放到应用程序中，如果客户端没有连接此时会陷入阻塞状态
            Socket socket = serverSocket.accept();
            //我们把任务提交到线程池中，重复利用已有的线程，避免线程频繁的创建和销毁
            executors.submit(()->{
                //处理连接的过程
                try {
                    processConnection(socket);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }

    private void processConnection(Socket socket) throws IOException {
        //我们需要生成一个长连接，处理多个请求（短连接只能处理一个请求）
        System.out.printf("[%s:%d 客户端上线\n",socket.getInetAddress(),socket.getPort());
        //由于我们面向的是字节流，因此我们要使用try-with处理字节流对象，处理很多个请求
        try(InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream()){
            //使用Scanner解析
            Scanner sc = new Scanner(inputStream);
            //使用Writer子类格式化写入到输出流中
            PrintWriter printWriter = new PrintWriter(outputStream);
            //接下来我们针对每一个请求进行处理
            while (true){
                if(!sc.hasNext()){
                    //如果没有输入流信息，则说明客户端关闭了连接
                    break;
                }
                String request = sc.next();
                String response = process(request);
                //我们再把结果写回到客户端
                printWriter.println(response);
                printWriter.flush();//直接写入内存
                System.out.printf("[%s:%d] request:%s,response:%s\n",socket.getInetAddress().toString(),socket.getPort(),request,response);
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            socket.close();
        }
    }

    public String process(String request) {
        return "哇酷哇酷"+request;
    }

    public static void main(String[] args) throws IOException {
        TcpEchoServer server = new TcpEchoServer(9010);
        server.start();
    }
}
