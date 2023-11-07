import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] arr;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);
        int startValue =0;
        int endValue = 0;
        int diff = Integer.MAX_VALUE;

        int start = 0;
        int end = N-1;
        while(start<end) {
            if (Math.abs(arr[start] + arr[end]) < diff) {
                diff = Math.abs(arr[start] + arr[end]);
                startValue = arr[start];
                endValue = arr[end];
            }

            if (arr[start]+arr[end] < 0) start++;
            else end--;
        }

        StringBuilder sb =new StringBuilder();
        sb.append(startValue).append(" ").append(endValue);
        System.out.println(sb);
    }
}