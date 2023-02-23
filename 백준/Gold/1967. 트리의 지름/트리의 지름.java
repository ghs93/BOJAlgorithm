import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int max = Integer.MIN_VALUE;
    static ArrayList<int[]>[] tree;
    static boolean[] parents, visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        tree = new ArrayList[N+1];
        parents = new boolean[N+1];

        for (int i = 0; i <= N; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            tree[from].add(new int[] {to, weight});
            tree[to].add(new int[] {from, weight});
            parents[from] = true;
        }

        for (int i = 1; i <= N; i++) {
            if(!parents[i]) {
                visited = new boolean[N+1];
                visited[i] = true;
                dfs(i, 0);
            }
        }

        System.out.println(max);
    }

    static void dfs(int cur, int sum) {
        boolean end = true;

        for(int[] t : tree[cur]) {
            if(visited[t[0]]) continue;

            end = false;
            visited[t[0]] = true;
            dfs(t[0], sum + t[1]);
        }

        if(end) {
            max = Math.max(max, sum);
        }
    }
}