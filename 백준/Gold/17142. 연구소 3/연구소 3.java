import javax.print.attribute.standard.MediaSize;
import java.io.*;
import java.util.*;

public class Main {
    static int[][] map, visited;
    static int N, M, cnt, result;
    static boolean[] numbers;
    static ArrayList<Virus> virusList;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static class Virus {
        int y, x, time;
        public Virus(int y, int x, int time) {
            this.y = y;
            this.x = x;
            this.time = time;
        }
    }
    static final int INF = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        virusList = new ArrayList<>();
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) {
                    virusList.add(new Virus(i, j, 0));
                }
            }
        }

        cnt = virusList.size();
        numbers = new boolean[cnt];

        result = INF;
        choice(0, 0);
        System.out.println(result == INF ? -1 : result);
    }
    static void choice(int index, int count) {
        if (index == cnt && count == M) {
            bfs();
            result = Math.min(result, check());
            return;
        } else if (index == cnt) return;

        numbers[index] = true;
        choice(index+1, count+1);
        numbers[index] = false;
        choice(index+1, count);
    }

    static void bfs() {
        visited = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                visited[i][j] = INF;
            }
        }

        Queue<Virus> q = new ArrayDeque<>();
        for (int i = 0; i < cnt; i++) {
            if (numbers[i]) {
                q.offer(virusList.get(i));
                visited[virusList.get(i).y][virusList.get(i).x] = 0;
            }
        }

        while(!q.isEmpty()) {
            Virus cur = q.poll();

            for (int i = 0; i < 4; i++) {
                int ny = cur.y + dy[i];
                int nx = cur.x + dx[i];

                if (ny < N && ny >= 0 && nx < N && nx >= 0
                        && map[ny][nx] != 1
                && visited[ny][nx] > cur.time + 1) {
                    visited[ny][nx] = cur.time+1;
                    q.add(new Virus(ny, nx, cur.time+1));
                }
            }
        }
    }

    static int check() {
        int maxTime = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 1 || map[i][j] == 2) continue;
                maxTime = Math.max(maxTime, visited[i][j]);
            }
        }
        return maxTime;
    }
}