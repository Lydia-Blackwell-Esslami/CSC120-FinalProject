public class Item {

    String name;
    String type;
    int price;

    public Item(String name){
        this.name = name;
        this.setPrice();
    }

    public void findType(){
        String eatables = "bread, sandwich, pizza, dinner, breakfast, lunch, egg, burger";
        String drinkables = "milk, water, soda, juice, coffee, wine(alcohol), beer(alcohol)";
        String toxics = "bleach, oil, coolant, brake fluid, transmission fluid, brakeleen, pb-blaster, wiper fluid";
        if (eatables.contains(this.name)){
            this.type = "eatable";
        } else if (drinkables.contains(this.name)){
            this.type = "drinkable";

        } else if (toxics.contains(this.name)){
            this.type = "toxic";

        } else {
            this.type = "nonconsumable";
        }
    }

    public void setPrice(){
        this.findType();
        if (this.type.contentEquals("eatable")){
            this.price = 50;
        } else if (this.type.contentEquals("drinkable")) {
            this.price = 25;
        } else {
            this.price = 10;
        }
    }

    public void use(Player p){
        if (this.type.contentEquals("eatable")){
            p.eat(this);
        } else if (this.type.contentEquals("drinkable")) {
            
        }
    }

    
}
