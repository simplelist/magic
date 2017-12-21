package jdk;

import sun.net.www.ParseUtil;

import java.util.*;

import static thread.wait_notify_size5.MyList.add;

/**
 * 简单实现 ThreadLocal
 *
 * @author shijikun
 * @date 21/12/2017 6:09 PM
 */
public class MyThreadLocal<T> {
    private Map<Thread, T> map = Collections.synchronizedMap(new HashMap<>());

    public void set(T value) {
        map.put(Thread.currentThread(), value);
    }

    public T get() {
        Thread currentThread = Thread.currentThread();
        T value = map.get(currentThread);
        if (value == null && !map.containsKey(currentThread)) {
            map.put(currentThread,value);
        }
        return value;
    }

    public void remove(){
        map.remove(Thread.currentThread());
    }

    public static void main(String args[]){
        MyThreadLocal threadLocal=new MyThreadLocal();
        new Thread(()->{
            List<String> list=new ArrayList(){{
                add("a");
                add("b");
                add("c");
            }};
            threadLocal.set(list);
            System.out.println(Thread.currentThread().getName());
            ((List)threadLocal.get()).forEach(param -> System.out.println(param));
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(1000);
                List<String> params = new ArrayList<>(2);
                params.add("Chinese");
                params.add("English");
                threadLocal.set(params);
                System.out.println(Thread.currentThread().getName());
                ((List)threadLocal.get()).forEach(param -> System.out.println(param));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
