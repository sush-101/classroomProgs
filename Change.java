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
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(),arr[] = new int[n],max = 0;
        for(int i=0;i<n;i++)arr[i] = sc.nextInt();
        int l=0,r=-1;
        while(l<n){
            int temp=0;
            while(l<n && arr[l]==0){l++;}
            while(l<n && arr[l]==1){
                temp++;l++;
            }
            if(l<n-1 && arr[l+1]==1){
                r=l+1;
                while(r<n && arr[r]==1){r++;temp++;}
                temp++;
            }
            max = Math.max(max,temp);
        }
        System.out.println(max);
    }
    
}
