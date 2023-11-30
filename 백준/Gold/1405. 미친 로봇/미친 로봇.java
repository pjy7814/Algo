import java.io.*;
import java.math.BigDecimal;
import java.util.*;

public class Main {
    static boolean[][] visited;
    static int N;
    static double[] percent;
    static int[] dy = {0, 0, -1, 1}; // E, W, N, S
    static int[] dx = {1, -1, 0, 0};
    static double result;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        percent = new double[4];
        for (int i = 0; i < 4; i++) {
            percent[i] = (double) Integer.parseInt(st.nextToken()) / 100;
        }

        visited = new boolean[30][30];
        visited[15][15] = true;
        dfs(0, 15, 15, 1);

        BigDecimal decimal = new BigDecimal(result);
        System.out.println(decimal);
    }

    static void dfs(int cnt, int y, int x, double sum) {
        if (cnt == N) {
            result += sum;
            return;
        }

        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            if (!visited[ny][nx]) {
                visited[ny][nx] = true;
                dfs(cnt+1, ny, nx, sum * percent[i]);
                visited[ny][nx] = false;
            }
        }
    }
}