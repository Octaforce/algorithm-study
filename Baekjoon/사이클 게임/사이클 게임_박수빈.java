import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        parents = new int[n];
        for(int i=0;i<n;i++){
            parents[i] = i;
        }

        for(int i=0;i<m;i++){
            st = new StringTokenizer(br.readLine());
            //이어지는 점
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            //어느 세 점도 일직선 위에 x
            //양방향
            //사이클 생기면 몇번째에서 끝났는지 / 안끝났다면 0
            if(find(a) == find(b)){
                System.out.println(i+1);
                return;
            }else{
                union(a,b);
            }

        }
        System.out.println(0);
    }


    static void union(int a, int b){
        a = find(a);
        b = find(b);

        if(a < b){
            parents[b] = a;
        }else{
            parents[a] = b;
            }
        }

    static int find(int a){
        if(parents[a]==a){
            return a;
        }
        return parents[a] = find(parents[a]);
    }

}
