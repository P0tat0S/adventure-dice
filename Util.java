import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Util {
    //Utility Methods
    public static int diceRoller(int min, int max) {
        Random random = new Random(); //Creation of an object Random
        //Gets two integers and returns a random value between the given values
        return random.nextInt(max + 1 - min) + min;
    }

    public static void print(String message) {//Just an abbreviation method
        System.out.println(message);
        return;
    }

    public static String strInput(String message) {
        print(message);
        Scanner sc = new Scanner(System.in); //Creation of a scanner
        String strInput = sc.nextLine();
        return strInput; //Method that returns the user's string strInput
    } //End strInput

    public static int intInput(String message) { //Method to check input int
        int input = 0;
        boolean valid = false;
        while(!valid) {
            try {
                input = Integer.parseInt(strInput(message));
                valid = true;
                return input;
            } catch (NumberFormatException e) {
                print("That was not a number. Please try again.");
            }
        }
        return input;
    }

    public static double doubleInput(String message) { //Same as intInput
        double input = 0;
        boolean valid = false;
        while(!valid) {
            try {
                input = Double.parseDouble(strInput(message));
                valid = true;
                return input;
            } catch (NumberFormatException e) {
                print("That was not a number. Please try again.");
            }
        }
        return input;
    }
    
    public static void pause() {//Method to pause
        String lost = strInput("\nPress Enter to continue...\n");
        return;
    }
}
