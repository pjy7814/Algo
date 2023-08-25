import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] number;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        number = new int[M];

        makeComb(0, 1);
        System.out.println(sb.toString());
    }

    static void makeComb(int cnt, int index) {
        if (cnt==M) {
            for (int i = 0; i < M; i++) {
                sb.append(number[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = index; i < N+1; i++) {
            number[cnt] = i;
            makeComb(cnt+1, i);
        }
    }
}