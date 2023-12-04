import java.io.*;
import java.util.*;

public class Main {
    static final int DIV = 1_000_000_000;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] dp = new int[201][201];
        Arrays.fill(dp[1], 1);

        for (int i = 2; i < K+1; i++) {
            dp[i][0] = 1;
            for (int j = 1; j < N+1; j++) {
                dp[i][j] = (dp[i-1][j] + dp[i][j-1]) % DIV;
            }
        }

        System.out.println(dp[K][N]);

    }
}