/*
Ameeta Mehra owns S horses. He wants to keep only horses that can run very fast. 
He decides which horses are fast by making them race against each other. 
If a horse comes in last, then Ameeta Mehra will sell it.

The race course consists of N checkpoints and E roads. Ameeta Mehra positions his horses at 
the first S checkpoints i.e., checkpoints 1 to S. The checkpoint N is chosen as the finish line. 
The horses need 1 unit of time to cross one road. 
If the horses have two or more paths to choose, then they choose the one which is shortest. 
The horses start the race at the same instant. When a horse reaches checkpoint N, it finishes 
the race.

Find the total number of horses that Ameeta Mehra will sell i.e., 
the number of horses that come in last.

NOTE : The roads are bidirectional.

Input Format:
-------------
The first line of the input consists of S (the number of horses), N (the number of checkpoints) 
and E (the number of roads) Each of the next E lines contains a and b, indicating that there is 
a road between checkpoints a and b.

Output Format:
--------------
Print a single integer, the number horses that come in last.

Constraints
-----------
1 ≤ S < N ≤ 300000
1 ≤ E ≤ 300000
1 ≤ a , b ≤ N

Sample Input:
        3 5 4
        1 5
        2 4
        3 4
        5 4
Sample Output:
        2

Explanation

    There are 3 horses, each starting at checkpoint 1, 2 and 3 respectively. 
    Checkpoint 5 is the finish line. The horse starting at checkpoint 1 finishes the race first, 
    in 1 unit of time. The horses at 2 and 3 come in last and finish the race at the same instant. 
    Hence, Ameeta Mehra will sell 2 horses.
*/
import java.util.*;
class Node{
    int id,wt;
    Node(int id,int wt){
        this.id = id;
        this.wt = wt;
    }
}
class Dij{
    static int dist[],visited[];
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), s = sc.nextInt()-1, d = sc.nextInt();
        int res = 0,max = Integer.MIN_VALUE;
        dist = new int[s+1];
        visited = new int[s+1];
        Arrays.fill(dist,Integer.MAX_VALUE);
        ArrayList<Integer> arr[] = new ArrayList[s+1];
        for(int i=0;i<=s;i++) arr[i] = new ArrayList<Integer>();
        while(d--!=0){
            int a = sc.nextInt() - 1, b = sc.nextInt() - 1;
            arr[a].add(b);
            arr[b].add(a);
        }
        findAll(arr,s);
        for(int i=0;i<n;i++){
           if(dist[i] > max){
               res = 1;
               max = dist[i];
           } 
           else if(dist[i] == max) res++; 
        }
       System.out.println(res);
        }
    static void findAll(ArrayList<Integer> arr[],int s){
        TreeSet<Node> set = new TreeSet<Node>(new Comparator<Node>(){
            @Override
            public int compare(Node  o1,Node o2){
                return o1.wt == o2.wt ? o1.id - o2.id :  o1.wt-o2.wt;
            }
            
        });
        set.add(new Node(s,0));
        dist[s] = 0;
        while(!set.isEmpty()){
            Node temp = set.pollFirst();
            int cur = temp.id, d = temp.wt;
            visited[cur] = 1;
            for(int i=0;i<arr[cur].size();i++){
                int v = arr[cur].get(i);
                if(visited[v] == 0 && dist[v] > d + 1){
                    Node temp1 = new Node(v,dist[v]);
                    if(set.contains(temp1)) set.remove(temp1);
                    set.add(new Node(v,d+1));
                    dist[v] = d+1;
                }
            }
        }
    }
    
}
