public class Person{
    String name;
    String location;
    int mood;
    String job;

    public Person(){
        this.location = "None";
        this.mood = 0; 
    }

    public Person(String name, String job){
        this.name = name;
        this.location = "None";
        this.mood = 0;
        this.job = job;
    }

    public void chat(){

    }

    public void sellCar(Car car){

    }
    

    public void buyCar(Car c){

    }

    public void recieveFlirting(){

    }

    public void negotiate(Car c){

    }

    public boolean  noticeTheft(){
        return true;
    }

    


}