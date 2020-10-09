package com.winston.practice.jdk.method.reference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MethodReferenceTest {

    private String name;
    private int age;

    public static MethodReferenceTest create(Supplier<MethodReferenceTest> supplier) {
        return supplier.get();
    }

    public static void updateMethodReferenceTestname(MethodReferenceTest test) {
        test.setName(test.getName() + " updated.");
    }

    public void updateAge() {
        this.setAge(this.getAge() + 10);
    }

    public void changeAge(MethodReferenceTest test) {
        test.setAge(test.getAge() + 10);
    }

    public int compareAge(MethodReferenceTest test) {
        return test.getAge() - this.getAge();
    }

    public static void main(String[] args) {
        List<MethodReferenceTest> list = initList();

        // 1、构造器方法引用
        MethodReferenceTest newMethodReferenceTest = MethodReferenceTest.create(MethodReferenceTest::new);
        newMethodReferenceTest.setAge(1);
        newMethodReferenceTest.setName("new");
        System.out.println(newMethodReferenceTest);

        // 2、类静态方法引用
        list.forEach(MethodReferenceTest::updateMethodReferenceTestname);

        // 3、类普通方法引用

        list.forEach(MethodReferenceTest::updateAge);
        /**
         *         list.sort((methodReferenceTest1, test) -> methodReferenceTest1.compareAge(test));
         *         ->
         *         list.sort(MethodReferenceTest::compareAge);
         *
         * 那使用 类名::实例方法名 方法引用时，一定是o1来调用了compareAge实例方法，
         * 并将o2作为参数传递进来进行比较。是不是就符合了compareAge的方法定义。
         */
        list.sort(MethodReferenceTest::compareAge);

        MethodReferenceTest methodReferenceTest = new MethodReferenceTest();
        // 4、实例方法引用
        list.forEach(methodReferenceTest::changeAge);

        list.forEach(System.out::println);
    }

    private static List<MethodReferenceTest> initList() {
        List<MethodReferenceTest> list = new ArrayList<>();
        list.add(new MethodReferenceTest("oaby", 23));
        list.add(new MethodReferenceTest("tom", 11));
        list.add(new MethodReferenceTest("john", 16));
        list.add(new MethodReferenceTest("jennis", 26));
        list.add(new MethodReferenceTest("tin", 26));
        list.add(new MethodReferenceTest("army", 26));
        list.add(new MethodReferenceTest("mack", 19));
        list.add(new MethodReferenceTest("jobs", 65));
        list.add(new MethodReferenceTest("jordan", 23));
        return list;
    }


}
