import java.io.*;
import java.util.*;

public class Main {
    static int N, a, b, c;
    static int[] visitedA, visitedB, visitedC;
    static LinkedList<Integer>[] linkedLists;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine());
        
        linkedLists = new LinkedList[N+1];
        for (int i = 0; i < N+1; i++) {
            linkedLists[i] = new LinkedList<>();
        }

        for (int i = 0; i < N-1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            linkedLists[n].add(m);
            linkedLists[m].add(n);
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        visitedA = dfs(Integer.parseInt(st.nextToken()));
        visitedB = dfs(Integer.parseInt(st.nextToken()));
        visitedC = dfs(Integer.parseInt(st.nextToken()));

        String result = "NO";
        for (int i = 1; i < N+1; i++) {
            if (linkedLists[i].size() == 1) {
                if (visitedA[i] < visitedB[i] && visitedA[i] < visitedC[i]) {
                    result = "YES";
                    break;
                }
            }
        }

        System.out.println(result);
    }

    static int[] dfs(int start) {
        int[] visited = new int[N+1];
        Queue<Integer> q = new ArrayDeque<>();
        Arrays.fill(visited, -1);
        visited[start] = 0;
        q.offer(start);

        while(!q.isEmpty()) {
            int cur = q.poll();

            for(int node : linkedLists[cur]) {
                if (visited[node] == -1) {
                    visited[node] = visited[cur]+1;
                    q.offer(node);
                }
            }
        }
        return visited;
    }
}