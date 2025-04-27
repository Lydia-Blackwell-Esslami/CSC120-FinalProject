
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
    }

    public Person(String name, Place location){
        this.name = name;
        this.location = location;
        this.mood = 0;
        this.cars = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            this.cars.add(new Car(location));
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
        p.bankBalance -= c.price;
        this.cars.remove(c);
    }
    

    public void buyCar(Car c, Player p, RepairShop r){
        r.cars.remove(c);
        p.bankBalance += c.price;
        this.cars.add(c);

    }

    public void recieveFlirting(Player p){
    


    }

    public void negotiate(Player p, Car c){
        Scanner negotiation = new Scanner(System.in);
        int salePrice = c.price-(50*mood);
        String offer = (this.name + " offers to sell you the car for " + salePrice);
        System.out.println(offer);
        String response = negotiation.nextLine().toLowerCase();
        if(!response.contains("yes")&!response.contains("buy")){
            System.out.println("Nice doing business with you");
            c.price = salePrice;
            this.buyCar(c, p, p.shop);
        } else {
            System.out.println("The negotiation failed and "+ this.name + " is angry that you wasted their time. If you want a good price, make sure the person you're dealing with is in a good mood.");
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
        for (int idx = 0; idx < (randomResponses.length/2); idx++) {
            conversation.nextLine();
            System.out.println(randomResponses[responseChooser.nextInt(randomResponses.length)]);
        }
        this.mood += 1;
        System.out.println("You wanna grab a drink or something?");
        if (!conversation.nextLine().toLowerCase().contains("no")){
            p.statusEffects.replace("Intoxicated", true);
            System.out.println("You got drunk with " + this.name + " . They really like you now");
            this.mood += 5;
        }
        System.out.println("Hey I'll see you around. Come back if you ever wanna buy a car or something.");
    }




    public String toString(){
        return this.name;
    }

    

    


}