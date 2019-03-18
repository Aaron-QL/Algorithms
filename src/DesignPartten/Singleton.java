package DesignPartten;

public class Singleton {
//    懒汉模式，线程不安全
//    private static Singleton instance;
//
//    private Singleton() {
//
//    }
//
//    public static Singleton getInstance() {
//        if (instance == null) {
//            instance = new Singleton();
//        }
//
//        return instance;
//    }

//    饿汉模式，线程安全
//    private static Singleton instance = new Singleton();;
//
//    private Singleton() {
//
//    }
//
//    public static Singleton getInstance() {
//        return instance;
//    }


//    懒汉模式，线程安全，性能不好，不推荐
//    private static Singleton instance;
//
//    private Singleton() {
//
//    }
//
//    public static synchronized Singleton getInstance() {
//        if (instance == null) {
//            instance = new Singleton();
//        }
//
//        return instance;
//    }

//    双重锁校验-线程安全
//    private static volatile Singleton instance;
//
//    private Singleton() {
//
//    }
//
//    public static synchronized Singleton getInstance() {
//        if (instance == null) {
//            synchronized (Singleton.class) {
//                if (instance == null) {
//                    instance = new Singleton();
//                }
//            }
//        }
//
//        return instance;
//    }

//  内部类实现
    private Singleton() {
    }

    private static class SingletonHolder {
        private static final Singleton INSTANCE = new Singleton();
    }

    public static Singleton getUniqueInstance() {
        return SingletonHolder.INSTANCE;
    }

}
