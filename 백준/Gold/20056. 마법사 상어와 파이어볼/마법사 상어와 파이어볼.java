import java.io.*;
import java.util.*;

public class Main {
    static class Ball {
        int y, x, m, s, d;
        public Ball (int y, int x, int m, int s, int d) {
            this.y = y;
            this.x = x;
            this.m = m;
            this.s = s;
            this.d = d;
        }

        public String toString() {
            return "Ball [y: "+ y+", x:"+x+", m:"+m+", s:"+s+", d: "+d+"] \n";
        }
    }
    static int N, M, K;
    static ArrayList<Ball> balls;
    static int[][] dirs = {{-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        balls = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken())-1;
            int x = Integer.parseInt(st.nextToken())-1;
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            balls.add(new Ball(y, x, m, s, d));
        }

        for (int i = 0; i < K; i++) {

            move();
        }

        // 더하기
        int result = 0;
        for(Ball b: balls) {
            result += b.m;
        }

        System.out.print(result);
    }

    static void move() {
        ArrayList<Ball>[][] map = new ArrayList[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = new ArrayList<>();
            }
        }

        for(Ball b :balls) {
            int ny = b.y + (b.s%N) * dirs[b.d][0];
            int nx = b.x + (b.s%N) * dirs[b.d][1];
            if (ny >= N) ny %= N;
            else if (ny < 0) ny = N-Math.abs(ny);

            if (nx >= N) nx %= N;
            else if (nx < 0) nx = N-Math.abs(nx);
            map[ny][nx].add(new Ball(ny, nx, b.m, b.s, b.d));
        }

        balls = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j].size() > 1) {
                    merge(map, i, j);
                } else if (map[i][j].size() == 1){
                    balls.add(new Ball(i, j, map[i][j].get(0).m, map[i][j].get(0).s, map[i][j].get(0).d));
                }
            }
        }
    }

    static void merge(ArrayList<Ball>[][] map, int y, int x) {
        int sumM = 0;
        int sumS = 0;

        boolean isEven = true;
        boolean isOdd = true;
        for(Ball b : map[y][x]) {
            sumM += b.m;
            sumS += b.s;

            if (b.d % 2 != 0) {
                isEven = false;
            } else {
                isOdd = false;
            }
        }

        int m = sumM/5;
        int s = sumS/map[y][x].size();

        if (m!=0) {
            if (isEven || isOdd) {
                for (int i = 0; i < 4; i++) {
                    balls.add(new Ball(y, x, m, s, i*2));
                }
            } else {
                for (int i = 0; i < 4; i++) {
                    balls.add(new Ball(y, x, m, s, i*2+1));
                }
            }

        }

    }
}