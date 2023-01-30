import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}

		int idx = 0, cur = 1;
		Stack<Integer> stack = new Stack<>();
		StringBuilder sb = new StringBuilder();

		while(N-- > 0) {
			if(cur <= arr[idx]) {
				for (int i = cur; i <= arr[idx]; i++) {
					stack.push(i);
					sb.append('+').append('\n');
				}

				cur = arr[idx] + 1;
			}

			if(stack.peek() == arr[idx]) {
				stack.pop();
				sb.append('-').append('\n');
				idx++;

			} else {
				System.out.println("NO");
				return;
			}
		}

		System.out.println(sb);
	}
}