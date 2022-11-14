import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	static int[] parents;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[R][C];
		int[][] swan = new int[2][2];
		
		int l = 0;
		for (int i = 0; i < R; i++) {
			String s = br.readLine();
			for (int j = 0; j < C; j++) {
				char c = s.charAt(j);
				
				if(c == '.') {
					map[i][j] = -2;
					
				} else if(c == 'L') {
					map[i][j] = -2;
					swan[l][0] = i;
					swan[l++][1] = j;
					
				} else {
					map[i][j] = -1;
				}
			}
		}
		
		int p = 2; //새로운 집합
		boolean[][] visited = new boolean[R][C];
		Queue<int[]> q = new ArrayDeque<>();
		Queue<int[]> ices = new ArrayDeque<>();
		
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if(map[i][j] == -2) {
					q.add(new int[] {i, j});
					visited[i][j] = true;
					
					while(!q.isEmpty()) {
						int[] m = q.poll();
						
						if(map[m[0]][m[1]] == -2)
							map[m[0]][m[1]] = p;
						
						for (int d = 0; d < 4; d++) {
							int r = m[0] + dir[d][0];
							int c = m[1] + dir[d][1];
							
							if(r<0 || r>=R || c<0 || c>=C || visited[r][c]) continue;
							
							visited[r][c] = true;
							if(map[r][c] == -2) {
								q.add(new int[] {r, c});
								
							} else {
								ices.add(new int[] {r, c});
							}
						}
					}
					
					p++;
				}
			}
		}
		
		parents = new int[p];
		for (int i = 0; i < p; i++) {
			parents[i] = i;
		}
		
		int day=0;
		while(find(map[swan[0][0]][swan[0][1]]) != find(map[swan[1][0]][swan[1][1]])) {
			visited = new boolean[R][C];
			for (int i = 0, size = ices.size(); i < size; i++) {
				int[] ice = ices.poll();
				
				visited[ice[0]][ice[1]] = true;
				for (int d = 0; d < 4; d++) {
					int r = ice[0] + dir[d][0];
					int c = ice[1] + dir[d][1];
					
					if(r<0 || r>=R || c<0 || c>=C) continue;
					
					if(map[r][c] == -1 && !visited[r][c]) {
						visited[r][c] = true;
						ices.add(new int[] {r, c});
						
					} else if (map[r][c] != -1) {
						if(map[ice[0]][ice[1]] == -1) {
							map[ice[0]][ice[1]] = map[r][c];
							
						} else { 
							union(map[r][c], map[ice[0]][ice[1]]);
						}
					}
				}
			}
			
			day++;
		}
		
		System.out.println(day);
	}

	static boolean union(int a, int b) {
		int ar = find(a);
		int br = find(b);
		
		if(ar == br) return true;
		
		parents[br] = ar;
		return false;
	}
	
	static int find(int a) {
		if(parents[a] == a)
			return a;
		
		return parents[a] = find(parents[a]);
	}
}