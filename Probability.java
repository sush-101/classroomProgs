/*
Given n matches, and corresponding probability of winning of Indian cricket team in that match.
Given k matches to win, ouptut the probability of winning exactly k matches.
Input Format:
-------------
Line-1 -> An integer N
Line-2 -> N space seperated double values, 0.0 <= value <=1.0 
Line-3 -> An integer K, 0<= K <=N

Output Format:
--------------
Print the result (double value).


Sample Input-1:
---------------
1
0.4
0

Sample Output-1:
----------------
0.6

Sample Input-2:
---------------
4
0.5 0.5 0.5 0.5
2

Sample Output-2:
----------------
0.375*/
import java.util.*;
class Probability{
    static float dp[][];
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        float win[] = new float[n];
        for(int i=0;i<n;i++){
            win[i] = sc.nextFloat();
        }
        int k = sc.nextInt();
        dp = new float[n][k+1];
        System.out.println(getWins(k,win,0,n-1));
    }
    static float getWins(int k,float win[],int start,int end){
        if(start>end)return 1;
        if(dp[start][k]!=0)return dp[start][k];
        float include = 0,exclude = 0;
        int n=win.length;
        
        if(k==0)return (1-win[start])*getWins(k,win,start+1,end);
        
        include = win[start]*getWins(k-1,win,start+1,end);
        if(k <= end-start){
            exclude = (1-win[start])*getWins(k,win,start+1,end);
        }
        dp[start][k] = include+exclude;
        return dp[start][k];
    }
}
