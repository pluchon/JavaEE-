/**
 * @author pluchon
 * @create 2025-11-04-19:44
 * 作者代码水平一般，难免难看，请见谅
 */
public class Demo18 {
    public static void main(String[] args) throws InterruptedException {
        Object lockers = new Object();
        synchronized (lockers){
            lockers.wait(10);
        }
    }
}
