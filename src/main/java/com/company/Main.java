package com.company;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        new ProducerConsumer().work();
    }
}

class ProducerConsumer {
    private final int LIMIT = 10;
    private Random random = new Random();
    private List<Integer> list = new ArrayList<>();


    public void addToList() throws InterruptedException {
        while (true) {
            synchronized (this) {

                while (list.size() == LIMIT) {
                    wait();
                }

                list.add(random.nextInt(LIMIT));
                System.out.println(" >>> add data " + list.toString());

                notify();
            }
        }
    }


    public void removeFromList() throws InterruptedException {
        while (true) {
            synchronized (this) {
                while (list.size() == 0) {
                    wait();
                }
                list.remove(list.size() - 1);
                System.out.println( " >>> remove data " + list.toString());
                notify();


            }
        }
    }






        public void work () {
            Thread thread1 = new Thread(() -> {
                try {
                    addToList();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            Thread thread2 = new Thread(() -> {
                try {
                    removeFromList();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

            thread1.start();
            thread2.start();


        }


    }


