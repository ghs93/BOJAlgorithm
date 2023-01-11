import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[20000001];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int s = Integer.parseInt(st.nextToken());
			arr[s+10000000]++;
		}
		
		int M = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			int s = Integer.parseInt(st.nextToken());
			sb.append(arr[s+10000000]).append(' ');
		}
		
		System.out.println(sb);
	}
}