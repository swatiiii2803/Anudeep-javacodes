// Base class (Grandparent)
class Grandparent {
    void showGrandparent() {
        System.out.println("I am the Grandparent.");
    }
}

// Derived class (Parent)
class Parent extends Grandparent {
    void showParent() {
        System.out.println("I am the Parent.");
    }
}

// Derived class (Child)
class Child extends Parent {
    void showChild() {
        System.out.println("I am the Child.");
    }
}

// Main class to run the program
 class multilevelinheritance {
    public static void main(String[] args) {
        // Creating object of Child class
        Child obj = new Child();

        // Using the object to access methods from all levels
        obj.showGrandparent(); // Inherited from Grandparent
        obj.showParent();      // Inherited from Parent
        obj.showChild();       // Defined in Child
    }
}
