import java.util.*;
class Dp{
    static int MAX = Integer.MAX_VALUE-1;
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        int r = sc.nextInt(), c = sc.nextInt(), k = sc.nextInt();
        int dx=-1,dy=-1;
        char arr[][] = new char[r][c];
        for(int i=0;i<r;i++){String temp = sc.next();
        for(int j=0;j<c;j++){arr[i][j] = temp.charAt(j); if(arr[i][j] == '*'){dx=i;dy=j;}}}
        int dp[][] = new int[r][c];
        for(int j=0;j<r;j++){Arrays.fill(dp[j],MAX);}
        build(dp,arr,k);
        
        System.out.println(dp[dx][dy]);
    }
    
    static void build(int dp[][],char arr[][],int time){
        int r = dp.length,c = dp[0].length;
        dp[0][0] = 0;
        for(int k=1;k<=time;k++){
            for(int i=0;i<r;i++){
                for(int j=0;j<c;j++){
                    if(i+j <= k){
                        dp[i][j] = Math.min(dp[i][j],getMin(arr,dp,i,j,k));
                    }
                }
            }
            /*System.out.println("After "+k);
            for(int i=0;i<r;i++)System.out.println(Arrays.toString(dp[k][i]));*/
        }
    }
    static int getMin(char arr[][],int dp[][],int x,int y,int z){
        int r = dp.length,c = dp[0].length,min = MAX;
        int dir[][] = {{-1,0},{1,0},{0,1},{0,-1}};
       // System.out.println("finding min for "+x+" "+y);
        for(int i=0;i<4;i++){
            int curx = x+dir[i][0], cury = y+dir[i][1];
            if(curx>=0 && cury>=0 && curx<r && cury<c){
                //System.out.println("at "+curx+" "+cury);
                min = Math.min(min,dp[curx][cury] + (noChange(arr[curx][cury],i)?0:1));
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
