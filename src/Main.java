/**
 * @author Anton Shagvaleev
 *
 * This app emulates threads pool working
 *
 */

public class Main{
    public static void main(String[] args){
        Manager manager = new Manager(15);
        manager.getThread().start();
        try{
            manager.getThread().join();
        }catch (InterruptedException ex){
            ex.printStackTrace();
        }
    }
}