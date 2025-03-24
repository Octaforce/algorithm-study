import java.util.*;
import java.io.*;

class Solution {
    int[] dp;
    public int solution(int n, int[] tops) {
        dp=new int[n*2+2];
        dp[0]=1;
        dp[1]=1;
        for(int i=2;i<=n*2+1;i++){
            dp[i]=dp[i-1]+dp[i-2];
            if(i%2==0&&tops[i/2-1]==1){
                dp[i]+=dp[i-1];
            }
            dp[i]%=10007;
        }
        return dp[n*2+1];
    }
}
