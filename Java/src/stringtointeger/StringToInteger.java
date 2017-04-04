package stringtointeger;

/**
 * Created by Parag Dakle on 4/4/17.
 *
 * Class accepts an input number as string from user and converts
 * the string to integer and prints the same.
 *
 * Class converts to an integer until end of string or first
 * non-digit character is found (whichever comes first).
 */
public class StringToInteger {

    public static void main(String args[]) {
        if(args.length == 0) {
            System.out.print("Invalid input.\nPlease execute the program in the following way:\n");
            System.out.print("java StringToInteger <number_to_convert>");
            return;
        }
        String input = args[0];
        if(input.length() > 0) {
            int[] numbers = new int[input.length()];
            int i;
            char c;
            int number = 0;
            boolean isNegative = false;
            //Loop converts the given string into integer array
            for (i = 0; i < input.length(); i++) {
                c = input.charAt(i);
                if (Character.isDigit(c)) { //if the current character is a digit
                    numbers[i] = ((int) input.charAt(i)) - 48;
                } else if (c == '-' && i == 0) {
                    /* if the first character is - for negative numbers
                       can be extended for checking the . character for floating numbers
                      */
                    isNegative = true;
                } else { //a non digit character has been encountered
                    i--;
                    break;
                }
            }
            //if entire string was only numbers then go back one index
            if (i == input.length()) i--;
            //Loop constructs the integer from individual digits.
            if (i >= 0) {
                int positionMultiplier = 1;
                for (; i >= 0; i--) {
                    number += (positionMultiplier * numbers[i]);
                    positionMultiplier *= 10;
                }
                //If - sign was found at the start convert number to negative
                if (isNegative) number *= -1;
                System.out.println(number);
            } else {
                System.out.println("Invalid input");
            }
        }
        else {
            System.out.println("Invalid input");
        }
    }
}
