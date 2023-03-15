import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] src = br.readLine().toCharArray();
        char[] dest = br.readLine().toCharArray();

        int N = src.length;
        int M = dest.length;

        int[][] dp = new int[N+1][M+1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                if (src[i-1] == dest[j-1]) {
                    dp[i][j] = dp[i-1][j-1] + 1;

                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(dp[N][M]).append('\n');

        Stack<Character> stack = new Stack<>();
        while (N > 0 && M > 0) {
            int tmp = dp[N][M];

            if (tmp == dp[N-1][M]) {
                N--;

            } else if (tmp == dp[N][M-1]) {
                M--;

            } else {
                stack.add(src[N-1]);
                N--;
                M--;
            }
        }

        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        System.out.println(sb);
    }
}