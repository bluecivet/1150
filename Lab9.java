/**
 * File:        Lab9.java
 * Author:      Zhilong Gan
 * ID:          100331942
 * Date:        2018.11.09
 * class:       CPSC 1150-02
 * instructor:  Instructor: H. Darbandi
 * title        represent number or date in words
 * Compiler:    java JDK 10.2
 */

/**
 * this program will show words for the number which the user input
 * if user inputs contain a slash '/' the program will recognize this is a date  show the words for date
 */
public class Lab9
{

    public static void main(String[] args)
    {
        String[] digit = new String[10];  getDigit(digit);
        String[] tenth = new String[10];  getTenth(tenth);
        String[] ten = new String [10];   getTen(ten);
        String[] place = new String[6];   getPlace(place);
        String[] month = new String[13];  getMonth(month);
        // the above is the vocabulary that may need store as a sting

        String message = input(); // let user input number
        String output;

        if(isDate(message)) // check is input is date format
            output = date(message, month); // get date format
        else // not a date format than is a number format
            output =  number(message, digit, ten, tenth, place);  // get number format

        System.out.println(output);   // output the format

    }

    ////////////////////////////////////////////////////////////////////////////////



     //             input methods
    //-----------------------------------------------------------------------------------------

    /**
     * let user input number in the computer and check is the legal input if is not than input again
     * @return a string that user input
     */

    public static String input()
    {
        java.util.Scanner input = new java.util.Scanner(System.in);
        String message;
        System.out.println("please input a number or date");
        do
        {
            message = input.next();  // user input
        }
        while(!isValidInput(message));  // check legal input

        return message;
    }


    //////////////////////////////////////////////////////////////

    /**
     * the method will check the user's input is valid or not by checking length and the content
     * @param message the message that need to check
     * @return true for valid input false for not valid input
     */

    public static boolean isValidInput(String message)
    {
         if(!legalLength(message))
             return false;
        // after checking the legal input length
         if(!legalInput(message))
             return false;
        // after checking the legal character input
        // if the program run to here it mean the input is valid
        return true;
    }


    ///////////////////////////////////////////////////////////////////////////

    /**
     * the method will check the length for the message the length should not greater than 25
     * @param message the string that need to check
     * @return true for legal length false for illegal length
     */

    public static boolean legalLength(String message)
    {
        if(message.length() > 15) // check it is grater than 15 or not
        {
            System.out.println("message input excess 15");
            System.out.println("please input again");
            return false;
        }
        else     // message not greater than 15
            return true;
    }


    ///////////////////////////////////////////////////////////////////////////

    /**
     * the method will check if the content of the message has a character that is not in the range of '0' to '9' and not '/'
     * @param message the message that need to be checked
     * @return true for legal input false for illegal input
     */

    public static boolean legalInput(String message)
    {
        for(int i = 0; i < message.length(); i++)// check form beginning to end
        {
            if((message.charAt(i) > '9' || message.charAt(i) < '0') && message.charAt(i) != '/') // checking range
            {
                System.out.println("invalid input");
                System.out.println("please input again");
                return false;
            }
        }
        //the program run to here mean it finish checking all the content
        return true;
    }
    ///////////////////////////////////////////////////////////////////////////
    //           end input methods






    //                      output for date
    //-------------------------------------------------------------------------

    /**
     * check is the format that the user input is a date
     * @param message the string that user input
     * @return ture for the format is date  false for format not a date
     */
    public static boolean isDate(String message)
    {
        if(message.indexOf('/') == -1) // if it a date  there should be a slash
            return false;
        else
            return true;
    }


    /////////////////////////////////////////////////////////////

    /**
     * show the proper format output for date that  user input
     * @param message string that user input
     * @param month string array that contain word for month
     * @return the entire proper output string
     */

    public static String date(String message, String[] month)
    {
        String[] date = message.split("/");
        checkForm(date);
        int monthNum = Integer.parseInt(date[1]);
        return month[monthNum] + " " + Integer.parseInt(date[0]) + " ," + Integer.parseInt(date[2]);
    }


    ////////////////////////////////////////////////////////////

    /**
     * this method will check the format for dat is wrong or not is the formant is wrong the program will be closed
     * @param message string array after split by /
     */

    public static void checkForm(String[] message)
    {
        checkLength(message);// check the length of the message to ensure format is right
        // after checking the length
        checkEmptyInput(message); // check if there is input between slash
        checkMonth(message);
        //after checking check for month
        checkDay(message);// check for day the date should not excess 31 and equal to 0
        //after checking date and month check for year
         checkYear(message);
    }


    //////////////////////////////////////////////////////////////////


    public static void checkEmptyInput(String[] message)
    {
        for(int i = 0; i < message.length; i++)
        {
            if(message[i].length() < 1)
            {
                System.out.println("input format wrong");
                System.exit(0);
            }
        }
    }


    ////////////////////////////////////////////////////////////////

    /**
     * check the length of the string array to ensure the format of the input for date is right
     * @param message the string array after split by /
     */
    public static void checkLength(String[] message)
    {
        if(message.length != 3)  // the form for date the array should always has length of 3
        {
            System.out.println("the characters input is legal");
            System.out.println("the form for showing for date is wrong");
            System.exit(0);
        }
    }


    ///////////////////////////////////////////////////////////////////////////

    /**
     * to ensure the format for input date is right by checking day
     * @param message the string array after split by /
     */

    public static void checkDay(String[] message)
    {
        int day = Integer.parseInt(message[0]);
        int month = Integer.parseInt(message[1]);
        byte[] dayInMonth = {0,31,29,31,30,31,30,31,31,30,31,30,31};
        if(day > dayInMonth[month] || day == 0) // the day should not excess 32 or equal 0
        {
            System.out.println("the range for day is wrong");
            System.exit(0);
        }
    }


    //////////////////////////////////////////////////////////////////////////
    /**
     * to ensure the format for input date is right by checking month
     * @param message the string array after split by /
     */

    public static void checkMonth(String[] message)
    {
        // month should not larger than 12 and equal to 0
        int month = Integer.parseInt(message[1]);
        if(month > 12 || month == 0)
        {
            System.out.println("month should not be larger than 12 or equal to 0");
            System.exit(0);
        }
    }


    ///////////////////////////////////////////////////////////////////////////
    /**
     * to ensure the format for input date is right by checking year
     * @param message the string array after split by /
     */

    public static void checkYear(String[] message)
    {
        // year should not equal to 0
        int year = Integer.parseInt(message[2]);
        if(year == 0)
        {
            System.out.println("year should not be 0");
            System.exit(0);
        }
    }
    ////////////////////////////////////////////////////////////////////////////////
    //                     end output for date









    //                        output for number
    //---------------------------------------------------------------------------

    /**
     * the method will make build the format for number which contain the original number add colen and the words to describe number
     * @param message the string that user input
     * @param digit string array that represent vocabulary for one digit
     * @param ten string array that represent vocabulary for digit before 20 and after 10
     * @param tenth string array that represent vocabulary for tenth
     * @param place string array that represent vocabulary for n * 10 where n > hundred
     * @return the format that representing the number
     */
    public static String number(String message, String[] digit, String[] ten, String[] tenth, String[] place)
    {
        char[] mgsArray = new char[numOfColen(message) + message.length()];// create array for contain colen
        addColen(mgsArray, message); // add colen and copy the number
        String[] newMgs = String.valueOf(mgsArray).split(",");// split the number by ,
        String word = word(newMgs, digit,ten,tenth,place); // find the words for the number
        return String.valueOf(mgsArray) + " " + word;  // original number with colen + word describe

    }


    ////////////////////////////////////////////////////////////////////////////////////

    /**
     * the method determine how many colen for the number need
     * @param message the string that need to determine
     * @return the number of colen needed
     */

    public static int numOfColen(String message)
    {
        int count = 1;
        for(int i = 0; i < message.length(); i++) //start from beginning
        {
            if(i == count * 3)// find the index the need
                count++;
        }
        return count - 1; // because count start from 1
    }

    //////////////////////////////////////////////////////////////////////////////////////

    /**
     * the method will copy the message to to new array and add colen
     * @param mgsArr that char array that represent target
     * @param message // the string that need to be copied
     */

    public static void addColen(char[] mgsArr, String message)
    {
        int different = mgsArr.length - message.length(); // find the different between length
        int colenIndex = mgsArr.length - 4; // because start form the end between next place it has 4 element and itself so 4

        for(int i = mgsArr.length - 1; i >= 0; i--) // start from end
        {
            if(i == colenIndex && i != 0) // the i reach to the index that need to add colec
            {
                mgsArr[i] = ',';  // add colen
                colenIndex -= 4;  // find the index that need to add colen
                different--; // decrease different
            }
            else
                mgsArr[i] = message.charAt(i - different); // copy string
        }
    }


    /////////////////////////////////////////////////////////////////////////////////

    /**
     * the program will find the word to describe the number
     * the number should split by , already and store in a string array
     * example: 123,465 store in 123 and 456
     * between the colen determine which place need to user such as: thousand, milloin, billoin
     * the above example will use thousand
     * @param message the string array after split by colen
     * @param digit string array that represent vocabulary for one digit
     * @param ten string array that represent vocabulary for digit before 20 and after 10
     * @param tenth string array that represent vocabulary for tenth
     * @param place string array that represent vocabulary for n * 10 where n > hundred
     * @return string that contain words to describe a number
     */

    public static String word(String[] message , String[] digit, String[] ten,String[] tenth, String[] place)
    {
        String answer = "";
        int name = message.length; // the place the store in the place
        boolean[] inputAllZero = new boolean[name]; // to check is input only have 0
        initicialAllZero(inputAllZero);
        for(int i = 0; i < message.length; i++)
        {
           // System.out.println(isInputAllZero(inputAllZero));
            if(! isAllZero(message[i])) // if is not the string is all 0
            {
                inputAllZero[i] = false;
               // System.out.println(isInputAllZero(inputAllZero));
                if (i != 0 && !isInputAllZero(inputAllZero,i))// if the string is the first one
                answer += ", and "; // add

                answer += numWord(message[i], digit, ten, tenth) + place[name - i]; // find the word to describe number under hundred
            }
            else
                inputAllZero[i] = true;
        }

        return (isInputAllZero(inputAllZero,name) ? "zero" : answer); // if input is only 0 than output should be zer0
    }


    ////////////////////////////////////////////////////////////////////////////////////

    /**
     * the method will find the words to describe a number under a thousand
     * because the entire number split by colen already so each one should be under a thousand
     * @param letter the string that contain number under a thousand
     * @param digit the vocabulary that need for a digit
     * @param ten the vocabulary that need for above 10 under 20
     * @param tenth the vocabulary that need for n * 10 n > hundred
     * @return the word to describe a number under a thousand
     */

    public static String numWord(String letter, String[] digit, String[] ten,String[] tenth)
    {
        int number = Integer.parseInt(letter); // for store number in a array
        int[] word = new int[3]; // for storing each digit that represent different place
        byte digitIndex = 0; // digit index
        byte tenthIndex = 1; // index represent for ten
        byte hundredIndex = 2;  // index present for hundred
        for(int i = 0; i < word.length; i++) // start from beginning
        {
            word[i] = number % 10; // the digit place will be the first place
            number /= 10;
        }
        String hundred = word[hundredIndex] == 0 ? "" : digit[word[hundredIndex]] + " " +"hundred" + " "; // words for hundred place
        String tenWord = tenWord(word, tenthIndex,digitIndex,ten,tenth); // words for tenth place
        String digitWord = digitWord(word,hundredIndex,tenthIndex,digitIndex, digit); // words for digit place
        return hundred + tenWord + digitWord; // entire word
    }


    //////////////////////////////////////////////////////////////////////////////////////

    /**
     * check a string that is it contain number other than 0
     * @param message a string that need to be checked
     * @return if there is a number is not 0 than return true otherwise return false
     */

    public static boolean isAllZero(String message)
    {
        for(int i = 0; i < message.length(); i++)
        {
            if(message.charAt(i) != '0') // check every digit
            {
                return false;
            }
        }
        // when the program run to here that mean is check all the digit already which mean is all zero
        return true;
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * the method wll find the format for output for tenth place
     * @param word a integer array that each digit contain
     * @param tenthIndex a integer the represent the tenth place index
     * @param digitIndex  integer the represent the digit place index
     * @param ten the vocabulary for number above 10 under 20
     * @param tenth the vocabulary for tenth place
     * @return a string that present how to describe a ten place word
     */

    public static String tenWord(int[]word, byte tenthIndex,byte digitIndex,String[] ten,String[] tenth)
    {
        if(word[tenthIndex] == 0) // check a tenth place exit
            return "";
        else
            return word[tenthIndex] == 1 ? ten[word[digitIndex]] + " " : tenth[word[tenthIndex]] + " ";
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * the method wll find the format for output for digit place
     * @param word a integer array that each digit contain
     * @param hundred a integer represent hundred place index
     * @param tenthIndex a integer the represent the tenth place index
     * @param digitIndex a integer the represent the digit place index
     * @param digit the vocabulary for digit place
     * @return
     */

    public static String digitWord(int[] word,byte hundred,byte tenthIndex,byte digitIndex, String[]digit)
    {
        if(word[tenthIndex] == 0 && word[digitIndex] != 0 && word[hundred] != 0) // check if need a "and"
              return "and " + digit[word[digitIndex]] + " ";
        else if(word[digitIndex] == 0) // check if digit place exit
              return "";
        else
             return word[tenthIndex] == 1 ? "" : digit[word[digitIndex]] + " ";
    }


    //////////////////////////////////////////////////////////////////////////////////////////

    /**
     * to assume all the input is zero
     * @param arr the boolean array
     */

    public static void initicialAllZero(boolean[] arr)
    {
        for(int i = 0;i < arr.length; i++)
        {
            arr[i] = true;
        }
    }
    /////////////////////////////////////////////////////////////////////////////////////////

    /**
     * check is input is only contain 0
     * @param arr the boolean array need to check
     * @param index a positive integer
     * @return true for only contain 0 false for not contain 0
     */

    public static boolean isInputAllZero(boolean[] arr,int index)
    {
        for(int i = 0; i < index; i++)
        {
            if(!arr[i])
                return false;
        }
        return true;
    }

    //////////////////////////////////////////////////////////////////////////////////////////
    //                        end number method






    //         word that need
    //------------------------------------------------------------------------------------------

    /**
     * set vocabulary for digit place
     * @param arr a string array the place to contain the vocabulary
     */
    public static void getDigit(String[] arr)
    {
        arr[0] = "";
        arr[1] = "one";
        arr[2] = "two";
        arr[3] = "three";
        arr[4] = "four";
        arr[5] = "five";
        arr[6] = "six";
        arr[7] = "seven";
        arr[8] = "eight";
        arr[9] = "nine";
    }


    ///////////////////////////////////////////////////////////////////////
    /**
     * set vocabulary for tenth place
     * @param arr a string array the place to contain the vocabulary
     */

    public static void getTenth(String[] arr)
    {
        arr[0] = "";
        arr[1] = "ten";
        arr[2] = "twenty";
        arr[3] = "thirty";
        arr[4] = "forty";
        arr[5] = "fifty";
        arr[6] = "sixty";
        arr[7] = "seventy";
        arr[8] = "eighty";
        arr[9] = "ninety";
    }


    //////////////////////////////////////////////////////////////////////
    /**
     * set vocabulary for the place over hundred
     * @param arr a string array the place to contain the vocabulary
     */

    public static void getPlace(String[] arr)
    {
        arr[0] = "";
        arr[1] = "";
        arr[2] = "thousand";
        arr[3] = "million";
        arr[4] = "billion";
        arr[5] = "trillion";
    }


    /////////////////////////////////////////////////////////////////////
    /**
     * set vocabulary for month
     * @param arr a string array the place to contain the vocabulary
     */

    public static void getMonth(String arr[])
    {
        arr[0] = "";
        arr[1] = "January";
        arr[2] = "February";
        arr[3] = "March";
        arr[4] = "April";
        arr[5] = "May";
        arr[6] = "June";
        arr[7] = "July";
        arr[8] = "August";
        arr[9] = "September";
        arr[10] = "October";
        arr[11] = "November";
        arr[12] = "December";
    }


    ////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * set vocabulary for digit above 10 and under 20
     * @param arr a string array the place to contain the vocabulary
     */

    public static void getTen(String[] arr)
    {
        arr[0] = "ten";
        arr[1] = "eleven";
        arr[2] = "twelve";
        arr[3] = "thirteen";
        arr[4] = "fourteen";
        arr[5] = "fifteen";
        arr[6] = "sixteen";
        arr[7] = "seventeen";
        arr[8] = "eighteen";
        arr[9] = "nineteen";
    }
    ////////////////////////////////////////////////////////////////////////////////////////////

}
