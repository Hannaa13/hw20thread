package com.company;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;

public class MainBlockingQueue {
    private static java.util.concurrent.BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            try {
                produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread thread2 = new Thread(() -> {
            try {
                consumer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
    }

    private static void produce() throws InterruptedException {
        Random random = new Random();
        while (true) {
            queue.put(random.nextInt(100));


        }
    }

    private static void consumer() throws InterruptedException {

        while (true) {
            Thread.sleep(100);

            System.out.println(queue.take());

        }
    }
}
