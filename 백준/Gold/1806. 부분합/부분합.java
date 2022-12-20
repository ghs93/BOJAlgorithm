import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());

		int[] arr = new int[N + 1];
		int sum = 0, sPosition = 0;
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			sum += Integer.parseInt(st.nextToken());
			arr[i] = sum;

			if (sum < S)
				sPosition = i + 1;
		}

		int dist = Integer.MAX_VALUE;
		
		if(sPosition > N) {
			System.out.println(0);
			return;
		}
		
		for (int i = 0; i <= N; i++) {
			if (arr[sPosition] - arr[i] >= S) {
				int min = sPosition - i;

				if (dist > min)
					dist = min;

			} else {
				if (sPosition + 1 <= N) {
					sPosition++;
					i--;
				}
			}
		}

		System.out.println(dist);
	}
}