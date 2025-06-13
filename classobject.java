class Car {
    String color;
    int speed;

    void drive() {
        System.out.println("The " + color + " car is driving at " + speed + " km/h.");
    }
}

public class classobject {
    public static void main(String[] args) {
        Car myCar = new Car();       // Object creation
        myCar.color = "Red";
        myCar.speed = 100;
        myCar.drive();               // Output: The Red car is driving at 100 km/h.
    }
}
