import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K;
    static int[] children;
    static LinkedList<Integer>[] lists;
    static boolean[] visited;
    static class Child {
        int childCnt, candyCnt;

        public Child(int childCnt, int candyCnt) {
            this.childCnt = childCnt;
            this.candyCnt = candyCnt;
        }

        @Override
        public String toString() {
            return "Child{" +
                    "childCnt=" + childCnt +
                    ", candyCnt=" + candyCnt +
                    '}';
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        children = new int[N+1];
        visited = new boolean[N+1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N+1; i++) {
            children[i] = Integer.parseInt(st.nextToken());
        }

        lists = new LinkedList[N+1];
        for (int i = 0; i < N+1; i++) {
            lists[i] = new LinkedList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            lists[a].add(b);
            lists[b].add(a);
        }

        ArrayList<Child> haveCandyList = new ArrayList<>();
        for (int i = 1; i < N+1; i++) {
            if(!visited[i]) {
                haveCandyList.add(bfs(i));
            }
        }

        int[] dp = new int[K];
        for (Child cur: haveCandyList) {
            for (int i = K-1; i >= cur.childCnt ; i--) {
                dp[i] = Math.max(dp[i], dp[i-cur.childCnt]+cur.candyCnt);
            }
        }

        System.out.println(dp[K-1]);
    }

    public static Child bfs(int num) {
        Queue<Integer> q = new ArrayDeque<>();
        q.add(num);
        visited[num] = true;
        int childCnt = 0;
        int candyCnt = 0;
        while(!q.isEmpty()) {
            int cur = q.poll();
            childCnt++;
            candyCnt += children[cur];

            for(int child: lists[cur]) {
                if (!visited[child]) {
                    q.offer(child);
                    visited[child] = true;
                }
            }
        }
        return new Child(childCnt, candyCnt);
    }
}