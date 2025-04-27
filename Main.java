
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Scanner;

public class Main {

        
    public Scanner getInput = new Scanner(System.in);
    public ArrayList<Item> items = new ArrayList<>();
    public String[] itemNames =  {"bread", "sandwich", "pizza", "dinner", "breakfast", "lunch", "egg", "burger", "milk", "water", 
                                 "soda", "juice", "coffee", "wine(alcohol)", "beer(alcohol)", "bleach", "oil", "coolant", "brake fluid", 
                                 "transmission fluid", "brakeleen", "pb-blaster", "wiper fluid"};
    public String[] locationNames = {"home, living room", "home, bed", "home, bedroom", "home, hallway", "home, bathroom", "home, kitchen", 
                                "lost", "outside, driveway", "outside, bus stop", "outside, bus", "outside, auto parts shop",
                                "car","outside, diner", "outside, trailer park", "outside, suburbs", "work, office", 
                                "work, parking lot", "work, repair shop"};
    

    public ArrayList<Place> locations;

    public Main(){
        this.locations = new ArrayList<>();
        for (int idx = 0; idx < locationNames.length; idx++) {
            String name = locationNames[idx];
            locations.add(new Place(name));
        }

    }

    public Item parseItemLocation(String input, Player p){
        for (int idx = 0; idx < p.location.objects.size(); idx++) {
            String itemName = p.location.objects.get(idx).name;
            if (input.contains(itemName))
            return p.location.objects.get(idx);
            
        }
        Item null_item = new Item("null");
        return null_item;
        }

        
    

    public Item parseItemInventory(String input, Player p){
        Enumeration<Item> playerItems = p.inventory.keys();
        while (playerItems.hasMoreElements()){
            Item testItem = playerItems.nextElement();
            if (input.contains(testItem.name)){
                return testItem;
            }
        }

        Item null_item = new Item("null");
        return null_item;
    }

    public Place parseLocation(String input){
        String[] parts = input.split(" ");
        String location = parts[parts.length - 1];
        for (int i = 0; i < locations.size(); i++) {
            if (locations.get(i).name.contains(location)){
                return locations.get(i);
            }
        }
        return new Place("null");

    }

    public void parse(Player p){
        String input = getInput.nextLine();
        input = input.toLowerCase();
        if (input.contains("look")) {
            p.look();
        } else if (input.contains("sleep")){
            p.sleep();
        } else if (input.contains(" up")){
            p.getUp();
        } else if ((input.contains("take")&& !input.contains("shower")) || input.contains("pick") ){
            Item item = parseItemLocation(input, p);
            if ((item.name.contentEquals("null"))){
                System.out.println("You couldn't pick it up.");
            } else {
                p.take(item);
                p.location.objects.remove(item);
                items.add(item);
            }
        } else if (input.contains("help")){
            p.help();
        } else if (input.contains("walk")|| input.contains("go")|| 
        (input.contains("get")&& !input.contains("dressed"))){
            Place location = parseLocation(input);
            if (!(location.name.contentEquals("null"))){
                p.go(location);
            } else {
                System.out.println("Can't get there");
            }
        } else if (input.contains("use ")|| input.contains("eat ")|| input.contains("drink ")) {
            Item item = parseItemInventory(input, p);
            for (int idx = 0; idx < items.size(); idx++) {
                Item useThis = items.get(idx);
                if (useThis.name.equals(item.name)){
                    useThis.use(p);
                    items.remove(useThis);
                }
                
            }
        } else if (input.contains("drive ")){
            Place destination = parseLocation(input);
            p.drive(destination);
        } else if (input.contains("shower ")) {
            p.shower();
        } else if (input.contains("drop")||input.contains("put down")) {
            Item item = parseItemInventory(input, p);
            for (int idx = 0; idx < items.size(); idx++) {
                Item dropThis = items.get(idx);
                if (dropThis.name.equals(item.name)){
                    p.drop(dropThis);
                    items.remove(dropThis);
                    p.location.objects.add(dropThis);
                } 
                
            }
        } else if (input.contains("buy")) {
            Item item = parseItemLocation(input, p);
            p.buy(item);
        } else if (input.contains("inventory")) {
            p.showInventory();
        } else if (input.contains("status")) {
            p.showStatus();
        } else if (input.contains("computer")&&(p.location.name.contains("office"))) {
            p.computer.parseMenu();
            p.updateTime(1);

        } else {
            System.out.println("That command isn't recognized");
        }
        
    }

    public static void main(String[] args) {
        Player p = new Player();
        Main main = new Main();
        p.showUI();
        p.showStatus();
        p.look();
        p.showInventory();
        for (int i = 0; i < 10; i++) {
            main.parse(p);
        }

        
    }
    
}
