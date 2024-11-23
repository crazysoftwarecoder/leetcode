import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class SimpleThreadPool {

    // Worker thread class
    private static class Worker extends Thread {
        private final BlockingQueue<Runnable> taskQueue;
        private volatile boolean isStopped = false;

        public Worker(BlockingQueue<Runnable> taskQueue) {
            this.taskQueue = taskQueue;
        }

        @Override
        public void run() {
            while (!isStopped) {
                try {
                    // Fetch task from the queue and execute it
                    Runnable task = taskQueue.take();
                    task.run();
                } catch (InterruptedException e) {
                    // Handle interruption gracefully
                    Thread.currentThread().interrupt();
                }
            }
        }

        public void stopWorker() {
            isStopped = true;
            this.interrupt(); // Interrupt the thread if it's waiting
        }
    }

    private final BlockingQueue<Runnable> taskQueue;
    private final Worker[] workers;

    public SimpleThreadPool(int numThreads) {
        taskQueue = new LinkedBlockingQueue<>();
        workers = new Worker[numThreads];

        // Initialize and start worker threads
        for (int i = 0; i < numThreads; i++) {
            workers[i] = new Worker(taskQueue);
            workers[i].start();
        }
    }

    // Method to submit a task to the pool
    public void submit(Runnable task) {
        try {
            taskQueue.put(task);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // Method to shut down the thread pool
    public void shutdown() {
        // Stop all workers
        for (Worker worker : workers) {
            worker.stopWorker();
        }
    }

    public static void main(String[] args) {
        // Create a thread pool with 3 threads
        SimpleThreadPool threadPool = new SimpleThreadPool(3);

        // Submit tasks to the thread pool
        for (int i = 1; i <= 10; i++) {
            final int taskId = i;
            threadPool.submit(() -> {
                System.out.println("Task " + taskId + " is executed by " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000); // Simulate task processing
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        // Shutdown the thread pool after all tasks are submitted
        threadPool.shutdown();
    }
}