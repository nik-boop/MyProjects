public class Main2 {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("The different states of a thread are: ");

        /* Add code here */
        for(Thread.State state: Thread.State.values())
        {
            System.out.print(state+" ");
        }
        System.out.print("\nLet's get practical:\n");

        MyThread t=new MyThread("Thread States");
        t.start();
        //wait 500 milliseconds for the thread to die
        /*Add code here*/
        Thread.sleep(500);

        t.printState();
        t.join(3000);
        t.printState();
        //Notify thread to wake up
        /* Add code here */
        t.threadNotify();
        t.printState();
        //wait forever for the thread to die
        /* Add code here */
        Thread.sleep(10);
        Thread.currentThread().interrupt();
        t.printState();

    }
}