package com.winston.practice.jdk.testcollections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * * Collection 集合框架里面的底层接口。一个集合通常包含一组元素。 其常用的子接口为
 * ①java.util.List
 * ②java.util.Set
 * ③java.util.Queue
 *
 * @Author Winston
 * @Version 1.0 2018年9月17日 下午4:11:54
 */
public class TestCollection {

    public static void main(String[] args) {
        /**
         * Collection
         */
        Collection<String> collection = new ArrayList<String>();
        // 1、新增元素
        collection.add("AA");
        System.out.println(collection);
        // 2、获取集合元素个数
        System.out.println("集合元素个数：" + collection.size());
        // 3、判断集合是否为空
        System.out.println("集合是否为空：" + collection.isEmpty());
        collection.add("BB");
        collection.add("CC");
        // 4、判断元素是否存在
        System.out.println("集合是否包含元素CC：" + collection.contains("CC"));
        // 此处是调用集合里面对象的equals方法 （if (o.equals(elementData[i]))）。所以下面即使是new
        // String("CC")。获取到的结果也是true
        System.out.println(collection.contains(new String("CC")));
        // 5、获取集合的迭代器 用于循环访问元素
        System.out.print("iterator() 获取集合的迭代器 用于循环访问元素 :");
        Iterator<String> iterator = collection.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + "  ");
        }
        System.out.println();
        // 6、将集合转换为对象数组
        Object[] newArray = collection.toArray();
        for (Object obj : newArray) {
            System.out.println(obj);
        }
        String[] stringArray = new String[5];
        // 7、将集合转换为指定类型的对象数组
        String[] newArray2 = collection.toArray(stringArray);
        for (String obj : newArray2) {
            System.out.println(obj);
        }
        // 当传入数组长度大于或者等于集合长度的时候 直接拷贝元素到数组 因此此处返回true
        System.out.println(stringArray == newArray2);
        String[] stringArray2 = new String[1];
        // 将集合转换为对象数组
        String[] newArray3 = collection.toArray(stringArray2);
        // 当传入数组长度小于集合长度的时候 创建一个新的数组实例并且将元素拷贝进去 因此此处返回false
        System.out.println(stringArray2 == newArray3);
        // 8、删除AA元素、返回是否删除成功
        collection.remove("AA");
        System.out.print("删除AA之后的元素：");
        collection.forEach(arg -> System.out.print(arg + "  "));
        System.out.println();

        Collection<String> collection2 = new ArrayList<String>();
        collection2.add("CC");
        // 9、containsAll 判断collection 是否包含 collection2 的所有元素。contains 只判断一个元素
        System.out.println("collection 是否包含 collection2 的所有元素" + collection.containsAll(collection2));
        collection2.add("DD");
        collection2.add("EE");
        // 10、将collection2全部添加到collection
        collection.addAll(collection2);
        System.out.print("将collection2全部添加到collection后的元素：");
        collection.forEach(arg -> System.out.print(arg + "  "));
        System.out.println();
        // 11、将集合collection里面存在collection2里面的元素 全部删除
        collection.removeAll(collection2);
        System.out.print("将集合collection里面存在collection2里面的元素  全部删除后的元素：");
        collection.forEach(arg -> System.out.print(arg + "  "));
        System.out.println();

        collection.add("DD");
        collection.add("EE");
        // 12、保存collection中 存在collection2中的元素。如果不再collection2中则删除。相当于取交集
        collection.retainAll(collection2);

        System.out.print("collection与collection2取交集后的元素：");
        collection.forEach(arg -> System.out.print(arg + "  "));
        System.out.println();

        // 13 清空集合
        collection.clear();

        System.out.println("集合清空后  集合的长度" + collection.size());
        collection.addAll(collection2);
        collection.addAll(collection2);
        // 14 jdk8 新特性 循环集合
        collection.forEach(arg -> {
            System.out.print(arg + "  ");
        });
        System.out.println();
        // 15 jdk8 新特性 循环集合 remove只会删除第一个匹配的。removeIf会循环删除所有匹配的
        collection.removeIf(arg -> arg.equals("DD"));
        System.out.print("lumada 删除元素后 ");
        collection.forEach(arg -> {
            System.out.print(arg + "  ");
        });

    }

}
