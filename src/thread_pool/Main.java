//package thread_pool;
//
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//
//import static java.lang.Thread.sleep;
//
//public class Main {
//
//    private static void StringAllocCheck() {
//
//        String a = "hello";
//        String b = "hello";
//        String c = "hell";
//        System.out.println("a: " + a.hashCode());
//        System.out.println("b: " + b.hashCode());
//        System.out.println("c: " + c.hashCode());
//
//        c += "o";
//        a += "b";
//
//        System.out.println("a: " + a.hashCode());
//        System.out.println("b: " + b.hashCode());
//        System.out.println("c: " + c.hashCode());
//
//        System.out.println("");
////        if(a == c){
////            System.out.println("equals");
////        }else
////            System.out.println("not equals");
//    }
//
//    public static void main(String[] args) {
//        Task taskIO1 = Task.createTask(() -> {
//            System.out.println("io1");
//            return 0;
//        }, TaskType.IO);
//        Task taskIO2 = Task.createTask(() -> {
//            System.out.println("io2");
//            return 0;
//        }, TaskType.IO);
//        Task taskComp = Task.createTask(() -> {
//            System.out.println("comp");
//            return 0;
//        }, TaskType.COMPUTATIONAL);
//        Task taskOther = Task.createTask(() -> {
//            System.out.println("io1");
//            return 0;
//        }, TaskType.OTHER);
//
//        int resIO = taskIO1.compareTo(taskIO2);
//        System.out.println(resIO);
//
//        resIO = taskIO2.compareTo(taskComp);
//        System.out.println(resIO);
//
//        resIO = taskComp.compareTo(taskOther);
//        System.out.println(resIO);
//
//
//
//
//    }
//}
package thread_pool;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

import static java.lang.Thread.sleep;

public class Main {
    private static Random rand = new Random();
    private static TaskType getRandomType(int i) {
        return TaskType.values()[i];
    }

    private static Callable<String> getRandomTask(int i, int ml) {
        return () -> {
            System.out.println("Task" + i + " started");
            Thread.sleep(ml + 1000);
            System.out.println("Task" + i + " finished");
            return "Task" + i + " result";
        };
    }

    private static List<Task<String>> createTasks(int amount) {

        List<Task<String>> tasks = new ArrayList<>();

        for (int i = 0; i < amount; i++) {
            int typeIndex = rand.nextInt(3);
            int sleepTime = rand.nextInt(2000);

            tasks.add(Task.createTask(
                    getRandomTask(i, sleepTime),
                    getRandomType(typeIndex)
            ));
        }
        return tasks;
    }
    private static List<FutureTask<String>> submitTasks(List<Task<String>> tasks, CustomExecutor executor) throws InterruptedException {
        List<FutureTask<String>> futures = new ArrayList<>();
        for(Task<String> task: tasks){
            futures.add(executor.submit(task));
//            sleep(rand.nextInt(1000));
        }
        return futures;
    }
    public static void main(String[] args) {
        CustomExecutor executor = new CustomExecutor();
        List<Task<String>> tasks = createTasks(300);

        try {
            List<FutureTask<String>> futureTasks = submitTasks(tasks, executor);
            for(FutureTask<String> futureTask: futureTasks){
                System.out.println("return val: " + futureTask.get());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
