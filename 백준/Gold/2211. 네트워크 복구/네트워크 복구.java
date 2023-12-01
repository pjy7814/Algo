import java.io.*;
import java.security.Key;
import java.util.*;

public class Main {
    static final int INF = Integer.MAX_VALUE;
    static int N, M, result = INF;
    static LinkedList<Node>[] list;
    static int[] dist;
    static boolean[] visited;
    static StringBuilder sb;
    static class Node implements Comparable<Node> {
        int num, weight;

        public Node(int num, int weight) {
            this.num = num;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node n) {
            return Integer.compare(weight, n.weight);
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        list = new LinkedList[N+1];
        for (int i = 0; i < N+1; i++) {
            list[i] = new LinkedList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            list[s].add(new Node(e, w));
            list[e].add(new Node(s, w));
        }

        dijstra(1);

    }

    static void dijstra(int start) {
        PriorityQueue<Node> q = new PriorityQueue<>();
        q.offer(new Node(start, 0));

        visited = new boolean[N+1];
        dist = new int[N+1];
        Arrays.fill(dist, INF);

        dist[start] = 0;

        int[] from = new int[N+1];
        while(!q.isEmpty()) {
            Node cur = q.poll();
            if (visited[cur.num]) continue;
            visited[cur.num] = true;
            for(Node next : list[cur.num]) {
                if (dist[next.num] > dist[cur.num] + next.weight) {
                    dist[next.num] = dist[cur.num] + next.weight;
                    q.offer(new Node(next.num, dist[next.num]));

                    from[next.num] = cur.num;
                }
            }
        }
        int sum = 0;
        for (int i = 1; i < N+1; i++) {
            sum += dist[i];
        }

        sb = new StringBuilder();
        result = sum;
        sb.append(N-1).append("\n");
        for (int i = 1; i < N+1; i++) {
            if (i == start) continue;
            sb.append(i).append(" ").append(from[i]).append("\n");
        }
        System.out.println(sb);
    }
}