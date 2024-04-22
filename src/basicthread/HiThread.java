package basicthread;

public class HiThread extends Thread {
    public void run() {
        for (int i = 0; i < 20; i++) {
            System.out.println("Hi");
            // try {
            // Thread.sleep(1000);
            // } catch (InterruptedException e) {
            // e.printStackTrace();
            // }
        }
    }
}
