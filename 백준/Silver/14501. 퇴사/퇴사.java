import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] T = new int[N+1];
        int[] P = new int[N+1];
        int[] DP = new int[N+2];

        for (int i = 1; i < N+1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            T[i] = Integer.parseInt(st.nextToken());
            P[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = N; i > 0; i--) {
            if (T[i]+i > N+1) {
                DP[i] = DP[i+1];
            } else {
                DP[i] = Math.max(DP[i+1], P[i]+DP[T[i]+i]);
            }
        }

        System.out.println(DP[1]);
    }
}