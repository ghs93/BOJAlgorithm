import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        //a = 97
        // ...
        //z = 122
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int L = Integer.parseInt(br.readLine());
        String str = br.readLine();

        int r = 31;
        int diff = 96;
        int mod = 1234567891;
        int result = str.charAt(0) - diff;
        for (int i = 1; i < L; i++) {
            int alpa = str.charAt(i) - diff;

            long sum = 1;
            for (int j = 0; j < i; j++) {
                sum = (sum*r) % mod;
            }

            sum = (sum*alpa) % mod;
            result += sum;
        }

        System.out.println(result);
    }
}