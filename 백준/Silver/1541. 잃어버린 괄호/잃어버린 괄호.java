import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), "-");

        int total = sum(st.nextToken());
        while (st.hasMoreTokens()) {
            int temp = sum(st.nextToken());

            total -= temp;
        }

        System.out.println(total);
    }

    static int sum(String str) {
        StringTokenizer st = new StringTokenizer(str, "+");

        int total = 0;
        while (st.hasMoreTokens())
            total += Integer.parseInt(st.nextToken());

        return total;
    }
}