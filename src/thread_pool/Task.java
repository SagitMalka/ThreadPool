package thread_pool;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class Task<T> implements Callable<T>, Comparable<Task>{

    private T returnVal;
    private TaskType type = TaskType.OTHER;
    private Callable<?> action;
    private Task<T> task;


    private Task(TaskType type, Callable<?> callable) {
        this.type = type;
        this.action = callable;
    }


     public TaskType getType(){
        return type;
     }


//    public Callable<?> callable() throws Exception {
//        return null;
//    }

//    public int getPriority();

//    public thread_pool.TaskType getTaskType(thread_pool.Task task){
//        return task.getType();
//    }
//
//    private thread_pool.TaskType getType() {
//        return type;
//    }


    public static Task createTask(Callable<?> task, TaskType type){
        return new Task(type, task);
//        switch (type){
//            case COMPUTATIONAL -> {
//                return new Task(type, task);
//            }
//            case IO -> {
//                return new thread_pool.IOTask();
//            }
//            case OTHER -> {
//                return new OtherTask();
//            }
//
//            default -> throw new IllegalStateException("Unexpected value: " + type);
//        }
    }

    @Override
    public T call() throws Exception {

        return returnVal;
    }

    @Override
    public int compareTo(Task other) {
        if(this.type.getPriorityValue() < other.type.getPriorityValue()){
            return 1;
        }
        else if(this.type.getPriorityValue() > other.type.getPriorityValue()){
            return -1;
        }
        else {
            return 0;
        }
    }
}
