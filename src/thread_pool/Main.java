package thread_pool;

import static java.lang.Thread.sleep;

public class Main {

    private static void StringAllocCheck() {

        String a = "hello";
        String b = "hello";
        String c = "hell";
        System.out.println("a: " + a.hashCode());
        System.out.println("b: " + b.hashCode());
        System.out.println("c: " + c.hashCode());

        c += "o";
        a += "b";

        System.out.println("a: " + a.hashCode());
        System.out.println("b: " + b.hashCode());
        System.out.println("c: " + c.hashCode());

        System.out.println("");
//        if(a == c){
//            System.out.println("equals");
//        }else
//            System.out.println("not equals");
    }

    public static void main(String[] args) {
        Task taskIO1 = Task.createTask(() -> {
            System.out.println("io1");
            return 0;
        }, TaskType.IO);
        Task taskIO2 = Task.createTask(() -> {
            System.out.println("io2");
            return 0;
        }, TaskType.IO);
        Task taskComp = Task.createTask(() -> {
            System.out.println("comp");
            return 0;
        }, TaskType.COMPUTATIONAL);
        Task taskOther = Task.createTask(() -> {
            System.out.println("io1");
            return 0;
        }, TaskType.OTHER);

        int resIO = taskIO1.compareTo(taskIO2);
        System.out.println(resIO);

        resIO = taskIO2.compareTo(taskComp);
        System.out.println(resIO);

        resIO = taskComp.compareTo(taskOther);
        System.out.println(resIO);
    }
}
