
import java.util.Hashtable;
import java.util.Random;

public class Worker extends Person{

    public Hashtable<String, Integer> skills;
    public int wage;

    

    public Worker(String name){
        super(name, "Mechanic");
        this.location = "Auto shop";
        Random skillAssigner = new Random();
        this.wage = skillAssigner.nextInt(64, 200);
        skills.put("Tires", skillAssigner.nextInt(0, 6));
        skills.put("Brakes", skillAssigner.nextInt(0, 6));
        skills.put("Suspension", skillAssigner.nextInt(0, 6));
        skills.put("Oil", skillAssigner.nextInt(0, 6));
        skills.put("Transmission", skillAssigner.nextInt(0, 6));
        skills.put("Engine", skillAssigner.nextInt(0, 6));


    }

    public void learn(String skill){

    }

    public void fix(Car c, String part){

    }
}
