/*
Roy is the owner of a flower plant farm and sells flower plants for a living.
Now the problem with flower plants is that they wither in a night if not taken care of. 
So at the end of the day, to make flower plants wither-resistant Roy uses special fertilizers.

There are N number of plants not sold on one particular day (say today), 
but Roy can sell them the next day (say tomorrow) if he fertilizes them tonight.
Roy sells a flower plant for Rs. X. Cost of fertilizing a plant is Rs. Y.
Currently Roy has P Rupees with him.

Given the selling price X and fertilizing cost Y of N plants, 
your task is to find minimum amount A (A ≤ P) 
that he should spend in fertilizing plants such that the total money B he has 
after selling plants on the next day is maximized. 

Assume that all the fertilized plants are sold on the next day.

Input Format:

First line contains two space separated integers N and P.
Following N lines each contain two space separated integers X and Y.

Output Format:
Print the two space separated integers A and B in a new line. 


Sample Input :
    5 100
    100 40
    50 40
    40 50
    60 20
    100 50

Sample Output :
    90 210


Explanation :
    Profit for each of the plants is 60, 10, -10, 40, 50 respectively. He has Rs. 100 with him. 
    The optimal selection would be fertilize first and last plant. 
    So Minimum Amount to spend is Rs. 90 and 
    total money he has after selling the plants is 100 - 40 - 50 + 100 + 100 =210

*/
import java.util.*;
class Flower{
     int x,y;
     Flower(int x,int y){
         this.x = x;
         this.y = y;
     }
     @Override
     public String toString(){
        return "[ "+this.x +" "+this.y+" ]";
     }
}
class Flower{
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), P = sc.nextInt(),n1=n,i=0,k=0;
        Flower arr[] = new Flower[n];
        while(i<n){
            int x = sc.nextInt(),y = sc.nextInt();
            if(x <= y){ n1--; }
            
            else arr[k++] = new Flower(x,y);
            i++;
        }
        getMax(arr,P,n1);
        }
    static void getMax(Flower arr[],int P,int n){
        int k=0;
        Flower dp1[] = new Flower[P+1];
        int max=Integer.MIN_VALUE,min = Integer.MAX_VALUE;
        for(int i=0;i<=P;i++) dp1[i] = new Flower(0,0);
        while(k<n){
            Flower dp2[] = new Flower[P+1];
            for(int i=0;i<=P;i++){
            if(arr[k].y > i) dp2[i] = dp1[i];
            else{
                int takecost = arr[k].y + dp1[i - arr[k].y].y, dtakecost = dp1[i].y;
                int take = arr[k].x + dp1[i - arr[k].y].x, dtake = dp1[i].x;
                if(take >= dtake ){
                    dp2[i] = new Flower(take, take == dtake ? Math.min(takecost,dtakecost): takecost);
                    if(max < dp2[i].x + P - takecost){
                        max = dp2[i].x + P - takecost;
                        min = dp2[i].y;
                        }
                    else if(max == take + P - takecost) min = Math.min(min,dp2[i].y);
                     }
                else dp2[i] = dp1[i];
             }
            }
            /*for(int l=10;l<=P;l=l+10)
            System.out.print(dp2[l]+" ");
            System.out.println();*/
            dp1 = dp2;
            k++;
        }
        System.out.println(min+ " "+max);
    }
}
