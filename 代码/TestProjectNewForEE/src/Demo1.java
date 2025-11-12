/**
 * @author pluchon
 * @create 2025-11-03-19:19
 * 作者代码水平一般，难免难看，请见谅
 */
class MyThead extends Thread{
    @Override
    public void run() {
        //这里就是线程入口
        while(true) {
            System.out.println("hello");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

public class Demo1 {
    public static void main(String[] args) throws InterruptedException {
        //我们使用向上转型，这是Java中常见的写法
        Thread thread = new MyThead();
        //我们通过start方法去调用操作系统的API，在系统内部创建了线程
        //此时start方法内部自动调用了run方法
        thread.start();
        while(true) {
            System.out.println("hello main");
            Thread.sleep(1000);
        }
    }
}
