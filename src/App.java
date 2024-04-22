
import basicthread.BasicThread;
import basicthread.HelloThread;
import basicthread.HiThread;
import childthread.ChildThread1;
import lifecycle.LifeCycleThread;
import lifecycle.TimedWaitingRunnable;
import priority.PriorityThread1;
import priority.PriorityThread2;
import synchronization.Counter;
import synchronization.CounterWithSynchronization;

public class App {
    private static final Object object = new Object();

    public static void main(String[] args) throws Exception {

        // Example for Basic Threads
        HiThread hiThread = new HiThread();
        HelloThread helloThread = new HelloThread();
        hiThread.start();
        helloThread.start();
        hiThread.join();
        helloThread.join();

        // Example when threads use same resource without synchronization
        Counter normalCounter = new Counter();
        Thread normalThread1 = new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 2000; i++) {
                    normalCounter.increment();
                }
            }
        });
        Thread normalThread2 = new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 2000; i++) {
                    normalCounter.increment();
                }
            }
        });
        normalThread1.start();
        normalThread2.start();
        normalThread1.join();
        normalThread2.join();

        System.out.println("final count normal thread:" + normalCounter.getCount());

        // Example for Thread using same reource with Synchronization
        CounterWithSynchronization counter = new CounterWithSynchronization();
        Thread incrementThread1 = new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 2000; i++) {
                    counter.increment();
                }
            }
        });

        Thread incrementThread2 = new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 2000; i++) {
                    counter.increment();
                }
            }
        });
        incrementThread1.start();
        incrementThread2.start();
        incrementThread1.join();
        incrementThread2.join();
        System.out.println("final count:" + counter.getCount());

        // Example for Thread using Lambda Expression
        Thread lambdaThread1 = new Thread(() -> {
            System.out.println("hello from lambda");
        });
        int value = 10;
        Thread lambdaThread2 = new Thread(() -> {
            System.out.println(value);
        });
        lambdaThread1.start();
        lambdaThread2.start();

        // Example for Thread Life Cycle
        Thread lifeCycleThread = new LifeCycleThread();
        System.out.println("Before Start, State:" + lifeCycleThread.getState()); //
        // NEW State
        lifeCycleThread.start();
        System.out.println("After Starting, State:" + lifeCycleThread.getState()); //
        // RUNNABLE State
        lifeCycleThread.join();
        System.out.println("After joining, State: " + lifeCycleThread.getState()); //
        // TERMINATED State
        Thread runnableWaitingThread = new Thread(new InternalRunnableWaitingThread());
        runnableWaitingThread.start();
        while (runnableWaitingThread.getState() != Thread.State.WAITING) {

        }
        System.out.println("After making thread Wait, State: " +
                runnableWaitingThread.getState()); // WAITING State
        synchronized (object) {
            object.notifyAll();
        }
        System.out.println("After notifying the thread, State: " +
                runnableWaitingThread.getState()); // TERMINATED
        // State
        Thread timedWaitingThread = new Thread(new TimedWaitingRunnable());
        timedWaitingThread.start();
        while (timedWaitingThread.getState() != Thread.State.TIMED_WAITING) {

        }
        System.out.println("Timed Waiting Thread, State: " +
                timedWaitingThread.getState()); // TIMED_WAITING State
        Thread lockholderThread = new Thread(new LockHolder());
        Thread blockedThread = new Thread(new BlockedThread());
        lockholderThread.start();
        blockedThread.start();
        while (blockedThread.getState() != Thread.State.BLOCKED) {

        }
        System.out.println("After Blocking the Thread, State: " +
                blockedThread.getState()); // BLOCKED State
        synchronized (object) {
            object.notifyAll();
        }

        // Example for Thread Priority

        PriorityThread1 priorityThread1 = new PriorityThread1();
        PriorityThread2 priorityThread2 = new PriorityThread2();

        System.out.println("Priority of priorityThread1: " +
                priorityThread1.getPriority());
        System.out.println("Priority of priorityThread2: " +
                priorityThread2.getPriority());

        priorityThread1.setPriority(3);
        priorityThread2.setPriority(7);

        priorityThread1.setPriority(Thread.MIN_PRIORITY);
        priorityThread2.setPriority(Thread.MAX_PRIORITY);

        priorityThread1.start();
        priorityThread2.start();

        System.out.println("After changing priority, priorityThread1: " +
                priorityThread1.getPriority());
        System.out.println("After changing priority, priorityThread2: " +
                priorityThread2.getPriority());

        // Example for Main Thread and Child Thread
        ChildThread1 childThread1 = new ChildThread1("Child Thread");
        System.out.println(
                "Current Running thread: " + Thread.currentThread() + ", name: " +
                        Thread.currentThread().getName()); // main
        childThread1.start();
        System.out.println("Current running thread: " + Thread.currentThread());

        // Example for Thread.start vs Thread.run methods
        for (int i = 0; i < 2; i++) {
            BasicThread basicThread = new BasicThread();
            basicThread.run(); // runs same thread

        }
        for (int i = 0; i < 2; i++) {
            BasicThread basicThread = new BasicThread();
            basicThread.start(); // runs unique thread everytime

        }
        // End of Main
    }

    private static class InternalRunnableWaitingThread implements Runnable {
        public void run() {
            synchronized (object) {
                try {
                    this.wait();
                } catch (Exception e) {

                }
            }
        }

    }

    private static class LockHolder implements Runnable {

        @Override
        public void run() {
            synchronized (object) {
                try {
                    Thread.sleep(2000);
                } catch (Exception e) {
                }
            }
        }

    }

    private static class BlockedThread implements Runnable {

        @Override
        public void run() {
            synchronized (object) {

            }
        }

    }

}
