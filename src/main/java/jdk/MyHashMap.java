package jdk;

import java.util.LinkedList;
import java.util.List;

/**
 * 未涉及到扩容操作的 hashMap
 */
public class MyHashMap {
    private static int default_length = 999;
    private int size = 0;
    private Object[] arr = new Object[default_length];

    public static void main(String args[]) {

        MyHashMap myHashMap = new MyHashMap();
        myHashMap.put("a", 123);
        myHashMap.put("a", 123);
        myHashMap.put("b", 345);
        myHashMap.put(null, 34545);
        System.out.println(myHashMap.size);
        System.out.println(myHashMap.get("a"));
        System.out.println(myHashMap.get(null));
    }

    public void put(Object key, Object value) {
        int index = hash(key);
        if (arr[index] == null) {
            // 如果当前槽位是空的,直接放进去就可以了
            List linkedList = new LinkedList();
            linkedList.add(new MyEntry(key, value));
            arr[index] = linkedList;
        } else {
            //如果不为空,检查当前 key 是否已存在
            //如果已经存在,替换 value, 否则在链表头部插入新的元素
            if (containsKey(key, index)) {
                LinkedList linkedList = (LinkedList) arr[index];
                for (Object o : linkedList) {
                    MyEntry entry = (MyEntry) o;
                    if (entry.getKey().equals(key)) {
                        entry.setValue(value);
                    }
                }
                return;
            } else {
                ((LinkedList) arr[index]).add(0, new MyEntry(key, value));
            }
        }
        size++;
    }


    private boolean containsKey(Object key, int index) {
        Object o = arr[index];
        if (o == null) {
            return false;
        }
        LinkedList linkedList = (LinkedList) o;
        for (Object o1 : linkedList) {
            if (o1 == null && key == null) {
                return true;
            }
            MyEntry entry = (MyEntry) o1;
            if (entry.getKey().equals(key)) {
                return true;
            }
        }
        return false;
    }

    public Object get(Object key) {
        if (key == null) {
            return ((MyEntry) ((LinkedList) arr[0]).getFirst()).getValue();
        }
        int index = hash(key);
        Object o = arr[index];
        if (o == null) {
            return null;
        }
        LinkedList linkedList = (LinkedList) o;
        for (Object o1 : linkedList) {
            if (o1 == null) {
                return null;
            }
            MyEntry entry = (MyEntry) o1;
            if (entry.getKey().equals(key)) {
                return entry.getValue();
            }
        }
        return null;
    }

    /**
     * 给定一个对象计算其 hash 值
     *
     * @param key
     * @return
     */
    private int hash(Object key) {
        if (key == null) {
            return 0;
        }
        return key.hashCode() & default_length;
    }

    /**
     * 内部类用来存放键值对
     */
    class MyEntry {
        private Object key;
        private Object value;

        private MyEntry() {
        }

        private MyEntry(Object key, Object value) {
            this.key = key;
            this.value = value;
        }

        public Object getKey() {
            return key;
        }

        public void setKey(Object key) {
            this.key = key;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }
    }

}
