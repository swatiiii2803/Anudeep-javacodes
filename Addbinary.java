import java.util.Scanner;

public class Addbinary {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter first binary number: ");
        String a = sc.nextLine();
        System.out.print("Enter second binary number: ");
        String b = sc.nextLine();
        int sum = Integer.parseInt(a, 2) + Integer.parseInt(b, 2);
        System.out.println("Sum = " + Integer.toBinaryString(sum));
    }
}