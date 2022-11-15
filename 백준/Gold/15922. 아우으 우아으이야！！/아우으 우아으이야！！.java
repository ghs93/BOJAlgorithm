import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine());
		int from = Integer.parseInt(st.nextToken()), to = Integer.parseInt(st.nextToken());
		
		int result = 0;
		for (int i = 0; i < n-1; i++) {
			st = new StringTokenizer(br.readLine());
			
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			
			if(s <= to) {
				to = Math.max(e, to);
				
			} else {
				result += Math.abs(to - from);
				from = s;
				to = e;
			}
		}
		
		result += Math.abs(to - from);
		System.out.println(result);
	}
}