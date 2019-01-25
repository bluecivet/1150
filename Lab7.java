/**
 * File:        Lab7.java
 * Author:      Zhilong Gan
 * ID:          100331942
 * Date:        2018.10.27
 * class:       CPSC 1150-02
 * instructor:  Instructor: H. Darbandi
 * title        array
 * Compiler:    java JDK 10.2
 */

/**
 * the program is for testing each method act on array
 * Using array to create to do different thing
 */
public class Lab7
{
    public static void main(String[] args)
    {
        int[] arr = new int[10];
        int[] arrsum = new int[10];
        int[] arr5 = new int[50];
        char[] letter = new char[256];

        initianlize(arr); //item 1
        printArray(arr); // item 2

        reverseArray(arr); // item 3
        printArray(arr);

        sumArray(arr, arrsum);  //item 4
        printArray(arrsum);

        initianlize(arr5, 10, 20); // item 5

        countArray(arr5);  //item 6

        readArray(letter); // item 7

        printOneInLine(letter);  // item 8
    }
////////////////////////////////////////////////

    /**
     * the method will give each index in the array a random number frome 1 to 10
     * @param arr the integer type array need to be initialize
     */
    public static void initianlize(int[] arr)
    {
        for (int i = 0; i < arr.length; i++)
        {
            arr[i] = 1 + (int) (Math.random() * 10); // give random number
        }
    }
    /////////////////////////////////////////////////////

    /**
     * to print the array
     * @param arr the integer type arr need to printed
     */

    public static void printArray(int[] arr)
    {
        for (int i = 0; i < arr.length; i++)
        {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
    ////////////////////////////////////////////////////

    /**
     * this method will take 2 array the first array is the input the second array is the output
     * the second will sum up the first array
     * @param arr the input integer type array
     * @param arrsum same length and same type as input array as the output array
     */
    public static void sumArray(int[] arr, int[] arrsum)
    {

        for (int i = 0; i < arr.length; i++)
        {
            if (i == 0) // the first element should be the same
                arrsum[i] = arr[i];
            else
                arrsum[i] = arrsum[i - 1] + arr[i]; // sam up
        }
    }

    /////////////////////////////////////////////

    /**
     * take an array and reverse it
     * @param arr the integer type array need to be reversed
     */
    public static void reverseArray(int[] arr)
    {
        int temp;
        for(int i = 0; i <= (arr.length - 1) / 2; i++) // change start element and end element until reach the middle element
        {
            temp = arr[i]; // sort the start element
            arr[i] = arr[(arr.length - 1) - i]; // the begin element change to end element
            arr[(arr.length - 1) - i] = temp; // the end element change to start element
        }
    }
    /////////////////////////////////////////////

    /**
     * the method will initialize an array by random number between the lower limit and upper limit
     * the method should take from [ -2^31 , 2^31]
     * @param arr the integer type array need to be initialized
     * @param min the lower limit
     * @param max the upper limit
     */
    public static void initianlize(int[] arr, int min, int max)
    {
        long different = max - min; // the different between max and min
        final double weigth = 11;// because there are 11 numbers from 0 to 10
        double randomInteger; // integer value after divide the range of [0 , 10) into 11 number from [0 , 10]
        double random; // random umber between [0 , 1]
        int temp;

        for(int i = 0; i < arr.length; i++)
        {
            if(min + different == max) // integer type not overflow
            {
                randomInteger = Math.random() * weigth;
                random = randomInteger / 10.0;
                arr[i] = min + (int)(random * different);
            }
            else  // integer type overflow
            {
                // if the integer type overflow it will give the array a random number between max and min
                // if the number exceed the limit it will give a random number again until the number stay in limit
                do
                {
                    randomInteger = Math.random() * weigth;
                    random = randomInteger / 10.0;
                    temp = (int)(min * random); // random number for min
                    randomInteger = Math.random() * weigth;
                    random = randomInteger / 10.0;
                    arr[i] = temp + (int)(random * max);
                }
                while(arr[i] > max || arr[i] < min);
            }
        }
    }
    ////////////////////////////////////////////////////////////

    /**
     * the method will count how many time a number occur in an array
     * @param arr the integer type array need to be count
     */

    public static void countArray(int[] arr)
    {
        /*
          create a count array to count how many time the same number occur
          the first place of count array is the number the second place of number is how many time it occur
          for example {0, 5, 1, 3} means 0 occur 5 time and 1 occur 3 time
         */
        int numOfUniqu = arr.length - numRepeatNum(arr); // find how many unique number in the array
        int[] count = new int[numOfUniqu * 2]; // create count array double size of number of unique
        boolean haveSame;
        int countcount = 0;
        for(int i = 0; i < arr.length; i++)
        {
         // checking i
         // if there is the same number in the count array
              haveSame = findSame(arr,i,count);
         /*
          if the there different number from array count it store in count array
          and the add count
          if there are same number in the count, it find where the array left off and add 1
           */
         if(!haveSame) // there aren't same number
         {
             if(count[countcount] == 0 && count[countcount + 1] > 0) // check if 0 has been count yet if 0 is count it skip
             {
                 countcount += 2;
              }
                 count[countcount] = arr[i]; // store the number to count array
                 count[countcount + 1]++; // the number of index after the number store add 1 mean it occur 1 time
                 countcount += 2;// go to next place that can store number
         }
         else // there are same number
         {
             int k = 0;
             while(k < count.length)// find where is the same number
             {
                 if(arr[i] == count[k])
                     break;
                 k += 2; // the place where the same number occur
             }
             count[k + 1]++; // add the occur time
         }
     }
      printCount(count); // print out what number occur how many time
    }

   ///////////////////////////////////////////////////////////////////

    /**
     * count how many number that is repeat
     * @param arr the integer array need to count
     * @return the number of repeat number in the array
     */
    public static int numRepeatNum(int[] arr)
    {
        int count = 0;
        /*
          find how many time that the array repeat the same number
          evert time when the array index increase by 1
          it will look for if there is the same number at the front
          if there is a number is same then if there is a number a the back have the sam number then it will skip count this time
          if there are no same at the back count add 1
         */
      A:  for(int i = 0; i < arr.length; i++)// increase by one
        {
            for(int j = 0; j < i; j++)// checking the number at the front
            {
                if(arr[i] == arr[j]) // if there the number have same at front
                {
                    for(int k = i + 1; k < arr.length; k++) // the is there the same number at the back
                    {
                        if(arr[k] == arr[i]) // if there are same number at the back skip arr[i] go to arr[i + 1]
                        {
                          continue A;
                        }
                    }
                    count++;
                }
            }
        }
        return count;
    }
    ////////////////////////////////////////////////////////////////////////////////////

    /**
     * the method will check is there a same number in the count array
     * @param arr the integer array
     * @param i the index of the integer array
     * @param count the integer array need to be check
     * @return if there is same number return true if not return false
     */
      public static boolean findSame(int arr[],int i, int count[])
      {
          //look into count array .
          for(int j = 0; j < count.length; j += 2) // j += 2 because the first place is the number the second place is how many time occur
          {
              if(arr[i] == count[j])
              {
                  return true;
              }
          }
          return false;
      }
      /////////////////////////////////////////////////////////////////////////////////

    /**
     * print method for method coundArray print out how many time the number occur
     * @param arr the integer type count array
     */
     public static void printCount(int[] arr)
     {
         for(int i = 0; i < arr.length; i += 2)
         {
             System.out.println(arr[i] + " occurs = " + arr[i+1]);
         }
     }
     ////////////////////////////////////////////////////////////////////////

    /**
     * the method read the character the user input and store into a char type array
     * @param letter the char type arry need to store
     */
     public static void readArray(char[] letter)
     {
         System.out.println("please input 256 symbols");
         java.util.Scanner input = new java.util.Scanner(System.in);
          String message = input.nextLine();
          for(int i = 0; i < message.length(); i++)// make sure the character in the message is in  the array length
          {
              if(i > letter.length - 1) // if excess the array length
              {
                  System.out.println("yours enter excess 256 characters");
                  break;
              }

              letter[i] = message.charAt(i);
          }
     }
     ///////////////////////////////////////////////////////////////////

    /**
     * the method for readArray the print the message in one line
     * @param message the char type array the need to be printed
     */
     public static void printOneInLine(char[] message)
     {
         for(int i = 0; i < message.length; i++)
         {
             System.out.print(message[i]);
         }
     }
}
