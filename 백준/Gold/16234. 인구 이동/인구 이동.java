import java.io.*;
import java.util.*;

public class Main {
    static int N, L, R;
    static int map[][];
    static boolean visited[][];
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    static class Land {
        int y, x;

        public Land(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int totalDay = 0;

        while(true) {
            if (bfs()) {
                totalDay++;
            } else break;
        }

        System.out.println(totalDay);
    }

    static boolean bfs() {

        ArrayList<Queue> queueList = new ArrayList<>();
        ArrayList<Integer> resultList = new ArrayList<>();

        visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    Queue<Land> memoryLand = new ArrayDeque<>();
                    Queue<Land> q = new ArrayDeque<>();
                    q.offer(new Land(i, j));
                    memoryLand.offer(new Land(i, j));
                    visited[i][j] = true;
                    int totalCnt = 1;
                    int totalPopul = map[i][j];
                    while (!q.isEmpty()) {
                        Land cur = q.poll();

                        for (int k = 0; k < 4; k++) {
                            int ny = cur.y + dy[k];
                            int nx = cur.x + dx[k];

                            if (ny < N && ny >= 0
                                    && nx < N && nx >= 0
                                    && !visited[ny][nx]
                                    && Math.abs(map[ny][nx] - map[cur.y][cur.x]) >= L
                                    && Math.abs(map[ny][nx] - map[cur.y][cur.x]) <= R) {
                                visited[ny][nx] = true;
                                q.offer(new Land(ny, nx));
                                memoryLand.offer(new Land(ny, nx));
                                totalCnt++;
                                totalPopul += map[ny][nx];
                            }
                        }
                    }

                    if (totalCnt > 1) {
                        queueList.add(memoryLand);
                        resultList.add(totalPopul / totalCnt);
                    }
                }
            }
        }

        if (resultList.size() == 0) return false;

        for (int i = 0; i < resultList.size(); i++) {
            int popul = resultList.get(i);
            Queue<Land> q = queueList.get(i);
            while(!q.isEmpty()) {
                Land cur = q.poll();
                map[cur.y][cur.x] = popul;
            }
        }

        return true;
    }
}