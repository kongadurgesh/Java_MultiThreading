package lifecycle;

public class RunnableWaitingThread implements Runnable {

    public void run() {
        try {
            Thread.sleep(1000);
        } catch (Exception e) {

        }

    }

}
