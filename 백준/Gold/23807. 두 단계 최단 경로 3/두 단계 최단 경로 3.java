import java.io.*;
import java.util.*;

public class Main {
    static int N, M, X, Z, P;
    static LinkedList<Node>[] linkedList;
    static final Long INF = 1_000_000L * 300_000L +1;
    static int[] essentialNode;
    static class Node implements Comparable<Node>{
        int v;
        long w;

        public Node(int v, long w) {
            this.v = v;
            this.w = w;
        }

        public int compareTo(Node n) {
            return Long.compare(this.w, n.w);
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        linkedList = new LinkedList[N+1];
        for (int i = 0; i < N+1; i++) {
            linkedList[i] = new LinkedList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            linkedList[u].add(new Node(v, w));
            linkedList[v].add(new Node(u, w));
        }

        st = new StringTokenizer(br.readLine());
        X = Integer.parseInt(st.nextToken());
        Z = Integer.parseInt(st.nextToken());

        P = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        essentialNode = new int[P];
        for (int i = 0; i < P; i++) {
            essentialNode[i] = Integer.parseInt(st.nextToken());
        }

        long[][] visitedP = new long[P][N+1];
        long[] visitedX = new long[N+1];
        Arrays.fill(visitedX, INF);
        PriorityQueue<Node> pq = new PriorityQueue<>();

        visitedX[X] = 0;
        pq.offer(new Node(X, 0));

        while(!pq.isEmpty()) {
            Node cur = pq.poll();
            if (cur.w > visitedX[cur.v]) continue;
            for(Node v : linkedList[cur.v]) {
                if (visitedX[v.v] > v.w + cur.w) {
                    visitedX[v.v] = v.w + cur.w;
                    pq.offer(new Node(v.v, visitedX[v.v]));
                }
            }
        }

        for (int i = 0; i < P; i++) {
            Arrays.fill(visitedP[i], INF);
        }
        for (int i = 0; i < P; i++) {
            pq = new PriorityQueue<>();

            visitedP[i][essentialNode[i]] = 0;
            pq.offer(new Node(essentialNode[i], 0));

            while(!pq.isEmpty()) {
                Node cur = pq.poll();
                if (cur.w > visitedP[i][cur.v]) continue;
                for(Node v : linkedList[cur.v]) {
                    if (visitedP[i][v.v] > v.w + cur.w) {
                        visitedP[i][v.v] = v.w + cur.w;
                        pq.offer(new Node(v.v, visitedP[i][v.v]));
                    }
                }
            }
        }

        long result = INF;
        for (int i = 0; i < P; i++) {
            for (int j = 0; j < P; j++) {
                for (int k = 0; k < P; k++) {
                    if (i==j || j == k || k == i) continue;
                    int a = essentialNode[i];
                    int b = essentialNode[j];
                    int c = essentialNode[k];
                    long dist = visitedX[a] + visitedP[i][b]+visitedP[j][c]+visitedP[k][Z];
                    result = Math.min(dist, result);
                }
            }
        }

        System.out.println(result == INF ? -1 : result);

    }
}