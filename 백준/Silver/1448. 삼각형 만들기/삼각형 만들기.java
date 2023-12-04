import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);
        int point = N-1;
        int result = -1;
        while(point>1) {
            if (arr[point] < arr[point-1] + arr[point-2]) {
                result = arr[point] + arr[point-1] + arr[point-2];
                break;
            }
            point--;
        }

        System.out.println(result);
    }
}