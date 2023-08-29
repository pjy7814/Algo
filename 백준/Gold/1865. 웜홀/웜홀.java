import java.io.*;
import java.util.*;

public class Main {
    static class Node {
        int num, weight;

        public Node(int num, int weight) {
            this.num = num;
            this.weight = weight;
        }
    }

    static final int INF = 10001;
    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int TC = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < TC; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());       // 지점의 수
            int M = Integer.parseInt(st.nextToken());       // 도로의 수
            int W = Integer.parseInt(st.nextToken());       // 웜홀의 수

            ArrayList<Node>[] list = new ArrayList[N+1];
            for (int i = 0; i < N+1; i++) {
                list[i] = new ArrayList<>();
            }

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());

                list[a].add(new Node(b, w));
                list[b].add(new Node(a, w));
            }

            for (int i = 0; i < W; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());

                list[a].add(new Node(b, -w));
            }

            boolean result = false;
            int[] dist = new int[N+1];
            Arrays.fill(dist, INF);
            dist[1] = 0;

            for (int i = 1; i < N; i++) {
                for (int j = 1; j < list.length; j++) {
                    for(Node next:list[j]) {
                        if (dist[next.num] > dist[j] + next.weight) {
                            dist[next.num] = dist[j] + next.weight;
                        }
                    }
                }
            }

            // 갱신이 더 가능하면 음의 사이클
            for (int j = 1; j < list.length; j++) {
                for(Node next:list[j]) {
                    if (dist[next.num] > dist[j] + next.weight) {
                        result = true;
                        break;
                    }
                }
                if (result) break;
            }


            sb.append(result ? "YES":"NO").append('\n');
        }

        System.out.print(sb.toString());
    }
}