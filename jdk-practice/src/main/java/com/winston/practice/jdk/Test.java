package com.winston.practice.jdk;


public class Test {


    public static void main(String[] args) {

        User u = new User();
        u.setName("小狗");
        System.out.println(u);
        chanegUser(u);
        //在调用changeUser的时候 会将u的地址 赋值给 user
        //所以 改变user名字为hehe的时候 其实改变的是 本方法中u的名字
        //但是后续 user被赋值了一个 新的地址 不再指向本方法中的 u了，所以他在后面修改为 小麦的时候 本方法的u不再变动
        System.out.println(u);

    }

    static void chanegUser(User user) {
        user.setName("hehe");
        System.out.println(user);
        user = new User();
        user.setName("小麦");
        System.out.println(user);
    }
}

class User {

    private String name;


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "User [name=" + name + "]";
    }


}