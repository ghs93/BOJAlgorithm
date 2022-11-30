import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] arr;
	static boolean[] visited;
    static boolean[] result;
    static int total;
    
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int TC = Integer.parseInt(br.readLine());
		
		for (int tc = 0; tc < TC; tc++) {
			int N = Integer.parseInt(br.readLine());
			
			total = 0;
			arr = new int[N+1];
			visited = new boolean[N+1];
			result = new boolean[N+1];
			
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++) {
				int target = Integer.parseInt(st.nextToken()); 
				arr[i] = target;
			}
			
			for (int i = 1; i <= N; i++) {
				if(!result[i])
					dfs(i);
			}
			
			sb.append(N-total).append('\n');
		}
		
		System.out.println(sb);
	}

	private static void dfs(int cur) {
		visited[cur] = true;
		
		int next = arr[cur];
		if(!visited[next]) {
			dfs(next);
			
		} else {
			if(!result[next]) {
				total++;
				
				while(cur != next) {
					total++;
					next = arr[next];
				}
			}
		}
		
		result[cur] = true;
	}
}