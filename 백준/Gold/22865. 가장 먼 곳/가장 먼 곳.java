import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static ArrayList<Integer> friends;
    static LinkedList<Land>[] roads;
    static final int INF = Integer.MAX_VALUE;
    static class Land {
        int num, length;

        public Land(int num, int length) {
            this.num = num;
            this.length = length;
        }

        @Override
        public String toString() {
            return "Land{" +
                    "num=" + num +
                    ", length=" + length +
                    '}';
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        roads = new LinkedList[N+1];
        for (int i = 1; i < N+1; i++) {
            roads[i] = new LinkedList<>();
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        friends = new ArrayList<>();
        for (int i = 1; i < 4; i++) {
            friends.add(Integer.parseInt(st.nextToken()));
        }

        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            roads[a].add(new Land(b, l));
            roads[b].add(new Land(a, l));
        }

        int maxLength = 0;  // 친구랑 같이 살면 0이다
        int[] maxDist = new int[N+1];
        Arrays.fill(maxDist, INF);
        for(int f: friends) {
            Queue<Land> q = new ArrayDeque<>();
            q.offer(new Land(f, 0));
            int[] dist = new int[N+1];
            Arrays.fill(dist, INF);
            dist[f] = 0;

            int min = INF;
            while(!q.isEmpty()) {
                Land cur = q.poll();
                for(Land l : roads[cur.num]) {
                    if (dist[l.num] > l.length + dist[cur.num]) {
                        dist[l.num] = l.length + dist[cur.num];
                        q.offer(new Land(l.num, dist[l.num]));
                    }
                }
            }

            for (int i = 1; i < N+1; i++) {
                maxDist[i] = Math.min(maxDist[i], dist[i]);
            }
        }
        int result = 1;
        int cnt = 0;
        for (int i = 1; i < N+1; i++) {
            if (maxDist[i] != 0 && maxLength < maxDist[i]) {
                maxLength = maxDist[i];
                result = i;
            }
        }

        System.out.println(result);
    }
}