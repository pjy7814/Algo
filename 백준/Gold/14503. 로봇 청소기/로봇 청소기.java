import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] map;
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int result = 0;
        while(true) {
            // 현재칸이 청소가 되지 않음
            if (map[r][c] == 0) {
                result++;
                map[r][c] = -1; // 청소 완료
            }

            boolean flag = false;
            for (int i = 0; i < 4; i++) {
                d = (d+3)%4;
                int ny = r + dy[d];
                int nx = c + dx[d];

                if (ny < N && ny >= 0 && nx < M && nx >= 0 && map[ny][nx] == 0) { // 청소해야함!
                    r = ny;
                    c = nx;
                    flag = true;
                    break;
                }
            }

            // 청소할 곳이 없음 => 후진
            if (flag) continue;
            int ny = r + dy[(d+2)%4];
            int nx = c + dx[(d+2)%4];
            if (ny<N && ny >= 0 && nx <M && nx >=0 &&map[ny][nx] != 1){
                r = ny;
                c = nx;
            } else break;

        }

        System.out.println(result);


    }

}