import java.io.*;
import java.util.*;

public class Main {
    static final int INF = 1000001;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N+1];

        Arrays.fill(arr, INF);
        arr[1] = 0;

        for (int i = 1; i < N; i++) {
            // 1 더하기
            arr[i+1] = Math.min(arr[i]+1, arr[i+1]);

            // 3 곱하기
            if (i*3 <= N) {
                arr[i*3] = Math.min(arr[i]+1, arr[i*3]);
            }
            // 2 곱하기
            if (i*2 <= N) {
                arr[i*2] = Math.min(arr[i]+1, arr[i*2]);
            }
        }

        System.out.println(arr[N]);
    }

}