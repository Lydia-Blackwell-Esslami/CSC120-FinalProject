
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;

public class Player extends Person {
    public Hashtable<String, Boolean> statusEffects;
    public int bankBalance;
    public int currentTime;
    public ArrayList<Worker> employees;
    public Computer computer;
    public Phone phone;
    public Hashtable<Item, Boolean> inventory;
    public int day;
    public Hashtable<String, Boolean> bills;
    public RepairShop shop;

    public Player(){
        super();
        Scanner input = new Scanner(System.in);
        System.out.println("What is your name?");
        this.name = input.nextLine();
        this.statusEffects = new Hashtable<>();
        this.inventory = new Hashtable<>();
        this.employees = new ArrayList<>();
        this.statusEffects.put("Tired", false);
        this.statusEffects.put("Unwashed", true);
        this.statusEffects.put("Hungry", true);
        this.statusEffects.put("Intoxicated", false);
        this.statusEffects.put("Disheveled", true);
        this.bills = new Hashtable<>();
        this.bills.put("House", false);
        this.bills.put("Shop", false);
        this.bills.put("Employees", false);
        this.bills.put("Taxes", false);
        this.bankBalance = 0;
        this.currentTime = 8;
        this.employees = new ArrayList<>();
        this.computer = new Computer(this, 4);
        this.phone = new Phone(3);
        this.day = 1;
        this.location = new Place("home, bed");
        this.shop = new RepairShop();
        
    }

    public void updateTime(int amt){
        int initalTime = this.currentTime;
        this.currentTime += amt;
        this.currentTime %= 24;
        if (this.currentTime <= initalTime){
            this.day+=1;
        }
        showUI();
        if (this.currentTime > 8 && this.currentTime <14){
            System.out.println("Now would be a good time to go to work");
        }
        if (this.currentTime > 18 && this.statusEffects.get("Hungry")){
            System.out.println("You should have some dinner. You can cook at home or go to the diner.");
        }
        if (this.shop.cars.isEmpty() && this.location.name.contains("work")){
            System.out.println("You can drive to suburbs or trailer park to buy cars for the shop");
        }
        
    }

    public void sleep(){
        if (this.location.name.contains("bed") || this.location.name.contains("office")){
            this.updateTime(8);
            System.out.println("You sleep restfully and wake up refreshed");
            this.statusEffects.replace("tired", false);
        } else {
            System.out.println("You tried to fall asleep here, to no avail");
        }
    }

    public void getUp(){
        if (this.location.name.equals("home, bed")){
            this.location = new Place("home, bedroom");
            System.out.println("Got out of bed.");
            look();
        } else {
            System.out.println("You have already gotten out of bed! Good for you.");
        }
    }

    public void look(){
        if (this.location.name.equals("home, bed")){
            System.out.println("You are in bed.");
            if (this.currentTime > 19 || this.currentTime <5){
                System.out.println("It's nighttime. Crickets are chirping.");
            } else {
                System.out.println("It's day! Birds are singing.");
            }
        } else if (this.location.name.contains("bedroom")){
            System.out.println("You are in your bedroom. There is a door to the hallway. The clock on the nightstand displays " + this.currentTime);
        } else if (this.location.name.contains("hallway")){
            System.out.println("You are in the hallway. There are doors to the bathroom and bedroom, or you can go over to the living room or kitchen.");
        } else if (this.location.name.contains("bathroom")){
            System.out.println("You are in the bathroom. There is a shower, toilet, sink, and toothbrush.");
        } else if (this.location.name.contains("kitchen")){
            System.out.println("You are in the kitchen. There is a large refridgerator in front of you. You can go to the hallway, to the living room, or outside. Your shoes and keys are by the door. You can see your car in the driveway.");
        } else if (this.location.name.contains("living room")) {
            System.out.println("You are in the living room. There is a couch and televsion. Your shoes and keys are by the door. You can go out, or to the kitchen, or to the hallway. You can see your car in the driveway");
        } else if (this.location.name.contains("lost")){
            System.out.println("You are lost. You have no idea where you are.");
        } else if (this.location.name.contains("driveway")){
            System.out.println("You are in your driveway. You can go back inside, get in your car, or try to walk somewhere");
        } else if (this.location.name.contains("bus stop")){
            System.out.println("You are at the bus stop. Wait for a bus?");
        } else if (this.location.name.equals("bus")){
            System.out.println("You are riding a bus");
            System.out.println("...");
            System.out.println("The bus just stopped!");
        } else if (this.location.name.contains("auto parts store")){
            System.out.println("You are in the auto parts store. The cashier is looking at you with a bored expression");
        } else if (this.location.name.contains("car")){
            System.out.println("You are in your car! Drive somewhere?");
        } else if (this.location.name.contains("diner")){
            System.out.println("You are in a small diner, with several patrons sitting at the bar. The bartender nods to you. A waitress is rushing around frantically.");
        } else if (this.location.name.contains("trailer park")){
            System.out.println("You are in a run down trailer park. Many old cars sit rusting in the grass. Somewhere, you can smell someone smoking weed.");
        } else if (this.location.name.contains("suburbs")){
            System.out.println("You are surrounded by identical houses. Most of them have two cars of various condition in the driveway. Children are playing in the road.");
        } else if (this.location.name.contains("office")){
            System.out.println("You are in your office. Your computer is on your desk.");
        } else if (this.location.name.contains("parking lot")){
            System.out.println("You are in the parking lot, surrounded by unsold cars");
        } else if (this.location.name.contains("repair shop")){
            System.out.println("You are in the back part of the shop, with tools, machinery, and broken down cars.");
            if (!this.employees.isEmpty()){
                System.out.println("Your employees look at you curiously.");
            }
        } else if (this.location.name.contains("computer")){
            computer.showOptions();
        } else if (this.location.name.contains("phone")){
            phone.showOptions();
        } else if (this.location.name.contains("grocery store")){
            System.out.println("You are at the grocery store. Many items sit enticingly on the shelves. Take something?");
        }
        if (!this.location.objects.isEmpty()&& !this.location.name.contains("diner")&& !this.location.name.contains("shop")){
            System.out.println("You can see someone has left ");
            System.out.println(this.location.objects);
            System.out.println("laying around");
        }
        
        if (this.location.NPC != null) {
            System.out.println(this.location.NPC + " is waving at you");
        }

    }


    public void take(Item item){
        this.inventory.put(item, false);
        System.out.println("Picked up the " + item.name);
    }

    public void drop(Item item){
        this.inventory.remove(item);
        System.out.println("Dropped the " + item.name);
    }

    public void stealFrom(Person person){
        if (this.inventory.contains(false)){
            if (person.noticeTheft()){
                this.inventory.forEach(
                    (key, value) -> {
                    if (value == false){
                        inventory.remove(key);
                        System.out.println("Put down the stolen " + key.name);
                    }
                  }
                  );
            } else {
                this.inventory.forEach(
                    (key, value) -> {
                        if (value == false){
                            inventory.replace(key, true);
                            System.out.println("Stole the " + key.name);
                        }
                      }
                );
            }
        }
    }

    public void buy(Item item){
        this.bankBalance -= item.price;
        if(this.inventory.containsKey(item)){
            this.inventory.replace(item, true);
        } else {
            this.inventory.put(item, true);
        }
        
    }

    public void eat(Item item){
        if (item.type.contentEquals("eatable")){
            System.out.println("Ate the " + item.name);
            this.statusEffects.replace("Hungry", false);
            inventory.remove(item);
        } else {
            System.out.println("You want to eat a " + item.name + "?? What are you, a goat?");
        }
    }

    public void go(Place destination){
        if((this.location.name.contains("home")&&destination.name.contains("home"))||
        (this.location.name.contains("outside")&& destination.name.contains("outside")||
        (this.location.name.contains("work")&& destination.name.contains("work"))|| 
        destination.name.contains("car")|| this.location.name.contains("car"))){
            this.location = destination;
            this.look();
        } else {
            System.out.println("Too far to walk there.");
        }
    }

    public void drive(Place destination){
        if (this.location.name.contains("car")){
            this.location = destination;
            this.updateTime(1);
            this.look();
        } else {
            System.out.println("You can't drive when you aren't in a car!");
        }
    }

    public void showUI(){
        System.out.println("=====DAY" + day + "=====");
        System.out.println("The time is: " + currentTime);
        System.out.println("You have $" + bankBalance);
        System.out.println("What would you like to do?");
    }

    public void showInventory(){
        System.out.println("You check your bag and find:");
        inventory.forEach((key,value)->{
            System.out.println(key.name);
        }
        );
        if( inventory.isEmpty()){
            System.out.println(".....A moth flies out. You should pick some things up.");
        }
    }

    public void showStatus(){
        System.out.println("You are: ");
        this.statusEffects.forEach((key, value)->{
            if (value){
                System.out.println(key);
            }
        });

    }

    public void drink(Item item){
        if (inventory.containsKey(item)){
            if (item.type.contentEquals("drinkable")){
                this.inventory.remove(item);
                System.out.println("Drank the " + item.name);
                if (item.name.contains("alcohol")){
                    this.statusEffects.replace("Intoxicated", true);
                    System.out.println("You are now drunk.");
                }
            } else if (item.type.contentEquals("toxics")) {
                System.out.println("You have poisoned yourself! \n You spend the rest of the day in the hospital... \n Hospital bill: $3,000");
                this.bankBalance -= 3000;
                this.sleep();
            } else {
                System.out.println("Drinking this sounds like a bad idea...");
            }
        } else {
            System.out.println("You don't have that.");
        }
        
    }

    public void shower(){
        if (this.location.name.contains("bathroom")){
            System.out.println("You take a shower and brush your teeth.");
            this.statusEffects.replace("Unwashed", false);
            this.updateTime(1);
        } else {
            System.out.println("You can't take a shower here");
        }
    }

    public void hire(Worker w){
        this.employees.add(w);
    }

    public void fire(Worker w){
        this.employees.remove(w);
    }

    public ArrayList<Integer> checkBillsDue(boolean silent){
        Integer houseBills = 500;
        Integer shopBills = 700;
        Integer workerSalaries = 0;
        ArrayList<Integer> billsDue = new ArrayList<>(4);
        for (int idx = 0; idx < employees.size(); idx++) {
            Worker w = employees.get(idx);
            workerSalaries += (10*w.wage);
        }
        Integer taxes = (int) (1000+ (0.15*bankBalance));
        billsDue.add(houseBills);
        billsDue.add(shopBills);
        billsDue.add(workerSalaries);
        billsDue.add(taxes);
        if (((day-30)%30)*-1 == 1){

        }
        if ((((day-30)+5)%30)*-1 == 1) {
            
        }
        if (true) {
            
        }
        if (true) {
            
        }
        if (!silent){
            System.out.println("You owe: \n $" + houseBills + " on your house, due in " + ((day-30)%30)*-1 + " days. Paid in advance: " + bills.get("House") + "\n"+
                               "$" + shopBills + " on the shop, due in " + (((day-30)+5)%30)*-1 + " days. Paid in advance: " + bills.get("Shop") + "\n" + 
                               "$" + workerSalaries + " to your employees, due in " + ((day-14)%14)*-1 + " days. Paid in advance: " + bills.get("Employees") + "\n" +
                               "$" + taxes + " in taxes, due in " + ((day-300)%365)*-1 + "days. Paid in advance: " + bills.get("Taxes"));
        }
        if ((((day-30)%30)*-1 == 0)&&(!bills.get("House"))){
            if ((bankBalance - houseBills)>= 0){
                bankBalance -= houseBills;
                System.out.println("Your house payment of $" + houseBills + " was automatically deducted from your account.");
            } else {
                System.out.println("You didn't have enough money, and the bank took your house");
                lose();
            }
        }
        if (((((day-30)+5)%30)*-1 == 0)&&(!bills.get("Shop"))){
            if ((bankBalance - shopBills)>= 0){
                bankBalance -= shopBills;
                System.out.println("Your shop payment of $" + shopBills + " was automatically deducted from your account.");
            } else {
                System.out.println("You didn't have enough money, and your shop had to shut down.");
                lose();
            }
        }
        if ((((day-14)%14)*-1 == 0)&&(!bills.get("Employees"))&&(!employees.isEmpty())){
            if ((bankBalance - workerSalaries)>= 0){
                bankBalance -= workerSalaries;
                System.out.println("Your payment of $" + workerSalaries + " to your employees was automatically deducted from your account.");
            } else {
                System.out.println("You didn't have enough money to pay your workers, and they all quit");
                employees.clear();
            }
        }
        if ((((day-300)%365)*-1 == 0)&&(!bills.get("Taxes"))){
            if ((bankBalance - taxes)>= 0){
                bankBalance -= taxes;
                System.out.println("Your tax payment of $" + taxes + " was automatically deducted from your account.");
            } else {
                System.out.println("You didn't have enough money to pay your taxes, and the IRS put you in jail.");
                lose();
            }
        } 
        return billsDue; 
    }



    public void lose(){
        throw new RuntimeException("You went broke! Start over?");
    }

    

    public void help(){
        System.out.println("""
                           ====The Basics==== 
                           Type commands into the terminal. You can go to nearby places by walking, but for further ones you will need to get in your car and drive.
                           Check your current condition using 'status' and your pockets using 'inventory'. Don't hesitate to look aroud you if you are lost.
                           Keep an eye on the time of day and your bank balance. It is a good idea to go to work every day, but take time to explore the city too.
                           ====Working====
                           You are the owner of a small used car dealership. You will need to explore the city to find used cars you can buy, repair them, and sell them.
                           1: Looking for cars
                           The trailer park is a good place to find inexpensive, broken down cars. There is a chance that you will also find rare and pricessless models buried in the weeds.
                           The suburbs have many cars for sale as well, which are more expensive but require less restoration.
                           2: Negotiating
                           Every car, no matter how broken down, has value to its owner. If you can get the owner into a good mood before starting the negotiation, you will get a better price.
                           Your physical appearance is of great importance when interacting with people, so be sure to attend to your personal hygine each morning before work.
                           3: Repair
                           You should repair every car to a perfect condition before selling it. Cars are repaired in the garage. 
                           The sale value of a car can be greatly increased by following consumer trends. You can use your phone to call your uncle for information about these.
                           4: Sale
                           Once you have repaired a car, drive it to the parking lot and return to your office to wait for customers. You will have to negotiate with the customers.
                           5: Employees
                           You can use your office computer to hire mechanics who can fix cars, saving you valuable time. Each mechanic has a unique skillset, however, every mechanic can be trained to do any task.
                           Mechanics are only able to do tasks they are proficient in. Once a mechanic is proficient in a skill, you can ask them to train other mechanics in that skill.
                           6: The Shop
                           You can use your office computer to purchase various upgrades for different parts of the shop. You should also check the garage supply closet regularly.
                           You can restock the garage at the auto parts store. Be warned that any chemicals you buy there are toxic.
                           ====Daily Life====
                           Other than working, you must eat and drink to sustain yourself. If you are hungry, it will take longer to complete most tasks.
                           You must go to the store periodically to buy food. You can also eat out at the diner. 
                           If you see someone out in the world, do not be afraid to start a conversation with them. Who knows, love may even bloom...""");
    }
    




    






}
