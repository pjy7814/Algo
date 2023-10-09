import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] map;
    static int[] dy = {0, 1, 0, -1};
    static int[] dx = {-1, 0, 1, 0};
    static int[] ratio = {1, 1, 2, 2, 5, 7, 7, 10, 10};
    static int[][] moveY =
            {
                    {-1, 1, -2, 2, 0, -1, 1, -1, 1, 0}, // dir 0
                    {-1, -1, 0, 0, 2, 0, 0, 1, 1, 1}, // dir 1
                    {-1, 1, -2, 2, 0, -1, 1, -1, 1, 0}, // 2
                    {1, 1, 0, 0, -2, 0, 0, -1, -1, -1}
            };
    static int[][] moveX =
            {
                    {1, 1, 0, 0, -2, 0, 0, -1, -1, -1}, // dir 0
                    {-1, 1, -2, 2, 0, -1, 1, -1, 1, 0}, // 1
                    {-1, -1, 0, 0, 2, 0, 0, 1, 1, 1},  // 2
                    {-1, 1, -2, 2, 0, -1, 1, -1, 1, 0}
            };
    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        moveTornado();
    }

    static void moveTornado() {
        int ny = N/2;
        int nx = N/2;

        int dir = 0;
        int dist = 1;
        int outSand = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < dist; j++) {
                ny += dy[dir%4];
                nx += dx[dir%4];

                outSand += moveSand(ny, nx, dir%4);
            }

            dir++;

            for (int j = 0; j < dist; j++) {
                ny += dy[dir%4];
                nx += dx[dir%4];

                outSand += moveSand(ny, nx, dir%4);
            }

            dir++;
            dist++;
        }

        System.out.print(outSand);
    }

    static int moveSand(int y, int x, int dir) {
        if (!possible(y, x)) return 0;
        int outSand = 0;
        int inSand = map[y][x];

        for (int i = 0; i < 9; i++) {
            int sand = inSand * ratio[i] / 100;
            int ny = y + moveY[dir][i];
            int nx = x + moveX[dir][i];

            if (possible(ny, nx)) {
                map[ny][nx] += sand;
            } else {
                outSand+= sand;
            }
            map[y][x] -= sand;
        }

        int ny = y+moveY[dir][9];
        int nx = x+moveX[dir][9];
        if (!possible(ny, nx)) {
            outSand += map[y][x];
        } else {
            map[ny][nx] += map[y][x];
        }

        return outSand;
    }
    static boolean possible(int y, int x) {
        if (y < 0 || y >= N || x < 0 || x >= N) return false;
        else return true;
    }
}