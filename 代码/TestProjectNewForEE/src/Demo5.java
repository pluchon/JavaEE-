/**
 * @author pluchon
 * @create 2025-11-03-19:59
 * 作者代码水平一般，难免难看，请见谅
 */
public class Demo5 {
    public static void main(String[] args) {
        Thread thread = new Thread(()->{
            while(true){
                System.out.println("hello");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
