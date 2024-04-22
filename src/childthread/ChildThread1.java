package childthread;

public class ChildThread1 extends Thread {
    public ChildThread1(String name) {
        this.setName(name);
    }

    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("Hi from Child Thread");
        }
        System.out.println("Checking from Child Thread: " + Thread.currentThread().getName());
    }
}
