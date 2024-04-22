package priority;

public class PriorityThread1 extends Thread {
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("Hi from PriorityThread1");
        }
    }
}
