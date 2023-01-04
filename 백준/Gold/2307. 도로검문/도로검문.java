import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PipedInputStream;
import java.util.*;

public class Main {
    static class Node implements Comparable<Node>{
        int to, weight;

        public Node(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }
    }

    static int N, M;
    static List<Node>[] adj;
    static int[][] blocks;
    static int[] dist;
    static PriorityQueue<Node> pq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        pq = new PriorityQueue<>();
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        adj = new ArrayList[N+1];
        blocks = new int[M][2];
        dist = new int[N+1];

        for (int i = 0; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            adj[a].add(new Node(b, t));
            adj[b].add(new Node(a, t));
            blocks[i][0] = a;
            blocks[i][1] = b;
        }

        if(adj[N].size() == 1) {
            System.out.println(-1);

        } else {
            dijkstra(0, 0);

            int min = dist[N];
            int diff = 0;
            for (int[] bl : blocks) {
                dijkstra(bl[0], bl[1]);

                int nx = dist[N];

                if(nx == Integer.MAX_VALUE) {
                    diff = -1;
                    break;

                } else {
                    diff = Math.max(diff, dist[N] - min);
                }
            }

            System.out.println(diff);
        }
    }

    private static void dijkstra(int a, int b) {
        Arrays.fill(dist, Integer.MAX_VALUE);

        pq.add(new Node(1, 0));
        dist[1] = 0;

        while(!pq.isEmpty()) {
            Node node = pq.poll();

            if(node.weight > dist[node.to]) continue;

            for (Node nd : adj[node.to]) {
                if((node.to == a || node.to == b) && (nd.to == a || nd.to == b)) continue;

                int cost = dist[node.to] + nd.weight;

                if(dist[nd.to] > cost) {
                    dist[nd.to] = cost;
                    pq.add(new Node(nd.to, cost));
                }
            }
        }
    }
}