import java.io.*;
import java.util.*;

public class Main {
    static int[][] map;
    static boolean[][][][] visited;
    static int N, M;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static class Ball {
        int redY, redX, blueY, blueX, count;

        public Ball(int redY, int redX, int blueY, int blueX, int count) {
            this.redY = redY;
            this.redX = redX;
            this.blueY = blueY;
            this.blueX = blueX;
            this.count = count;
        }

        @Override
        public String toString() {
            return "Ball{" +
                    "redY=" + redY +
                    ", redX=" + redX +
                    ", blueY=" + blueY +
                    ", blueX=" + blueX +
                    ", count=" + count +
                    '}';
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M][N][M];  // redY, redX, blueY, blueX;

        int redY = 0;
        int redX = 0;
        int blueY = 0;
        int blueX = 0;
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                switch (s.charAt(j)){
                    case '#':
                        map[i][j] = 1;
                        break;
                    case 'O':
                        map[i][j] = -1;
                        break;
                    case 'R':
                        redY = i;
                        redX = j;
                        break;
                    case 'B':
                        blueY = i;
                        blueX = j;
                        break;
                }
            }
        }

        Queue<Ball> q = new ArrayDeque<>();
        q.offer(new Ball(redY, redX, blueY, blueX, 0));
        visited[redY][redX][blueY][blueX] = true;
        int result = -1;
        while(!q.isEmpty()) {
            Ball cur = q.poll();
            if (map[cur.redY][cur.redX] == -1 && map[cur.blueY][cur.blueX] != -1) {
                result = cur.count;
                break;
            }

            for (int i = 0; i < 4; i++) {
                redY = cur.redY;
                redX = cur.redX;
                blueY = cur.blueY;
                blueX = cur.blueX;

                // 빨간공
                while(true){
                    if (map[redY][redX] == 0) {
                        redY += dy[i];
                        redX += dx[i];
                    } else {
                        if (map[redY][redX] == 1) {
                            redY -= dy[i];
                            redX -= dx[i];
                        }
                        break;
                    }
                }

                // 파란공
                while(true){
                    if (map[blueY][blueX] == 0) {
                        blueY += dy[i];
                        blueX += dx[i];
                    } else {
                        if (map[blueY][blueX] == 1) {
                            blueY -= dy[i];
                            blueX -= dx[i];
                        }
                        break;
                    }
                }

                // 둘이 같은 장소라면(구멍에 빠지지 않음)? 더 많이 이동한 공 -1 이동
                if (redY == blueY && redX == blueX && map[redY][redX] != -1) {
                    int redDist = Math.abs(redY-cur.redY) + Math.abs(redX-cur.redX);
                    int blueDist = Math.abs(blueY - cur.blueY) + Math.abs(blueX - cur.blueX);
                    if (redDist > blueDist) {
                        redY -= dy[i];
                        redX -= dx[i];
                    } else {
                        blueY -= dy[i];
                        blueX -= dx[i];
                    }
                }

                if (!visited[redY][redX][blueY][blueX] && cur.count < 10) {
                    q.offer(new Ball(redY, redX, blueY, blueX, cur.count+1));
                    visited[redY][redX][blueY][blueX] = true;
                }
            }
        }
        System.out.println(result);
    }

}