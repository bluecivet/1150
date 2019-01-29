public class Test 
{
	public static void main(String[] args)
	{

		int n = 5;

		for(int row = 0; row < n; row++)
		{
			for(int col = 0; col < n; col++)
			{
        
               if(col == n - row - 1)
               {
               	System.out.print(row + 1);
               }
               else
               {
               	System.out.print(".");
               }
			}

			System.out.println();
		}
	}
}