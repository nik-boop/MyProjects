public class MyThread extends Thread {
    private final Object obj;

    public MyThread(String name) {
        setName(name);
        obj = new Object();
    }

    @Override
    public void run() {
        printState();
        try {
            //Make thread sleep for 2000 milliseconds
            /*Add some code here*/
            sleep(2000, 20);
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

    @Override
    public synchronized void start() {
        printState();
        super.start();

    }

    public void printState() {
        //print the current state of the thread
        /*Add some code here*/
        System.out.println("Thread States: " + getState());
    }


    public void threadNotify() {
        synchronized (obj) {
            obj.notify();
        }
    }
}