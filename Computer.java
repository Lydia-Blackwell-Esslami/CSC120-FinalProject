
import java.util.ArrayList;


public class Computer extends Menu{
    
    
    public Computer(int options){
        super(options);
        this.options.put(1, "Hire employees");
        this.options.put(2, "Fire employees");
        this.options.put(3, "View employees");
        this.options.put(4, "Schedule garage upgrades");
        this.options.put(5, "Pay bills");
        this.options.put(6, "Check email");
        this.options.put(7, "Turn off");
    }


    public void parseMenu(){
        int choice = this.runMenu();
        if (choice == 1){
            

        }

    }

    public void hire(Player p){
        Menu potentialEmployees = new Menu(3);
        ArrayList<Worker> workers = new ArrayList<>();
            for (int i = 1; i < 4; i++) {
                String name = this.generateWorkerName();
                Worker w = new Worker(name);
                workers.add(w);
                potentialEmployees.options.put(i, name);  
            }
            int widx = (potentialEmployees.runMenu()-1);
            p.hire(workers.get(widx));
            
        
    }

    public String generateWorkerName(){
        return "";
    }

    public void fire(Player p){
        

    }

    public void viewWorkers(Player p){

    }

    public void upgradeGarage(RepairShop r){

    }

    public void payBills(Player p){

    }

    public void checkEmail(){

    }

    

    
}
