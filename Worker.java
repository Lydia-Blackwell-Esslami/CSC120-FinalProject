
import java.util.Hashtable;
import java.util.Random;

public class Worker extends Person{

    public Hashtable<String, Integer> skills;
    public int wage;
    public Player boss;

    

    public Worker(String name, Player boss){
        super(name, "Mechanic");
        this.location = new Place("garage");
        this.skills = new Hashtable<>();
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
        if (this.skills.get(part) >= 5){
            c.upgrade(part);
            System.out.println("Repaired the " + part);
            boss.updateTime(1);
        } else {
            System.out.println(this.name + " needs more training in order to repair " + part + 
                               ". \n Current skill level: " + this.skills.get(part) + "Required level: 5");
        }

    }
    public String toString(){
        System.out.println("Worker: " +this.name+ "\n Skills: ");
        System.out.println(this.skills);
        return "";
    }
}
