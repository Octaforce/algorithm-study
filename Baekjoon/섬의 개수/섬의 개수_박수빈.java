import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int w, h, res;
    static int[][] ground;
    static boolean[][] visited;
    static int[] dr = {-1,-1,-1,0,0,1,1,1};
    static int[] dc = {-1,0,1,-1,1,-1,0,1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while (true) {
            res = 0;
            st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            if(w == 0 && h == 0)
                break;

            ground = new int[h][w];

            for(int i = 0; i < h; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < w; j++) {
                    ground[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            visited = new boolean[h][w];
            for(int i = 0; i < h; i++) {
                for(int j = 0; j < w; j++) {
                    if(ground[i][j] == 1 && !visited[i][j]){
                        bfs(i, j);
                        res++;
                    }
                }
            }

            System.out.println(res);

        }
    }

    static void bfs(int i, int j) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{i, j});

        while(!queue.isEmpty()) {
            int[] now = queue.poll();

            for(int k=0;k<8;k++){
                int nextR = now[0] + dr[k];
                int nextC = now[1] + dc[k];

                if(nextR < 0 || nextR >= h || nextC < 0 || nextC >= w || visited[nextR][nextC] || ground[nextR][nextC] == 0)
                    continue;

                visited[nextR][nextC] = true;
                queue.add(new int[]{nextR, nextC});
            }
        }
    }


}
