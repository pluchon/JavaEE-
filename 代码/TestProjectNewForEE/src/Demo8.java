/**
 * @author pluchon
 * @create 2025-11-04-10:09
 * 作者代码水平一般，难免难看，请见谅
 */
public class Demo8 {
    public static int result = 0;
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(()->{
            for (int i = 0; i < 10000000; i++) {
                result++;
            }
        });
        t.start();
        t.join(1);
        System.out.println(result);
    }
}
