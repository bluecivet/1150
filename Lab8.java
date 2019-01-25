import java.util.Scanner;
/**
 * File:        Lab8.java
 * Author:      Zhilong Gan
 * ID:          100331942
 * Date:        2018.11.02
 * class:       CPSC 1150-02
 * instructor:  Instructor: H. Darbandi
 * title        add and multiply
 * Compiler:    java JDK 10.2
 */

/**
 * the program provide the addition and multiply operation for 30 digits integer number
 * the user will enter 2 integer number and select the operation and the program will get the answer
 */
public class Lab8
{
    static final int MAX = 30;  // the max number the the integer can input
    static final int START_SPACE = 5; // the out put format
    static int Colen = 3; // every n place will have a colen
    public static void main(String[] args)
    {
        char[] num1 = getNumber("First number :"); // input 1
        char[] num2 = getNumber("Second number :"); // input 2

        int selcet = select(); // select the operation
        switch(selcet)
        {
            // add
            case 1:  char[] sum = add(num1, num2); // calculation
                printArray(sum,num1,num2,'+');  // output
                break;
             // multiply
            case 2:  char[] answer = mutiply(num1, num2);  // calculation
                printArray(answer,num1,num2,'x');  // output
                break;
            // exit the program
            case 3: System.exit(0);
                break;
        }

    }





    //entry method start
    ////////////////////////////////////////////////////

    /**
     * let user to input the integer if the user input excess the max number then it will cut the the number behind
     * if user input a character in the input then the program will let the user input again until input all number
     * if the input is empty then it will let user input again until the program get number
     * @param mgs a string for introduction
     * @return character type array then contain all number
     */
    public static char[] getNumber(String mgs)
    {
        char[] number = new char[MAX];
        Scanner input = new Scanner(System.in);
        System.out.println(mgs); // introduction
        String meMgs;        // the string contain user input

        do
        {
            meMgs = input.nextLine(); // the original message that user input
            storeNumber(number,meMgs); // copy the number in the array
            if(meMgs.length() == 0)   // if there are no input
                System.out.println("there are no input");
        }
        while(!allNumber(number) || meMgs.length() == 0); // check if the input is all number or there no input if not input again

        return number;
    }


    /////////////////////////////////////////////////////////////////////////////////////

    /**
     * the method will store the string to a character array from the end of array
     * @param number the character type array that contain the string
     * @param meMgs the characters that need to be stored
     */
    public static void storeNumber(char[] number, String meMgs)
    {
        /*
          if string length within array length
          the string will store begin from its end to its head
          the string is store in the end of the array
         */
        if(meMgs.length() < number.length) // compare to length of array and string
        {
            for(int i = 0; i < meMgs.length(); i++)
            {
                number[number.length - 1 - i] = meMgs.charAt(meMgs.length() - 1 - i);
            }
        }
        else// if not string length in array length it will cut the string
        {
            for(int i = 0; i < number.length; i++)
            {
                number[number.length - 1 - i] = meMgs.charAt(number.length - 1 - i);
            }
        }
    }


    /////////////////////////////////////////////////////////////////////////////////////////

    /**
     * the method will check the array if the array contain is all number then return true
     * the if the array include a character that is not number true false
     * @param arr  the character type array that need to check
     * @return  true for all number false for not all number
     */

    public static boolean allNumber(char[] arr)
    {
        for(int i = 0; i < arr.length; i++)
        {
            /*
             check from beginning
             once the character array is not default character and
             not in the range of '0' to '9' return false
             */
            if((arr[i] < '0' || arr[i] > '9') && arr[i] != 0)
            {
                System.out.println("there are character other than number in the input at " + i);
                reset(arr);  //clear the input
                return false;
            }
        }
        return true;
    }


    /////////////////////////////////////////////////////////////////////////////////////////

    /**
     * the method let the user select a operation
     * 1 for add
     * 2 for multiply
     * 3 for exit the progrma
     * if the user enter either not the above number user will inter again
     * @return a integer number
     */

    public static int select()
    {
        System.out.println("Please select a operation");
        System.out.println("enter 1: add ");
        System.out.println("enter 2 : multiply ");
        System.out.println("enter 3: exit");

        Scanner input = new Scanner(System.in);

        char selection = input.next().charAt(0);

        while(selection != '1' && selection != '2' && selection != '3') // user input
        {
            System.out.println("please enter again");
            selection = input.next().charAt(0);
        }
        return selection - '0';
    }


    ///////////////////////////////////////////////////////////////////////
    //entry method END







    //share method for add and multiply
    //-----------------------------------------------------------------------------------

    /**
     * the method will set the character array that is ready for calculation
     * the empty space that need to add or multiply will set to 0
     * compare which array content is larger
     * @param num1 the character array need to seted
     * @param num2 the character array need to seted
     * @return the number of max content
     */
    public static int setCalculation(char[] num1, char[] num2)
    {
        int max; // the max length of number contain
        int start1 = num1.length - location(num1); //to calculate the length of number contain in num1
        int start2 = num2.length - location(num2); // to calculate the length of number contain in sum2
        if(start1 > start2)
        {
            max = start1;
            initicial(num2,location(num1)); // set 0
        }
        else
        {
            max = start2;
            initicial(num1,location(num2)); // set 0
        }
        return max;
    }


    /////////////////////////////////////////////////////////////////////

    /**
     * the method set the digit the is not from '0' to '9' to '0'
     * @param arr the character array the needed to make '0'
     * @param start the place start to set
     */
    public static void initicial(char[] arr , int start)
    {
        for(int i = start; i < arr.length; i++)
        {
            if(arr[i] < '0' || arr[i] > '9') // check in range of number character
                arr[i] = '0';
            else
                return;
        }
    }


    //////////////////////////////////////////////////////////////////////

    /**
     * the method will find the start index for the array that contain number character
     * @param arr the character need to find
     * @return the start index that inn contain number character in the array
     */

    public static int location(char[] arr)
    {
        for(int i = 0; i < arr.length; i++)
        {
            /*
            start from begin
            if the array content is a number character
            the location will the the index for that character
             */
            if(arr[i] >= '0' && arr[i] <= '9')
                return i;
        }

        return 0;
    }


    /////////////////////////////////////////////////////////////////////////

    /**
     * the method will find which is larger between 2 given number
     * @param num1 a integer
     * @param num2 a integer
     * @return the larger value between two numer
     */
    public static int max(int num1, int num2)
    {
        if(num1 > num2)
            return num1;
        else
            return num2;
    }


    ////////////////////////////////////////////////////////////////////////

    /**
     * the method will make all the character into unit code 0
     * @param arr the character array that need to reset
     */
    public static void reset(char[] arr)
    {
        for(int i = 0; i < arr.length; i++)
        {
            arr[i] = 0;
        }
    }


    /////////////////////////////////////////////////////////////////////////////

    /**
     * the method will set the character array's content all to '0' which make the array calculation easier
     * @param arr character type array the need to be set
     */
    public static void resetForCal(char[] arr)
    {
        for(int i = 0; i < arr.length; i++)
        {
            arr[i] = '0';
        }
    }


    ////////////////////////////////////////////////////////////////////////
    // share method END








    // ADD method
    //-----------------------------------------------------------------------------------

    /**
     * the method write down the step that need to do to finish addition calculation
     * 1. set two character array to a calculable condition and find the max value between two array length
     * 2. create a new array for containing the answer the length for new array is the max length + 1
     * 3. do the calculation
     * @param num1 character array as first number need to be add
     * @param num2 character array as second number need to be add
     * @return the answer array
     */
    public static char[] add(char[] num1, char[] num2)
    {
        int max = setCalculation(num1,num2);  // find max value between two array length
        char[] sum = new char[max + 1]; // create sum array use the larger array for sum. +1 mean the array may need one more space
        addCal(sum,num1,num2);   // do the calculation
        return sum;
    }


    ///////////////////////////////////////////////////////////////////////////////

    /**
     * the method will do the calculation for the two input array
     * the calculation are done in a loop
     * the calculation will all start from the end of the array
     * 1. get the actual number that is containing in a character array
     * 2. add the number together and calculation the carry out
     * 3. calculation the number left off after adding the carry out
     * 4. convert the left off number into character and sort the character
     * @param sum the character array that is contain the answer
     * @param num1 the character array that need to added
     * @param num2 the character array that need to added
     */
    public static void addCal(char[] sum, char[] num1, char[] num2)
    {
        int firstNumber, secondNumber, added, leftoff;
        int increase = 0;

        int i;
        for(i = 0; i < sum.length - 1; i++) // add the smaller array
        {
            firstNumber = num1[num1.length - 1 - i] - '0'; // get the number
            secondNumber = num2[num2.length - 1 - i] - '0';
            added = firstNumber + secondNumber;  // add two number together
            leftoff = added + increase > 9 ? (added + increase - 10) : added + increase; // get what is left off
            sum[sum.length - 1 - i] = (char)(leftoff + '0');  // convert and sort the value of left off from the end of array
            increase = added + increase> 9 ? 1 : 0;  // get carry out
        }
         /*
         because the loop is finish before the array get the last digit which is carry out
         the last digit will be carry out
          */
        sum[sum.length - 1 - i] = (char)(increase + '0');

    }



    ///////////////////////////////////////////////////////////////////////////////
    // ADD method END








    // mutiply method start
    //-----------------------------------------------------------------------------

    /**
     * the method contain the step to finish multiply operation
     * 1. set the two to calcuable condition and find the max value between two array length
     * 2. create new array to contain the answer the length should be the max length * 2
     * 3. do the calculation
     * @param num1 a character array that need to be multiply
     * @param num2 a character array that need to be multiply
     * @return the answer array
     */
    public static char[] mutiply(char[] num1, char[] num2)
    {
        int max = setCalculation(num1,num2);  // set array and find the max array length
        char[] answer = new char[max * 2];  // create new array for answer
        return mutiplyCal(answer,num1,num2);  // calculation step
    }


    /////////////////////////////////////////////////////////////////////////////

    /**
     * this method do the calculation for multiply the method will use the method for addition
     * the calculation will finish in a loop
     * the calculation will start from the end of the array
     * 1. find the actual number in the character array
     * 2. multiply two number and get the carry out
     * 3. calculate what is left off after two number multiply and add the carry out
     * 4. convert the number into character and store it from the end of answer array
     * there will be a temp array to store the answer instead of answer array
     * after the temp array get the answer then it will add the answer array
     * which mean the answer array is the sum of all temp array
     * how many time the temp array is added depend on how many number num2 array contain
     * @param answer the character array which is the answer for multiply two array
     * @param num1 character array that need to be multiply
     * @param num2 character array that need to be multiply
     * @return the answer array after calculation
     */
    public static char[] mutiplyCal(char[] answer, char[] num1, char[] num2)
    {
        // find the end index for each array
        int endIndex1 = num1.length - 1;
        int endIndex2 = num2.length - 1;
        int endAnswer = answer.length - 1;
        //find the first index that contain number character in the array
        int startIndex1 = location(num1);
        int startIndex2 = location(num2);
        // the variable that is need for calculation
        int timed, firstNumber, secondNumber, leftoff, increase;

        // the array that contain the answer once at time when one number in array * other array
        char[] temp = new char[answer.length];
        resetForCal(temp); // set both array to calculation condition
        resetForCal(answer);

        for(int i = 0; i < num2.length - startIndex2; i++) // start from the end of num2
        {
            increase = 0; // reset carryout
            for(int j = 0; j < num1.length - startIndex1; j++) // start from the end of num1
            {
                firstNumber = num1[endIndex1 - j] - '0'; // get actual number
                secondNumber = num2[endIndex2 - i] - '0';  // get actual number
                timed = firstNumber * secondNumber ;  // two number multiply
                leftoff = timed + increase > 9 ? (timed + increase) % 10 : (timed + increase); // get left off
                temp[endAnswer - j - i]  = (char)(leftoff + '0'); // because temp is same length as answer store left off
                increase = (timed + increase) / 10; // get carry out
                temp[endAnswer - j - i - 1] = (char)(increase + '0'); //this is for the last digit in the answer array
            }

            addCal(answer, temp, answer); // sum up the temp array
            resetForCal(temp); // reset the temp array
        }
        return answer;
    }


    ////////////////////////////////////////////////////////////////////////




    /////////////////////////////////////////////////////////////////////////
    // multiply method END








    //output method START
    //-----------------------------------------------------------------------------

    /**
     * his method will print the format that the arrays need to output by
     * calculating the array length and compare them to find out the way for input
     * also the the number that show up will have colen
     * @param answer character array
     * @param num1 character array
     * @param num2 character array
     * @param sign a character the showing the what is the sign of operation
     */

    public static void printArray(char [] answer, char[]num1, char[] num2, char sign)
    {
        //find the length for each array
        int lenForNum1 = num1.length - locatNonIncludeZero(num1) + determinColen(num1);
        int lenForNum2 = num2.length - locatNonIncludeZero(num2) + determinColen(num2);
        int lenForAnswer = answer.length - locatNonIncludeZero(answer) + determinColen(answer);
        // find the max value for the longest array
        int max = max(lenForAnswer, max(lenForNum1, lenForNum2));

        printArray(num1, max - lenForNum1); System.out.printf(" " + sign); // print the first array
        System.out.printf("\n");// go to next line
        printArray(num2, max - lenForNum2); // print the second array
        System.out.printf("\n"); // go to next line
        printLine(max); // print a line
        System.out.printf("\n");// go to next line
        printArray(answer, max - lenForAnswer); // print the answer array

    }


    //////////////////////////////////////////////////////////////////////////

    /**
     * the method will print the spaces
     * @param number positive integer that show how many spaces are needed
     */
    public static void printSpace(int number)
    {
        for(int i = 0; i < number; i++)
        {
            System.out.printf(" ");
        }
    }


    ////////////////////////////////////////////////////////////////////

    /**
     * the method will print a line
     * @param until a positive integer show how many '-' are needed
     */
    public static void printLine(int until)
    {
        printSpace(START_SPACE);
        for(int i = 0; i < until; i++)
        {
            System.out.printf("-");
        }
    }


    ///////////////////////////////////////////////////////////////////////

    /**
     * the methos will find how many colens are need in a number presenting in a character array
     * @param arr the character array need to find number of colens
     * @return the number of colens needed
     */
    public static int determinColen(char[] arr)
    {
        int numOfcontent = arr.length - locatNonIncludeZero(arr); // find how many number contain in the array
        int need = 0;
        for(int i = 1; i * Colen < numOfcontent; i++) // every n number will add a colen here n is Colen = 3
        {
            need++; // report the time that how many colens add
        }
        return need;
    }


    ////////////////////////////////////////////////////////////////

    /**
     * the method find start index for a character array where it is a number character but not include 0
     * @param arr the character array need to determine
     * @return a integer presenting the start index
     */
    public static int locatNonIncludeZero(char[] arr)
    {
        for(int i = 0; i < arr.length; i++) // start from index 0
        {
            if(arr[i] > '0' && arr[i] <= '9') // where it find out return the index
                return i;
        }

        return (arr.length - 1); // when it cannot find return to last index
    }


    //////////////////////////////////////////////////////////////////

    /**
     * the method will print character array will colen by creating a new array and copy the old array and add the colen
     * then print out the new array
     * @param arr the character array need to be printed
     * @param start positive integer that presenting how many space need to print before print the actual array
     */
    public static void printArray(char[] arr, int start) // print char type
    {
        int numOfcontent = arr.length - locatNonIncludeZero(arr); // get how many number character contain ni array
        int needColen = determinColen(arr); // find out how many colen need
        printSpace(START_SPACE); // to make the output look nicer
        printSpace(start); // print space for matching the format
        char[] print = new char[numOfcontent + needColen]; // create new array for copying old array with colens
        copyArray(print,arr); // copy old array

        for(int i = 0; i < print.length; i++) // print new array
        {
            System.out.print(print[i]);
        }
    }


    /////////////////////////////////////////////////////////////////////////////////

    /**
     * the method contain two array which is the new array and old array
     *  copy the old array to new array and new array will add colens into itself
     * @param target the character array for new array
     * @param num the charcter array for old array
     */
    public static void copyArray(char[] target,char[] num)
    {
        int different = target.length - num.length; // get the length of different
        int colenIndex = target.length - 1 - Colen; // the index the contain colen

        for(int i = target.length - 1; i >= 0; i--) // start from the end for both array
        {
            if(i == colenIndex && colenIndex != 0) // if i reach the the index contain cloen then add colen
            {
                target[i] = ','; // add colen
                colenIndex = colenIndex - Colen - 1; // find the next index that contain colen
                different--; // differnt between will be shorten
            }
            else
            {
                target[i] = num[i - different]; // copy old array
            }
        }

    }



}
