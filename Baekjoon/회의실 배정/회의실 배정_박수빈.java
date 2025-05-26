import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[][] meetings = new int[N][2];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            meetings[i][0] = Integer.parseInt(st.nextToken());
            meetings[i][1] = Integer.parseInt(st.nextToken());
        }

        //최대한 회의 많이
        //끝남과 동시에 시작도 ok

        //하나씩 고르고 안고르고 재귀로 돌리면 2^N이라 안될 거 같음
        //끝나는 시간 빠른순(오름차순) -> 같다면, 시작시간 오름차순

        Arrays.sort(meetings, new Comparator<int[]>() {
            public int compare(int[] o1, int[] o2) {
                if(o1[1] == o2[1])
                    return o1[0] - o2[0];

                return o1[1] - o2[1];
            }
        });

        int res = 1;
        int endTime = meetings[0][1];

        for(int i = 1; i < N; i++) {
            if(meetings[i][0] >= endTime) {
                res++;
                endTime = meetings[i][1];
            }
        }
        System.out.println(res);

    }
}
