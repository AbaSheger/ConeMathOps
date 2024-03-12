import java.util.Scanner;
/** The program carry out different mathematical operations on a cone using different methods.
    1.User is prompted to enter several integers before pressing enter to test the area and volume methods
    2.The input method validate the input and return  -1 if the user press "q"
    3.The main method calls the input method to read the radius and the height and exit the loop if the input is invalid.
    5.If the input is valid, it passed down the numbers to the respective methods that hold radius and height as a parameter.

    6.There are two area methods with different parameters: one for the base area and another for the mantel area. 
    7.The volume of the cone is calculated in the volume method.
    8.The main method call back the two area methods and the volume and print the results.
    9.The number of times the two area methods and the volume depends on the pair of numbers(radius&height) the user chose to input.
    10.If the user press q, the user gets prompted  to enter another pair of numbers to Test the fraction methods.
    11.If the inputs are valid, calculation of fraction methods will be carried out
    12.Then the fraction methods will be called back to the main method to display the results.
    13.The result of the fractions will show fraction the left side and integer 
     numerator/denominator on the right side.
    14.Pressing "q" this time ends program.
    * @author Abenezer Anglo, abeang-2 
    */

public class ConeCalc {
    static Scanner userInput = new Scanner(System.in);
    static final int INVALID_INPUT = -1;

    public static void main(String[] args) {
        // Declare Variables

        int numerator;
        int denominator;
        int radius = 0; // initialize variables
        int height = 0;
        float baseArea = 0;
        float mantelArea = 0;
        float volume = 0;

        System.out.println("-----------------------------------");
        System.out.println("Test of area and Volume methods");
        System.out.println("-----------------------------------");
        System.out.print(">");
        while (true) {
            radius = input(); // call input method to read radius

            if (radius == INVALID_INPUT) { //check if its an invalid input
                break;
            }


            height = input(); // call input method to read height
            if (height == INVALID_INPUT) { //check if an invalid input
                break;
            }

            System.out.println("r=" + radius + " h=" + height);
            baseArea = area(radius); // call area method to calculate base area
            mantelArea = area(radius, height); // call area method to calculate mantel area
            volume = volume(radius, height); // call volume method to calculate volume
            System.out.printf("Base area=%.2f\n", baseArea);
            System.out.printf("Mantel area=%.2f\n", mantelArea);
            System.out.printf("Volume=%.2f\n", volume);
            System.out.println(); // print space or new line
        }

        System.out.println("-----------------------------------");
        System.out.println("Test of the fractional methods");
        System.out.println("-----------------------------------");
        System.out.print(">");
        while (true) {

            numerator = input(); // call input method to read numerator
            if (numerator == -1) { 
                break;
            }

            denominator = input(); // call input method to read denominator
            if (denominator == -1) { 
                break;
            }



            int[] numberParts = fraction(numerator, denominator);
            System.out.printf("%d/%d = ", numerator, denominator);
            printFraction(numberParts); 
            System.out.println(); 
        }

    }


    /**
     * This Method take user input 
     *
     * @return integer or -1 if the user enters "q"
     */

    public static int input() {
        int inputNumber = 0;
        while (true) {
            if (userInput.hasNextInt()) {
                inputNumber = Math.abs(userInput.nextInt()); //use Maths.abs method to return the positive value
                return inputNumber;
            }
            if (userInput.next().equalsIgnoreCase("q")) {
                return -1;
            }
            System.out.println("Please choose a valid integer");
        }
    }



    /**
     * This Method to calculate the area of a circle.
     * @param radius is an integer
     * @return the area as a float number
     */
    public static float area(int radius) {
        // must return a float number
        double rSquare = Math.pow(radius, 2); //save the result in a local variable
        float baseArea = (float)(Math.PI * rSquare);
        return baseArea;
    }
    /**
     * This method calculate the mantel area of the cone.
     * @param radius is an integer
     * @param height is an integer
     * @return the area as a float number
     */
    public static float area(int radius, int height) {
        // must return a float number
        double rSquare = Math.pow(radius, 2); //save the result in a local variable
        double hSquare = Math.pow(height, 2);
        float mantelArea = (float)(Math.PI * radius * Math.sqrt(rSquare + hSquare));
        return mantelArea;
    }

    /**
     * This method calculate volume of the cone.
     * @param radius is an integer
     * @param height is an integer
     * @return the volume as a float number
     */
    public static float volume(int radius, int height) {
        
        double rSquare = Math.pow(radius, 2); // save the result in a local variable
        float volume = (float)((Math.PI * Math.pow(radius, 2) * height) / 3);
        return volume;
    }

    /**
     * Converts a fraction into an integer part and a reduced fraction part.
     * If the denominator is zero, returns null to indicate an error.
     * If the numerator is zero, returns an array representing zero.
     * Otherwise, returns an array where the first element is the integer part of the fraction,
     * the second element is the reduced numerator, and the third element is the reduced denominator.
     *
     * @param numerator the numerator of the fraction
     * @param denominator the denominator of the fraction; must not be zero
     * @return an integer array containing three elements: the integer part of the fraction,
     *         the reduced numerator, and the reduced denominator. Returns null if the denominator is zero.
     */
    public static int[] fraction(int numerator, int denominator) {

        // Check if denominator is zero, return null
        if (denominator == 0) {
            return null;
        } 

        // Check if numerator is zero, return {0, 0, 0}
        if (numerator == 0) {
            return new int[] { 0,0,0};
        }

        // Calculate the integer part
        int integerPart = numerator / denominator;

        // Calculate the numerator part
        int numeratorPart = numerator % denominator;

        // Save the denominator
        int denominatorPart = denominator;

        // Calculate the greatest common divisor
        int gcd = gcd(numeratorPart, denominatorPart);

        // Reduce the fraction by the greatest common divisor
        if (gcd != 1) {
            gcd = Math.abs(gcd);
            numeratorPart = numeratorPart / gcd;
            denominatorPart = denominatorPart / gcd;
        }

        // Return the result in an integer array
        return new int[] {integerPart,numeratorPart,denominatorPart};
    }



    /**
     * Finds the greatest common divisor of two integers using the Euclidean algorithm.
     *
     * @param a the first integer
     * @param b the second integer
     * @return the greatest common divisor of the two integers
     */
    public static int gcd(int a, int b) {
        // Make sure that a > b
        if (a < b) {
            int temp = a;
            a = b;
            b = temp;
        }

        // Find the GCD using the Euclidean algorithm
        while (b != 0) {
            int c = a % b;
            a = b;
            b = c;
        }

        // Return the GCD
        return a;
    }
    /**
     * Prints fractions in a formatted manner as "integer part" "numerator/denominator",
     * handling special cases where the fraction can be simplified to an integer or zero.
     * If the parts array is null or does not have exactly three elements, prints "Error".
     *
     * @param parts an integer array where parts[0] is the integer part of the fraction,
     *              parts[1] is the numerator, and parts[2] is the denominator.
     */
    public static void printFraction(int[] parts) {

        if (parts != null && parts.length==3) {
            if (parts[0] == 0 && parts[1] == 0 && parts[2] == 0) { 
                System.out.println("0");
            } else if (parts[0] == 0) {
                System.out.println(parts[1] + "/" + parts[2]); 
            } else if (parts[1] == 0) {
                System.out.println(parts[0]); 
            } else if (parts[0]!= 0 && parts[1]!= 0 && parts[2]!= 0){
                System.out.println(parts[0] + " " + parts[1] + "/" + parts[2]);
            }
        } else {
            System.out.println("Error");
        }

    }
}