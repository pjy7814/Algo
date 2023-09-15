import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());   // 기차의 수
        int M = Integer.parseInt(st.nextToken());   // 명령의 수

        int[] trains = new int[N+1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int order = Integer.parseInt(st.nextToken());
            int num = Integer.parseInt(st.nextToken());
            switch (order) {
                case 1:
                    int seat = Integer.parseInt(st.nextToken()) -1;
                    trains[num] |= (1 << seat);
                    break;
                case 2:
                    seat = Integer.parseInt(st.nextToken()) -1;
                    trains[num] &= ~(1 << seat);
                    break;
                case 3:
                    trains[num] = (trains[num] & ~(1 << 19)) << 1;
                    break;
                case 4:
                    trains[num] = trains[num] >> 1;
                    break;
            }
        }

        Set<Integer> set = new HashSet<>();
        for (int i = 1; i < N+1; i++) {
            set.add(trains[i]);
        }

        System.out.println(set.size());

    }
}