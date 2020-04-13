/*A histogram is a polygon composed of a sequence of rectangles aligned at a common base line.
For simplicity, assume that the rectangles have equal widths but may have different heights. 
For	example, the figure on the left shows a histogram that consists of rectangles 
with the heights 3, 2, 5, 6, 1, 4, 4, measuredin units where 1 is the width of the rectangles.

Here our problem is:given an array with heights of rectangles (assuming	width is 1),
we need to find the largest rectangle.
In below diagram, each line(|) indicates one small rectangle.

         |						 		 |
      |  |					          |==|
      |  |     |  |				      |==|     |  |
|     |  |     |  |				|     |==|     |  |
|  |  |  |     |  |			    |  |  |==|     |  |
|  |  |  |  |  |  |				|  |  |==|  |  |  |
3  2  5  6  1  4  4				3  2  5  6  1  4  4
Sample Input:
	7
	3 2 5 6 1 4 4
Sample Output:
	10

Explanation: 
Here using 5 and 6 rectangles. 
we can make maximum rectangle choosing height "5" from each rectangle.
so, 5*2 = 10. 
*/
import java.util.*;
class Node{
    int index,val;
    Node(int index,int val){
        this.index = index;
        this.val = val;
    }
}
class LargestRectangularAreaInHistogram{
    static int res = Integer.MIN_VALUE;
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int a = 8;
        while(a--!=0){
        res  = Integer.MIN_VALUE;
        int n = sc.nextInt(),arr[] = new int[n];
        Node st[] = new Node[4*n];
        for(int i=0;i<n;i++){
            arr[i] = sc.nextInt();
        }
        buildTree(st,arr,0,n-1,1);
        getMax(arr,st,0,n-1,n);
        System.out.println(res);
        }
    }
    static void getMax(int arr[],Node st[],int left,int right,int n){
        //System.out.println(left+" "+right);
        if(left > right)return;
        Node min = query(st,1,0,n-1,left,right);
        int index = min.index;
        int value = min.val;
        //System.out.println("found "+value +" at "+index+" left "+left+" right "+right );
        res = Math.max(res,(right-left+1)*value);
        getMax(arr,st,left,index-1,n);
        getMax(arr,st,index+1,right,n);
    }
    static void buildTree(Node st[],int arr[],int start,int end,int treeNode){
        if(start == end){
            st[treeNode] = new Node(start,arr[start]);return;
        }
        int mid = (start+end)/2;
        buildTree(st,arr,start,mid,2*treeNode);
        buildTree(st,arr,mid+1,end,2*treeNode+1);
        if(st[2*treeNode].val<st[2*treeNode+1].val)
        st[treeNode] = new Node(st[2*treeNode].index,st[2*treeNode].val);
        else st[treeNode] = new Node(st[2*treeNode+1].index,st[2*treeNode+1].val);
    }
    static Node query(Node st[],int treeNode,int start,int end,int left,int right){
       
        if(start > right || end < left) return new Node(-1,Integer.MAX_VALUE);
        if(start >= left && end <= right) return st[treeNode];
        int mid = (start+end)/2;
        Node a = query(st,2*treeNode,start,mid,left,right);
        Node b = query(st,2*treeNode+1,mid+1,end,left,right);
        return a.val<b.val ? a : b;
    }
}
