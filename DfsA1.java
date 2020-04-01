/*Tyrion has recently qualified for Google's CodeJAM, 
and he can't keep his excitement with himself and share this news with 'K' of his friends.

One day Scrolling on Instagram Tyrion find a quote written :
 "The more the people knows, the more is the chances of FAILURE.", 
Now reading this he is scared and fears what if it will become true. 
However, he is well prepared for Google's CodeJAM even though he wants to 
find the number of peoples who had by now known the news.

Tyrion knows his friends and his friends friend (and so on) very well, 
he knows that his friends will share this news to every friend who has friendship strength ≤ 'S'.

You are given the graph representing the friendship and the friendship strength, 
find the number of people who knows this news. 

The graph can also be disconnected.

Input Format:
------------
    1st line:
    N M (N = No of total friends, M = total no of friendship bonds)
    Next M Lines contains 3 integers: a,b,c representing a and b are both friends with friendship strength of c.
    K S (K = no of friends with whom Tyrion shared the news, S = mentioned above)
    Next K space separated integers representing the friends whom Tyrion shared the news.
    k1,k2.....kk.
    
    
Output Format:
--------------
    Single Integer representing the number of friends who by now knows a news.


Constraints
-----------
1≤a,b≤N,
Ki ≤ N

Sample Input:
        5 5
        1 2 6
        1 3 3
        2 3 2
        3 4 6
        3 5 5
        2 5
        1 5

Sample Output:
        4

QUICK EXPLANATION:
-----------------
    Run a DFS from each reachable node. 
    If any neighbour has edge weight ≤S, 
    it is reachable and run a DFS from it. 
    Maintain a visited array so that you visit each node utmost once. 
    Print the number of nodes that were visited.*/
    import java.util.*;
class Node{
    int id,w;
    Node(int id,int e){
        this.id = id;
        w = e;
    }
}
class DfsA1{
    static int visited[],S;
    public static void main (String[] args) {
        Scanner sc  = new Scanner(System.in);
        int n = sc.nextInt(), d = sc.nextInt();
        ArrayList<Node> arr[] = new ArrayList[n];
        visited = new int[n];
        for(int i=0;i<n;i++)arr[i] = new ArrayList<>();
        while(d--!=0){
            int a = sc.nextInt()-1,b = sc.nextInt()-1, w = sc.nextInt(); 
            arr[a].add(new Node(b,w));
            arr[b].add(new Node(a,w));
        }
        int c =  sc.nextInt();
        S = sc.nextInt();
        int res = 0;
        while(c--!=0){
            res += dfs(sc.nextInt()-1,arr);
        }
        System.out.println(res);
    }
    static int dfs(int v,ArrayList<Node> arr[]){
        if(visited[v] == 1) return 0;
        int res = 1;
        visited[v] = 1;
        for(int i = 0;i<arr[v].size();i++){
            Node temp = arr[v].get(i);
            if(visited[temp.id] == 0 && temp.w <= S){
                res += dfs(temp.id,arr);
            }
        }
        return res;
    }
}
