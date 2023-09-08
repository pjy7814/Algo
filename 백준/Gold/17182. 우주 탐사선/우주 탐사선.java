import java.io.*;
import java.util.*;

public class Main {
    static int result = Integer.MAX_VALUE;
    static int N;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 모든 행성 탐사
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    map[j][k] = Math.min(map[j][k], map[j][i]+map[i][k]);
                }
            }
        }

        visited = new boolean[N];
        visited[K] = true;
        dfs(0, K, 0, map);
        System.out.println(result);
    }

    static void dfs(int cnt, int n, int cost, int[][] map) {
        if (cnt == N-1) {
            result = Math.min(result, cost);
            return;
        }

        for (int i = 0; i < N; i++) {
            if (!visited[i] && i != n) {
                visited[i] = true;
                dfs(cnt + 1, i, cost + map[n][i], map);
                visited[i] = false;
            }
        }
    }
}