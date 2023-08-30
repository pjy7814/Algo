import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        int[][] people = new int[N+1][N+1];


        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int E = st.nextToken().equals("E") ? -1 : 1;
            int p = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());

            people[p][q] = E;
            people[q][p] = E;
        }

        int cnt = 0;
        int[] group = new int[N+1];
        Arrays.fill(group, 0);

        for (int i = 1; i < N+1; i++) {
            if (group[i] == 0) {
                cnt++;
                group[i] = cnt;
            }
            else continue;

            boolean[][] visited = new boolean[N+1][N+1];
            for (int j = 0; j < N+1; j++) {
                Arrays.fill(visited[j], false);
            }

            Queue<int[]> q = new ArrayDeque<>();
            for (int j = 1; j < N+1; j++) {
                if (people[i][j] == 1) {
                    q.offer(new int[] {j, 1});
                    group[j] = cnt;
                    visited[i][j] = true;
                } else if (people[i][j] == -1) {
                    q.offer(new int[] {j, -1});
                    visited[i][j] = true;
                }
            }
            while (!q.isEmpty()) {
                int[] cur = q.poll();
                if (cur[1] == 1) {  // 친구
                    group[cur[0]] = cnt;
                    for (int j = 1; j < N+1; j++) {
                        if (people[cur[0]][j] == 1&& !visited[cur[0]][j]) {
                            q.offer(new int[] {j, 1});
                            visited[cur[0]][j] = true;
                        } else if (people[cur[0]][j] == -1&& !visited[cur[0]][j]) {
                            q.offer(new int[] {j, -1});
                            visited[cur[0]][j] = true;
                        }
                    }
                } else {    // 원수
                    for (int j = 1; j < N+1; j++) {
                        if (people[cur[0]][j] == -1 && !visited[cur[0]][j]) {
                            q.offer(new int[] {j, 1});
                            visited[cur[0]][j] = true;
                        }
                    }
                }
            }
        }

        System.out.print(cnt);
    }
}