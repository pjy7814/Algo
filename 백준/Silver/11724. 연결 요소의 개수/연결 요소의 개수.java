import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] parents = new int[N+1];
        for (int i = 0; i < N+1; i++) {
            parents[i] = i;
        }

        for (int i = 0; i < M; i++) {
            // union
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            union(parents, x, y);
        }

        // check
        boolean[] check = new boolean[N+1];
        int count = 0;
        for (int i = 1; i < N+1; i++) {
            if (check[i] || check[parents[i]]) continue;

            check[find(parents, i)] = true;
        }

        for (int i = 1; i < N+1; i++) {
            if (check[i]) count++;
        }
        System.out.println(count);
    }

    static void union(int[] parents, int x, int y) {
        x = find(parents, x);
        y = find(parents, y);

        if (x > y) {
            parents[x] = y;
        } else {
            parents[y] = x;
        }
    }

    static int find(int[] parents, int num) {
        if (parents[num] == num) return num;
        return parents[num] = find(parents, parents[num]);
    }
}