import javax.swing.*;
import java.io.*;
import java.util.*;

public class Main {
    static int N, result;
    static int[][] map, copyMap;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static int[] numbers;
    static boolean[][] visited;
    static Queue<Block> q;
    static class Block {
        int y, x, sum;

        public Block(int y, int x, int sum) {
            this.y = y;
            this.x = x;
            this.sum = sum;
        }

        @Override
        public String toString() {
            return "Block{" +
                    "y=" + y +
                    ", x=" + x +
                    ", sum=" + sum +
                    '}';
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        copyMap = new int[N][N];
        numbers = new int[5];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        choice(0);
        System.out.println(result);
    }

    static void choice(int cnt) {
        if (cnt == 5) {
            result = Math.max(result, execute());
            return;
        }

        for (int i = 0; i < 4; i++) {
            numbers[cnt] = i;
            choice(cnt+1);
        }
    }

    static int execute() {
        copyMap();
        for(int dir : numbers) {
            bfs(dir);
        }

        return findMaxValue();
    }

    static int findMaxValue() {
        int maxValue = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                maxValue = Math.max(maxValue, copyMap[i][j]);
            }
        }
        return maxValue;
    }

    static void copyMap() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                copyMap[i][j] = map[i][j];
            }
        }
    }

    static void bfs(int dir) {
        visited = new boolean[N][N];

        q = new ArrayDeque<>();
        if (dir%2==0) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j] && copyMap[i][j] != 0) {
                        addQueue(i, j, dir + 1);
                    }
                }
            }
        } else {
            for (int i = N-1; i >= 0; i--) {
                for (int j = N-1; j >= 0; j--) {
                    if (!visited[i][j] && copyMap[i][j] != 0) {
                        addQueue(i, j, dir - 1);
                    }
                }
            }
        }

        copyMap = new int[N][N];
        while(!q.isEmpty()) {
            Block cur = q.poll();
            int ny = cur.y;
            int nx = cur.x;
            while(true) {
                ny += dy[dir];
                nx += dx[dir];
                if (ny == -1 || nx == -1 || ny == N || nx == N || copyMap[ny][nx] != 0) {
                    ny -= dy[dir];
                    nx -= dx[dir];
                    copyMap[ny][nx] = cur.sum;
                    break;
                }
            }
        }
    }

    static void addQueue(int y, int x, int dir) {
        int nextY = y;
        int nextX = x;
        visited[y][x] = true;
        while(true) {
            nextY += dy[dir];
            nextX += dx[dir];
            if (nextY >= N || nextY < 0 || nextX >= N || nextX < 0) {
                q.offer(new Block(y, x, copyMap[y][x]));
                return;
            }
            if (copyMap[y][x] == copyMap[nextY][nextX]) {
                visited[nextY][nextX] = true;
                q.offer(new Block(y, x, copyMap[y][x] * 2));
                return;
            } else if (copyMap[nextY][nextX] != 0 && copyMap[y][x] != copyMap[nextY][nextX]) {
                q.offer(new Block(y, x, copyMap[y][x]));
                return;
            }
        }
    }
}