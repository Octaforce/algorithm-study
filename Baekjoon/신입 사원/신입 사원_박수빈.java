import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T  = Integer.parseInt(br.readLine());
        for(int t = 1; t <= T; t++){
            int N  = Integer.parseInt(br.readLine());

            int[] scores = new int[N];
            //인덱스가 시험성적 순위, 값이 그 사람의 면접 점수
            StringTokenizer st;

            //인덱스를 기억하고 있어야함
            //그냥 완전탐색은 터짐

            for(int i= 0; i < N; i++){
                st = new StringTokenizer(br.readLine());
                int score = Integer.parseInt(st.nextToken());
                int interview = Integer.parseInt(st.nextToken());
                scores[score-1] = interview;
            }

            //시험 점수는 자동적으로 정렬이 된 상태 (인덱스)
            int res = 1; //맨 처음사람은 무조건 합격
            int rating = scores[0];

            for(int i = 1; i < N; i++){
                //어떤 한 명보다 면접 점수가 높아야 함
                if(rating > scores[i]){
                    res++;
                    rating = scores[i];
                }
            }
            System.out.println(res);

        }


    }
}
