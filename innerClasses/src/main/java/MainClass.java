public class MainClass {

    public static void main(String[] args) {
        OuterClass outerClass = new OuterClass();
        OuterClass.InnerClass innerClass = outerClass.new InnerClass();
        OuterClass.staticInnerClass staticInnerClass = new OuterClass.staticInnerClass();
    }

}
