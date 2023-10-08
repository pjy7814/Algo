import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] map;
    static boolean[][] isCloud;
    static int[] dy = {0, 0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dx = {0, -1, -1, 0, 1, 1, 1, 0, -1};
    static Queue<int[]> cloud, waterCopy;
    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());   // 이동 회수

        map = new int[N+1][N+1];
        for (int i = 1; i < N+1; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < N+1; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        cloud = new ArrayDeque<>();
        isCloud = new boolean[N+1][N+1];
        // 초기 구름
        cloud.offer(new int[]{N, 1});
        cloud.offer(new int[]{N, 2});
        cloud.offer(new int[]{N-1, 1});
        cloud.offer(new int[]{N-1, 2});

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            move(d, s);

            rain();
            bug();
            makeCloud();
        }

        System.out.println(sumTotal());


    }

    // 구름 이동
    static void move(int dir, int num) {
        isCloud = new boolean[N+1][N+1];
        for (int i = 0; i < cloud.size(); i++) {
            int[] cur = cloud.poll();

            int ny = cur[0] + dy[dir]*(num%N);
            int nx = cur[1] + dx[dir]*(num%N);

            if (ny > N) ny -= N;
            else if (ny < 1) ny += N;

            if (nx > N) nx -= N;
            else if (nx < 1) nx += N;

            isCloud[ny][nx] = true;
            cloud.offer(new int[]{ny, nx});
        }
    }

    // 구름 만들기
    static void makeCloud() {
        cloud = new ArrayDeque<>();
        for (int i = 1; i < N+1; i++) {
            for (int j = 1; j < N+1; j++) {
                if (!isCloud[i][j] && map[i][j] >= 2) {
                    map[i][j] -= 2;
                    cloud.add(new int[]{i, j});
                }
            }
        }
    }

    // 비내리기
    static void rain() {
        for (int i = 0; i < cloud.size(); i++) {
            int[] cur = cloud.poll();
            map[cur[0]][cur[1]] += 1;
            cloud.offer(cur);
        }
    }

    // 물복사버그
    static void bug() {
        Queue<int[]> waterCopy = new ArrayDeque<>();

        for (int i = 0; i < cloud.size(); i++) {
            int[] cur = cloud.poll();

            int cnt = 0;
            for (int j = 2; j <= 8; j+=2) {
                int ny = cur[0] + dy[j];
                int nx = cur[1] + dx[j];

                if (ny <= N && ny > 0 && nx <= N && nx > 0 && map[ny][nx] > 0) {
                    cnt++;
                }
            }
            waterCopy.offer(new int[]{cur[0], cur[1], cnt});
            cloud.offer(cur);
        }

        while(!waterCopy.isEmpty()) {
            int[] cur = waterCopy.poll();
            map[cur[0]][cur[1]] += cur[2];
        }
    }

    static int sumTotal() {
        int count = 0;
        for (int i = 1; i < N+1; i++) {
            for (int j = 1; j < N+1; j++) {
                count += map[i][j];
            }
        }
        return count;
    }
}