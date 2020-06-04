/*
An icecream is at position 0,0 on a table of size N X M divided into N*M cells.
The icecream melts at K time.The icecream has to reach the destination cell denoted by '*'.
A cell has a direction.The direction on the cell can be changed.
Given that can you tell minimum no.of changes you have to make in order to get the icecream to its destination cell with K time max.
(If the icecream reaches destination in K-2 time and 8 changes and can also reach in K time with 6 changes answer should be 6.
If the icecream reaches destination in K-2 time and 6 changes and can also reach in K time with 8 changes answer should be 6.)
Input Format:
-------------
Line-1 -> Three integers N, M, and K respectively. 
Next N Lines ->contains M letters, with letter set [U,R,D,L,*]

Output Format:
--------------
Print an integer as answer, if you achieve it.
Otherwise, print -1.

Sample Input:
-------------
2 2 1
RD
*L

Sample Output:
--------------
1
SampleInput:
____________
4 4 6
DRRR
DRRR
RD*R
RRUR
Sample Output:
______________
0

*/
import java.util.*;
class Dp{
    static int MAX = Integer.MAX_VALUE-1;
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        int r = sc.nextInt(), c = sc.nextInt(), k = sc.nextInt(),res = MAX;
        int dx=-1,dy=-1;
        char arr[][] = new char[r][c];
        for(int i=0;i<r;i++){String temp = sc.next();
        for(int j=0;j<c;j++){arr[i][j] = temp.charAt(j); if(arr[i][j] == '*'){dx=i;dy=j;}}}
        int dp[][][] = new int[k+1][r][c];
        for(int i=0;i<=k;i++){for(int j=0;j<r;j++){Arrays.fill(dp[i][j],MAX);}}
        build(dp,arr,k);
        for(int i=0;i<=k;i++){res = Math.min(res,dp[i][dx][dy]);}
        System.out.println(res);
    }
    
    static void build(int dp[][][],char arr[][],int time){
        int r = dp[0].length,c = dp[0][0].length;
        dp[0][0][0] = 0;
        for(int k=1;k<=time;k++){
            for(int i=0;i<r;i++){
                for(int j=0;j<c;j++){
                    if(i+j <= k){
                        dp[k][i][j] = getMin(arr,dp,i,j,k);
                    }
                }
            }
            /*System.out.println("After "+k);
            for(int i=0;i<r;i++)System.out.println(Arrays.toString(dp[k][i]));*/
        }
    }
    static int getMin(char arr[][],int dp[][][],int x,int y,int z){
        int time = dp.length-1,r = dp[0].length,c = dp[0][0].length,min = MAX;
        int dir[][] = {{-1,0},{1,0},{0,1},{0,-1}};
       // System.out.println("finding min for "+x+" "+y);
        for(int i=0;i<4;i++){
            int curx = x+dir[i][0], cury = y+dir[i][1];
            if(curx>=0 && cury>=0 && curx<r && cury<c){
                //System.out.println("at "+curx+" "+cury);
                min = Math.min(min,dp[z-1][curx][cury] + (noChange(arr[curx][cury],i)?0:1));
            }
        }
        return min;
        
    }
    static boolean noChange(char dir,int x){
        //System.out.println("to process "+dir+" "+x);
        
        switch(dir){
            case 'U' : return (x==1);
            case 'D' : return (x==0);
            case 'L' : return (x==2);
            case 'R' : return (x==3);
        }
        //System.out.println(flag);
        return false;
    }
}
