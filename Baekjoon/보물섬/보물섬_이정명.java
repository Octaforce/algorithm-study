import java.io.*;
import java.util.*;

public class Main {
	static final int[] dirX = {-1,1,0,0};	//상하좌우
	static final int[] dirY = {0,0,-1,1};
	static int N, M;
	static int[][] map;

	private static int search(int x, int y) {
		ArrayDeque<int[]> q = new ArrayDeque<>();

		q.offer(new int[] {x,y,0});
		
        int result = 0;
		boolean[][] visited = new boolean[N][M];

		visited[x][y] = true;	//시작점 방문 처리

		while (!q.isEmpty()) {
			int[] poll = q.poll();

			if (result < poll[2]) {
				result = poll[2];
			}
            
			for (int d = 0; d < 4; d++) {
				int dx = poll[0] + dirX[d];
				int dy = poll[1] + dirY[d];
				
				if(0<=dx && dx<N && 0<=dy && dy<M && map[dx][dy] == 0 && !visited[dx][dy]) {
					visited[dx][dy] = true;
					q.offer(new int[] {dx, dy, poll[2] + 1});
				}
			}
		}

        return result;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		char[] ch;
		for (int i = 0; i < N; i++) {
			ch = br.readLine().toCharArray();
			for (int j = 0; j < M; j++) {
				if (ch[j] == 'W') map[i][j] = 1;	// 이동할 수 없는 곳을 1로 초기화 (이동할 수 있는 0은 자동 초기화)
			}
		}
		int result = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 1) continue;
				result = Integer.max(result, search(i, j));
			}
		}

		//최장 거리를 출력한다.
		System.out.println(result);
	}
}