import java.math.*;
public class Lab2
{

    public static void main(String[] args)
    {
        // declare valuble for input

	  double a = 0;
	  double b = 0;
	  double c = 1;

	  // dividy the function seperate the function into two part which is left side and right side

	  double insideSqure = (b * b) - (4 * a * c);
	  double leftSide = (-b) / (2 * a);
	  double rightSide = Math.sqrt(insideSqure) / (2 * a);

	  //  get x1 and x2 for the tow situation because the one is add them together or subtract together
	  double x1 = leftSide + rightSide;
	  double x2 = leftSide - rightSide;
	  // determind the cases there are 4 different case

        boolean is_less_than_zero = (insideSqure < 0); // inside the sqrt root is negatived
        boolean a_is_equal_zero = (a == 0) & (b != 0) & (c != 0); // input a is zero which is dividing by zero
        boolean all_zero = (a == 0) & (b == 0) & (c == 0); // all the input are zero
        boolean a_b_is_zero = (a == 0) & (b == 0) & (c != 0);  // the function is equal to c


        // output x1 and x2

        if(is_less_than_zero) // case for inside sqrt root is negatived
        {
            System.out.println("a = " +a +" b = " +b +" c = " +c);
            System.out.println("there are no real roots");
        }
        else if(a_is_equal_zero) // case for dividing zero which become a linear function
        {
            System.out.println("a = " +a +" b = " +b +" c = " +c);
            System.out.println("since a is zero so it become a linear function");
            double x = -(c / b);
            System.out.println("the answer for the linear function is " +x);
        }
        else if(all_zero)  // case for all the input is zero
        {
            System.out.println("a = " +a +" b = " +b +" c = " +c);
            System.out.println("because all the input is zero so the output is 0");
            System.out.println("there are no answer for x");
        }
        else if(a_b_is_zero)  // case for the function is equal to c
        {
            System.out.println("a = " + a + " b = " + b + " c = " + c);
            System.out.println("both a and b is 0 so the function equal to c");
            System.out.println("the are no answer for x1 and x2");
        }
        else  // when there are no special case or math error output the answer
        {
            System.out.println("a = " +a +" b = " +b +" c = " +c);
            System.out.println("the answer for x1 is " +x1 +" the answer for x2 is " +x2);
        }




    }
}
