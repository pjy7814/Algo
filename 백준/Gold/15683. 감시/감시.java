import java.io.*;
import java.util.*;

public class Main {
    static int[] dy = {-1 , 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    static int[][] dir2 = {{0, 1}, {2, 3}};
    static int[][] dir3 = {{0, 3}, {1, 3}, {1, 2}, {0, 2}};
    static int[][] dir4 = {{0, 2, 3}, {0, 1, 3}, {1, 2, 3}, {0, 1, 2}};

    static ArrayList<int[]> cctv = new ArrayList<>();
    static int[][] map, tmpMap;
    static int[] numbers;
    static int N, M, result;

    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        result = N * M;

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] != 0 && map[i][j] != 6) {
                    cctv.add(new int[]{i, j, map[i][j]});
                }
            }
        }

        numbers = new int[cctv.size()];
        choice(0);
        System.out.print(result);
    }

    // 1. 선택
    static void choice(int cnt) {
        if (cnt == cctv.size()) {
            watch();
            return;
        }
        int num = cctv.get(cnt)[2];
        switch (num) {
            case 1:
            case 3:
            case 4:
                for (int i = 0; i < 4; i++) {
                    numbers[cnt] = i;
                    choice(cnt+1);
                }
                break;
            case 2:
                numbers[cnt] = 0;
                choice(cnt+1);
                numbers[cnt] = 1;
                choice(cnt+1);
                break;
            case 5:
                numbers[cnt] = 0;
                choice(cnt+1);
                break;
        }
    }

    // 복사하기
    static void copyArray() {
        tmpMap = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                tmpMap[i][j] = map[i][j];
            }
        }
    }

    // 감시 칸 이동하기
    static void move(int y, int x, int dir) {
        int ny = y;
        int nx = x;
        while(true) {
            ny += dy[dir];
            nx += dx[dir];
            if (ny >= N || ny < 0 || nx >= M || nx < 0 || map[ny][nx] == 6) return;
            tmpMap[ny][nx] = -1;
        }
    }

    // 감시하기
    static void watch() {
        copyArray();

        for (int c = 0; c < cctv.size(); c++) {
            int[] curCctv = cctv.get(c);    // cctv 위치, 번호
            switch (curCctv[2]) {
                case 1:
                    move(curCctv[0], curCctv[1], numbers[c]);
                    break;
                case 2:
                    move(curCctv[0], curCctv[1], dir2[numbers[c]][0]);
                    move(curCctv[0], curCctv[1], dir2[numbers[c]][1]);
                    break;
                case 3:
                    move(curCctv[0], curCctv[1], dir3[numbers[c]][0]);
                    move(curCctv[0], curCctv[1], dir3[numbers[c]][1]);
                    break;
                case 4:
                    move(curCctv[0], curCctv[1], dir4[numbers[c]][0]);
                    move(curCctv[0], curCctv[1], dir4[numbers[c]][1]);
                    move(curCctv[0], curCctv[1], dir4[numbers[c]][2]);
                    break;
                case 5:
                    for (int i = 0; i < 4; i++) {
                        move(curCctv[0], curCctv[1], i);
                    }
                    break;
            }
        }

        check();
    }


    // 사각지대 체크하기
    static void check() {
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (tmpMap[i][j] == 0) count++;
            }
        }
        result = Math.min(result, count);
    }

}