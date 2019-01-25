/**
 * File:        PrimePalindrome.java
 * Author:      Zhilong Gan
 * ID:          100331942
 * Date:        2018.10.04
 * class:       CPSC 1150-02
 * instructor:  Instructor: H. Darbandi
 * title        prime palindrome number
 * Compiler:    java JDK 10.2
 */

import java.util.Scanner;

/**
 * the user type a positive integer number
 * to find all the prime palindrome number that is less than and equal to the number that user type
 */
public class PrimePalindromeNumber
{
    public static double inputNumber;      // the number the user type
    public static final double EPSILON = 0.0000001;
    public static void main(String[] args)
    {
	// write your code here
      task();     // how the program process
    }
    ///////////////////////////////////////////////////////////////

    /**
     * the steps that the program need to proceed
     * the program start with introduction of the program
     * let the user input the number
     * do the calculation and output the answers
     */
    public static void task()
    {
        start();
        inputNumber();
        System.out.println("the answer");
        //the number the user input may be a faction
        check((int)inputNumber); // make sure inputNumber is a integer type
    }

    ///////////////////////////////////////////////////////////////

    /**
     * tell the user what this program do
     * tell the user how to use the program
     */
    public static void start()
    {
        System.out.println("the goal for the program is to find the prime palindrome numbers");
        System.out.println("the user need to input a number");
        System.out.println("the program will find all the prime palindrome numbers less or equal to the number that user input");
        System.out.println();
        System.out.println("Star");
    }
    ///////////////////////////////////////////////////////////////

    /**
     * the user input a number to the variable inputNumber
     * make sure the number is greater than one
     * otherwise user will retry input
     *
     */
    public static void inputNumber()
    {

        System.out.println("please enter a number");
        Scanner input = new Scanner(System.in);
        inputNumber = input.nextDouble();
        /*
          to make sure the number that the user type is greater than 1
          because the smallest prime number is 2
         */
        while(2 - inputNumber > EPSILON) // use epsilon to compare floating point
        {
            System.out.println("the smallest prime number is 2!");
            System.out.println("please enter a integer number greater than 1");
            inputNumber = input.nextDouble();
        }
         checkFloatingPoint(); // check the number is it an integer number


    }
    ////////////////////////////////////////////////////////////////

    /**
     * check the number that the user input to determine the type of number
     * if the number is faction it will become the integer by cutting the number after decimal point
     * if the number after decimal point is 0 than the number will remain the same but change to integer type
     * if the number is super close to its integer type than the number will change to integer type
     */
    public static void checkFloatingPoint()
    {
        if(inputNumber - (int)inputNumber > EPSILON)  // use epsilon to compare floating point
        {
            System.out.println("prime number is a integer number");
            System.out.println("the number should be " +(int)inputNumber);
        }
    }
//////////////////////////////////////////////////////////////////

    /**
     * the function output all the number is prime palindrome number less than or equal to user number
     * @param n the target number the output cannot greater than it
     */
    public static void check(int n)
    {
        /*
          using for loop to make decrement to check is the number below or equal
          to n is a prim palindrome number
         */
        for(int i = n; i > 0; i--)
        {
            if(palindromes(i))  // check if i is a palindromes
            {
                if(primeNumber(i))  // check if i is a prime number
                {
                    System.out.println(i);  // the answer
                }
            }
        }
    }

    //////////////////////////////////////////////////

    /**
     * check variable n is or is not a prime number by checking the time that the number is dividable
     * n divide by 1 to its sqrt root if n can divide only 1 then it is prime number
     * if n can divide more than 1, n is not prime number
     * @param n the value need to be checked
     * @return  true for prime number false for not a prime number
     */

    public static boolean primeNumber (int n)
    {
        int number = n;
        /*
        check very number from 1 to its sqrt root
        if it can divide by more than 1 than return false
         */
        for(int i = 1; i <= Math.sqrt(number); i++)
        {
            if(number % i == 0 && i > 1 || number == 1) // check if it can only dividable by 1
                return false;
        }
        return true;
    }

///////////////////////////////////////////////////////////////

    /**
     * the method check a number is a palindromes or not
     * using the other method call reverseNumber
     * if the number is the same after the number is reverse
     * then the number is palindromes
     * if not the number is not palindromes
     * @param n  the number needed to be checked
     * @return  true for palindromes false for not a palindromes
     */
    public static boolean palindromes(int n)
    {
        int reverse = reverseNumber(n); // using method
        int number  = n;
        if(number == reverse)// compare number and reverse number
            return true;
        else
            return false;
    }
//////////////////////////////////////////////////////////////

    /**
     * the method reverse the number for example 123 become 321
     * @param n the number needed to be reverse
     * @return the reverse number of variable n
     */
    public static int reverseNumber(int n)
    {
        int number = n;
        int answer = 0;
        int reminder;

        if(n < 0) // make sure if the number is negative the method will do the same thing
            number = Math.abs(number);

        /*
        in the loop make sure the number is greater than 0
        to divide the number by 10 to get the reminder for every digit
        the reverse will be the first reminder will times 10 and add the next reminder
         */
        while(number / 10 > 0)
        {
            reminder = number % 10;
            answer = answer * 10 + reminder;
            number /= 10;  // decrement
        }
        /*
         in the loop when the digit remain less than 10 the reminder will be 0
         the last digit will be the number itself
         */
        answer = answer * 10 + number;

        if(n < 0) // if the number is negative return negative
            return -answer;
        else
            return answer;
    }

}
