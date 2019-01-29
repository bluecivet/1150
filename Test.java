public class Test 
{
	public static void main(String[] args)
	{

		int n = 5; // 这个不说了就是多少行多少列

		for(int row = 0; row < n; row++)   // 我是从行开始的 从第一行开始 到第5行
		{
			for(int col = 0; col < n; col++)   // 这里从列开始
			{
        
                // 比如输出第一行 。。。。1  输入。 就不说了
                //输出数字的位置： 因为我们共输出5个字符然后最后一位是数字 所以col的位置在第5个的时候输出 
                //这是是第一行 所以row = 0  col 的位置关系如下 n - row - 1
                // n - row 因为是从倒数开始 想像一下 col = n - row  越往下row（行数）越大 那么 n - row越小 col最开始是5 然后是4 ，3， 2.。。。
                // n - row - 1 因为row 从 0 开始 col 也是从0开始  也会就是说 row = 0 是在输出第一行， col = 1 时在输出那一行的第2个
                // 还是第一行举例 。。。。1  当col = 0 时输出“。”  我们要col在第5个的时候输出1 所以 当 col = 4 的时候输出 （row = 0， n = 5） n - row = 5 - 0 = 5）要减1 5 - 1 = 4 
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