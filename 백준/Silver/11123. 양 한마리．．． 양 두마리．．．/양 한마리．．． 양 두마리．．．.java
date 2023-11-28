import java.io.*;
import java.util.*;

public class Main {
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int H = Integer.parseInt(st.nextToken());
            int W = Integer.parseInt(st.nextToken());
            int[][] map = new int[H][W];

            for (int i = 0; i < H; i++) {
                String s = br.readLine();
                for (int j = 0; j < W; j++) {
                    map[i][j] = s.charAt(j) == '#' ? 1 : 0;
                }
            }

            boolean[][] visited = new boolean[H][W];
            int result = 0;
            for (int i = 0; i < H; i++) {
                for (int j = 0; j < W; j++) {
                    if (map[i][j] == 1&& !visited[i][j]) {
                        result++;
                        bfs(visited, map, i, j, H, W);
                    }
                }
            }

            sb.append(result).append("\n");
        }
        System.out.println(sb);
    }

    static void bfs(boolean[][] visited, int[][] map, int y, int x, int H, int W) {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{y, x});
        visited[y][x] = true;

        while(!q.isEmpty()) {
            int[] cur = q.poll();

            for (int i = 0; i < 4; i++) {
                int ny = cur[0] + dy[i];
                int nx = cur[1] + dx[i];

                if (ny < H && ny >= 0 && nx < W && nx >= 0 && map[ny][nx] == 1 && !visited[ny][nx]) {
                    q.offer(new int[]{ny, nx});
                    visited[ny][nx] = true;
                }
            }
        }
    }
}