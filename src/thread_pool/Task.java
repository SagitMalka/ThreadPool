package thread_pool;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class Task<T> implements Callable<T>, Comparable<Task>{

    private T returnVal;
    private TaskType type;
    private Callable<T> action;
    private Task<T> task;
    private FutureTask<T> futureTask;

    public FutureTask<T> getFutureTask() {
        return futureTask;
    }

    public void setFutureTask(FutureTask<T> futureTask) {
        this.futureTask = futureTask;
    }

    private Task(Callable<T> callable, TaskType type) {
        this.type = type;
        this.action = callable;
    }


    public static <V> Task<V> createTask(Callable<V> task, TaskType type){
        return new Task<V>(task, type);
    }

//
//    public Task createTask(){
//        return createTask(this.action, TaskType.OTHER);
//    }


    @Override
    public T call() throws Exception {

        return returnVal;
    }

    @Override
    public int compareTo(Task other) {
        if(this.type.getPriorityValue() > other.type.getPriorityValue()){
            return 1;
        }
        else if(this.type.getPriorityValue() < other.type.getPriorityValue()){
            return -1;
        }
        else {
            return 0;
        }
    }
}
