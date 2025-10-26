package org.example.algorithms.microbatching;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class MicroBatcher {

    public static class Result {
        private String url;
        private String content;
        private Exception exception;

        public Result(String url, String content, Exception exception) {
            this.url = url;
            this.content = content;
            this.exception = exception;
        }

        @Override
        public String toString() {
            return "Result{" +
                    "url='" + url + '\'' +
                    ", content='" + content + '\'' +
                    ", exception=" + exception + System.getProperty("line.separator") +
                    '}';
        }
    }

    private ExecutorService executorService = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors() * 100
    );

    private final Random rand = new Random();

    private String getURL(String url) throws InterruptedException {
        int randomNum = rand.nextInt(2000 - 1000 + 1) + 1000;
        Thread.sleep(randomNum);
        return "Calling URL " + url;
    }

    public List<Result> getUrls(List<String> urls, int concurrencyLimit) {

        List<Result> result = new CopyOnWriteArrayList<>();
        
        var semaphore = new Semaphore(concurrencyLimit);

        for (String url : urls) {
            try {
                semaphore.acquire();
                executorService.submit(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            System.out.println("Calling URL" + url);
                            var response = getURL(url);
                            result.add(new Result(url, response, null));
                        } catch (InterruptedException e) {
                            result.add(new Result(url, null, e));
                        } finally {
                            semaphore.release();
                        }
                    }
                });
            } catch (InterruptedException e) {
                result.add(new Result(url, null, e));
                semaphore.release();
            }
        }

        executorService.shutdown();

        return result;
    }

    public List<Result> getUrlsByBatch(List<String> urls, int concurrencyLimit) {
        List<Result> result = Collections.synchronizedList(new ArrayList<>());

        for (int i=0;i<urls.size();i+=concurrencyLimit) {
            CountDownLatch latch = new CountDownLatch(Math.min(concurrencyLimit, urls.size() - i));
            for (int j=i;j<Math.min(i+concurrencyLimit, urls.size());j++) {
                String url = urls.get(j);
                executorService.submit(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            System.out.println("Calling URL" + url);
                            var response = getURL(url);
                            result.add(new Result(url, response, null));
                        } catch (InterruptedException e) {
                            result.add(new Result(url, null, e));
                        } finally {
                            latch.countDown();
                        }
                    }
                });
            }
            try {
                latch.await(); // wait for all concurrent threads to complete before hitting the next batch.
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }

        executorService.shutdown();

        return result;
    }

    public static void main(String[] args) {
        var batcher = new MicroBatcher();

        List<String> urls = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            urls.add("https://example.com/api/endpoint " + i);
        }

        int concurrencyLimit = 9;
        List<Result> results = batcher.getUrlsByBatch(urls, concurrencyLimit);
        System.out.println("Results " + results);
    }
}
