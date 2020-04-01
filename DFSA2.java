/*
There are n cities connected by m flights. 
Each fight starts from city u and arrives at v with a price w.

Now given all the cities and flights, 
together with starting city src and the destination dst, 
your task is to find the cheapest price from src to dst with up to k stops. 
If there is no such route, output -1.

Note:

The number of nodes n will be in range [1, 100], with nodes labeled from 0 to n - 1.
The size of flights will be in range [0, n * (n - 1) / 2].
The format of each flight will be (src, dst, price).
The price of each flight will be in the range [1, 10000].
k is in the range of [0, n - 1].
There will not be any duplicated flights or self cycles.


Sample Input 1: 
        3 3
        0 1 100
        1 2 100
        0 2 500
        0 2 1

Sample Output 1: 
        200

Explanation: 
The graph looks like this:
	       0
	  100 / \ 500
	     /	 \
	    1-----2
	      100
The cheapest price from city 0 to city 2 with at most 1 stop costs 200.

Sample Input 2: 
        3 3
        0 1 100
        1 2 100
        0 2 500
        0 2 0

Sample Output 2: 
        500

Explanation: 
The graph looks like this:
	       0
	  100 / \ 500
	     /	 \
	    1-----2
	      100

The cheapest price from city 0 to city 2 with at most 0 stop costs 500.

*/
import java.util.*;
class DFSA2{
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), d = sc.nextInt();
        long res = Long.MAX_VALUE;
        long arr[][] = new long[n][n];
        
        while(d--!=0){
            int a = sc.nextInt(),b = sc.nextInt();
            long wt = sc.nextLong();
            arr[a][b] = wt;
            arr[b][a] = wt;
        }
        int src = sc.nextInt(), des = sc.nextInt(), k = sc.nextInt();
        for(int i=0;i<n;i++){
            if(arr[src][i] > 0){
                int v[] = new int[n];
                v[src] = 1;
                long temp =  dfs(i,des,v,arr,k,0);
                if(temp != -1)
                res = Math.min(res, temp + arr[src][i]);
            }
        }
        System.out.println(res == Long.MAX_VALUE ? -1 : res);
    }
    static long dfs(int src,int des,int v[],long arr[][],int k,int done){
        if(done > k) return -1;
        if(src == des) return 0;
        v[src] = 1;
        long res = Integer.MAX_VALUE;
        for(int i=0;i<v.length;i++){
            if(v[i] == 0 && arr[src][i] > 0){
                long temp =  dfs(i,des,v,arr,k,done+1);
                if(temp != -1)
                res = Math.min(res, temp + arr[src][i]);
                }
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }
}
