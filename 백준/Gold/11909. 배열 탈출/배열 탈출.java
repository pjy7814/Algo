import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] map, visited;
    static int[] dy = {1, 0};
    static int[] dx = {0, 1};
    static class Node implements Comparable<Node>{
        int y, x, cost;

        public Node(int y, int x, int cost) {
            this.y = y;
            this.x = x;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node n) {
            return Integer.compare(this.cost, n.cost);
        }

        @Override
        public String toString() {
            return "Node{" +
                    "y=" + y +
                    ", x=" + x +
                    ", cost=" + cost +
                    '}';
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        visited = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(visited[i], Integer.MAX_VALUE);
        }
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(0, 0, 0));

        while(!pq.isEmpty()) {
            Node cur = pq.poll();
            if (cur.y == N-1 && cur.x == N-1) {
                System.out.println(cur.cost);
                break;
            }
            for (int i = 0; i < 2; i++) {
                int ny = cur.y + dy[i];
                int nx = cur.x + dx[i];

                if (ny < N && ny >= 0 && nx < N && nx >= 0) {
                    if (map[ny][nx] < map[cur.y][cur.x] && visited[ny][nx] > cur.cost) {
                        visited[ny][nx] = cur.cost;
                        pq.offer(new Node(ny, nx, cur.cost));
                    } else if (map[ny][nx] >= map[cur.y][cur.x]
                            && visited[ny][nx] > cur.cost + map[ny][nx] - map[cur.y][cur.x]+1) {
                        visited[ny][nx] = cur.cost + map[ny][nx] - map[cur.y][cur.x]+1;
                        pq.offer(new Node(ny, nx, cur.cost + map[ny][nx] - map[cur.y][cur.x]+1));
                    }
                }
            }
        }

    }
}