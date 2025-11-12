/**
 * @author pluchon
 * @create 2025-11-04-14:20
 * 作者代码水平一般，难免难看，请见谅
 */
public class Demo9 {
    public static void main(String[] args) {
        Thread t = new Thread() {
            @Override
            public void run() {
                System.out.println(this.getName());
            }
        };
        t.start();
    }
}
