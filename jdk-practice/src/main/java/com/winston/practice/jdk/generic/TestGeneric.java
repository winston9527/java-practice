package com.winston.practice.jdk.generic;

import java.util.ArrayList;
import java.util.List;

public class TestGeneric {
    public static void main(String[] args) {


        List<String> list = new ArrayList<String>();
        //泛型的参数类型  不能是基本数据类型  只能是引用数据类型  包括用户自定义类型
        //传入的实参类型   需要和定义的 与泛型的类型参数类型相同
        GenericType<String> genericTypeString = new GenericType<String>("猪猪侠");
        System.out.println(genericTypeString.getData());

        GenericType<Integer> genericTypeInt = new GenericType<Integer>(110);
        System.out.println(genericTypeInt.getData());

        //可以不指定泛型类型   此时  可以传入任意类型
        GenericType genericType = new GenericType(new TestGeneric());
        System.out.println(genericType.getData());
        //不能对明确类型参数的泛型  使用instanceof
		/*if(genericTypeInt instanceof GenericType<Integer>) {
			
		}*/
        GenericType<Person> genericPerson = new GenericType<Person>(new Person("猪猪侠", 12));
        printGenericType(genericPerson);

        GenericType<Male> genericMale = new GenericType<Male>(new Male("猪猪侠", 12, "菲菲公主"));
        //The method printGenericType(GenericType<Person>) in the type TestGeneric is not applicable for the arguments (GenericType<Male>)
        //printGenericType(genericMale);

        try {
            Male male = GenericType.genericMethod(Male.class);
            System.out.println(male);
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    //测试泛型类型参数是否可以继承
    public static void printGenericType(GenericType<Person> person) {

        System.out.println(person.toString());

    }


}
