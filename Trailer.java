/*

Introduction to programming is considered as easiest subject among the students of first years 
(well ,usually). So they are not focusing much on lectures. When professor realises 
this he becomes very angry and plans to fail all the students of the batch.

He writes a very big number on the whiteboard and says : 
" This will be the minimum mark required to pass this course" . 
When students see this gigantic number written on the whiteboard most of them become 
pretty sure that they are going to fail this semester.

So the some of the students started trying to convince the professor to not do so. 
So the professor gives them a number K and says : 
" You can erase exactly K digits of the given number" .

Now students want to make this number as small as possible. 
So you being the second most intelligent student of the batch decide 
to solve the problem. Save the other students.

Input
The only line in the input contains one string denoting the number written on the whiteboard
and integer K.

Output
Output the minimum number you can make without any trailing zeroes.


Sample Input :
1023231 3

Sample output :
221

Explanation :
Need to remove 3 digits from the given number so 
First we need to remove starting 1 to take the advantage of preceeding zero which 
any way has no value being as first digit. 
So Number is 023231(23231)
Second Remove 3 Number becomes 2231
Third Remove 3 Number becomes 221
*/
import java.util.*;
class Ch{
    int index;
    char c;
    Ch(char c,int index){
        this.c = c;
        this.index = index;
    }
}
class Trailer{
    static int inf = Integer.MAX_VALUE;
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        int x=8;
       // while(x--!=0){
        String s = sc.next();
        int r = sc.nextInt(),k=0;
        if(r>=s.length()){
            System.out.println("0");
           
        }
        else System.out.println(recurse(s,r));
       // }
    }
    static int recurse(String s,int r){
        if(r>=s.length())  return 0;
        boolean flag = false;
        int i=0;
        while(i<r){
            if(s.charAt(i+1) -'0' == 0){ flag = true; break; }
            i++;
        }
        if(flag)
        return recurse(s.substring(i+2),r-i-1);
        else return remove(s,r);
    }
    static int remove(String s,int r){
        if(r>=s.length()) return 0;
        int n = s.length()-r+1,dp1[] = new int[n],i=0;
        Arrays.fill(dp1,inf);
        dp1[0] = 0;
       // dp1[1] = 3;
        while(i<s.length()){
            int dp2[] = new int[n];
            Arrays.fill(dp2,inf);
            dp2[0]=0;
            for(int j=1;j<n && j<=i+1;j++){
                int cur = dp1[j-1]*10 + (s.charAt(i) - '0');
               // if(cur<0)continue;
                dp2[j] = Math.min(cur,dp1[j]);
            }
            i++;
            //System.out.println(Arrays.toString(dp2));
            dp1 = dp2;
        }
        return dp1[n-1];
    }
    
    //naive way
    /*Ch arr[] = new Ch[s.length()];
        for(int i=0;i<s.length();i++) arr[i] = new Ch(s.charAt(i),i);
        Arrays.sort(arr,(o1,o2) -> o2.c-o1.c);
        Ch ans[] = new Ch[s.length()-r];
        for(int i=r;i<s.length();i++) ans[i-r] = arr[i];
        Arrays.sort(ans,(o1,o2) -> o1.index-o2.index);
        String a = "";
        for(Ch x:ans) a += x.c;
        return Integer.parseInt(a);*/
}
