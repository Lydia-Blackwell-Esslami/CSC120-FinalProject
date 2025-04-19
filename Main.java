
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public Scanner getInput = new Scanner(System.in);
    public ArrayList<Item> items = new ArrayList<>();
    public ArrayList<String> itemNames = new ArrayList<>();
    public ArrayList<String> locations = new ArrayList<>();


    public Item parseItem(String input){
        for (int idx = 0; idx < itemNames.size(); idx++) {
            String name = itemNames.get(idx);
            if (input.contains(name)){
                Item item = new Item(name);
                return item;
            }
        }
        Item null_item = new Item("null");
        return null_item;
    }

    public String parseLocation(String input){
        for (int i = 0; i < locations.size(); i++) {
            if (input.contains(locations.get(i))){
                return locations.get(i);
            }
        }
        return "null";

    }

    public void parse(Player p){
        String input = getInput.nextLine();
        input = input.toLowerCase();
        if (input.contains("look")) {
            p.look();
        } else if (input.contains("sleep")){
            p.sleep();
        } else if (input.contains(" up ")){
            p.getUp();
        } else if (input.contains("take") || input.contains("pick") ){
            Item item = parseItem(input);
            if (!(item.name.contentEquals("null"))){
                System.out.println("You couldn't pick it up.");
            } else {
                p.take(item);
            }
        } else if (input.contains("help")){
            p.help();
        } else if (input.contains("walk")|| input.contains("go")|| 
        (input.contains("get")&& !input.contains("dressed"))){
            String location = parseLocation(input);
            if (!(location.contentEquals("null"))){
                p.go(location);
            } else {
                System.out.println("Can't get there");
            }
        } else if (true) {
            
        }
        
    }
    
}
