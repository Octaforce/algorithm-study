import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //파일 두 개를 하나로 합쳐감
        //최종 하나 파일 만들 때 필요한 비용의 총 합 중 최소 비용

        int T = Integer.parseInt(br.readLine());

        for(int t = 1; t <= T; t++){
            int K = Integer.parseInt(br.readLine());

            PriorityQueue<Long> pq = new PriorityQueue<>();

            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i = 0; i < K; i++){
                pq.add(Long.parseLong(st.nextToken()));
            }

            //큰 값은 최대한 한 번만 더해야함
            //작은거 우선으로 더하기

            Long res = (long) 0;
            //두 개를 빼야하니까
            while(pq.size() > 1){
                Long file1 = pq.poll();
                Long file2 = pq.poll();

                res += file1 + file2;

                //이렇게 하면 계속 누적되어 잘못된 값이 됨
                //pq.add(res);
                pq.add(file1+ file2);
            }

            System.out.println(res);


        }


    }
}
