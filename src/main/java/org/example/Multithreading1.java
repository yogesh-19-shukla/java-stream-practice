package org.example;

import java.util.concurrent.Semaphore;


public class Multithreading1 {

    private int n;
    private Semaphore semZero = new Semaphore(1);
    private Semaphore semOdd = new Semaphore(0);
    private Semaphore semEven = new Semaphore(0);

    public Multithreading1(int n) {
        this.n = n;
    }

    public void printZero() {
        for (int i = 1; i <= n; i++) {
            //critical section
            try {
                semZero.acquire();
                System.out.print("0 ");
                if (i % 2 == 1) {
                    semOdd.release(); // odd turn
                } else {
                    semEven.release(); // even turn
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void printOdd() {
        for (int i = 1; i <= n; i += 2) {
            try {
                semOdd.acquire();
                System.out.print(i + " ");
                semZero.release(); // give back to zero
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void printEven() {
        for (int i = 2; i <= n; i += 2) {
            try {
                semEven.acquire();
                System.out.print(i + " ");
                semZero.release(); // give back to zero
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Multithreading1 printer = new Multithreading1(5);

        Thread t1 = new Thread(printer::printZero);
        Thread t2 = new Thread(printer::printOdd);
        Thread t3 = new Thread(printer::printEven);

        t1.start();
        t2.start();
        t3.start();
    }
}
