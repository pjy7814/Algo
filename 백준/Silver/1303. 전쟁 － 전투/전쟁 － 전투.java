import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[M][N];
        visited = new boolean[M][N];

        for (int i = 0; i < M; i++) {
            String s = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = s.charAt(j) == 'W' ? 0 : 1;     // W : 0, B : 1
            }
        }

        int totalMy = 0;
        int totalOpp = 0;
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (visited[i][j]) continue;

                if (map[i][j] == 0) {
                    totalMy += Math.pow(bfs(i, j, 0), 2);
                } else {
                    totalOpp += Math.pow(bfs(i, j, 1), 2);
                }
            }
        }

        sb.append(totalMy).append(" ").append(totalOpp);
        System.out.println(sb);

    }

    static int bfs(int y, int x, int color) {
        visited[y][x] = true;
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{y, x});

        int total = 1;
        while(!q.isEmpty()) {
            int[] cur = q.poll();

            for (int i = 0; i < 4; i++) {
                int ny = cur[0] + dy[i];
                int nx = cur[1] + dx[i];

                if (ny < M && ny >= 0 && nx < N && nx >= 0 && !visited[ny][nx] && map[ny][nx] == color) {
                    total++;
                    visited[ny][nx] = true;
                    q.offer(new int[]{ ny, nx});
                }
            }
        }
        return total;

    }
}