import java.util.PriorityQueue;

/**
 * @author pluchon
 * @create 2025-11-05-14:55
 * 作者代码水平一般，难免难看，请见谅
 */

//实现接口，重写方法，把先添加的任务放在前面执行
//达到类似延迟执行的效果
class MyTimerTask implements Comparable<MyTimerTask>{
    private Runnable runnable;
    //判定是否到达了执行时间
    private long time;

    public MyTimerTask(Runnable runnable,long delay){
        //执行时间是当前系统时间+指定的延时执行时间delay
        this.time = System.currentTimeMillis()+delay;
        this.runnable = runnable;
    }

    public long getTime(){
        return time;
    }

    public void run(){
        runnable.run();
    }

    @Override
    public int compareTo(MyTimerTask o) {
        return (int)(this.time-o.time);
    }
}

public class MyTimer {
    private PriorityQueue<MyTimerTask> queue = new PriorityQueue<>();
    //引入锁对象
    private Object locker = new Object();

    public void schedule(Runnable runnable,long delay){
        synchronized (locker) {
            MyTimerTask task = new MyTimerTask(runnable, delay);
            queue.add(task);
            //此时任务队列就有新的任务类，我们执行唤醒操作
            locker.notify();
        }
    }

    public MyTimer(){
        Thread t = new Thread(()->{
            synchronized (locker) {
                while (true) {
                    //每次查看这个任务是否到了需要执行的时间
                    MyTimerTask task = queue.peek();
                    //处理空队列情况
                    while (task == null) {
                        //如果任务队列是空的，我们就等待
                        try {
                            locker.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        //等待之后我们再看看是否任务队列还是空的
                        task = queue.peek();
                    }
                    long currentTime = System.currentTimeMillis();
                    if (currentTime >= task.getTime()) {
                        //说明时间已经到了，需要执行
                        task.run();
                        //任务执行完毕后踢出队列
                        queue.poll();
                    } else {
                        //说明时间还没有到，继续判断
                        //我们加入wait使其进入阻塞状态
                        //我们等多久呢，我们等到这个任务需要执行的时候即可
                        try {
                            locker.wait(task.getTime()-currentTime);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        });
        t.start();
    }

    public static void main(String[] args) {
        MyTimer timer = new MyTimer();
        timer.schedule(()->{
            System.out.println("1000");
        },1000);
        timer.schedule(()->{
            System.out.println("2000");
        },2000);
        timer.schedule(()->{
            System.out.println("3000");
        },3000);
    }
}
