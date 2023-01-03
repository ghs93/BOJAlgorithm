import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static class Node implements Comparable<Node> {
		int to, weight;

		public Node(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {
			return this.weight - o.weight;
		}

		@Override
		public boolean equals(Object obj) {
			Node node = (Node) obj;
			return this.to == node.to;
		}
	}

	static List<Node>[] nodes;
	static List<Node>[] visited;
	static int[] minDist;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			if(N == 0 && M == 0) break;
			
			nodes = new ArrayList[N];
			visited = new ArrayList[N];
			minDist = new int[N];

			for (int i = 0; i < N; i++) {
				nodes[i] = new ArrayList<>();
				visited[i] = new ArrayList<>();
			}

			st = new StringTokenizer(br.readLine());
			int S = Integer.parseInt(st.nextToken());
			int D = Integer.parseInt(st.nextToken());
			
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int U = Integer.parseInt(st.nextToken());
				int V = Integer.parseInt(st.nextToken());
				int P = Integer.parseInt(st.nextToken());
				
				nodes[U].add(new Node(V, P));
			}
			
			dijkstra(S, D);
		
			remove(D);
			
			dijkstra(S, D);
			
			sb.append(minDist[D] != Integer.MAX_VALUE ? minDist[D] : -1).append('\n');
		}
		
		System.out.println(sb);
	}

	private static void dijkstra(int s, int d) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		Arrays.fill(minDist, Integer.MAX_VALUE);
		
		pq.add(new Node(s, 0));
		minDist[s] = 0;
		
		while(!pq.isEmpty()) {
			Node node = pq.poll();
			
			if(node.weight > minDist[node.to]) continue;
			
			for(Node nd : nodes[node.to]) {
				int cost = minDist[node.to] + nd.weight;
				
				if(minDist[nd.to] == cost) {
					visited[nd.to].add(node);
					
				} else if(minDist[nd.to] > cost) {
					minDist[nd.to] = cost;
					
					visited[nd.to].clear();
					visited[nd.to].add(node);
					pq.add(new Node(nd.to, cost));
				}
			}
		}
	}
	
	private static void remove(int idx) {
		for(int i=visited[idx].size()-1; i>=0; i--) {
			Node node = visited[idx].remove(i);
			nodes[node.to].remove(new Node(idx, 0));
			
			remove(node.to);
		}
	}
}