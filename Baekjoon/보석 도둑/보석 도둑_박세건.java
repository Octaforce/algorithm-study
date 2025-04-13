import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static StringTokenizer st;
    private static StringBuilder sb = new StringBuilder();
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int N, K;
    private static List<int[]> jewelries = new ArrayList<>();
    private static List<Integer> bags = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        inputSetting();
    }

    private static void solution() {
        /*
         * 가방과 보석들을 정렬한다
         * 정렬된 데이터를 바탕으로 DP 알고리즘 적용
         * 현재의 순서의 보석을 현재의 순서의 가방에 넣을 수 없다면 다음의 가방에도 넣을 수 없음
         * 또한, 현재의 보석을 현재의 가방에 넣을 수 없다면, 다음 보석도 현재의 가방에 넣을 수 업음
         * */
    }

    private static void inputSetting() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            jewelries.add(new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
        }
        for (int i = 0; i < K; i++) {
            bags.add(Integer.parseInt(br.readLine()));
        }
    }
}
