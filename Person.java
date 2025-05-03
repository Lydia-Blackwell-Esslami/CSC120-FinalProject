
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Person{
    public String name;
    public Place location;
    public int mood;
    public String job;
    public ArrayList<Car> cars;


    public Person(){
        this.location = new Place("null");
        this.mood = 0; 
        this.cars = new ArrayList<>();
    }

    public Person(String name, Place location){
        this.name = name;
        this.location = location;
        this.mood = 0;
        this.cars = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Car c = new Car(this.location);
            this.cars.add(c);
        }
    }

    public Person(String name, String job){
        this.name = name;
        this.location = new Place("null");
        this.mood = 0;
        this.job = job;
    }

    public void chat(){

    }

    public void sellCar(Car c, Player p, RepairShop r){
        r.cars.add(c);
        p.cars.add(c);
        this.cars.remove(c);
    }
    

    public void buyCar(Car c, Player p){
        p.cars.remove(c);
        p.bankBalance += c.price;
        this.cars.add(c);

    }

    public void recieveFlirting(Player p){
    


    }

    public void negotiate(Player p, Car c){
        if (c!= null){
            Scanner negotiation = new Scanner(System.in);
            int salePrice = c.price-(50*mood);
            if (salePrice < 1){
                salePrice = 1;
            }
            String offer = (this.name + " offers to sell you the car for $" + salePrice);
            System.out.println(offer);
            String response = negotiation.nextLine().toLowerCase();
            if(response.contains("yes")||response.contains("buy")){
                System.out.println("Nice doing business with you");
                p.bankBalance -= salePrice;
                this.sellCar(c, p, p.shop);
            } else {
                System.out.println("The negotiation failed and "+ this.name + " is angry that you wasted their time. If you want a good price, make sure the person you're dealing with is in a good mood.");
            }
            p.updateTime(1);
        }       

    }

    public void negotiateAsCustomer(Player p, Car c){
        if (c!= null){
            Scanner negotiation = new Scanner(System.in);
            c.calculateFinalPrice();
            c.price += 50*this.mood;
            String offer = (this.name + " offers to buy  the car from you for $" + c.price);
            System.out.println(offer);
            String response = negotiation.nextLine().toLowerCase();
            if(response.contains("yes")||response.contains("sell")){
                System.out.println("Nice doing business with you");
                this.buyCar(c, p);
            } else {
                System.out.println("The negotiation failed and "+ this.name + " is angry that you wasted their time. If you want a good price, make sure the person you're dealing with is in a good mood.");
            }
            p.updateTime(1);
            

        }
    }


    public boolean  noticeTheft(){
        Random random = new Random();
        int notice = random.nextInt(0, 2);
        if (notice == 0){
            return false;
        } else {
            this.mood -= 10;
            System.out.println(this.name + "caught you stealing and is very upset...");
            return true;
        }
    }


    public void talk(Player p){
        String[] randomResponses = {"Now you don't see weather like this very often.", 
        "You know, the other day it rained so hard, it wasn't even raining cats and dogs, it was raining lions and wolves!",
        "Yeah, I know what you mean.", "Hey, cheer up pal.", "You check your email recently? I hear you can find out about market trends that way.",
        "Did you see what that guy said on TV last night? Unbelievable."};
        Random responseChooser = new Random();
        Scanner conversation = new Scanner(System.in);
        System.out.println("Hey what's up?");
        if (this.mood > 3){
            System.out.println("You should ask me about my cars sometime after this conversation.");
        }
        for (int idx = 0; idx < (randomResponses.length/2); idx++) {
            conversation.nextLine();
            System.out.println(randomResponses[responseChooser.nextInt(randomResponses.length)]);
        }
        this.mood += 1;
        System.out.println("You wanna grab a drink or something?");
        if (!conversation.nextLine().toLowerCase().contains("n")){
            p.statusEffects.replace("Intoxicated", true);
            System.out.println("You got drunk with " + this.name + ". They really like you now");
            this.mood += 5;
            p.updateTime(1);
        }
        System.out.println("Hey I'll see you around. Come back if you ever wanna buy a car or something.");
        p.updateTime(1);
    }

    public void talkAsCustomer(Player p){
        String[] randomResponses = {"Now you don't see weather like this very often.", 
        "You know, the other day it rained so hard, it wasn't even raining cats and dogs, it was raining lions and wolves!",
        "Yeah, I know what you mean.", "Hey, cheer up pal.", "You check your email recently? I hear you can find out about market trends that way.",
        "Did you see what that guy said on TV last night? Unbelievable."};
        Random responseChooser = new Random();
        Scanner conversation = new Scanner(System.in);
        System.out.println("Hey what's up?");
        for (int idx = 0; idx < (randomResponses.length/2); idx++) {
            conversation.nextLine();
            System.out.println(randomResponses[responseChooser.nextInt(randomResponses.length)]);
        }
        this.mood += 1;
        System.out.println("After talking to you, the customer's mood improved! You should try to sell them a car.");
        p.updateTime(1);
    }

    public Car showCars(){
        Menu pickCar = new Menu(this.cars.size());
        for (int idx = 0; idx < this.cars.size(); idx++) {
            pickCar.options.put(idx+1, this.cars.get(idx).toString());
            System.out.println(this.cars.get(idx).toDetailedString());
        }
        int choice = pickCar.runMenu();
        if (choice >0){
            return this.cars.get(choice-1);
        } else {
            System.out.println("Didn't pick one");
            return null;
        }
        
    }




    public String toString(){
        return this.name;
    }

    

    


}