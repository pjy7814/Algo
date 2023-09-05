import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        int[] house = new int[N+1];
        for (int i = 1; i < N+1; i++) {
            house[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(house);

        int start = 1;
        int end = house[N]-house[1]+1;
        while (start < end) {
            int mid = (start + end) / 2;
            if (C > countHouse(house, N, mid)) end = mid;
            else start = mid + 1;
        }

        System.out.println(end-1);
    }

    static int countHouse(int[] house, int N, int length) {
        int count = 1;
        int end = 1;

        for (int i = 1; i < N+1; i++) {
            if (house[i]- house[end] >= length) {
                count++;
                end = i;
            }
        }

        return count;
    }

}