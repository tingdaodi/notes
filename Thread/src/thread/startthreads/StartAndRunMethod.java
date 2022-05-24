package thread.startthreads;

public class StartAndRunMethod {

    public static void main(String[] args) {
        Runnable runnable = ()->{
            System.out.println(Thread.currentThread().getName());
        };
        runnable.run();

        Thread thread = new Thread(runnable);
        thread.start();

    }

}
