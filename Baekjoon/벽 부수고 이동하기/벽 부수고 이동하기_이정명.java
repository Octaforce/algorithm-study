import java.io.*;
import java.util.*;

public class Main {
	static final int[] dirX = {-1,1,0,0};	//상하좌우
	static final int[] dirY = {0,0,-1,1};
	static int N, M;
	static int[][] map;
	static int[][][] check;

	private static int search() {
		ArrayDeque<int[]> q = new ArrayDeque<>();
        // {0,0,1,1}
        // x : start x
        // y : start y
        // cnt : 이동 횟수
        // wall : 벽 파괴 가능 횟수
		q.offer(new int[] {0,0,1,1});
		
        int result = Integer.MAX_VALUE;
		
		while (!q.isEmpty()) {
			int[] poll = q.poll();
            
			// (N, M)의 위치까지 이동
			if (poll[0] == N-1 && poll[1] == M-1) {
				result = Math.min(poll[2], result);
			}
			
			for (int d = 0; d < 4; d++) {
				int dx = poll[0] + dirX[d];
				int dy = poll[1] + dirY[d];
				
				if(0<=dx && dx<N && 0<=dy && dy<M) {
					if(map[dx][dy] != 1) {
						if(check[dx][dy][poll[3]] > poll[2]) {
							check[dx][dy][poll[3]] = poll[2];
							q.offer(new int[] {dx,dy,poll[2]+1,poll[3]});
						}
					} else {
						if (poll[3] == 0)continue;
						
						if(check[dx][dy][poll[3]] > poll[2]) {
							check[dx][dy][poll[3]] = poll[2];
							q.offer(new int[] {dx,dy,poll[2]+1,poll[3]-1});
						}
					}
				}
			}
		}

        return result;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());	//N(1 ≤ N ≤ 1,000)
		M = Integer.parseInt(st.nextToken());	//M(1 ≤ M ≤ 1,000)
		
		map = new int[N][M];
		check = new int[N][M][2];
		
		char[] ch;
		for (int i = 0; i < N; i++) {
			ch = br.readLine().toCharArray();
			for (int j = 0; j < M; j++) {
				//0은 이동할 수 있는 곳을 나타내고, 1은 이동할 수 없는 벽이 있는 곳
				map[i][j] = ch[j] - '0';	//N(1 ≤ N ≤ 1,000), 문자를 숫자로 변환
				Arrays.fill(check[i][j], Integer.MAX_VALUE);
			}
		}
		int result = search();

		//첫째 줄에 최단 거리를 출력한다. 불가능할 때는 -1을 출력한다.
		System.out.println(result==Integer.MAX_VALUE?-1:result);
	}
}