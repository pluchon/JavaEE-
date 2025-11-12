import java.util.Locale;

/**
 * @author pluchon
 * @create 2025-11-04-20:13
 * 作者代码水平一般，难免难看，请见谅
 */
class Singleton {
    private static Singleton instance = new Singleton();//保证一个进程只有一个实例

    public static Singleton getInstance() {
        return instance;
    }

    private Singleton(){//构造方法私有化，防止通过new对象new出来

    }
}

class Singletons{
    public static Object locker = new Object();
    private static volatile Singletons instance;

    public static Singletons getInstance() {
        //调用该方法的时候才new对象
        if(instance == null) {
            synchronized (locker) {
                if (instance == null) {
                    instance = new Singletons();
                }
            }
        }
        return instance;
    }

    private Singletons(){

    }
}