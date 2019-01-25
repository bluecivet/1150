/**
 * File:        TestUnique.java
 * Author:      Zhilong Gan
 * ID:          100331942
 * Date:        2018.10.27
 * class:       CPSC 1150-02
 * instructor:  Instructor: H. Darbandi
 * title        test unique
 * Compiler:    java JDK 10.2
 */
public class TestUnique
{
    public static void main(String[] args)
    {
        int[] test1={4,6,1,-8,-12,6,1,-12,-12,4,5};
        int[] test2={0, 0, 1, 3, -9, 6, 1, 0, 6};
        int[] test3 ={1}; // The result should be the same array
        // Test case 1:
        System.out.println("Test casse 1:");
        print(test1);
        unique(test1);
        print(test1);

        // Test case 2:
        System.out.println("Test casse 2:");
        print(test2);
        unique(test2);
        print(test2);

        // Test case 3:
        System.out.println("Test casse 3:");
        print(test3);
        unique(test3);
        print(test3);
    }
    //-----------------
    /** prints content of array of integers
     @param arr : array of integers
     */
    public static void print(int[] arr)
    {
        int i;
        System.out.print("{");
        for (i=0; i<arr.length; i++)
        {
            if (i==arr.length-1)
                System.out.println(arr[i]+"}");
            else
                System.out.print(arr[i]+",");
        }
    }
    //----------------------
    // Your code:

    /** Removes redundant elemts of the array without reordering the array.
     fills the rest of the array with zeros.
     @param arr : array of integers
     */
    public static void unique(int[] arr)
    {
       makeUni(arr);// make the arr unique there should not be same number in the array except 0
    }
    // Add appropriate helper methods

    /**
     * the method will find the number that is repeated in an array then replace it to a number that is not repeat by shifting the array
     * using 0 to fill the excess area
     * @param arr the integer array need to be made unique
     */
    public static void makeUni(int [] arr)
    {
        /*
        the array wil check from the beginning to the end find the same number occur after
        then from the number that repeat move the array toward to begin
         */
        int shitfCount = 0; // make sure the program is not doing extra job
        for(int i = 0; i < arr.length; i++) // start from begin
        {
            int j = i + 1;// checking the number after 2
            while(j < arr.length - shitfCount) // checking the back
            {
                if(arr[i] == arr[j])
                {
                    shitfArray(arr,j);// move array
                    shitfCount++; // once the array is move the last place in the array do not need to check again next
                    continue; // if there is a continue number repeat
                }
                j++; // go to next number
            }
        }
    }
    ////////////////////////////////////////////

    /**
     * the mothod will move the array toward to begin and the empty space will filled wilth 0
     * @param arr the array need to move  the array should be integer type
     * @param index the place where need to start  it should be positive  a integer
     */
    public static void shitfArray(int[] arr, int index)
    {
        for(int k = index; k < arr.length - 1; k++)
        {
            arr[k] = arr[k + 1]; // number exchange
        }
        arr[arr.length - 1] = 0; // fill with 0
    }
}
