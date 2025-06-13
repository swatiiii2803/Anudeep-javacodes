public class palindrom {
    public static void main(String[] args) {
        int number = 454;
        int original = number;
        int reversed = 0;

        while (number != 0) {
            int digit = number % 10;        // Get last digit
            reversed = reversed * 10 + digit;
            number /= 10;                   // Remove last digit
        }

        if (original == reversed) {
            System.out.println(original + " is a palindrome.");
        } else {
            System.out.println(original + " is not a palindrome.");
        }
    }
}
