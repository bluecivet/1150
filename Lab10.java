import java.util.Scanner;
import java.io.*;

public class Lab10 {
    static byte head = 0;  // representing the the first letter in word
    static byte tail = 1;  // representing the the last letter in word

    public static void main(String[] args) throws FileNotFoundException
    {
        introduction();

        String fileName = getInput();        // input the name of the file

        File file = new File(fileName);      // create file object for input

        checkExist(file);       // check if the file exist

        String[] words = loadword(file);        // string array for storing words in the file

        sort(words);         // storing the words in order

        File outputFile = new File("output.txt");            // create file for result

        writeFile(outputFile, words);            // write result into the output file

        System.out.println("Finish!");
    }

    //                          enter
    //---------------------------------------------------------------------------------------

    /**
     * the method will prompt the user to input words which should be a file name
     *
     * @return a string that represent the name of the file
     */

    public static String getInput() {
        Scanner input = new Scanner(System.in);
        System.out.println("please input a file name with prefix");
        System.out.println("the files should be pleased in the same directory");
        String name = input.next();
        return name;
    }

    /////////////////////////////////////////////////////////////////

    /**
     * tell the user how the program work
     */

    public static void introduction()
    {
        System.out.println("this program will scanner the content in a file");
        System.out.println("store words into a new file and count how many other same words appear");
        System.out.println("all the output for all the word will be all lower case and in order");
        System.out.println();
    }
    ////////////////////////////////////////////////////////////////


    /**
     * the method check if the file exist ro not with the given file name
     * if the file not exist the program will exit
     *
     * @param file the file need the check
     */

    public static void checkExist(File file) {
        if (!file.exists())  // check if the file is not exit than exit the program
        {
            System.out.println(file.getPath() + " is not found ");
            System.exit(0);       // exit the program
        }
    }
    /////////////////////////////////////////////////////////////////


    //                       storing and sorting
    //--------------------------------------------------------------------------------

    /**
     * the method will load words from the file to the memory to form a string array
     * when loading the word, all the punctuation and digit will be removed
     * the words that were loaded will only contain characters that represent English letter and '-'
     * the character in the word are all lower case
     *
     * @param file the File object that need to be loaded
     * @return string array that contain words in the file
     * @throws FileNotFoundException
     */

    public static String[] loadword(File file) throws FileNotFoundException {
        System.out.println("loading file");
        Scanner scan = new Scanner(file);  // create a scanner to scanner the message in the given file
        String[] message = new String[totalWord(file)];  // create array to store words
        String word;
        for (int i = 0; i < message.length; ) // start scan from beginning
        {
            word = scan.next().toLowerCase();  // change all letters in the word into lower case
            if (isWord(word))    // check the content is a word or not if is store it if not skip it
            {
                message[i] = removeNonWord(word);   //  remove the character that is not English letter and not '-'
                i++;
            }
        }
        return message;
    }


    ///////////////////////////////////////////////////////////////

    /**
     * find the total number of words contain in the file
     *
     * @param file the File object
     * @return a positive integer that show the number of total words contain in the file
     * @throws FileNotFoundException
     */

    public static int totalWord(File file) throws FileNotFoundException {
        Scanner scan = new Scanner(file);   // scanner for scanning the file
        int count = 0;
        while (scan.hasNext())   // scan until end
        {
            String word = scan.next();
            if (isWord(word))   // is the content is a word than increase count by 1
                count++;
        }
        return count;
    }

    ////////////////////////////////////////////////////////////////

    /**
     * to determine a string is a word or not
     * a word from its first letter start and the last letter end should not contain other non-letter character
     * other than '-' and it should not continuous appearing
     *
     * @param word the string need to determine
     * @return true for is a word false for not a word
     */

    public static boolean isWord(String word) {
        String lower = word.toLowerCase();
        int start = getLetterIndex(lower, head);    // to find the index for the letter appear
        int end = getLetterIndex(lower, tail);     // to find the last index for the letter appear

        if (start == -1) // if does not find a letter in the string
            return false;

        while (start < end) // check the middle
        {
            if (lower.charAt(start) == '-' && lower.charAt(start + 1) == '-')// check continuous
                return false;
            if ((lower.charAt(start) < 'a' || lower.charAt(start) > 'z') && lower.charAt(start) != '-') // check the character
                return false;
            start++;
        }

        return true;
    }


    /////////////////////////////////////////////////////////////////

    /**
     * the method will remove any character that is not english letter and not '-' in a string
     * the string should be define as a word first (see method: isWord() to see how a word is define)
     *
     * @param message a string, collection of character
     * @return a string, collection of character that remove non-English character
     */

    public static String removeNonWord(String message)
    {
        int start = getLetterIndex(message, head);     //get the fist index that contain English letter
        int end = getLetterIndex(message, tail);       //get the last index that contain English letter
        if (start == end)   // if only contain one letter
            return String.valueOf(message.charAt(start));  // return the letter
        return message.substring(start, end + 1);   // return the content that contain English letter
    }


    /////////////////////////////////////////////////////////////////

    /**
     * the method will find the index of a string that first contain a english letter
     * depending on starting from the head or tail
     * head for from the beginning
     * tail for start from the end
     *
     * @param word      a string a collection of character should be define as a word first (see method: isWord() to see how a word is define)
     * @param startFrom an integer indicate head or tail
     * @return an integer that representing the index
     */

    public static int getLetterIndex(String word, byte startFrom) {
        int i, j;
        if (startFrom == head)  // start checking from beginning
        {
            for (i = 0; i < word.length(); i++) {
                if (word.charAt(i) >= 'a' && word.charAt(i) <= 'z')
                    return i;
            }
            return -1;     // cannot found any letter in the string
        } else if (startFrom == tail)    // start check from the ed
        {
            for (j = word.length() - 1; j >= 0; j--) {
                if (word.charAt(j) >= 'a' && word.charAt(j) <= 'z')
                    return j;
            }
            return -1;      // cannot found any letter in the string
        }
        return 0;
    }

    ////////////////////////////////////////////////////////////////

    /**
     * the method will sort the array in order by using selection sort
     *
     * @param arr the array that need to sort
     */

    public static void sort(String[] arr) {
        System.out.println("sorting word");
        int min;   // the index of min
        String temp;
        for (int i = 0; i < arr.length - 1; i++)  // start form beginning to the second last element
        {
            min = findMin(arr, i); // find the minimum content index of the array
            //  swap the place
            temp = arr[i];
            arr[i] = arr[min];
            arr[min] = temp;
        }
    }


    ////////////////////////////////////////////////////////////////

    /**
     * the method will find the index of the minimum content of an array
     *
     * @param arr   the string array
     * @param index an integer he start index
     * @return an integer that represent the minimum content index
     */

    public static int findMin(String[] arr, int index) {
        int min = index;
        for (int i = index + 1; i < arr.length; i++)   // start from start index
        {
            if (arr[min].compareTo(arr[i]) > 0)  // compare
            {
                min = i;
            }
        }
        return min;
    }


    ////////////////////////////////////////////////////////////////


    //                     writing file
    //---------------------------------------------------------------------------------

    /**
     * the method will output words into a file and also count the number of time of words repeated
     * creating a temp string for the word that is appear and compare to new string
     * if they are same than skip this time
     * if they are not same than write it into the file
     * for example: a a b b
     * temp will be a first than when the program reach to the second it will skip this time
     * run for next time and the program reach to b since b not equal to a count num of time of b and temp become b
     *
     * @param file the target output file
     * @param arr  the array of string that need to output
     * @throws FileNotFoundException
     */

    public static void writeFile(File file, String[] arr) throws FileNotFoundException {
        System.out.println("writing file");
        PrintWriter writer = new PrintWriter(file);   // create writer
        String temp = "";    // use to compare
        int count;

        for (int i = 0; i < arr.length; i++)    // start from beginning of the array
        {
            if (arr[i].equals(temp) && i != 0)   // if arr[i] is same as before     != 0 for  write the first element
                continue;     // skip
            else {
                temp = arr[i];
                count = count(arr, arr[i], i);   // count number of time appear
                writer.printf("%-15s %5d %n", temp, count);   // write into file
            }
        }
        writer.close();    // write into hard disk
    }


    ////////////////////////////////////////////////////////////////

    /**
     * the method count number of time a word appear in a sorted array
     *
     * @param arr    the string array
     * @param target the word that need to find
     * @param index  the start index
     * @return a positive integer
     */

    public static int count(String[] arr, String target, int index) {
        int count = 0;
        for (int i = index; i < arr.length; i++)  // start from start index
        {
            if (!arr[i].equals(target))   // once the element is not equal than there are no need to find further
                return count;

            count++;
        }
        return count;
    }
    ////////////////////////////////////////////////////////////////

}