package project1;

import java.util.concurrent.BlockingQueue;

class Producer implements Runnable {
    private final BlockingQueue<String> sharedQueue;

    public Producer(BlockingQueue<String> sharedQueue) {
        this.sharedQueue = sharedQueue;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            try {          	
				sharedQueue.put("This is a new Item"+ i); // if the queue is full, the thread will be blocked here
                Thread.sleep(50); // simulate time passing during production
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }
}



