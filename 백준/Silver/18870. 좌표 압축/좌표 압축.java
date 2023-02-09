import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        int[] sort = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(st.nextToken());

            arr[i] = n;
            sort[i] = n;
        }

        Map<Integer, Integer> map = new HashMap<>();
        Arrays.sort(sort);

        int rank = 0;
        for (int i = 0; i < N; i++) {
            if(!map.containsKey(sort[i])) {
                map.put(sort[i], rank++);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int n : arr) {
            sb.append(map.get(n)).append(' ');
        }

        System.out.println(sb);
    }
}