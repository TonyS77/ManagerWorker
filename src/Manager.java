import java.util.Arrays;
public class Manager implements Runnable{
    private final Thread thread;
    private final Monitor monitor;
    private final int countOfWorkers;
    private final WorkersPool pool;
    public Manager(int countOfWoorkers){
        thread = new Thread(this);
        monitor = new Monitor();
        this.countOfWorkers = countOfWoorkers;
        pool = WorkersPool.getWorkersPool(monitor, countOfWorkers);
    }

    @Override
    public void run(){
        pool.start();
        for (int i = 0; i < 10; i++){
            synchronized(monitor){
                while(Arrays.stream(monitor.intsRes).sum() < countOfWorkers){
                    try{
                        monitor.wait();
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
                System.out.println();
                Arrays.fill(monitor.intsRes, 0);
                monitor.notifyAll();
            }
        }
    }
    public Thread getThread(){
        return thread;
    }
}