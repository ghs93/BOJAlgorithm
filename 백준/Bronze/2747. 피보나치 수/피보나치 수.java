import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int fibo = N;
        int before = 0;
        int current = 1;
        for (int i = 2; i <= N; i++) {
            fibo = before + current;
            before = current;
            current = fibo;
        }

        System.out.println(fibo);
    }
}