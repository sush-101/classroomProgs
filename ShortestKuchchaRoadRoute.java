/*

Detective Rust is investigating a homicide and he wants to chase down the murderer. 
The murderer knows he would definitely get caught if he takes the main roads for fleeing, 
so he uses the village roads (or side lanes) for running away from the crime scene.

Rust knows that the murderer will take village roads and he wants to chase him down. 
He is observing the city map, but it doesn't show the village roads (or side lanes) 
on it and shows only the main roads.

The map of the city is a graph consisting N nodes (labeled 1 to N) where a specific 
given node S represents the current position of Rust and the rest of the nodes denote 
other places in the city, and an edge between two nodes is a main road between two 
places in the city. It can be suitably assumed that an edge that doesn't exist/isn't 
shown on the map is a village road (side lane).
That means, there is a village road between two nodes a and b iff(if and only if) 
there is no city road between them.

In this problem, distance is calculated as number of village roads (side lanes)
between any two places in the city.

Rust wants to calculate the shortest distance from his position (Node S) to all 
the other places in the city if he travels only using the village roads (side lanes).

Note: The graph/map of the city is ensured to be a sparse graph.


Input Format
------------
 - First line has two integers N, denoting the number of cities in the map
and M, denoting the number of roads in the map.
 - The next M lines each consist of two space-separated integers x and y denoting a main 
road between city x and city y. 
 - The last line has an integer S, denoting the current position of Rust. 

Note
----
    - No nodes will have a road to itself.
    - There will not be multiple edges between any pair of nodes i.e. 
    there is at most one undirected edge between them.
    - It is guranteed that there will be a path between any pair of nodes using the side lanes.

Output Format
-------------
Print a single line consisting of N-1 space separated integers, 
denoting the shortest distances of the remaining N-1 places from Rust's position 
(that is all distances, except the source node to itself) using the village roads/side lanes 
in ascending order based on vertex number. 

Sample Input: 
4 3
1 2
2 3
1 4
1
Sample Output:
3 1 2

Explanation:
		
Graph shown like below
 S
(1)-------(4)
 |
 |
(2)-------(3)

Here the source node is 1 (marked S).
The distance from 1 to 2 is 3. Path: 1 -> 3 -> 4 -> 2
The distance from 1 to 3 is 1. Path: 1 -> 3
The distance from 1 to 4 is 2. Path: 1 -> 3 -> 4 
*/
import java.util.*;
class Node{
    int in,d;
    Node(int in,int d){
        this.in = in;
        this.d = d;
    }
}
class ShortestKuchchaRoadRoute{
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), n1 = sc.nextInt();
        HashMap<Integer,ArrayList<Integer>> map = new HashMap<>();
        while(n1--!=0){
            ArrayList<Integer> temp1,temp2;
            int a = sc.nextInt()-1,b = sc.nextInt()-1;
            if(map.get(a) == null){ temp1 = new ArrayList<>(); }
            else temp1 = map.get(a);
            if(map.get(b) == null){ temp2 = new ArrayList<>(); }
            else temp2 = map.get(b);
            temp1.add(b);
            temp2.add(a);
            map.put(a,temp1);
            map.put(b,temp2);
        }
        int S = sc.nextInt()-1;
        for(int i=0;i<n;i++){
           if(i==S)continue;
           System.out.print(getRoute(map,S,i,n)+" ");
        }
    }
    static int getRoute(HashMap<Integer,ArrayList<Integer>> map,int S,int D,int n){
        Queue<Node>  q = new LinkedList<>();
        int visited[] = new int[n];
        //System.out.println("At source "+S);
        q.add(new Node(S,0));
        visited[S] = 1;
        while(!q.isEmpty()){
            Node temp = q.remove();
            ArrayList<Integer> r = map.get(temp.in);
            for(int i=0;i<n;i++){
                if(visited[i]==0 && (r==null || !r.contains(i))){
                    if(i == D) return temp.d+1;
                    q.add(new Node(i,temp.d+1));
                    visited[i] = 1;
                }
            }
        }
        return Integer.MAX_VALUE;
    }
}
