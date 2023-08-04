import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        int index = Integer.parseInt(br.readLine());
        int cnt = 0;
        for (int i = 0; i < n-1; i++) {
            if (arr[i] >= index) break;

            for (int j = n-1; j > i; j--) {
                int sum = arr[i] + arr[j];

                if (sum == index) cnt++;

                if (sum < index) break;
            }
        }

        System.out.println(cnt);
    }
}