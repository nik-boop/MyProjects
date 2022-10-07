public class OuterClass {

    public OuterClass(){
        System.out.println("Created OuterClass");
    }

    public int publicVar = 1;
    private int privateVar = 2;
    protected int protectedVar = 3;
    int var = 4;

    public static int staticVar = 5;

    public static void staticMethod(){
        System.out.println("Static method");
    }

    public void publicMethod(){
        System.out.println("Public method");
    }

    private void privateMethod(){
        System.out.println("Private method");
    }

    protected void protectedMethod(){
        System.out.println("Protected method");
    }

    void method (){
        System.out.println("Method");
    }

    public class InnerClass {

        public InnerClass(){
            System.out.println("Created InnerClass");
            System.out.println(publicVar);
            System.out.println(privateVar);
            System.out.println(protectedVar);
            System.out.println(var);

            publicMethod();
            privateMethod();
            protectedMethod();
            method();
        }
    }

    public static class staticInnerClass {
        public staticInnerClass(){
            System.out.println("Created staticInnerClass");
            System.out.println(staticVar);
            staticMethod();
        }
    }
}
