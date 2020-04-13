/*Hackerland is a one-dimensional city with houses aligned at integral locations 
along a road. The Mayor wants to install radio transmitters on the roofs of 
the city's houses. Each transmitter has a fixed range meaning it can transmit 
a signal to all houses within that number of units distance away.

Given a map of Hackerland and the transmission range, determine the minimum 
number of transmitters so that every house is within range of at least one transmitter. 
Each transmitter must be installed on top of an existing house.

For example, assume houses are located at x = [1,2,3,5,9] and 
the transmission range k=1.
3 antennae at houses 2 and 5 and 9 would provide complete coverage. 
There is no house at location 7 to cover both 5 and 9. Ranges of coverage, 
are [1,2,3],[5], and [9]. 

Input Format
------------
The first line contains two space-separated integers n and k, the number of houses
and the range of each transmitter. 
The second line contains n space-separated integers describing the respective 
locations of each house x[i].

Output Format
-------------
Print a single integer denoting the minimum number of transmitters needed to cover 
all of the houses.

Sample Input: 
	8 2
	7 2 4 6 5 9 12 11

Sample Output:
	3

Explanation:
        _________________     _____________________     ___
                |                      |                 |
<-------o-------*---o----o----o--------*----------o------*------------------------->
	|	|	|	|	|	 |	  |    |   |   |	  |	     |	    |	   |	  |
	1	2	3	4	5	 6	  7	   8   9   10	 11	     12     13	   14	  15

We can cover the entire city by installing 3 transmitters on houses at locations 4, 9, and 12.
*/
import java.util.*;
class Towers{
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), k = sc.nextInt(),arr[] = new int[n];
		for(int i=0;i<n;i++)arr[i] = sc.nextInt();
       
        System.out.println(hackerlandRadioTransmitters(arr,k));
        
    }
	static int hackerlandRadioTransmitters(int[] a, int k) {
            int res=0;
            Integer ar[] = Arrays.stream(a).boxed().toArray(Integer[]::new);
        Set<Integer> set = new HashSet<Integer>(Arrays.asList(ar));
        int arr[] = set.stream().mapToInt(s->s).toArray();
        Arrays.sort(arr);
       int n = arr.length;
      int i=0;
        while(i<n){
            int cur = arr[i];
            int index = Arrays.binarySearch(arr,arr[i]+k);
            if(index < 0) index = -index -1 -1;
            res+=1;
            if(index==i){i++;continue;}
            int next = Arrays.binarySearch(arr,arr[index]+k);
            if(next < 0) next = -next -1 -1;
            if(index==next){i=index+1;continue;}
            i = next+1;
        }
        return res;
    }
}
