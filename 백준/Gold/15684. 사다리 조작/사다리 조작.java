import java.io.*;
import java.util.*;

public class Main {
    static int N, M, H, result = 4;
    static int[][] ladder;
    static int[] dy = {0, 0, 1};
    static int[] dx = {-1, 1, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        // 사다리 그리기
        ladder = new int[H+2][(N-1)*2+1];
        for (int i = 0; i < H+2; i++) {
            for (int j = 0; j < (N-1)*2+1; j+=2) {
                ladder[i][j] = 1;
            }
        }


        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());

            ladder[y][(x-1)*2+1] = 1;
        }

        put(0, 1, 1);
        System.out.println(result==4 ? -1: result);
    }

    static void put(int cnt, int y, int x) {
        if (check()) {
            result = Math.min(result, cnt);
            return;
        }

        if (cnt == 3 || cnt == result || (y>=H+1 && x>=(N-1)*2)) {
            return;
        }

        if (ladder[y][x] == 0) {
            if (x==(N-1)*2-1) {
                if (y == H+1) return;
                ladder[y][x] = 1;
                put(cnt+1, y+1, 1);
                ladder[y][x] = 0;
                put(cnt, y+1, 1);
            } else {
                ladder[y][x] = 1;
                put(cnt+1, y, x+2);
                ladder[y][x] = 0;
                put(cnt, y, x+2);
            }
        } else {
            if (x==(N-1)*2-1) {
                if (y == H+1) return;
                put(cnt, y+1, 1);
            } else {
                put(cnt, y, x+2);
            }
        }
    }

    static boolean check() {
        for (int i = 0; i < (N-1)*2+1; i+=2) {
            if (move(0, i) != i/2+1) return false;
        }
        return true;
    }
    static int move(int y, int x) {
        if (y == H+1) {
            return x/2+1;
        }

        for (int i = 0; i < 3; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            if (nx >= 0 && nx < (N-1)*2+1 && ladder[ny][nx] == 1) {
                if (i == 0 || i == 1) {
                    return move(ny+1, nx+ dx[i]);
                } else {
                    return move(ny, nx);
                }
            }
        }

        return 0;
    }
}