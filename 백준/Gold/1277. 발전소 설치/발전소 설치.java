import java.io.*;
import java.util.*;

public class Main {
    static int N, W;
    static double M;
    static Node[] map;
    static class Node {
        int y, x;

        public Node(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        M = Double.parseDouble(br.readLine());
        map = new Node[N+1];
        double[] dist = new double[N+1];
        Arrays.fill(dist, Double.MAX_VALUE);
        for (int i = 1; i < N+1; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            map[i] = new Node(y, x);
        }

        boolean[][] connect = new boolean[N+1][N+1];
        for (int i = 0; i < W; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            connect[s][e] = true;
            connect[e][s] = true;
        }

        dist[1] = 0;
        boolean[] visited = new boolean[N+1];
        for (int i = 2; i<N+1; i++) {
            if (connect[1][i]) dist[i] = 0;
        }

        for (int i = 1; i < N+1; i++) {
            double minDist = Double.MAX_VALUE;
            int cur = 1;
            for (int j = 1; j < N+1; j++) {
                if (!visited[j] && minDist >= dist[j]) {
                    minDist = dist[j];
                    cur = j;
                }
            }
            if (cur == N) break;
            visited[cur] = true;
            for (int j = 1; j < N+1; j++) {
                if (j==cur) continue;
                double d;
                if (connect[j][cur]) d = 0;
                else d = Math.hypot(Math.abs(map[cur].y - map[j].y), Math.abs(map[cur].x - map[j].x));
                if (d > M) continue;
                if (dist[j] > dist[cur] + d) {
                    dist[j] = dist[cur] + d;
                }
            }
        }
        System.out.println((long)(dist[N] * 1000));
    }
}