import java.io.*;
import java.util.*;

public class Main {
    static long[][] dp;
    static final int DIV = 1_000_000_007;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine())+1;
        dp = new long[1002][2];

        
        dp[1][0] = 1;
        dp[2][0] = 1;
        dp[2][1] = 0;
        dp[3][0] = 2;
        dp[3][1] = 1;

        for (int i = 4; i < N+1; i++) {
            dp[i][0] = (dp[i-1][0] + dp[i-2][0] + dp[i-3][0]) % DIV;
            dp[i][1] = (dp[i-1][1] + dp[i-2][0] + dp[i-2][1] * 2 + dp[i-3][0] * 2 + dp[i-3][1] *3) % DIV;
        }

        System.out.println(dp[N][1]);
    }
}