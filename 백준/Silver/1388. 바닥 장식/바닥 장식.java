import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, -1, 0, 1};
    static class Node {
        int y, x;
        public Node(int y, int x) {
            this.y = y;
            this.x = x;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "y=" + y +
                    ", x=" + x +
                    '}';
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = s.charAt(j) == '-' ? 1 : 0;
            }
        }


        int total = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j]) {
                    total++;
                    bfs(i, j);
                }
            }
        }

        System.out.println(total);

    }

    static void bfs(int y, int x) {
        Queue<Node> q = new ArrayDeque<>();
        q.offer(new Node(y, x));
        visited[y][x] = true;
        int shape = map[y][x];
        while(!q.isEmpty()) {
            Node cur = q.poll();

            for (int i = shape; i < 4; i+=2) {
                int ny = cur.y + dy[i];
                int nx = cur.x + dx[i];

                if (ny < N && ny >= 0 && nx < M && nx >= 0 && !visited[ny][nx] && map[ny][nx] == shape) {
                    q.offer(new Node(ny, nx));
                    visited[ny][nx] = true;
                }
            }
        }
    }
}