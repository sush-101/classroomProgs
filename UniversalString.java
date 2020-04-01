/*kWe are given two arrays A and B of words.  Each word is a string of lowercase letters.

Now, say that word b is a subset of word a if every letter in b occurs in a, including multiplicity.  
For example, "wrr" is a subset of "warrior", but is not a subset of "world".

Now say a word 'a' from A is universal if for every 'b' in B, 'b' is a subset of 'a'. 

Return a list of all universal words in A.  You can return the words in alphabetical order.

 
Sample Input 1:
        leoc amazon apple facebook google 
        lo eo
Sample Output 1: 
        google leoc


Sample Input 2: 
        amazon apple facebook google leoc
        ec oc ceo
Sample Output 2: 
        facebook leoc
 

Note:
    1 <= A.length, B.length <= 10000
    1 <= A[i].length, B[i].length <= 10
    A[i] and B[i] consist only of lowercase letters.
    All words in A[i] are unique: there isn't i != j with A[i] == A[j].

*/
import java.util.*;
class UniversalString{
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        String A[] = sc.nextLine().split(" "), B[] = sc.nextLine().split(" ");
        int map[] = new int[26];
        mapIt(B,map);
        //for(int x:map)System.out.print(x+" ");System.out.println();
        Arrays.sort(A);
        for(String a:A){
            if(isAll(a,map)) System.out.print(a+" ");
        }
    }
    static boolean isAll(String a,int []map){
        HashMap<Character,Integer> map1 = new HashMap<>();
       
            for(char b:a.toCharArray())
            map1.put(b,map1.getOrDefault(b,0)+1);
            
            /*for(Character key:map1.keySet())System.out.print(key+" "+map1.get(key)+" ");
            System.out.println();*/
        for(int i=0;i<26;i++){
            char cur = (char)(i+'a');
            if(map[i] > 0 && (map1.get(cur) == null || map1.get(cur) < map[i])) return false;
        }
        return true;
    }
    static void mapIt(String B[],int map[]){
        for(String s:B){
            int temp[] = new int[26];
            for(char b:s.toCharArray()){
            temp[b-'a']++;
            }
            for(int i=0;i<26;i++){
                map[i] = Math.max(temp[i],map[i]);
            }
        }
    }
}
