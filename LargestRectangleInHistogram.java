/*
A histogram is a polygon composed of a sequence of rectangles aligned at a common base line.
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
    int index,ht;
    Node(int index,int ht){
        this.index = index;
        this.ht = ht;
    }
}
class LargestRectangleInHistogram{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int n= sc.nextInt(),arr[] = new int[n]; 
        for(int i=0;i<n;i++)arr[i] =sc.nextInt();
        System.out.println(largestRectangleArea(arr));
    }
    static int largestRectangleArea(int[] arr) {
        int res = 0,n=arr.length,left =-1,right=-1;
        Stack<Node> stack = new Stack<>();
        for(int i=0;i<n;i++){
            if(stack.isEmpty())stack.push(new Node(i,arr[i]));
            else{
              while(!stack.isEmpty() && stack.peek().ht > arr[i]){
                  Node x = stack.pop();
                  while(!stack.isEmpty() && stack.peek().ht == x.ht){stack.pop();}
                  if(stack.isEmpty())left = -1;
                  else {
                      left = stack.peek().index;
                  }
                  res = Math.max(res,x.ht*(i-left-1));
              }
              stack.push(new Node(i,arr[i]));
            }
        }
        if(!stack.isEmpty())right = stack.peek().index;
        while(!stack.isEmpty()){
            Node x = stack.pop();
            if(stack.isEmpty())left = -1;
            else left = stack.peek().index;
            res = Math.max(res,x.ht*(right-left));
        }
        return res;
    }
}
