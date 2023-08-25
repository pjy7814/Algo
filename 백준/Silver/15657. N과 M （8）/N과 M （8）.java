import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] number;
    static int[] num;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        number = new int[M];
        num = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(num);

        makeComb(0, 0);
        System.out.println(sb.toString());
    }

    static void makeComb(int cnt, int index) {
        if (cnt==M) {
            for (int i = 0; i < M; i++) {
                sb.append(num[number[i]]).append(" ");
            }
            sb.append("\n");
            return;
        }


        for (int i = index; i < N; i++) {
            number[cnt] = i;
            makeComb(cnt + 1, i);
        }
    }
}