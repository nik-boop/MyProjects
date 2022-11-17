
public class MyThreadRunnable implements Runnable{
    private Thread t;
    private final String name;
    private final Object obj;
    private static boolean objNotified = false;
    public MyThreadRunnable(String name)
    {
        this.name=name;
        obj=new Object();
    }
    @Override
    public void run() {
        printState();
        try {
            //Make thread sleep for 2000 milliseconds
            /*Add some code here*/
            Thread.sleep(2000, 20);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        synchronized (obj) {
            try {
                //make the thread wait until it is notified
                /*Add some code here*/
                //System.out.println("Waiting");

                obj.wait();
                //threadNotify();
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        }
        printState();
    }
    public void start()
    {
        t=new Thread(this,name);
        printState();
        t.start();
    }

    public  void printState()
    {
        //print the current state of the thread
        /*Add some code here*/
        //System.out.println("Thread name: " + Thread.currentThread().getName());

        System.out.println("Thread States: " + t.getState());
    }

    public void join() {
        try {
            t.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void join(int millis) {
        try {
            t.join(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void threadNotify()
    {
        synchronized (obj) {
            obj.notify();
        }
    }
}
