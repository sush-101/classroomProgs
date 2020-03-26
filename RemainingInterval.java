/*
Protection of the Indian border and safe transport of items from one point 
to another along the border are the paramount jobs for the Indian army. 
However they need some information about the protection status along the 
length of the border. The border can be viewed as the real x-axis. 
Along the axis, Indian army has N checkpoints for lookout.

We know that each checkpoint is located at an integer location xi. 
Each checkpoint must have a fleet of armed men which are responsible 
for guarding the neighboring areas of the checkpoint and provide 
military assistance of all kinds. The size of the fleet is based on 
the location of the checkpoint and how active the region is for terrorist activities.

Given the number of armed men assigned at the ith checkpoint, as pi, 
this information is available for all checkpoints.
With the skills of the armed men, it is known that if for the ith checkpoint, 
the length on the x axis that they can defend is a closed interval [xi-pi, xi+pi].

Now, your task is to transport some military items from position S to 
the end position E on the x-axis. 

Input Format
------------
First line of the input contains 3 integers N, S and E. 
N is the number of checkpoints that the Indian Army has on the border.
Then N lines follow. ith line contains 2 integers, xi and pi.

Output Format
-------------
Print the total distance of the x-axis from S to E, that is not protected by the armed forces.

Sample Input
    5 229 8419
    1795 95
    4873 720
    3149 81
    6101 2325
    3674 629
Sample Output
    2626
*/
import java.util.*;
class RemainingInterval{
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(),S = sc.nextInt(),E = sc.nextInt();
        HashMap<Integer,Integer> map = new HashMap<>();
        while(n--!=0){
            int point  = sc.nextInt(),range = sc.nextInt();
            int start = point - range,end = point + range;
            if(end < S || start > E)
            continue;
            if(map.get(start)!=null){
                map.put(start, map.get(start)>end ? map.get(start):end);
            }
            else map.put(start,end);
        }
        TreeMap<Integer,Integer> map1 = new TreeMap<>(map);
        System.out.println(findDis(map1,S,E));
    }
    static int findDis(TreeMap<Integer,Integer> map , int S,int E){
        int res = 0,start = S;
        for(Integer key:map.keySet()){
            if(start > E ) break;
            if(key > start){
                res += key-start;
                start = map.get(key);
            }
            else{
                start = map.get(key) > start ? map.get(key) : start;
            }
        }
        if(start < E) res += E-start;
        return res;
        
    }
}
