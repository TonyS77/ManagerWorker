public class Worker implements Runnable{
    private final Thread thread;
    private final Monitor monitor;
    Worker(Monitor monitor){
        thread = new Thread(this);
        this.monitor = monitor;
    }
    public Thread getThread(){
        return thread;
    }

    @Override
    public void run(){
        for (int i = 0; i < 10; i++){
            synchronized (monitor){
                boolean found = false;
                while(!found){
                    for(int j = 0; j < monitor.intsRes.length; j++){
                        if (monitor.intsRes[j] == 0){
                            monitor.intsRes[j] = 1;
                            found = true;
                            break;
                        }
                    }
                    if(!found){
                        try{
                            monitor.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                System.out.printf("%-10s", Thread.currentThread().getName());
                monitor.notifyAll();
            }
        }
    }
}