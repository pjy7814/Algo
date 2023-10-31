import java.io.*;
import java.util.*;

public class Main {
    static int h, w, docuCnt;
    static char[][] map;
    static boolean[][] visited;
    static boolean[] keys;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static class Place {
        int y, x;

        public Place(int y, int x) {
            this.y = y;
            this.x = x;
        }

        @Override
        public String toString() {
            return "Place{" +
                    "y=" + y +
                    ", x=" + x +
                    '}';
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int testCase = 0; testCase < T; testCase++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            h = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());

            map = new char[h+2][w+2];
            keys = new boolean[27];
            docuCnt = 0;
            for (int y = 1; y < h+1; y++) {
                String s = br.readLine();
                for (int x = 1; x < w+1; x++) {
                    char c = s.charAt(x-1);
                    map[y][x] = c;
                }
            }

            // 열쇠 얻기
            String s = br.readLine();
            if (!s.equals("0")) {
                for (int i = 0; i < s.length(); i++) {
                    keys[s.charAt(i)-'a'] = true;
                }
            }

            bfs(0, 0);

            sb.append(docuCnt).append("\n");

        }

        System.out.println(sb);
    }

    static void bfs(int y, int x) {
        Queue<Place> q = new ArrayDeque<>();
        visited = new boolean[h+2][w+2];
        q.offer(new Place(y, x));
        visited[y][x] = true;

        while(!q.isEmpty()) {
            Place cur = q.poll();
            char c = map[cur.y][cur.x];
            if (c >= 'a' && c <= 'z') { // 키가 있음
                keys[c-'a'] = true;
                visited = new boolean[h+2][w+2];
                map[cur.y][cur.x] = '.';
            } else if (c >= 'A' && c <= 'Z' && keys[c-'A']) {
                map[cur.y][cur.x] = '.';
            } else if (c >= 'A' && c <= 'Z' && !keys[c-'A']) {
                continue;
            } else if (c == '$') {
                map[cur.y][cur.x] = '.';
                docuCnt++;
            }

            for (int i = 0; i < 4; i++) {
                int ny = cur.y + dy[i];
                int nx = cur.x + dx[i];
                if (ny < h+2 && ny >= 0 && nx < w+2 && nx >= 0 && !visited[ny][nx] && map[ny][nx] != '*') {
                    q.offer(new Place(ny, nx));
                    visited[ny][nx] = true;
                }
            }
        }
    }
}