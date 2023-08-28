import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] parent;
    static boolean[] visited;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        parent = new int[N+1];
        visited = new boolean[N+1];

        ArrayList<Integer>[] list = new ArrayList[N+1];
        for (int i = 0; i < N+1; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < N-1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            list[a].add(b);
            list[b].add(a);
        }

        findParent(list, 1);

        for (int i = 2; i < N+1; i++) {
            sb.append(parent[i]).append('\n');
        }

        System.out.println(sb);
    }

    static void findParent(ArrayList<Integer>[] list, int index) {
        visited[index] = true;
        for (int n : list[index]) {
            if (!visited[n]) {
                parent[n] = index;
                findParent(list, n);
            }
        }
    }
}