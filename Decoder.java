/**
 * File:        Decoder.java
 * Author:      Zhilong Gan
 * ID:          100331942
 * Date:        2018.10.20
 * class:       CPSC 1150-02
 * instructor:  Instructor: H. Darbandi
 * title        encode and decode
 * Compiler:    java JDK 10.2
 */

/**
 * the program is to get the original message from the message after encoded
 */
public class Decoder
{
    // the message that need to be decoded
    public static String message = "010100010110010101100110011100000010000001100110011100000010000001111000001000000101000101100010011100000111000100111111001000000010000100100000001111000101011101100101011001100110100101101100011010110110010000111110";
    // the way to encode then how to decode
    public static double code = 23;

    public static void main(String[] args)
    {
        int codeNumber = (int)code;
      decoder(message, codeNumber);
    }
    /////////////////////////////////////////////

    /**
     * to decode every 8 digit in the message change to character and display them
     * @param mgs the message need to decoded
     * @param code the way encoded the message
     */

    public static void decoder(String mgs, int code)
    {
        mgs = message;
        int numberOfLetter = 0;
        int binary;
        int numletter;
        char letter;
        while(numberOfLetter < mgs.length() / 8) // take every 8 digits in the message
        {
            /*
            first take the 8 digits in the message which should be in binary form
            second change the binary into decimal
            last decode the the number and change to character and display
             */
            binary = get8Digit(numberOfLetter * 8,mgs); // take digits
            numletter = toDecimal(binary);             //change to decimal
            letter = decode((char)numletter, code);    // decode
            System.out.print(letter);     //display
            numberOfLetter++;   // take the next 8 digits
        }
    }
    /////////////////////////////////////////////////////

    /**
     * take 8 digits in the message
     * @param start where to start to take the digits
     * @param mgs  the message that want to take
     * @return the binary form of the letter
     */

    public static int get8Digit(int start, String mgs)
    {
        int end = start + 7; // calculate where is the end
        int count = start;
        int answer = 0;
        while(count <= end)
        {
            int digit = mgs.charAt(count) - '0';  // calculate the value in the message at count
            answer = answer * 10 + digit;
            count++;
        }
        return answer;
    }
    /////////////////////////////////////////////////////////

    /**
     * change binary form to decimal form
     * @param n the number in binary form need to be changed into decimal
     * @return  the decimal form of n
     */

    public static int toDecimal(int n)
    {
        int count = 0;
        int reminder;
        int answer = 0;
        int number = n;
        while (number > 0)
        {
            reminder = number % 10;
            // the the number definition
            answer = answer + reminder * (int)Math.pow(2, count);
            number /= 10;
            count++;
        }
        return answer;
    }
    /////////////////////////////////////////////////////////////

    /**
     * get the original character by subtracting code and keep the value of character stay in domain expect
     * from a to z
     * from A to Z
     * from 0 to 9
     * @param letter the letter need to decoded
     * @param code the way to encode message
     * @return the original character
     */

    public static char decode(char letter, int code)
    {
        int oldLetter = letter - code; // decode

        /*
         make the value stay in domain
         */
        if(letter >= 'a' && letter <= 'z')
            oldLetter = donmain(oldLetter, 'a', 'z');
        else if (letter >= 'A' && letter <= 'Z')
            oldLetter = donmain(oldLetter, 'A', 'Z');
        else if (letter >= '0' && letter <= '9')
            oldLetter = donmain(oldLetter, '0', '9');
        else
            return letter; // the letter not in domain

        return (char)oldLetter; // after change in domain
    }
    ////////////////////////////////////////////////////////
    /**
     * let the message after decode stay in the range
     * @param input the value of the character after decode
     * @param begin the beginning of the range
     * @param end the end of the range
     * @return the value that should stay in the domain
     */

    public static int donmain(int input, char begin, char end)
    {
        int different = (int)end - (int)begin + 1;
        if(input <= (int) end && input >= (int) begin)
        {
            return input;
        }
        else if(input > end)
        {
            return donmain(input - different, begin, end);
        }
        else
        {
            return donmain(input + different, begin, end);
        }
    }

}
