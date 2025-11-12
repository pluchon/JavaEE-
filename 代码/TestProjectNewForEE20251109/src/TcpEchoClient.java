import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.DatagramSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author pluchon
 * @create 2025-11-09-16:35
 * 作者代码水平一般，难免难看，请见谅
 */
public class TcpEchoClient {
    private Socket socket;

    public TcpEchoClient(String serverIp, int serverPort) throws IOException {
        socket = new Socket(serverIp, serverPort);
    }

    public void start() {
        try (InputStream inputStream = socket.getInputStream();
             OutputStream outputStream = socket.getOutputStream()) {
            Scanner sc = new Scanner(System.in);
            //针对服务器的响应，我们也要使用Scanner进行包装
            Scanner scOut = new Scanner(inputStream);
            PrintWriter printWriter = new PrintWriter(outputStream);
            while (true) {
                String request = sc.next();
                //格式化包装发给服务器
                printWriter.println(request);
                printWriter.flush();//直接写入内存
                if(!scOut.hasNext()){
                    break;
                }
                String response = scOut.next();
                System.out.println("服务器返回的结果是"+response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException{
        TcpEchoClient client = new TcpEchoClient("127.0.0.1",9010);
        client.start();
    }
}