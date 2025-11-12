import java.io.IOException;
import java.util.HashMap;

/**
 * @author pluchon
 * @create 2025-11-09-16:00
 * 作者代码水平一般，难免难看，请见谅
 */
public class UdpTransServer extends UdpEchoServer{
    //翻译词典
    private HashMap<String,String> hash = new HashMap<>();

    public UdpTransServer(int port) throws IOException{
        super(port);
        //加入一些词典单词
        hash.put("hello","你好");
        hash.put("world","世界");
        hash.put("apple","苹果");
        hash.put("tree","树");
    }

    @Override
    public String process(String request){
        return hash.getOrDefault(request,"未找到该单词");
    }

    public static void main(String[] args) throws IOException {
        UdpTransServer server = new UdpTransServer(9009);
        server.start();
    }
}
