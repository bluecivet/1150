/**
 * File:        ShapeCalculation.java
 * Author:      Zhilong Gan
 * ID:          100331942
 * Date:        2018.10.04
 * class:       CPSC 1150-02
 * instructor:  Instructor: H. Darbandi
 * title        perimeter and area for triangle and circle
 * Compiler:    java JDK 10.2
 */

import java.util.Scanner;

/**
 * The user choose one of the shape :triangle and circle
 * solve the perimeter and area for the shape
 */
public class ShapeCalculation
{
    // set the variable that share to all the method
    public static int a = 0;                      // count for cases
    public static double x1, x2, x3, y1, y2, y3;  // three points for triangle
    public static double perimeter, area;         // for both shape
    public static double radius;                // radius for circle
    final static double EPSILON = 0.0000001;   // a small number  for compare floating point

    public static void main(String[] args)
    {
        decide_shape();                    // user choose the shape
        determin_case();                     // calculate the perimeter and area for the shape
        print_answer();                      // output the perimeter and area
    }

    ///////////////////////////////////////////////////////////////////////////

    /**
     * let the user select which shape they want to calculate
     * using global variable a to detect the input from the user
     */
    public static void decide_shape()
    {
        Scanner input = new Scanner(System.in);
        while(a != 1 && a != 2)
        {
            /*
              user can only press 1 2 or 3 otherwise the program will not process to next step
              press 1 to select triangle
              press 2 to select circle
              press 3 to exit the program
             */
            System.out.println("enter 1: triangle");
            System.out.println("enter 2: circle");
            System.out.println("enter 3: exit the program");
            a = input.nextInt();
            if (a == 3)
            {
                System.out.println("closing the program");
                System.out.println("Bye");
                System.exit(0);
            }
        }
    }

    ////////////////////////////////////////////////////////////////////////////

    /**
     * using global variable a to determine which shape does the user want to calculate
     * do the function that need to calculate the perimeter and area
     */

    public static void determin_case()
    {
        if(a == 1)
        {
            input_triangle();
            triangle();
        }
        else if(a == 2)
            circle();
        else
            System.exit(0);
    }
    ///////////////////////////////////////////////////////////////////////////////////

    /**
     * output the perimeter and area for shape that the user select
     */

    public static void print_answer()
    {
        if(a == 1)
        {
            System.out.println("perimeter = " + perimeter);
            System.out.println("area = " + area);
        }
        else if(a == 2)
        {
            System.out.println("radius = " + radius);
            System.out.println("perimeter = " + perimeter );
            System.out.println("area = " + area);
        }
        else
        {

        }

    }

    //////////////////////////////////////////////////////////////////////////////////////

    /**
     * using Heron's formula to calculate  area for the triangle
     * s = half of the perimeter
     * perimeter = the sum of three side
     * also compare the slops of sides check is three point that user input are all in the one line
     * the perimeter will be longest side
     */
    public static void triangle()
    {
        double side1, side2, side3;             // sides for triangle
        double slope1, slope2, slope3;          // slopes for the sides
        double s;                             // half perimeter

        // using the side function to calculate the side for triangle
        side1 = side(x1, y1, x2, y2);
        side2 = side(x1, y1, x3, y3);
        side3 = side(x2, y2, x3, y3);

        //using the slope function to calculate the slope for the sides
        slope1 = slope(x1, y1, x2, y2);
        slope2 = slope(x1, y1 ,x3, y3);
        slope3 = slope(x2, y2, x3, y3);

        // using the check_triangle function to to check are all point in the same line
        check_triangle(slope1, slope2, slope3, side1, side2, side3);

        perimeter = side1 + side2 + side3;
        s = perimeter / 2;
        area = Math.sqrt(s * (s - side1) * (s - side2) * (s - side3));
    }

    //////////////////////////////////////////////////////////////////////////////

    /**
     * the user input the points in the x y aix form
     * the points will short into the related global variable's name as x and y
     */

    public static void input_triangle()
    {
        Scanner input = new Scanner(System.in);
        System.out.println("please enter 3 points for triangle");
        System.out.println("point 1");
        System.out.print("x = ");
        x1 = input.nextDouble();
        System.out.print("y = ");
        y1 = input.nextDouble();
        System.out.println("point 2");
        System.out.print("x = ");
        x2 = input.nextDouble();
        System.out.print("y = ");
        y2 = input.nextDouble();
        System.out.println("point 3");
        System.out.print("x = ");
        x3 = input.nextDouble();
        System.out.print("y = ");
        y3 = input.nextDouble();
    }

    //////////////////////////////////////////////////////////////////////////////////////////

    /**
     * to calculate the side from the points using pythagoras theorem a^2 + b^2 = c^2
     * a = second point of x - second point of x
     * b = second point of y - first point pf y
     * c = the length of the two point
     * @param fx the first point of x
     * @param fy the first point of y
     * @param sx the second point of x
     * @param sy the second point of y
     * @return the length of two point connected
     */

    public static double side(double fx, double fy, double sx, double sy)
    {
        double side;
        double deltax = sx - fx;
        double deltay = sy - fy;
        side = Math.sqrt(deltax * deltax + deltay * deltay);
        return(side);
    }
    ////////////////////////////////////////////////////////////////////////////////////////

    /**
     * the slope function to calculate the side of slope using delta y / delta x
     * delta y = second point of y - first point of y
     * delta x = second point of x - first point of x
     *
     * @param x the first point of x
     * @param y the first point of y
     * @param secondx the second point of x
     * @param secondy the second point of y
     * @return the slope of the side
     */

    public static double slope(double x, double y, double secondx, double secondy)
    {
        double delta_x = secondx - x;
        double delta_y = secondy - y;
        /*
          if delta x is 0 that mean the slope will be infinite
          the infinite slope means a vertical line
          in the program we set the slope as large as possible
          compare floating point number using epsilon
         */
        if(Math.abs(delta_x) < EPSILON)
        {
            return(999999997); // leave two space in the computer for binary number
        }
        return(delta_y / delta_x);
    }
    //////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * the max function is to calculate the maximum number by comparing them
     * @param a the fist value
     * @param b the second value
     * @return the maximum value between two number
     */

    public static double max(double a, double b)
    {
        // using epsilon to compare floating points
        if(a - b > EPSILON)
            return a;
        else
            return b;
    }
    //////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * the check_triangle function is to check if the points are all in three line
     * chick the slope of the the side
     * if two of the slope is the same then one of the point is in the same line
     * if the points are in same line the the maximum length of side will be the perimeter
     * @param a first slope
     * @param b second slope
     * @param c third slope
     * @param side1 first side
     * @param side2 second side
     * @param side3 third side
     */

    public static void check_triangle(double a, double b, double c, double side1, double side2, double side3)
    {
        //using epsilon to compare the slopes
        if(Math.abs(a - b) < EPSILON || Math.abs(a - c) < EPSILON || Math.abs(c - b) < EPSILON)
        {
            perimeter =  max(side1, max(side2,side3)); // using max function to get maximum number
            System.out.println("all three points are in one line the perimeter take the longest line");
            System.out.println("perimeter = " + perimeter);
            System.exit(0);
        }
    }
    /////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * the circle function is to input the radius than get the perimeter and area
     * using the circle perimeter formula 2 * PI * R for perimeter
     * using the circle area formula PI * r^2 for area
     */
    public static void circle()
    {
        Scanner input = new Scanner(System.in);
        System.out.print("radius = ");
        radius = input.nextDouble();
        /*
          if the radius is negative tell the user the input should not be negative
          the negative number change to positive
         */
        if(radius < 0)
        {
            System.out.println("the radius cannot be negative");
            radius = Math.abs(radius);
            System.out.println("input radius: " + radius);
        }
        perimeter = Math.PI * 2 * radius;
        area = Math.PI * radius * radius;
    }
}
