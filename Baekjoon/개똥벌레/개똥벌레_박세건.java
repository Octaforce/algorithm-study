import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    private static StringTokenizer st;
    private static StringBuilder answerString = new StringBuilder();
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N, M;
    private static List<Integer> up = new ArrayList<>();
    private static List<Integer> down = new ArrayList<>();
    private static int[] lineCnt;

    public static void main(String[] args) throws IOException {
        inputSetting();
        solution();
        System.out.println(answerString);
    }

    private static void solution() {
        Collections.sort(up);
        Collections.sort(down);
        int before = 1;
        for (int i = 0; i < down.size(); i++) {
            int cur = down.get(i);
            /*
             * 오름차순으로 정렬시켰으니 만약 down 리스트의 사이즈가 5이고 처음 원소가 1이라면 1은 5번 모두 포함하고 있음
             * 이를 이용해서 슬라이딩 윈도우 느낌으로 진행하면 lineCnt에 적절한 값을 만들어줄 수 있지 않을까 생각해봄
             * */
        }
    }

    private static void inputSetting() throws IOException {
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        lineCnt = new int[N + 1];
        for (int i = 0; i < M; i++) {
            if (i % 2 == 0) down.add(Integer.parseInt(br.readLine()));
            else up.add(Integer.parseInt(br.readLine()));
        }
    }
}
