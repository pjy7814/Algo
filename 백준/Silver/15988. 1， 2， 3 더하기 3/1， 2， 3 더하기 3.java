import java.io.*;
import java.util.*;

public class Main {
    static long dp[] = new long[1_000_001];
    static final long DIV = 1_000_000_009;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;

        for (int i = 4; i < 1_000_001; i++) {
            dp[i] = (dp[i-1] + dp[i-2] + dp[i-3]) % DIV;
        }

        for (int i = 0; i < T; i++) {
            sb.append(dp[Integer.parseInt(br.readLine())]).append("\n");
        }

        System.out.println(sb);
    }
}