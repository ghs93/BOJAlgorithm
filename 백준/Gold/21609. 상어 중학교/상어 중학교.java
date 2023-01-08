import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, map[][];
	static int[][] del = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	static boolean[][] visited, rVisited;
	static ArrayList<int[]> blocks = new ArrayList<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int score = 0;
		do {
			blocks.clear();
			findBlock();
			
			if(blocks.size() > 1) {
				score += Math.pow(blocks.size(), 2);
			}
		} while(blocks.size() > 1);
		
		System.out.println(score);
	}

	private static void findBlock() {
		visited = new boolean[N][N];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int block = map[i][j];
				if(visited[i][j] || block <= 0)
					continue;
				
				ArrayList<int[]> bls = bfs(block, i, j);
				if(bls.size() < 2)
					continue;
				
				if(bls.size() > blocks.size()) {
					blocks = bls;
					
				} else if (bls.size() == blocks.size()) {
					int curR = getRainbowCnt(blocks);
					int comR = getRainbowCnt(bls);
					
					if(comR > curR) {
						blocks = bls;
						
					} else if(curR == comR) {
						int[] current = blocks.get(0);
						int[] compare = bls.get(0);
						
						if(compare[0] >= current[0]) {
							if(compare[0] == current[0]) {
								if(compare[1] > current[1])
									blocks = bls;						
							} else {
								blocks = bls;
							}
						}
					}
				}
			}
		}
        
		if(blocks.size() > 1)
			removeBlock();
	}
	
	private static int getRainbowCnt(ArrayList<int[]> bl) {
		int cnt = 0;
		for(int[] b : bl) {
			if(map[b[0]][b[1]] == 0)
				cnt++;
		}
		
		return cnt;
	}
	
	private static ArrayList<int[]> bfs(int b, int i, int j) {
		ArrayList<int[]> bls = new ArrayList<>();
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {i, j});
		
		rVisited = new boolean[N][N];
		visited[i][j] = true;
		
		while(!q.isEmpty()) {
			int[] position = q.poll();
			bls.add(position);
			
			for(int d=0; d<4; d++) {
				int r = position[0] + del[d][0];
				int c = position[1] + del[d][1];
				
				if(r>=0 && r<N && c>=0 && c<N && (!visited[r][c] && !rVisited[r][c]) 
						&& (map[r][c] == b || map[r][c] == 0)) {
					if(map[r][c] == 0)
						rVisited[r][c] = true;
					else
						visited[r][c] = true;
					
					q.add(new int[] {r, c});
				}
			}
		}
		
		return bls;
	}
	
	private static void removeBlock() {
		for(int[] b : blocks) {
			map[b[0]][b[1]] = -2;
		}
		
		dropBlock();
		
		int[][] temp = new int[N][N];
		for(int j=N-1; j>=0; j--) {
			for(int i=0; i<N; i++) {
				temp[N-j-1][i] = map[i][j];
			}
		}
		
		map = temp;
		
		dropBlock();
	}
	
	private static void dropBlock() {
		for (int i = N-2; i >= 0; i--) {
			for (int j = N-1; j >= 0; j--) {
				if(map[i][j] == -1)
					continue;
				
				int r = i;
				int c = j;
				
				while(true) {
					r += del[2][0];
					
					if(r<N && map[r][c] == -2) {
						continue;
						
					} else {
						r -= del[2][0];
						
						if(r != i) {
							map[r][c] = map[i][j];
							map[i][j] = -2;
						}
						
						break;
					}
				}
			}
		}
	}
}