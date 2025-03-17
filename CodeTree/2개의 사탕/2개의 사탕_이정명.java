import java.io.*;
import java.util.*;

public class Main {
	static final int[] DIR_X = new int[] {0, -1, 0, 1};
	static final int[] DIR_Y = new int[] {-1, 0, 1, 0};

	// 같은 줄에 있을 때에만 공통으로 고려 (우선순위? -> 같이 이동)
	static boolean[][] map;
	
	static class Candy {
		boolean goal;
		int x, y;
		
		public Candy(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static class State {
		int cnt;
		Candy candyR;
		Candy candyB;
		
		public State(int cnt, Candy candyR, Candy candyB) {
			this.cnt = cnt;
			this.candyR = candyR;
			this.candyB = candyB;
		}
	}
	
	static Candy move(int[] goal, int dir, Candy candy) {
		if (goal[0] == candy.x && goal[1] == candy.y) {
			candy.goal = true;
			return candy;
		}
		
		if (map[candy.x + DIR_X[dir]][candy.y + DIR_Y[dir]]) {
			map[candy.x][candy.y] = true;
		} else {
			candy.x += DIR_X[dir];
			candy.y += DIR_Y[dir];					
		}
		
		return candy;
	}
	
	// 간단한건 DFS, 효율적인건 BFS
	static int reachedGoal(int[] goal, Candy candyR, Candy candyB) {
		int result = -1;
		
		ArrayDeque<State> q = new ArrayDeque<>();
		
		q.offer(new State(1, candyR, candyB));
		
		while (!q.isEmpty()) {				// deque가 빌때까지
			State poll = q.poll();
			
			for (int d = 0; d < 4; d++) {	// 전방향 진행
				Candy curCandyR = new Candy(poll.candyR.x, poll.candyR.y);
				Candy curCandyB = new Candy(poll.candyB.x, poll.candyB.y);
				while (!( (curCandyR.goal || map[curCandyR.x][curCandyR.y]) && (curCandyB.goal || map[curCandyB.x][curCandyB.y]) )) {
					if(!curCandyR.goal) curCandyR = move(goal, d, curCandyR);
					if(!curCandyB.goal) curCandyB = move(goal, d, curCandyB);
				}
        
				if (curCandyR.goal && !curCandyB.goal) return poll.cnt;
				map[curCandyR.x][curCandyR.y] = false;
				map[curCandyB.x][curCandyB.y] = false;
				if (poll.cnt < 10 && !curCandyR.goal && !curCandyB.goal) q.offer(new State(poll.cnt+1, curCandyR, curCandyB));
			}
		}
		
		return result;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int result = -1;

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] goal = new int[2];
		Candy candyB = null, candyR = null;
		map = new boolean[N][M];	// 벽 검사 전용 지도
		
		// 맵 작성
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			char[] v = st.nextToken().toCharArray();
				
			for (int j = 0; j < M; j++) {
				switch (v[j]) {
				case '#':
					map[i][j] = true;
					break;
				case 'O':					
					goal = new int[] {i, j};
					break;
				case 'R':
					candyR = new Candy(i, j);
					break;
				case 'B':
					candyB = new Candy(i, j);
					break;
				default:
					break;
				}
			}
		}
		
		System.out.println(reachedGoal(goal, candyR, candyB));
		br.close();
	}
}