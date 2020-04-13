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