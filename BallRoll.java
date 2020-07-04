/*
A ball has to reach its destination with minimum steps. 1 indicates wall, 0 indicates surface.
A ball can move in 4 directions from a point. A ball continues to roll in a direction until it hits a wall and can change its direction.
Input Format:
-------------
Line-1 An integer N
Next N lines -> N space seperated integers [0,1]
Next line -> two integers indicates position-1
Next line -> two integers indicates position-2

Output Format:
--------------
Print an integer as result, 
if result is not possible, print -1. 


Sample Input-1:
---------------
5
0 0 1 0 0
0 0 0 0 0
0 0 0 1 0
1 1 0 1 1
0 0 0 0 0
0 4
4 4

Sample Output-1:
----------------
12


Sample Input-2:
---------------
5
0 0 1 0 0
0 0 0 0 0
0 0 0 1 0
1 1 0 1 1
0 0 0 0 0
0 4
3 2

Sample Output-2:
----------------
-1
*/

import java.util.*;
import javafx.util.Pair;
class BallRoll{
    static int dist[][],arr[][];
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        arr = new int[n][n];dist = new int[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                arr[i][j] = sc.nextInt();
                if(arr[i][j] == 0){dist[i][j] = Integer.MAX_VALUE;}
            }
        }
        int sx = sc.nextInt(),sy = sc.nextInt(),ex = sc.nextInt(),ey = sc.nextInt();
        System.out.println(findDist(sx,sy,ex,ey));
    }
    static int findDist(int sx,int sy,int ex,int ey){
        int n = arr.length;
        TreeSet<Pair<Integer,Integer>> q = new TreeSet<>((o1,o2)->{
                int a = o1.getKey(),b = o1.getValue(),c = o2.getKey(),d = o2.getValue();
                if(dist[a][b] == dist[c][d])return (a*n+b)-(c*n+d);
                return dist[a][b] - dist[c][d];
                });
        dist[sx][sy] = 0;     
        q.add(new Pair(sx,sy));
        while(q.size()!=0){
            
            Pair<Integer,Integer> cur = q.pollFirst();
            
            makeChanges(q,cur.getKey(),cur.getValue());
        }
        return dist[ex][ey]==Integer.MAX_VALUE?-1:dist[ex][ey];
    }
    static void makeChanges(TreeSet<Pair<Integer,Integer>> q,int cx,int cy){
        int cd = dist[cx][cy],n = arr.length;
        int dir[][] = {{1,0},{-1,0},{0,1},{0,-1}};
        for(int i=0;i<4;i++){
            int tx = cx,ty = cy,td = cd;
            while(tx>=0 && tx<n && ty>=0 && ty<n && arr[tx][ty]==0){tx+=dir[i][0];ty+=dir[i][1];td++;}
            tx -= dir[i][0];ty -= dir[i][1];td--;
            if(dist[tx][ty] > td){
                dist[tx][ty] = td;
                q.remove(new Pair(tx,ty));q.add(new Pair(tx,ty));
            }
        }
    }
    
}
