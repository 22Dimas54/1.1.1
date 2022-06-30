package ru.netology.honeybadger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import static java.lang.Thread.currentThread;

public class Main {
    final private static int maxCount = 3;

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        Callable<String> myCallable1 = createCallableInteger("1");
        Callable<String> myCallable2 = createCallableInteger("2");
        Callable<String> myCallable3 = createCallableInteger("3");
        Callable<String> myCallable4 = createCallableInteger("4");
        List<Callable<String>> taskList = new ArrayList<>();
        taskList.add(myCallable1);
        taskList.add(myCallable2);
        taskList.add(myCallable3);
        taskList.add(myCallable4);
        final ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        List<Future<String>> resultList = null;
        try {
            resultList = executorService.invokeAll(taskList);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < resultList.size(); i++) {
            Future<String> future = resultList.get(i);
            try {
                System.out.println(future.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        try {
            String resultInvokeAll = executorService.invokeAny(taskList);
            System.out.println(resultInvokeAll);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
    }

    private static Callable<String> createCallableInteger(String name) {
        return () -> {
            int count = 0;
            currentThread().setName(name);
            System.out.printf("Я поток %s. Всем привет!\n", currentThread().getName());
            try {
                while (!currentThread().isInterrupted() && count < maxCount) {
                    Thread.sleep(1000);
                    System.out.printf("Я поток %s. Всем привет!\n", currentThread().getName());
                    count++;
                }
            } catch (InterruptedException err) {

            } finally {
                System.out.printf("%s завершен\n", currentThread().getName());
            }
            return "Поток " + currentThread().getName() + " вывел " + count + " сообщений";
        };
    }
}
