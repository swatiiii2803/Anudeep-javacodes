public class numcount {

    public static void main(String[] args) {
        int number = 69543;
        int count = 0;

        while (number != 0) {
            number /= 10; // Remove the last digit
            count++;
        }

        System.out.println("Number of digits: " + count);
    }
}


