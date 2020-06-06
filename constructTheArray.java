https://www.hackerrank.com/challenges/construct-the-array/problem


static int MOD = (int)Math.pow(10,9)+7;
    // Complete the countArray function below.
    static long countArray(int n, int k, int x) {
        long oneC=0,noneC=0;
        if(x == 1)oneC = 1;
        else noneC = 1;
        for(int i=n-2;i>=1;i--){
            long poneC = oneC;
            oneC = noneC;
            noneC = ((poneC*(k-1))%MOD + (noneC*(k-2))%MOD)%MOD;
          
        }
        //noneOneCount = ((prevoneCount*(k-1))%MOD + (noneOneCount*(k-2))%MOD)%MOD;
        return noneC;
    }
