import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int[] parent;
    static class Node implements Comparable<Node>{
        int a, b, w;

        public Node(int a, int b, int w) {
            this.a = a;
            this.b = b;
            this.w = w;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.w, o.w);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        int result = 0;

        int[][] map = new int[E][3];
        PriorityQueue<Node> pq = new PriorityQueue<>();
        parent = new int[V+1];

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            pq.offer(new Node(a, b, w));
        }

        for (int i = 1; i < V+1; i++) {
            parent[i] = i;
        }

        while(!pq.isEmpty()) {
            Node cur = pq.poll();
            if (union(cur.a, cur.b)) {
                result += cur.w;
            }
        }

        System.out.println(result);
    }

    static boolean union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a == b) {
            return false;
        } else if (a > b) {
            parent[a] = b;
        } else {
            parent[b] = a;
        }
        return true;
    }

    static int find(int a) {
        if (parent[a] == a) return a;
        else return parent[a] = find(parent[a]);
    }
}