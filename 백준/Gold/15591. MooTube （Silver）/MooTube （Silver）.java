import java.io.*;
import java.util.*;

public class Main {
    static class Cow implements Comparable<Cow>{
        int num, usado;
        public Cow (int num, int usado) {
            this.num = num;
            this.usado = usado;
        }

        public int compareTo(Cow c) {
            return Integer.compare(usado, c.usado);
        }

        public String toString(){
            return "[Cow num: "+num+", usado: "+usado+"] ";
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(st.nextToken());       // 소의 수
        int Q = Integer.parseInt(st.nextToken());       // 연관 수

        LinkedList<Cow>[] linkedLists = new LinkedList[N+1];
        for (int i = 0; i < N+1; i++) {
            linkedLists[i] = new LinkedList<>();
        }

        for (int i = 0; i < N-1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int u = Integer.parseInt(st.nextToken());

            linkedLists[a].add(new Cow(b, u));
            linkedLists[b].add(new Cow(a, u));
        }

        // 유사도 계산
        int[][] usados = new int[N+1][N+1];
        for (int i = 1; i < N+1; i++) {
            PriorityQueue<Cow> q = new PriorityQueue<>();
            boolean[] visited = new boolean[N+1];
            Arrays.fill(visited, false);

            visited[i] = true;
            usados[i][i] = 0;
            // 초기
            for (Cow cow : linkedLists[i]) {
                q.offer(new Cow(cow.num, cow.usado));
            }

            while(!q.isEmpty()) {
                Cow cur = q.poll();
                usados[i][cur.num] = cur.usado;
                visited[cur.num] = true;

                for(Cow cow : linkedLists[cur.num]) {
                    if (!visited[cow.num]) {
                        q.offer(new Cow(cow.num, Math.min(cow.usado, cur.usado)));
                    }
                }
            }
        }

        // 결과 출력
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int count = 0;
            // 확인
            for (int j = 1; j < N+1; j++) {
                if (usados[v][j] >= k) count++;
            }
            sb.append(count).append('\n');
        }
        System.out.print(sb.toString());
    }

}