/*
We have to paint n boards of length {A1, A2…An}. There are k painters available and each takes 1 unit time to paint 1 unit of board. The problem is to find the minimum time to get
this job done under the constraints that any painter will only paint continuous sections of boards, say board {2, 3, 4} or only board {1} or nothing but not board {2, 4, 5}.

Examples:

Input : k = 2, A = {10, 10, 10, 10} 
Output : 20.
Here we can divide the boards into 2
equal sized partitions, so each painter 
gets 20 units of board and the total
time taken is 20. 
Input : k = 2, A = {10, 20, 30, 40} 
Output : 60.
Here we can divide first 3 boards for
one painter and the last board for 
second painter.
*/
import java.util.*;
class PainterPartition{
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        int boards[] = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int k = sc.nextInt();
        System.out.println(findMin(boards,k));
    }
    
    static int findMin(int boards[],int k){
        int col = boards.length+1;
        int sum[] = new int[col];
        sum[0] = 0;
        for(int i=1;i<col;i++) sum[i] = sum[i-1]+boards[i-1];
        int dp[][] = new int[k+1][col];
        
        for(int i=0;i<=k;i++){
            if(i==0){Arrays.fill(dp[i],Integer.MAX_VALUE); dp[i][0] = 0; continue;}
            
            for(int j=0;j<col;j++){
                if(j==0){dp[i][j] = 0; continue;}
                
                dp[i][j] = Integer.MAX_VALUE;
                for(int p = j-1;p>=0;p--){
                    int cur = sum[j] - sum[p];
                    dp[i][j] = Math.min(dp[i][j], Math.max(cur,dp[i-1][p]));
                }
                
            }
        }
        return dp[k][col-1];
    }
}
