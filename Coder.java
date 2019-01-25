/**
 * File:        Coder.java
 * Author:      Zhilong Gan
 * ID:          100331942
 * Date:        2018.10.20
 * class:       CPSC 1150-02
 * instructor:  Instructor: H. Darbandi
 * title        encode and decode
 * Compiler:    java JDK 10.2
 */

import java.util.Scanner;
/*
  program let the user input a message and encode the message
  the user will input a code number inorder to encode the message
 */
public class Coder
{

    public static void main(String[] args)
    {
	// write your code here
        char mgs[] = getMgs(); // the user message
        int code = getCode();  // the way the user want to encode the message
        long binary; // the output of the encode message

        for(int i = 0; i < mgs.length; i++)
        {
            binary = toBinary((int)coder(mgs[i],code));  //output after encoded
            printBinary(binary);   //to print the 8 bit binary form
        }

    }
    /////////////////////////////////////////////////////////////

    /**
     * let the user enter message
     * @return the user message
     */
    public static char []getMgs()
    {
        System.out.println("please enter message you want to encode");
        Scanner input = new Scanner(System.in);
        String message = input.nextLine();
        return message.toCharArray();
    }
    ///////////////////////////////////////////////////////////

    /**
     * let the user enter the way they want to encode the message
     * @return code number
     */
    public static int getCode()
    {
        System.out.println("code:");
        Scanner input = new Scanner(System.in);
        int code = (int)input.nextDouble();
        return code;
    }
    /////////////////////////////////////////////////////////

    /**
     * to encode the each letter in the message by adding the code value expect the charater is not
     * from a to z
     * from A to Z
     * from 0 to 9
     *if the value is excess the domain for the message is return in the domain
     * @param message character valueis of letter from the message that the user input
     * @param code an integer number that the user input
     * @return message character value after adding the code value if in the domain if not
     * it return to original value
     */
    public static char coder(char message, int code)
    {
        int newMgs = (int)message + code;  // encode letter

        if(message >= 'a' && message <= 'z')  // encode from a to z
           newMgs = donmain(newMgs, 'a', 'z');
        else if (message >= 'A' && message <= 'Z')  // encode from A to Z
            newMgs = donmain(newMgs, 'A', 'Z');
        else if (message >= '0' && message <= '9')   // encode from 0 to 9
            newMgs = donmain(newMgs, '0', '9');
        else
            return message; // return to original value

        return (char)newMgs; // return to value after encode
    }
    ////////////////////////////////////////////////////////

    /**
     * let the message after encode stay in the range
     * @param input the value of the character after encode
     * @param begin the beginning of the range
     * @param end the end of the range
     * @return the value that should stay in the domain
     */

    public static int donmain(int input, char begin, char end)
    {
        /*
          if the vaule do not out of domain then it will be the vale itself
          if the value is greater to the domain it will be the value that subtract period times of domain
          until the value stay in domain
          if the value is less tan domain it will be the value that add the period times of domain
          until the value stay in domain
         */
        int different = (int)end - (int)begin + 1; // calculate the number from begin to end
        if(input <= (int) end && input >= (int) begin) // the value do not excess the domain
        {
            return input;
        }
        else if(input > end)  // the value the excess the domain
        {
            return donmain(input - different, begin, end);  // subtract period times
        }
        else  // the value is smaller than domain
        {
            return donmain(input + different, begin, end); // add period times
        }
    }
    /////////////////////////////////////////////////////////

    /**
     * to change the decimal form to a binary form
     * @param n the decimal value the need to be changed to binary form
     * @return the binary form of the decimal form
     */
    public static long toBinary(int n)
    {
        int number = n;
        int reminder;
        int count = 0;
        long answer = 0;
        /*
         sum up the reminder of n divide by 2 until n get 0
         */
        while(number > 0)
        {
            reminder = number % 2;
            answer = answer + reminder * (int)Math.pow(10,count);
            count++;
            number /= 2;
        }
        return answer;
    }
    /////////////////////////////////////////////////////////////////

    /**
     * to print the encode message into 8 bits binary form
     * @param n the binary form that need to be printed
     */

    public static void printBinary(long n)
    {
        /*
          if the number of digits is less than 8 than it print 0 at front
         */
        char zero = '0';
        int countDigit = getDigit(n); // get the number of digits

        while(countDigit < 8) //print zero until the total digit get to 8
        {
            System.out.print(zero);
            countDigit++;
        }
        System.out.print(n);  // print the binary
    }
    //////////////////////////////////////////////////////////////

    /**
     * count the number of digits that n has
     * @param n the number that need to be counted
     * @return the number of digits
     */
    public static int getDigit(long n)
    {
        long number = n;
        int count = 0;
        while(number > 0) // divide n  by 10 until it gets 0
        {
            number /= 10;
            count++;
        }
        return count;
    }
}
