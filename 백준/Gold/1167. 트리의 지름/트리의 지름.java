import java.io.*;
import java.util.*;

public class Main {
    static class Node {
        int num, length, totalLength =0;

        public Node(int num, int weight) {
            this.num = num;
            this.length = weight;
        }

        public Node(int num, int weight, int totalLength) {
            this.num = num;
            this.length = weight;
            this.totalLength = totalLength;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "num=" + num +
                    ", length=" + length +
                    ", totalLength=" + totalLength +
                    '}';
        }
    }

    static int N;
    static ArrayList<Node>[] list;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        list = new ArrayList[N+1];
        for (int i = 0; i < N+1; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 1; i < N+1; i++) {
            StringTokenizer st =new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            while(st.hasMoreTokens()) {
                int n = Integer.parseInt(st.nextToken());
                if (n == -1) break;
                list[v].add(new Node(n, Integer.parseInt(st.nextToken())));
            }
        }

        // 한 지점 선택해서 제일 먼 지점 찾기
        int maxLength = 0;
        int maxNodeNum = 1;
        Queue<Node> q = new ArrayDeque<>();
        boolean[] visited = new boolean[N+1];
        visited[maxNodeNum] = true;
        for (Node n : list[maxNodeNum]) {
            q.offer(new Node(n.num, n.length, n.length));
            visited[n.num] = true;
        }

        while(!q.isEmpty()) {
            Node cur = q.poll();
            if (maxLength < cur.totalLength) {
                maxLength = cur.totalLength;
                maxNodeNum = cur.num;
            }

            for(Node node : list[cur.num]) {
                if (!visited[node.num]) {
                    visited[node.num] = true;
                    q.offer(new Node(node.num, node.length, cur.totalLength + node.length));
                }
            }
        }


        maxLength = 0;
        q = new ArrayDeque<>();
        visited = new boolean[N+1];
        visited[maxNodeNum] = true;
        for (Node n : list[maxNodeNum]) {
            q.offer(new Node(n.num, n.length, n.length));
            visited[n.num] = true;
        }

        while(!q.isEmpty()) {
            Node cur = q.poll();
            maxLength = Math.max(maxLength, cur.totalLength);

            for(Node node : list[cur.num]) {
                if (!visited[node.num]) {
                    visited[node.num] = true;
                    q.offer(new Node(node.num, node.length, cur.totalLength + node.length));
                }
            }
        }

        System.out.println(maxLength);
    }
}