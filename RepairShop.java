
import java.util.ArrayList;
import java.util.Hashtable;

public class RepairShop {
    public ArrayList<Car> cars;
    public int buildingSize;
    public int garageCapacity;
    public int parkingCapacity;
    private int upgrade;
    private boolean inProgress;
    private int completionDate;
    public Hashtable<Item, Integer> inventory;
    Item[] supplies = {new Item("bleach"),new Item("oil"),new Item("coolant"),new Item("brake fluid"),
                       new Item("transmission fluid"),new Item("brakeleen"),new Item("pb-blaster"),new Item("wiper fluid")};

    public RepairShop(){
        this.cars = new ArrayList<>();
        this.buildingSize = 1;
        this.garageCapacity = 3;
        this.parkingCapacity = 15;
        this.inventory = new Hashtable<>();
        this.inProgress = false;
        this.completionDate = -1;
        for (int idx = 0; idx < supplies.length; idx++) {
            this.inventory.put(supplies[idx], 15);
            
        }
        
    }

    public boolean  upgrade(Player p, int upgradeThis){
        if (this.inProgress){
            System.out.println("Upgrades are already in progress. They will be finished in " + this.completionDate + " days");
            return false;
        } else {
            Menu confirm = new Menu(2);
            confirm.options.put(1, "Yes");
            confirm.options.put(2, "No");
            if (upgradeThis == 1){
                System.out.println("Increase the size of the garage, allowing 5 new bays to be built. The cost of this upgrade is $6000 and the time it takes is 30 days \n Confirm upgrade?");
                int c = confirm.runMenu();
                if (c == 1){
                    p.bankBalance -= 6000;
                    passiveUpgradeCheck((p.day+30), upgradeThis, p);
                    return true;
                } else {
                    return false;
                }
            } else if (upgradeThis == 2){
                System.out.println("Install a new bay and lift in the garage, allowing 1 new car to be worked on at a time. The cost of this upgrade is $1000 and the time it will take is 10 days.\n Confirm upgrade?");
                int c = confirm.runMenu();
                if (c == 1){
                    p.bankBalance -= 1000;
                    passiveUpgradeCheck((p.day+10), upgradeThis, p);
                    return true;
                } else {
                    return false;
                }
            } else if (upgradeThis == 3){
                System.out.println("Clear weeds and pour asphalt to make room for 10 new parking spaces in the front lot, allowing you to store 10 more cars. The cost of this upgrade is $500 and the time it will take is 7 days. \n Confirm upgrade?");
                int c = confirm.runMenu();
                if (c == 1){
                    p.bankBalance -= 500;
                    passiveUpgradeCheck((p.day+7), upgradeThis, p);
                    return true;
                } else {
                    return false;
                }
            }
            return false;
        }
        
    }

    public void passiveUpgradeCheck(int completionDate, int upgrade, Player p){
        this.inProgress = true;
        this.completionDate = completionDate;
        this.upgrade = upgrade;
        if (p.day == this.completionDate){
            if (upgrade == 1){
                this.buildingSize += 1;
                this.inProgress = false;
            } else if (upgrade==2){
                this.garageCapacity += 5;
            } else if (upgrade == 3){
                this.parkingCapacity += 10;
            }
        }

    }

    public String toString(){
        return ("Garage has a max capacity of "+ 5*this.buildingSize + "with " + this.garageCapacity + 
        "bays currently installed. \n A maximum of" +  this.parkingCapacity +" cars can be stored here \n Upgrades in progress: " + this.inProgress);
    }
    
}
