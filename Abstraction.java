  abstract class Animal {
    abstract  void walk();
    
Animal(){ 
    System.out.println("Constrctor of animal");
}
 void eat(){
    System.out.println("animal can eat");
 }   
}
 class horse extends Animal{
    void walk()
    {
        System.out.println("Horse is walking");
    }

} 
public class Abstraction{
    public static void main(String[] args) {
        horse h=new horse();
        
        h.walk();
        h.eat();
        
        };
          }

