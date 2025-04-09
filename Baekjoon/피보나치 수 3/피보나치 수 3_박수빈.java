import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int pisano = 1500000;
        long n = Long.parseLong(br.readLine()) % pisano;

        //최대 n을 크기로 선언할 수 x
        //-> 피사노 주기 이용
        //주기를 p라고 하면,n번째 피보나치 수를 m으로 나눈 나머지는
        //n%p번째 피보나치수를 m으로 나눈 나머지와 같음
        //p = 15 * 10^(k-1) , k = log(m). k>2

        long[] dp = new long[pisano +1];

        dp[0] = 0;
        dp[1] = 1;

        for(int i=2;i<=pisano;i++){
            dp[i] = (dp[i-1]+dp[i-2]) % 1000000;
        }
        System.out.println(dp[(int)n]);
    }
}
