import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        long[] subjects = new long[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());
            for (int j = 0; j < M; j++) {
                subjects[i] |= (1L <<Integer.parseInt(st.nextToken()));
            }
        }
        int C = Integer.parseInt(br.readLine());
        for (int i = 0; i < C; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());
            int cnt = 0;
            long tmp = 0;
            for (int j = 0; j < M; j++) {
                tmp |= 1L << Integer.parseInt(st.nextToken());
            }

            for (int j = 0; j < N; j++) {
                if ((subjects[j] & tmp) == subjects[j]) {
                    cnt++;
                }
            }

            sb.append(cnt).append("\n");
        }

        System.out.println(sb);

    }
}