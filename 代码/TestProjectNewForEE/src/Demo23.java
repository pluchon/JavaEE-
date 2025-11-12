import java.util.Timer;
import java.util.TimerTask;

/**
 * @author pluchon
 * @create 2025-11-05-14:50
 * 作者代码水平一般，难免难看，请见谅
 */
public class Demo23 {
    public static void main(String[] args) throws InterruptedException {
        Timer timer = new Timer();
        timer.schedule(new TimerTask(){
            @Override
            public void run() {
                System.out.println("hello");
            }
        },5000);
        Thread.sleep(8000);
        timer.cancel();
    }
}
