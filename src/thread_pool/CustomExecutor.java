package thread_pool;


import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.concurrent.*;

public class CustomExecutor {

    ThreadFactory threadFactory;
    private PriorityQueue<Task> taskQueue;
    private ArrayList<Thread> threads;
    private int availableProcessors = Runtime.getRuntime().availableProcessors();

    ExecutorService pool = Executors.newFixedThreadPool(availableProcessors/2, threadFactory );


    public CustomExecutor() {
        this.taskQueue = new PriorityQueue<>();
        this.threads = new ArrayList<>();

        for (int i = 0; i < availableProcessors / 2; i++) {
            Thread thread = new Thread(() -> runTasks());
            threads.add(thread);
            thread.start();
        }
    }



    /**
     * TODO check what the return type should be
     */
    public FutureTask submit(Callable operation, TaskType type) {
        /** insert task to queue */
        Task task;
        if (operation instanceof Task) {
            task = (Task) operation;
        } else {
            task = Task.createTask(operation, TaskType.OTHER);
        }
        taskQueue.add(task);


        FutureTask futureTask = new FutureTask<>(task);
        task.setFutureTask(futureTask);

//        if (threads.isEmpty()) {
//            // TODO create more threads until max
//            // TODO timeout
//        } else {
//            Thread thread = threads.remove(0);
////            futureTask = new FutureTask();
//            thread.start();
//            thread.run();
//
//        }
        return futureTask;
    }

    private void runTasks(){
        while(true){
            Task t = this.taskQueue.poll();
            t.getFutureTask().run();

        }
    }
    public String getCurrentMax() {
        return "hi";
    }

    public void gracefullyTerminate() {
    }



    class ExecutorThread extends Thread {
        @Override
        public void run() {
            Task poll = CustomExecutor.this.taskQueue.poll();
            try {
                assert poll != null;
                poll.call();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }





}
