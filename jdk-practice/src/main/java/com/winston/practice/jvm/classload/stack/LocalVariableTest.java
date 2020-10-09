package com.winston.practice.jvm.classload.stack;

public class LocalVariableTest {
    public static void main(String[] args) {
        String a = "sss";
        int b = 10;
        LocalVariableTest c = new LocalVariableTest();
    }

    public void sayHello() {
        int i = 0;
        System.out.println(i);
    }

    /**
     * {
     *   public com.winston.practice.jvm.classload.stack.LocalVariableTest();
     *     descriptor: ()V
     *     flags: ACC_PUBLIC
     *     Code:
     *       stack=1, locals=1, args_size=1
     *          0: aload_0
     *          1: invokespecial #1                  // Method java/lang/Object."<init>":()V
     *          4: return
     *       LineNumberTable:
     *         line 3: 0
     *       LocalVariableTable:
     *         Start  Length  Slot  Name   Signature
     *             0       5     0  this   Lcom/winston/practice/jvm/classload/stack/LocalVariableTest;
     *
     *   public static void main(java.lang.String[]);
     *     descriptor: ([Ljava/lang/String;)V
     *     flags: ACC_PUBLIC, ACC_STATIC
     *     Code:
     *       stack=2, locals=4, args_size=1
     *          0: ldc           #2                  // String sss
     *          2: astore_1
     *          3: bipush        10
     *          5: istore_2
     *          6: new           #3                  // class com/winston/practice/jvm/classload/stack/LocalVariableTest
     *          9: dup
     *         10: invokespecial #4                  // Method "<init>":()V
     *         13: astore_3
     *         14: return
     *       LineNumberTable:
     *         line 5: 0
     *         line 6: 3
     *         line 7: 6
     *         line 8: 14
     *       LocalVariableTable:
     *         Start  Length  Slot  Name   Signature
     *             0      15     0  args   [Ljava/lang/String;
     *             3      12     1     a   Ljava/lang/String;
     *             6       9     2     b   I
     *            14       1     3     c   Lcom/winston/practice/jvm/classload/stack/LocalVariableTest;
     *
     *   public void sayHello();
     *     descriptor: ()V
     *     flags: ACC_PUBLIC
     *     Code:
     *       stack=2, locals=2, args_size=1
     *          0: iconst_0
     *          1: istore_1
     *          2: getstatic     #5                  // Field java/lang/System.out:Ljava/io/PrintStream;
     *          5: iload_1
     *          6: invokevirtual #6                  // Method java/io/PrintStream.println:(I)V
     *          9: return
     *       LineNumberTable:
     *         line 11: 0
     *         line 12: 2
     *         line 13: 9
     *       LocalVariableTable:
     *         Start  Length  Slot  Name   Signature
     *             0      10     0  this   Lcom/winston/practice/jvm/classload/stack/LocalVariableTest;
     *             2       8     1     i   I
     * }
     */
}
