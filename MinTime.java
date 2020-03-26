/*
Abhinav is organizing a racing competation.N numbers of team (numbered as 1 to N) 
are participating in this race. Each team has M number of players. 
For each team M (team size) number of T-shirts are to be provided by the organizer. 
Each T-shirt contains a number(of that player) and may contain number of 
other player(s) of his own team.

At a time maximum P number of players(P≤M) can run in a ground.

Rules of race are following-
* Each player takes 1 minute to complete his race.
* Players(may be all) are dependent on some other players(the player(s)number written on his T-shirt) of his team.
* A player can only start the race if player(s) number written on his T-shirt have finished their race.
* Time taken by a team to complete the race is total time taken by players of team untill all have finished the race.
Time may be infinite.
* Team who will take minimum time will be winner.
* Your task is to find winner team.

Input Format:
-------------
* The first line of the input contains an integer N denoting the number of teams
* The second line contains integer M denoting number of players of a team and D denoting 
sum of count of numbers written on T-shirt of each player(excluding his number) of a team.
* The next D lines contain two integer A and B denoting B will finish only after A finished.
* Repeat the steps 2 and 3 N times.

Output Format:
--------------
Output a single integer in a separate line denoting the team number of winner team.
If all teams take infinite time to complete the race or in case of tie output "0"(without quote).

Sample Input:
2
4 3
1 3
3 2
4 2
3 3
1 2
1 3
2 1
Sample Output:
1

Explanation:

For Team-1:
Firstly 1 and 4 finish their race simultaneously and take 1 minute, because they haven't need to wait for others.
Now 3 will finish his race in 1 minute.Since, only 2 is left ,he will finish his race in 1 minute.

Total no of time taken by Team-1 =1+1+1=3 minutes.

For Team-2:
3 and 2 will wait to finish 1,but 1 is also waiting for 2. In this situation no one can start race. Hence, 
Team-2 will take infinite time.

Team 1 will be winner.
*/
import java.util.*;
class Player{
    int indg;
    ArrayList<Integer> al;
    Player(int indg,ArrayList<Integer>al){
        this.indg = indg;
        this.al = al;
    }
}
class MinTime{
    public static void main (String[] args) {
        Scanner sc= new Scanner(System.in);
        int n = sc.nextInt(), ltime = Integer.MAX_VALUE, wteam = 0;
        for(int i =0 ;i<n; i++){
            int M = sc.nextInt(),D = sc.nextInt();
            Player arr[] = new Player[M];
            for(int k=0;k<M;k++)
            arr[k] = new Player(0,new ArrayList<Integer>());
            while(D--!=0){
                int a = sc.nextInt()-1,b = sc.nextInt()-1;
                arr[b].indg++;
                arr[a].al.add(b);
            }
            int time = calc(arr,M);
            if(time < ltime){
                ltime = time;
                wteam = i+1;
            }
            else if(time == ltime) wteam = 0;
        }
        System.out.println(wteam);
    }
    static int calc(Player arr[],int n){
        int count = 0,res = 0;
        Queue<Player> q = new LinkedList<>();
        while(true){
            for(int i=0;i<n;i++){
                if(arr[i].indg == 0){
                   // System.out.println("adding "+i);
                q.add(arr[i]);
                arr[i].indg--;
                count++;
                }
            }
            if(q.isEmpty()) break;
            res++;
           while(!q.isEmpty()){
               Player temp = q.remove();
               for(int i=0;i<temp.al.size();i++){
                   arr[temp.al.get(i)].indg--;
               }
           }
        }
        if(count!=n)return Integer.MAX_VALUE;
        return res;
        
    }
    
}
