import java.util.Scanner;

public class Compoundinterest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter principal, rate, and time: ");
        double p = sc.nextDouble();
        double r = sc.nextDouble();
        double t = sc.nextDouble();
        double amount = p * Math.pow((1 + r / 100), t);
        System.out.println("Compound Interest = " + (amount - p));
    }
}