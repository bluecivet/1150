public class Arrow
{
    // the program draw seperate the arrow into three part the upper part and bottom is a triangle
    // the middle part is a line
    public static void main(String[] args)
    {
        // check is there a input if not then exit the program
        if(args.length < 1)
        {
            System.out.println("please enter a positive number");
            System.exit(0);
        }

        // set n is the input if input is a negative number then exit the program
        int n = Integer.parseInt(args[0]);

        if(n <= 0)
        {
            System.out.println("please enter a positive number");
            System.exit(0);
        }
        // print the upper part of the arrow which is a triangle
        n = n-1;
        int i = 1;
        while(i <= n)
        {
            int j = n;
            while(j - i >= 0)
            {
                System.out.print(" ");
                j--;
            }
             int k = 1;
            while(i - k >= 0)
            {
                System.out.print("*");
                k++;
            }
            System.out.println(" ");
            i++;
        }

        // print the middle part of the arrow which is a long line
        // reset i;
              i = 1;
            while(i < 2 * (n + 1))
              {
                System.out.print("*");
                i++;
              }

              System.out.println(" ");

            // print the bottom part of the arrow which is a triangle
             // reset i
               i = 1;
            while(i <= n)
            {
                int j = 1;
                while(j <= i)
                {
                    System.out.print(" ");
                    j++;
                }

                int k = n;
                while(k >= i)
                {
                    System.out.print("*");
                    k--;
                }
                System.out.println(" ");
                i++;
            }
    }
}
