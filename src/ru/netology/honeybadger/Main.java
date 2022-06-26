package ru.netology.honeybadger;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        Runnable runnable = () -> System.out.println("Не понял зачем и куда этот Переопределенный run!");
        ThreadGroup threadGroup = new ThreadGroup("threadGroup");
        System.out.println("Создаю потоки...");
        MyThread myThread1 = new MyThread(threadGroup, runnable);
        MyThread myThread2 = new MyThread(threadGroup, runnable);
        MyThread myThread3 = new MyThread(threadGroup, runnable);
        MyThread myThread4 = new MyThread(threadGroup, runnable);

        myThread1.setName("1");
        myThread2.setName("2");
        myThread3.setName("3");
        myThread4.setName("4");

        myThread1.start();
        myThread2.start();
        myThread3.start();
        myThread4.start();

        try {
            myThread1.sleep(3000);
            myThread2.sleep(3000);
            myThread3.sleep(3000);
            myThread4.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Завершаю все потоки");
        threadGroup.interrupt();
        if (myThread1.isInterrupted() && myThread2.isInterrupted() && myThread3.isInterrupted() && myThread4.isInterrupted()){
            threadGroup.destroy();
        }
    }
}
