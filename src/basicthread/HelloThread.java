package basicthread;

public class HelloThread extends Thread {

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            System.out.println("Hello");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
