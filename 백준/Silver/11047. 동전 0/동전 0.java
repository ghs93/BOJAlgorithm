import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < N; i++) {
            stack.push(Integer.parseInt(br.readLine()));
        }

        int cnt = 0;
        while (!stack.isEmpty()) {
            int pop = stack.pop();

            if(pop <= K) {
                cnt += K / pop;
                K %= pop;
            }
        }

        System.out.println(cnt);
    }
}