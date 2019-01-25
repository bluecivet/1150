/**
 * File:        Lab11.java
 * Author:      Zhilong Gan
 * ID:          100331942
 * Date:        2018.11.25
 * class:       CPSC 1150-02
 * instructor:  Instructor: H. Darbandi
 * title        matching word
 * Compiler:    java JDK 10.2
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.PrintWriter;

/**
 * the program will let the user input the names of files of telephone number and word list
 * and find the all the match words for the telephone number exclude 604
 * also find the find all the word that match first 3 number and last 4 number
 */
public class Lab11
{

    public static void main(String[] args) throws FileNotFoundException
    {
        intro();     // introduction

        // input telephone file
        System.out.println("please input file of the telephone text");
        Scanner input = new Scanner(System.in);
        String telephone = input.next();//"telephone.txt";
        File telephoneFile = new File(telephone);
        checkExist(telephoneFile);

        // input word list file
        System.out.println("please input file of the words list");
        String wordFilePath = input.next();//"word_list.txt";
        File wordFile = new File(wordFilePath);
        checkExist(wordFile);

        //  check vaild word list
        System.out.println("loading the word list");
        String[] wordList = getWord(wordFile);
        if(wordList.length < 1)
        {
            System.out.println("there are no vaild word in the word list");
            return;
        }

        // sort the word list in the memory
        System.out.println("sorting the word list");
        sort(wordList,0,wordList.length - 1);

        // print out the result
        System.out.println("writing out the result");
        printWord(telephoneFile,wordList);

        System.out.println("finish");
    }


    //-----------------------------------------------------------

    /**
     * to tell the user how the program work
     */
    public static void intro()
    {
        System.out.println("user will need to have a file that contain telephone start will 604");
        System.out.println("the user need to have a file with a list of word");
        System.out.println("the list of words should all be upper case");
        System.out.println("the program will find all the  word to match the number exclude 604 ");
        System.out.println("also will give the first part and second part of word which is 3 and 4");
    }


    /////////////////////////////////////////////////////////////////////////////

    /**
     * load the word from the word list into memory that is vaild for the telephone
     * store the words into string array and also find the number represent the word
     * @param wordList the file that contain words
     * @return the string array the contain words
     * @throws FileNotFoundException
     */

    public static String[] getWord(File wordList) throws FileNotFoundException
    {
        Scanner scan = new Scanner(wordList);
        int length = findLength(wordList);     //  find the number of word in file
        String[] words = new String[length];
        int i = 0;  // from beginning of the file
        String word;     // a temperate string to check is vaild or not
        while(scan.hasNext())
        {
            word = scan.next();     // load the word to check
            if(isVaild(word))   // checking
            {
                words[i] = number(word) + " " + word;    // load into array
                i++;
            }
        }
        return words;
    }


    //////////////////////////////////////////////////////////////

    /**
     * find how many vaild words in the word list file
     * @param file the word list file
     * @return an integer that represent how many word are vaild
     * @throws FileNotFoundException
     */

    public static int findLength(File file) throws FileNotFoundException
    {
        Scanner scan = new Scanner(file);
        int count = 0;   // set counter
        String word ;    // a temperate string to check is vaild or not
        while(scan.hasNext())
        {
            word = scan.next();   // load word
            if(isVaild(word))    // check is vaild or not
            count++;        // increase count
        }
        return count;
    }


    ///////////////////////////////////////////////////////////////

    /**
     * check the word is vaild for the telephone or not
     * the word that is vaild, it's length should not greater than 7
     * it should not contain any character other than English letter
     * @param word the word need to check
     * @return true for is vaild word  false for not vaild word
     */

    public static boolean isVaild(String word)
    {
        if(word.length() > 7)    // word length that is greater than 7
            return false;
        for(int i = 0; i < word.length(); i++)   // check each letter
        {
            if(word.charAt(i) < 'A' || word.charAt(i) > 'Z')  // letter that is not English letter
                return false;
        }
        return true;
    }


    /////////////////////////////////////////////////////////////////

    /**
     * check the file exist or not
     * @param file the file
     */

    public static void checkExist(File file)
    {
        if(!file.exists())
        {
            System.out.println("the file is not exist");
            System.exit(0);
        }
    }

    ///////////////////////////////////////////////////////////////

    /**
     * change the each letter in the word to a number
     * @param word a collection of characters
     * @return a collection of characters which changed to number character
     */

    public static String number(String word)
    {
        char[] letters = word.toCharArray();
        for(int i = 0; i < letters.length; i++)
        {
            letters[i] = letterToNum(letters[i]);
        }
        return String.valueOf(letters);
    }


    ///////////////////////////////////////////////////////////////

    /**
     * to change the word to number
     * the English letters refer the the number on the telephone button
     * @param letter the letter the need to change
     * @return the number character
     */

    public static char letterToNum(char letter)
    {
        // this is the fastest way to change because when the user have a big list of words
        // any searching will slow down the speed
        if(letter >= 'A' && letter <= 'C')
            return '2';
        else if(letter >= 'D' && letter <= 'F')
            return '3';
        else if(letter >= 'G' && letter <= 'I')
            return '4';
        else if(letter >= 'J' && letter <= 'L')
            return '5';
        else if(letter >= 'M' && letter <= 'O')
            return '6';
        else if(letter >= 'P' && letter <= 'S')
            return '7';
        else if(letter >= 'T' && letter <= 'V')
            return '8';
        else if(letter >= 'W' && letter <= 'Z')
            return '9';
        else
            return 0;
    }

    /////////////////////////////////////////////////////////////////////////////









   //---------------------------------------------------------------------------

    /**
     * the method will sort the words
     * after load the list of words into memory
     * using quick sort only sort the number part exclude the word part
     * for example "2 A" and "22 A" it will only compare the number part of the string
     * @param i the integer that represent the start index
     * @param j the integer that represent the end index
     */

    public static void sort(String[] wordList, int i, int j)
    {
        int low = i + 1;  // start from left find the index of number that is less than privax
        int high = j;     // start from the right find the index of number that is greater than privax
        String temp;     // using for exchange
        if(j - i < 1 || i >= j)   // if there are only 1 thing left then end the program
            return;
        // the below will using substring because it will be faster
        int spaceIndex = wordList[i].indexOf(" ");   // find where the index of space contain in the string
        String privax = wordList[i].substring(0,spaceIndex);   // only store the number part
        while(low != high)   // if low do not meet high
        {
            while(compare(privax,wordList[low]) >= 0 && low != high)  // when low is less than privax
                low++;     // move to right
            while(compare(privax,wordList[high]) <= 0 && low != high)  // when high is greater than privax
                high--;      // move to left
            if(compare(privax,wordList[low]) < 0 && compare(privax,wordList[high]) > 0)   // when both low and high stop
            {
                // exchange the string
                temp = wordList[low];
                wordList[low] = wordList[high];
                wordList[high] = temp;
            }
        }
        if(compare(privax,wordList[low]) < 0)   // if the meeting place is great than privax
        {
            low -= 1;   // that meeting place move 1 to left
        }

            temp = wordList[i];      // move the privax point to the right place
            wordList[i] = wordList[low];
            wordList[low] = temp;

        sort(wordList, i, low - 1);   // do the front part
        sort(wordList, low + 1, j);   // do the back part
    }


    ///////////////////////////////////////////////////////////////////////

    /**
     * to compare the only the number pare of the string
     * return the different between target and the word
     * @param target  the string that want to compare but only contain number
     * @param word  the string that want co compare
     * @return the different between target and the word
     */

    public static int compare(String target,String word)
    {
        int spaceIndex = word.indexOf(" ");   // find the index of space
        String wordNum = word.substring(0,spaceIndex);   // get rib of non number part
        int different;
        for(int i = 0; i < target.length();i++)
        {
            if(i >= wordNum.length())   // when the number between target and word is same but target has longer length
                return 1;
            different = target.charAt(i) - wordNum.charAt(i);
            if(different != 0)   // when they are same number go to next place
                return different;
        }
        if(wordNum.length() > target.length())  // if the number is same but number length is longer
            return -1;
        return 0;
    }

    //////////////////////////////////////////////////////////////////////////

    /**
     * the method is find the telephone number in the word list
     * using binary search
     * @param target a string that represent the number that want to find
     * @param list string array represent the word list in the memory
     * @return an integer that present the index in the word list
     */

    public static int search(String target, String[] list)
    {
        int max = list.length - 1;  // the max range the right boundary
        int min = 0;               // the min range the left boundary
        int mid = (max + min) / 2;    // the middle between the max range and min range
        int spaceIndex = list[mid].indexOf(" ");
        String listMid = list[mid].substring(0,spaceIndex);   // get rid of the non number part of middle
        String listMax, listMin;
        while(!target.equals(listMid))   // while not find the target in the list
        {
            max = listMid.compareTo(target) > 0 ? mid - 1 : max;  //if target on the left max range become mid - 1
            if(max < 0)
                return -1;
            min = listMid.compareTo(target) < 0 ? mid + 1 : min;   // if target on the right min range become mid + 1
            if(min >= list.length)
                return -1;
            mid = (max + min) / 2;         // find the next middle
            // get rid of non number part
            spaceIndex = list[mid].indexOf(" ");
            listMid = list[mid].substring(0,spaceIndex);
            spaceIndex = list[max].indexOf(" ");
            listMax = list[max].substring(0,spaceIndex);
            spaceIndex = list[min].indexOf(" ");
            listMin = list[min].substring(0,spaceIndex);
            //if found of not found
            if(listMax.equals(target))
                return max;
            if(listMin.equals(target))
                return min;
            if(listMax.compareTo(target) < 0 || listMin.compareTo(target) > 0)
                return -1;
        }
        return mid;
    }


    ///////////////////////////////////////////////////////////////////////
    //-------------------------------------------------------------------------









    //------------------------------------------------------------------------

    /**
     * write the word that fit the telephone number exclude 604
     * and separate the the telephone number into 2 part
     * first part find all the word that is fit the first 3 number
     * second part find all the word that is fit is the last 4 number
     * write them to an output file
     * @param telephone the file that contain the telephone number
     * @param wordlist ta string array that represent he word list
     * @throws FileNotFoundException
     */

    public static void printWord(File telephone,String[] wordlist) throws FileNotFoundException
    {
        Scanner scan = new Scanner(telephone);    // create scanner to scan telephone number
        PrintWriter writer = new PrintWriter(new File("result.txt"));  // target output file
        String number;
        while(scan.hasNextLine())  // each telephone number write the format
        {
            number = scan.nextLine();
            number = number.replace("604"," ");
            number = number.trim();
            writer.println("TEL: " + number);
            partA(number,wordlist,writer);
            partB(number,wordlist,writer);
            writer.println(findphrase(number,wordlist));
            writer.println();
            writer.println("--------------------------");
        }
        writer.close();
    }


    //////////////////////////////////////////////////////////////////////

    /**
     * find the word that is fit the telephone number
     * @param number a string that represent the telephone number
     * @param wordlist the string array that need to match
     * @return a string of found word if not found than return empty string
     */

    public static String findphrase(String number, String[] wordlist)
    {
        String twoword = twoWord(number, wordlist);
         if(twoword != null)
             return twoword;
         String threeword = threeWord(number,wordlist);
         if(threeword != null)
             return threeword;
         return " ";
    }


    //////////////////////////////////////////////////////////////////


    public static String twoWord(String number, String[] wordList)
    {
        String a,b;
        int index;
        // two word
        for(int i = 1; i < number.length() - 2; i++)
        {
            index = search(number.substring(0,i),wordList);
            if(index >= 0)
            {
                a = wordList[index].split(" ")[1];
                index = search(number.substring(i  + 1),wordList);
                if(index >= 0)
                {
                    b = wordList[index].split(" ")[1];
                    return a + " " + b;
                }
            }
        }
        return null;
    }


    ///////////////////////////////////////////////////////////////////


    public static String threeWord(String number, String[] wordList)
    {
        String a,b,c;
        int index;
        for(int i = 1; i < number.length() - 1; i++)
        {
            for(int j = i + 1; j < number.length(); j++)
            {
                index = search(number.substring(0,i - 1),wordList);
                if(index >= 0)
                {
                    a = wordList[index].split(" ")[1];
                    index = search(number.substring(i,j - 1),wordList);
                    if(index >= 0)
                    {
                        b = wordList[index].split(" ")[1];
                        index = search(number.substring(j),wordList);
                        if(index >= 0)
                        {
                            c = wordList[index].split(" ")[1];
                            return a + " " + b + " " + c;
                        }
                    }
                }
            }
        }
        return null;
    }


    //////////////////////////////////////////////////////////////////



    /**
     * print format for part A
     * @param number String that represent telephone number
     * @param wordlist the list of word
     */

    public static void partA(String number,String[] wordlist,PrintWriter writer)
    {
        int index = search(number,wordlist);
        if(index >= 0)  // if found the match word print them out
        {
            writer.println(number + ": " + wordlist[index].split(" ")[1]);  // split into 2d array
            printFront(number,index,wordlist,writer);
            printBack(number,index,wordlist,writer);
        }

    }


    ////////////////////////////////////////////////////////////////////

    /**
     * find the first and second part of the telephone number with is first 3 number and last 4 number
     * print the format in the file
     * @param number  a string represent the telephone number
     * @param wordlist the string array that represent the list of word
     * @param writer the writer
     */

    public static void partB(String number, String[] wordlist,PrintWriter writer)
    {
        String teleNum = number.substring(0,3);  // the first 3 number
        int index = search(teleNum,wordlist);
        if(index >= 0)  // print all the match word
        {
            writer.println(teleNum + ": " +wordlist[index].split(" ")[1]);   // split into 2d array
            printFront(teleNum,index,wordlist,writer);
            printBack(teleNum,index,wordlist,writer);
        }

        teleNum = number.substring(3,7);   // the last 4 number
        index = search(teleNum,wordlist);
        if(index >= 0)  // print all the match word
        {
            writer.println(teleNum + ": " +wordlist[index].split(" ")[1]);   // split into 2d array
            printFront(teleNum,index,wordlist,writer);
            printBack(teleNum,index,wordlist,writer);
        }

    }


    //////////////////////////////////////////////////////////////////

    /**
     * print all the word has the same number before index
     * @param teleNum a string represent the telephone number
     * @param index an integer represent the first search index
     * @param wordlist the string array that represent list of word
     * @param writer the writer
     */

    public static void printFront(String teleNum, int index,String[] wordlist, PrintWriter writer)
    {
        for(int i = index - 1; i >= 0; i--)  // start from middle go to left
        {
            if(compare(teleNum,wordlist[i]) != 0)
              return;
            writer.println(teleNum + ": " + wordlist[i].split(" ")[1]);    // split into 2d array
        }
    }


    ///////////////////////////////////////////////////////////////

    /**
     * print all the match word after the index
     * @param teleNum a string represent the target number
     * @param index an integer represent the first search index
     * @param wordlist the string array that represent list of word
     * @param writer the writer
     */

    public static void printBack(String teleNum, int index,String[] wordlist, PrintWriter writer)
    {
        for(int i = index + 1; i < wordlist.length; i++)  // start from middle go to right
        {
            if(compare(teleNum,wordlist[i]) != 0)
                break;
            writer.println(teleNum + ": " + wordlist[i].split(" ")[1]);     // split into 2d array
        }
    }
}
