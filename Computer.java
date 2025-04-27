
import java.util.ArrayList;
import java.util.Random;




public class Computer extends Menu{
    public Player owner;
    
    
    public Computer(Player p, int options){
        super(options);
        this.options.put(1, "Hire employees");
        this.options.put(2, "Fire employees");
        this.options.put(3, "View employees");
        this.options.put(4, "Schedule garage upgrades");
        this.options.put(5, "Pay bills");
        this.options.put(6, "Check email");
        this.owner = p;
    }


    public void parseMenu(){
        while (true){
            int choice = this.runMenu();
            if (choice == 0){
                System.out.println("Power off...");
                break;
            } else {
                switch (choice) {
                    case 1 -> hire();
                    case 2 -> fire();
                    case 3 -> viewWorkers();
                    case 4 -> upgradeGarage();
                    case 5 -> payBills();
                    case 6 -> checkEmail();
                    default -> {
                    }
                }
            }
            
        }
        

    }

    public void hire(){
        Menu potentialEmployees = new Menu(3);
        ArrayList<Worker> workers = new ArrayList<>();
            for (int i = 1; i < 4; i++) {
                String name = this.generateWorkerName();
                Worker w = new Worker(name, this.owner);
                workers.add(w);
                potentialEmployees.options.put(i, name);  
            }
            int widx = (potentialEmployees.runMenu()-1);
            this.owner.hire(workers.get(widx));
            
        
    }

    public String generateWorkerName(){
        String[] names = {"Marco", "Ross", "Billy", "Chet", "Roberto", "Emily", "Sam", "Lyle", "Steven", "Beth", "Debra", "Arnold", "Wanda", 
        "John","Amy", "Nelson", "Vivian", "Beatrice", "Harvey", "Mason", "Matt", "Mary", "Lucy", "Simon", "Ruthie", "Eric", "Annie"};
        Random randName = new Random();
        return names[randName.nextInt(0, 26)];
    }

    public void fire(){
        System.out.println("Fire who? (enter the number next to their name, or 0 to quit)");
        Menu workers = new Menu(this.owner.employees.size());
        for (int idx = 0; idx < this.owner.employees.size(); idx++) {
            Worker w = this.owner.employees.get(idx);
            workers.options.put((idx+1), w.name);
        }
        int widx = (workers.runMenu()-1);
        this.owner.fire(this.owner.employees.get(widx));

        

    }

    public void viewWorkers(){
        for (int idx = 0; idx < this.owner.employees.size(); idx++) {
            System.out.println(this.owner.employees.get(idx));
        }

    }

    public void upgradeGarage(){
        Menu garage = new Menu(3);
        System.out.println(this.owner.shop);
        System.out.println("Upgrade what?");
        garage.options.put(1, "Building size");
        garage.options.put(2, "Work bays");
        garage.options.put(3, "Parking spaces");
        int upgradeThis = garage.runMenu();
        boolean success = this.owner.shop.upgrade(this.owner, upgradeThis);
        if (success){
            System.out.println("Upgrade schedueled");
        } else {
            System.out.println("Upgrade canceled");
        }
    }

    public void payBills(){
        ArrayList<Integer> bills = this.owner.checkBillsDue(false);
        System.out.println("What do you want to pay?");
        Menu payBills = new Menu(4);
        payBills.options.put(1, "House");
        payBills.options.put(2, "Shop");
        payBills.options.put(3, "Wages");
        payBills.options.put(4, "Taxes");
        int bill = payBills.runMenu();
        if (bill <=4 && bill != 0){
            this.owner.bankBalance -= bills.get(bill-1);
            this.owner.bills.replace((payBills.options.get(bill)), true);
        } else {
            System.out.println("Canceling payment...");
        }
        
    }

    public void checkEmail(){

    }

    

    
}
