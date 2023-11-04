package project1;

import java.util.concurrent.BlockingQueue;

class Consumer implements Runnable {
    private final BlockingQueue<String> sharedQueue;

    public Consumer(BlockingQueue<String> sharedQueue) {
        this.sharedQueue = sharedQueue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                String message = sharedQueue.take(); // if the queue is empty, the thread will be blocked here
                Thread.sleep(50); // simulate time passing during consumption
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
