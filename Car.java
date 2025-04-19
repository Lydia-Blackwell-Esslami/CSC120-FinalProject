
import java.util.Hashtable;
import java.util.Random;

public class Car {

    public Hashtable<String, Integer> parts;
    public String color;
    public String name;
    public int price;
    public int year;
    public int mileage;
    public String location;

    public Car(String startingLocation){
        Random partMaker = new Random();
        this.location = startingLocation;
        if (this.location.contentEquals("suburbs")){
            this.year = partMaker.nextInt(1998, 2023);
            parts.put("Tires", partMaker.nextInt(3, 6));
            parts.put("Brakes", partMaker.nextInt(3, 6));
            parts.put("Suspension", partMaker.nextInt(3, 6));
            parts.put("Oil", partMaker.nextInt(0, 2));
            parts.put("Transmission", partMaker.nextInt(3, 6));
            parts.put("Engine", partMaker.nextInt(3, 6));
            this.mileage =  partMaker.nextInt(20000, 70000);
            this.price = partMaker.nextInt(1000, 5000);
        } else {
            this.year = partMaker.nextInt(1945, 2005);
            parts.put("Tires", partMaker.nextInt(0,4));
            parts.put("Brakes", partMaker.nextInt(0,4));
            parts.put("Suspension", partMaker.nextInt(0,4));
            parts.put("Oil", partMaker.nextInt(0, 2));
            parts.put("Transmission", partMaker.nextInt(0,4));
            parts.put("Engine", partMaker.nextInt(0,4));
            this.mileage = partMaker.nextInt(100000, 500000);
            this.price = partMaker.nextInt(30, 500);
        }
        String[] carNames = new String[] {"Toyota Land Cruiser", "Toyota Crown", "Toyota Corolla", "Toyota Century", 
        "Nissan Skyline", "Nissan GTR", "Nissan Z", "Ford F-Series", "Ford Mustang", "Ford Bronco", "Ford Transit", 
        "Chevrolet Suburban", "Chevrolet Corvette", "Mitsubishi Delica", "Mitsubishi Minica", "Mitsubishi Galant", 
        "Mitsubishi Lancer", "Dodge Charger", "Volkswagon Caravelle", "Volkswagon Beetle", "Mercedes E-Class", "Mercedes S-Class",
        "Mercedes AMG SL", "Mazda Carol", "Mazda Bongo", "Mazda B-Series", "Mazda Familia", "Subaru Sambar Truck", "Subaru Sambar Van"};
        this.name = carNames[partMaker.nextInt(0, 30)];
        
        String[] colors = new String[] {"red", "blue", "green", "white", "black"};
        this.color = colors[partMaker.nextInt(0,6)];

    }

    public void upgrade(String part){
        if (location.contains("garage")){
        int value = this.parts.get(part);
        this.parts.replace(part, value+1);
        } else {
            System.out.println("Can't do that here.");
        }
    }

    public void overhaul(){
        if ((parts.get("engine")+
            parts.get("transmission")+
            parts.get("tires")+
            parts.get("brakes")+
            parts.get("oil")+
            parts.get("suspension"))>= 30) {
                mileage/=2;       
        }
    }

    public void paint(String color){
        if (this.location.contains("garage")){
            this.color = color;
        } else {
            System.out.println("Can't do that here.");
        }
    }

    public void calculateFinalPrice(){
        
        String s = "Milage, Year";
         p = parts.values();
        
        
    }







    
}
