import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author pluchon
 * @create 2025-11-06-08:32
 * 作者代码水平一般，难免难看，请见谅
 */
public class Demo2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable<Integer> callable = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                int sum = 0;
                for (int i = 0; i < 1000; i++) {
                    sum += i;
                }
                return sum;
            }
        };
        //因为对于Thread类来说无法直接接收Callable类的返回值
        //需要一个类表示Callable的未来会接收到的结果
        FutureTask<Integer> futureTask = new FutureTask<>(callable);
        Thread t = new Thread(futureTask);
        t.start();
        //此时get方法如果我们Callable中没有执行完就进入阻塞状态
        //等到执行完了我们就使用get方法获取值
        System.out.println(futureTask.get());
    }
}
