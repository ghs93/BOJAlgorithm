import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());

        int cnt = 0;
        for (int i = 0; i < n; i++) {
            int doll = Integer.parseInt(st.nextToken());
            arr[i] = doll;

            if (doll == 1) cnt++;
        }

        if (cnt < k) {
            System.out.println(-1);
            return;
        }

        cnt = 1;
        int start = 0, end = 0, min = n;
        for (int i = 0; i < n; i++) {
            if (arr[i] == 1) {
                start = i;
                end = i+1;
                break;
            }
        }

        while (end < n) {
            if (arr[end] == 1) cnt++;

            if (cnt < k) {
                end++;

            } else if (cnt == k) {
                min = Math.min(min, end - start + 1);

                end++;
                cnt--;
                start++;

                while(arr[start] != 1)
                    start++;
            }
        }

        System.out.println(min);
    }
}
