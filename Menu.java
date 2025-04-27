
import java.util.Hashtable;
import java.util.Scanner;

public class Menu {
    public Hashtable<Integer, String> options;

    public Menu(int options){
        this.options = new Hashtable<Integer, String>(options);
    }


    public int runMenu(){
        Scanner input = new Scanner(System.in);
        while (true) { 
            this.showOptions();
            Integer choice = input.nextInt();
            if (options.containsKey(choice)){
                return choice;
            } else if (choice == 0){
                System.out.println(input.nextLine());
                return 0;
            }  else {
                System.out.println("You need to choose one of the numbers on the list! (Or press 0 to quit)");
            }
        }

    }

    public void showOptions(){
        options.forEach((key,value)->{
            System.out.println("==="+ key +": " + value + "===");
        }
        );
        
    }
    
}
