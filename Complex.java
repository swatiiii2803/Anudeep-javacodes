
import java.util.Scanner;

class Complex {
    int real, imag;

    Complex(int r, int i) {
        real = r;
        imag = i;
    }

    Complex add(Complex c) {
        return new Complex(this.real + c.real, this.imag + c.imag);
    }

    void display() {
        System.out.println("Sum = " + real + " + " + imag + "i");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter real and imaginary part of first number: ");
        Complex c1 = new Complex(sc.nextInt(), sc.nextInt());
        System.out.print("Enter real and imaginary part of second number: ");
        Complex c2 = new Complex(sc.nextInt(), sc.nextInt());
        Complex result = c1.add(c2);
        result.display();
    }
}
