import java.util.ArrayList;
import java.util.List;
public class WorkersPool{
    private int countOfWorkers;
    private List<Worker> workers;
    private Monitor monitor;
    private WorkersPool(Monitor monitor, int countOfWorkers){
        this.monitor = monitor;
        monitor.intsRes = new int[countOfWorkers];
        this.countOfWorkers = countOfWorkers;
    }

    public static WorkersPool getWorkersPool(Monitor monitor, int countOfWorkers){
        return new WorkersPool(monitor, countOfWorkers);
    }

    public void start(){
        createWorkers();
        workers.forEach(w -> w.getThread().start());
    }

    private void createWorkers(){
        workers = new ArrayList<>();
        for (int i = 0; i < countOfWorkers; i++)
            workers.add(new Worker(monitor));
    }
}