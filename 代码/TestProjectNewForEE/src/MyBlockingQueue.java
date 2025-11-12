/**
 * @author pluchon
 * @create 2025-11-05-10:55
 * 作者代码水平一般，难免难看，请见谅
 */
public class MyBlockingQueue {
    private String[] array;
    //环形队列队头队尾指针
    private int head;
    private int end;
    //判断数组是否满了
    private int usedSize;
    //锁
    private Object locker = new Object();

    public MyBlockingQueue(int size){
        array = new String[size];
    }

    private void put(String str) throws InterruptedException {
        synchronized (locker){
            while(usedSize == array.length){
                locker.wait();
            }
            array[end] = str;
            end++;
            if(end >= array.length){
                end = 0;
            }
            usedSize++;
            //唤醒另一个因为队列为空而阻塞的线程
            locker.notify();
        }
    }

    private String take() throws InterruptedException {
        synchronized (locker){
            while(usedSize == 0){
                locker.wait();
            }
            String str = array[head];
            head++;
            if(head >= array.length){
                head = 0;
            }
            usedSize--;
            //唤醒另一个因为队列满了而阻塞的线程
            locker.notify();
            return str;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyBlockingQueue myBlockingQueue = new MyBlockingQueue(10);
        myBlockingQueue.put("aaa");
        myBlockingQueue.put("bbb");
        System.out.println(myBlockingQueue.take());
    }
}
