import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static final int INF = Integer.MAX_VALUE;
    static class Node{
        int num, weight;

        public Node(int num, int weight) {
            this.num = num;
            this.weight = weight;
        }

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());   // 유저의 수
        M = Integer.parseInt(st.nextToken());   // 친구 관계의 수
        int minDist = INF;
        int minPerson = 0;

        LinkedList<Integer>[] linkedList = new LinkedList[N+1];
        for (int i = 1; i < N+1; i++) {
            linkedList[i] = new LinkedList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            linkedList[a].add(b);
            linkedList[b].add(a);
        }

        for (int i = 1; i < N+1; i++) {
            int[] dist = new int[N+1];
            Arrays.fill(dist, INF);

            dist[i] = 0;      // 자기 자신

            Queue<Node> q = new ArrayDeque<>();
            q.offer(new Node(i, 0));
            while(!q.isEmpty()) {
                Node cur = q.poll();

                for(int next: linkedList[cur.num]) {
                    if (dist[next] > dist[cur.num] + 1) {
                        dist[next] = dist[cur.num] + 1;
                        q.offer(new Node(next, dist[next]));
                    }
                }
            }

            int sum = 0;
            for (int j = 1; j < N+1; j++) {
                sum += dist[j];
            }

            if (minDist > sum) {
                minDist = sum;
                minPerson = i;
            }

        }

        System.out.println(minPerson);

    }

}