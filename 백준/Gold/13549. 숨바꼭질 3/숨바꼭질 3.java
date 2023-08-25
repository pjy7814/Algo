import java.io.*;
import java.util.*;

public class Main {
    static int N, K;

    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int[] visited = new int[100_001];
        Arrays.fill(visited, -1);
        visited[N] = 0;
        Deque<Integer> q = new ArrayDeque<>();
        q.offer(N);
        while(!q.isEmpty()) {
            int cur = q.pollFirst();
            if (cur == K) {
                System.out.println(visited[cur]);
                break;
            }

            if (cur *2 <= 100_000 && visited[cur*2] == -1) {
                visited[cur*2] = visited[cur];
                q.offerFirst(cur*2);
            }
            if (cur -1 >= 0 &&visited[cur-1] == -1) {
                visited[cur-1] = visited[cur]+1;
                q.offerLast(cur-1);
            }
            if (cur +1 <= 100_000 && visited[cur+1] == -1) {
                visited[cur+1] = visited[cur]+1;
                q.offerLast(cur+1);
            }
            

        }
    }
}