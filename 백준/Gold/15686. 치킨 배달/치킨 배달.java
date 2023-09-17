import java.io.*;
import java.util.*;

public class Main {
    static int N, M, minDist;
    static final int INF = Integer.MAX_VALUE;
    static int[][] map;
    static ArrayList<int[]> chicken;
    static int[] numbers;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N+1][N+1];
        chicken = new ArrayList<>();
        numbers = new int[M];

        for (int i = 1; i < N+1; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < N+1; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) {
                    chicken.add(new int[]{i, j});
                }
            }
        }
        minDist=INF;
        choice(0, 0, new boolean[chicken.size()]);
        System.out.print(minDist);
    }

    static void choice(int cnt, int start, boolean[] visited) {
        if (cnt == M) {
            // 치킨 거리 구하기
            minDist = Math.min(minDist, distChicken());
            return;
        }

        for (int i = start; i < chicken.size(); i++) {
            visited[i] = true;
            numbers[cnt] = i;
            choice(cnt+1, i+1, visited);
            visited[i] = false;
        }
    }

    static int distChicken() {
        int[][] dist = new int[N+1][N+1];
        for (int j = 0; j < N+1; j++) {
            Arrays.fill(dist[j], INF);
        }
        for (int i = 0; i < M; i++) {
            boolean[][] visited = new boolean[N+1][N+1];
            for (int j = 0; j < N+1; j++) {
                Arrays.fill(visited[j], false);
            }

            // bfs 수행
            Queue<int[]> q = new ArrayDeque<>();
            q.add(new int[]{chicken.get(numbers[i])[0], chicken.get(numbers[i])[1], 0});
            visited[chicken.get(numbers[i])[0]][chicken.get(numbers[i])[1]] = true;
            while(!q.isEmpty()) {
                int[] cur = q.poll();
                dist[cur[0]][cur[1]] = cur[2];
                for (int j = 0; j < 4; j++) {
                    int ny = cur[0] + dy[j];
                    int nx = cur[1] + dx[j];

                    if (ny > N || ny <= 0 || nx > N || nx <= 0 || cur[2]+1>dist[ny][nx] || visited[ny][nx]) continue;

                    dist[ny][nx] = cur[2]+1;
                    visited[ny][nx] = true;
                    q.add(new int[]{ny, nx, cur[2]+1});
                }

            }
        }

        int result = 0;
        for (int i = 1; i < N+1; i++) {
            for (int j = 1; j < N+1; j++) {
                if (map[i][j] == 1) result += dist[i][j];
            }
        }

        return result;
    }
}