/*Sansa has an array. She wants to find the value obtained by XOR-ing the contiguous subarrays, 
followed by XOR-ing the values thus obtained. Determine this value.

For example, if arr=[3,4,5]:

Subarray	Operation			Result
--------------------------------------
3			None				3
4			None				4
5			None				5
3,4			3 XOR 4				7
4,5			4 XOR 5				1
3,4,5		3 XOR 4 XOR 5		2

Now we take the resultant values and XOR them together:
3 XOR 4 XOR 5 XOR 7 XOR 1 XOR 2 = 6

Input Format
- The first line contains an integer n, the number of elements in .
- The second line of each test case contains n space-separated integers .

Output Format
Print the results of each test case on a separate line.

Sample Input-1
    3
    1 2 3
Sample Output-1
    2

Sample Input-2
    4
    4 5 7 5
Sample Output-2
    0

*/
import java.util.*;
class XORSubarrayxor{
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(),dp[] = new int[n+1],res = 0;
        dp[0] =0;
        for(int i=1;i<=n;i++){
            int num = sc.nextInt(),times;
            
                int sum = ((i-1)*i)>>1;
                dp[i] = dp[i-1] + n-i+1;
                times = dp[i] - sum;
     
            if((times&1) == 1) res ^= num;
        }
        System.out.println(res);
    }
}
