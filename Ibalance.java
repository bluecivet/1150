public class Ibalance
{
    public static void main(String[] args)
    {
        // check the number of input if input is zero then exit the program
        if(args.length < 1)
        {
            System.out.println("please enter initial balance");
            System.exit(0);
        }

        // set initial money is input if input is negative number then exit the program
        double start_money = Double.parseDouble(args[0]);

        if(start_money < 0)
        {
            System.out.println("please enter a positive number");
            System.exit(0);
        }

        // set up the value
        final double YEAR_INTEREST = 0.06;
        final double MONTH_INTEREST = YEAR_INTEREST / 12;
        final double WITHDRAW = 500;
        int year = 0;
        int month = 0;
        String yue = "month";
        String nian = "year";


       // calculate the money after interest then take the withdraw money from total
       // calculate when will take all the money by how many time the loop run
       // each time the loop increase 1 month
       /* also if the money after interest is more than or equal to the money withdraw, it means the
          whatever you take the account will never goes to 0
          than exit the program
        */
        while(start_money + start_money * MONTH_INTEREST >= WITHDRAW)
        {
            start_money += start_money * MONTH_INTEREST;
            start_money -= WITHDRAW;
            month++;
            year = (month % 12 == 0) ? ++ year : year;

            if(start_money * MONTH_INTEREST >= WITHDRAW)
            {
            	System.out.println("good new you can take your money with " +WITHDRAW + " forever");
            	return;
            }
        }
        // calculate how many month after change to year
        if(year != 0)
        {month %= 12;}

        // if the month and year is more than one the add "s" at the end
       yue = month > 1 ? "months" : "month";
       nian = year > 1 ? "years" : "year";

       // if year or month is zero then only print the string thai is not 0
        // otherwise display both strings
        if(year == 0 && month != 0)
        {
            System.out.println(month + " " + yue);
        }
        else if(year != 0 && month == 0)
        {
            System.out.println(year + " " + nian);
        }
        else if(year == 0 && month == 0)
        {
            System.out.println("there are not enough so the account will be depleted");
        }
        else
        {
            System.out.println(year + " " + nian + " " + month + "  " + yue);
        }




    }
}
