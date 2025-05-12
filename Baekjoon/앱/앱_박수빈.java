import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] costs, memories;

    public static void main(String[] args) throws IOException {
        //m이 메모리
        //c가 활성화시킬 때 필요한 비용

        //몇 개 비활해서 M 메모리 공간 확보 + 최소의 c
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        memories = new int[N];
        costs = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            memories[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            costs[i] = Integer.parseInt(st.nextToken());
        }
        //recur(0,0,0);

        // dp[cost] = 해당 비용으로 확보할 수 있는 최대 메모리
        int[] dp = new int[10001];

        for(int i=0; i<N;i++) {
            for (int j = 10000; j >= costs[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - costs[i]] + memories[i]);
            }
        }
        // 최소 비용 찾기
        for (int i = 0; i <= 10000; i++) {
            if (dp[i] >= M) {
                System.out.println(i);
                break;
            }
        }

    }

    //2^100 ~~ 10^30 -> 시간초과
//    static void recur(int cur, int costSum, long memorySum){
//        if(cur == N){
//            if(memorySum >= M)
//                res = Math.min(res, costSum);
//            return;
//        }
//
//        //안고르거나
//        recur(cur+1, costSum,memorySum);
//        //고르거나
//        recur(cur+1,costSum+costs[cur],memorySum+memories[cur]);
//    }
}
