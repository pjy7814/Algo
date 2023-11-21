import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        HashMap<Integer, PriorityQueue<Integer>> map = new HashMap<>();
        for (int i = 1; i < N+1; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            while(st.hasMoreTokens()) {
                int sushiNum = Integer.parseInt(st.nextToken());
                if (map.containsKey(sushiNum)) map.get(sushiNum).add(i);
                else {
                    map.put(sushiNum, new PriorityQueue<>());
                    map.get(sushiNum).add(i);
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        int[] people = new int[N+1];
        for (int i = 0; i < M; i++) {
            int target = Integer.parseInt(st.nextToken());
            if (map.containsKey(target) && !map.get(target).isEmpty()) {
                int num = map.get(target).poll();
                people[num]++;
            }
        }

        for (int i = 1; i < N+1; i++) {
            sb.append(people[i]).append(" ");
        }
        System.out.println(sb);
    }
}