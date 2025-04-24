// [version 1.0] - priority queue
import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(st.nextToken());
        for (int t = 0; t < T; t++) {
        	st = new StringTokenizer(br.readLine());
        	int N = Integer.parseInt(st.nextToken());
        	int cnt = 0;
        	
        	PriorityQueue<int[]> q = new PriorityQueue<>(new Comparator<int[]>() {
        		@Override
        		public int compare(int[] o1, int[] o2) {
        			return Integer.compare(o1[0], o2[0]);
        		}
			});
        	
        	for (int i = 0; i < N; i++) {
        		st = new StringTokenizer(br.readLine());
				q.add(new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
			}
        	
        	int cur = Integer.MAX_VALUE;
        	while (!q.isEmpty()) {
				int[] poll = q.poll();
				
				if (cur > poll[1]) {
					cur = poll[1];
					cnt++;
				}
			}
        	sb.append(cnt).append("\n");
		}
        
        System.out.println(sb);
        br.close();
	}
}

// [version 2.0] - 배열

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringBuilder sb = new StringBuilder();
	    StringTokenizer st = new StringTokenizer(br.readLine());

	    int T = Integer.parseInt(st.nextToken());
	    
	    for (int test_case = 0; test_case < T; test_case++) {
	    	st = new StringTokenizer(br.readLine());
	    	int N = Integer.parseInt(st.nextToken());
	    	
	    	int[] num = new int[N];
	    	
	    	for (int i = 0; i < N; i++) {
	    		st = new StringTokenizer(br.readLine());
	    		int A = Integer.parseInt(st.nextToken()) - 1;
	    		int B = Integer.parseInt(st.nextToken()) - 1;
		    	
	    		num[A] = B;
			}
	    	
	    	int check = num[0], result = 1;
			
	    	for (int i = 1; i < N; i++) {
				if(num[i] < check) {
					check = num[i];
					result++;
				}
			}
	    	sb.append(result + "\n");
		}
	    System.out.println(sb);
	    br.close();
	}
}