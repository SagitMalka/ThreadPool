package thread_pool;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class Task<T> implements Callable<T>, Comparable<Task>{

    private T returnVal;
    private TaskType type;
    private Callable<?> action;
    private Task<T> task;
    private FutureTask<T> futureTask;

    public FutureTask<T> getFutureTask() {
        return futureTask;
    }

    public void setFutureTask(FutureTask<T> futureTask) {
        this.futureTask = futureTask;
    }

    private Task(Callable<?> callable, TaskType type) {
        this.type = type;
        this.action = callable;
    }


     public TaskType getType(){
        return this.type;
     }




    public static Task createTask(Callable<?> task, TaskType type){
        return new Task(task, type);
    }

    public Task createTask(){
        return createTask(this.action, TaskType.OTHER);
    }


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
