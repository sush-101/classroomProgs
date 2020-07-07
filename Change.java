/*
Given an array, output the largest subarray with all 1s , one 0 can be changed to 1.
Do in O(n).
Input Format:
-------------
Line-1 -> An integer N
Line-2 -> N integers, only 0's and 1's

Output Format:
--------------
Print an integer as your result.


Sample Input-1:
---------------
10
1 1 0 1 1 1 0 1 1 1 

Sample Output-1:
----------------
7

*/
import java.util.*;
class Change{
    public static void main (String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(),arr[] = new int[n],max = 0,i=0;
        for(i=0;i<n;i++)arr[i] = scanner.nextInt();
        int fc=0,sc=0,temp=0;
        i=0;
        while(i<n){
            while(i<n && arr[i]==0){i++;}
            while(i<n && arr[i]==1){
                fc++;i++;
            }
            temp = fc+sc;
            if(i<n-1 && arr[i+1]==1)sc = fc+1;
            else sc = 0;
            fc = 0;
            max = Math.max(max,temp);
        }
        System.out.println(max);
    }
    
}
