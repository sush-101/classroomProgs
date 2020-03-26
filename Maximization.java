/*Bharat is chocoholic. He found a chocolate factory of N floors ,
but the factory has N*N rooms where in each room specific number of chocolate 
is present. Now, Bharat starts collecting chocolate from ground floor. 
He can only collect chocolate from one room in a floor. Bharat can only 
move to upper room or upper-right room or upper-left room .

He want to collect maximum number of chocolate possible. Help him in 
finding maximum number of chocolate.

Input Format
------------
First line contains a value of N. 
Next N lines contains N space separated integer.

Output Format
-------------
Output a single integer denoting the maximum number of chocolate Bharat can collect.


Sample Input:
    5
    1 2 3 4 5
    1 2 3 4 5
    1 2 3 4 5
    1 2 3 4 5
    100 2 3 4 5
Sample Output:
    114*/
    import java.util.*;
class Maximization{
    static String dp[][];
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), arr[][] = new int[n][n],max = Integer.MIN_VALUE;
        dp = new String[n][n];
        for(String[] c:dp)
        Arrays.fill(c,"#");
        for(int i=0;i<n;i++)for(int j=0;j<n;j++) arr[i][j] = sc.nextInt();
        for(int i = 0;i < n ;i++) max = Math.max(max,find(arr,n-1,i));
        System.out.println(max);
    }
    static int find(int arr[][],int row,int col){
        if(row<0 || col>=arr.length || col<0) return 0;
        if(!dp[row][col].equals("#")) return Integer.parseInt(dp[row][col]);
        int ans = arr[row][col];
        dp[row][col] = String.valueOf((ans + Math.max(find(arr,row-1,col),Math.max(find(arr,row-1,col-1),find(arr,row-1,col+1)))));
        return Integer.parseInt(dp[row][col]);
    }
}
