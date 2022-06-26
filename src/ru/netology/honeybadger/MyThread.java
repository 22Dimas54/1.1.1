package ru.netology.honeybadger;

public class MyThread extends Thread{
    public MyThread(ThreadGroup threadGroup, Runnable runnable) {
        super(threadGroup,runnable);
    }

    @Override
    public void run() {
        try {
            while(!isInterrupted()) {
                Thread.sleep(3000);
                System.out.printf("Я поток %s. Всем привет!\n",getName());
            }
        } catch (InterruptedException err) {

        } finally{
            System.out.printf("%s завершен\n", getName());
        }
    }
}
