/*Input Format:
    - The first line contains m and n. 
    - The next lines contain characters each describing the state of the land.
        'x' if it is a swamp and '.' otherwise
Output Format:
   - Output contains a single integer - the largest perimeter. If the rectangular fence cannot be built, 
     print -1
     
     
Sample Input:
3 4
....
.xx.
....

Sample Output:
10


Explanation:
Fence can be put up across the entire. The perimeter is 2*(3-1)+2*(4-1)=10


Sample Input:2
2 2
x.
.x

Output
-1

We need minimum of 4 points to place the 4 corners of the fence. Hence, print -1.


*/
import java.util.*;
import javafx.util.Pair;
class Field{
    static char arr[][];
    static ArrayList<ArrayList<Pair<Integer,Integer>>> l = new ArrayList<ArrayList<Pair<Integer,Integer>>>();
    public static void main (String[] args) {
        Scanner sc =  new Scanner(System.in);
        int r = sc.nextInt(),c = sc.nextInt();
        sc.nextLine();
        arr = new char[r][c];
        for(int i=0;i<r;i++){
            arr[i] = sc.nextLine().toCharArray();   
        }
        System.out.println(getMaxFence(r,c));
    }
    static void processPairs(int r,int c){
        for(int i=0;i<r;i++)l.add(new ArrayList<Pair<Integer,Integer>>());
        for(int i=0;i<r;i++){
            int j=0;
            while(j<c){
                while(j<c && arr[i][j]=='x'){j++;}
                int start = j;
                while(j<c && arr[i][j]=='.'){j++;}
                if(start!=j)l.get(i).add(new Pair<>(start,j-1));
            }
        }
    }
    static ArrayList<Pair<Integer,Integer>> getOverlaps(ArrayList<Pair<Integer,Integer>> a,ArrayList<Pair<Integer,Integer>> b){
        int i=0,j=0,alen = a.size(),blen = b.size();
        ArrayList<Pair<Integer,Integer>> res = new ArrayList<Pair<Integer,Integer>>();
        if(alen == 0 || blen == 0)return res;
        while(i<alen && j<blen){
            Pair<Integer,Integer> p1 = a.get(i), p2 = b.get(j);
            int x1 = p1.getKey(),x2 = p2.getKey(), y1= p1.getValue(), y2 = p2.getValue();
            if(x1>y2 || x2>y1){
                if(x1>y2){j++;}
                else i++;
                continue;
            }
            res.add(new Pair<Integer,Integer>(Math.max(x1,x2),Math.min(y1,y2)));
            if(y1<y2){i++;}
            else if(y2<y1){j++;}
            else {i++;j++;}
        }
        return res;
    }
    static long getMaxFence(int r,int c){
        processPairs(r,c);
        long max = -1;
        for(int i=0;i<r-1;i++){
            for(int j=i+1;j<r;j++){
                ArrayList<Pair<Integer,Integer>> temp = getOverlaps(l.get(i),l.get(j));
                for(Pair<Integer,Integer> p:temp){
                        if(isSafe(p.getValue(),p.getKey(),i+1,j-1)){
                        max = Math.max(max,2*((j-i)+(p.getValue()-p.getKey())));}
                }
            }
        }
        return max;
    }
    static boolean isSafe(int col1,int col2,int r1,int r2){
        if(col1 == col2)return false;
        if(r1>r2)return true;
        for(int i=r1;i<=r2;i++){
            if(arr[i][col1] == 'x' || arr[i][col2] == 'x')return false;
        }
        return true;
    }
}
