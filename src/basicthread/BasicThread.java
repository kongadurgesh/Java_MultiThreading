package basicthread;

public class BasicThread extends Thread {
    public void run() {
        System.out.println("From BasicThread run method: " + Thread.currentThread().threadId());
    }
}
