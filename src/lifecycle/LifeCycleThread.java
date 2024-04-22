package lifecycle;

public class LifeCycleThread extends Thread {
    public void run() {
        for (int i = 0; i < 10; i++) {
            // System.out.println("LifeCycle Thread Started");
            try {
                Thread.sleep(100);
            } catch (Exception e) {

            }
        }

    }
}
