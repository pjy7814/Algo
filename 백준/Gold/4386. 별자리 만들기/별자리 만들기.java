import java.io.*;
import java.util.*;

public class Main {
    static class Star implements Comparable<Star>{
        int to, from;
        double dist;

        public Star(int to, int from, double dist) {
            this.to = to;
            this.from = from;
            this.dist = dist;
        }


        @Override
        public int compareTo(Star o) {
            return Double.compare(this.dist, o.dist);
        }

        @Override
        public String toString() {
            return "Star{" +
                    "to=" + to +
                    ", from=" + from +
                    ", dist=" + dist +
                    '}';
        }
    }

    static ArrayList<Star> starDist;
    static int[] parents;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Double[][] stars = new Double[N][2];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            stars[i][0] = Double.parseDouble(st.nextToken());
            stars[i][1] = Double.parseDouble(st.nextToken());
        }

        starDist = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 1; j < N; j++) {
                if (i==j) continue;
                double dist = Math.sqrt(Math.pow((stars[i][0] - stars[j][0]), 2) + Math.pow((stars[i][1] - stars[j][1]), 2));
                starDist.add(new Star(i, j, dist));
            }
        }
        Collections.sort(starDist);

        parents = new int[N];
        for (int i = 0; i < N; i++) {
            parents[i] = i;
        }

        Double totalDist = (double) 0;
        for(Star cur: starDist) {
            if (find(cur.to) != find(cur.from)) {
                totalDist += cur.dist;
                union(cur.to, cur.from);
            }
        }

        System.out.println(String.format("%.2f", totalDist));
    }

    static int find(int a) {
        if (parents[a] == a) return a;
        return parents[a] = find(parents[a]);
    }

    static void union(int a, int b) {
        int parentA = find(a);
        int parentB = find(b);
        if(parentA < parentB) {
            parents[parentB] = parentA;
        } else {
            parents[parentA] = parentB;
        }
    }
}