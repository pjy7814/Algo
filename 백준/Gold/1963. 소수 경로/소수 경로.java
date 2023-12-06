import java.io.*;
import java.util.*;

public class Main {
    static final int INF = Integer.MAX_VALUE;
    static class Number implements Comparable<Number>{
        int num, cnt;

        public Number(int num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Number o) {
            return Integer.compare(this.cnt, o.cnt);
        }

        @Override
        public String toString() {
            return "Number{" +
                    "num=" + num +
                    ", cnt=" + cnt +
                    '}';
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int testCase = 0; testCase < T; testCase++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int[] arr = new int[10000];
            Arrays.fill(arr, INF);
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            arr[from] = 0;
            int result = makePrime(from, to, arr);
            sb.append(result == -1 ? "Impossible" : result).append("\n");

        }
        System.out.println(sb);
    }
    
    static int makePrime(int from, int to, int[] arr) {
        PriorityQueue<Number> pq = new PriorityQueue<>();
        pq.offer(new Number(from, 0));
        arr[from] = 0;

        while(!pq.isEmpty()) {
            Number cur = pq.poll();
            if (cur.num == to) return cur.cnt;

            int num = cur.num;
            int div = 1000;
            while (div > 0) {
                int c = num / div;
                num %= div;

                for (int i = 1; i < 10-c; i++) {
                    int tmp = cur.num + i * div;
                    if(tmp > 10000) continue;
                    if (arr[tmp] != -1 && arr[tmp] > cur.cnt && isPrime(tmp, arr)) {
                        arr[tmp] = cur.cnt + 1;
                        pq.offer(new Number(tmp, cur.cnt+1));
                    }
                }

                for (int i = 1; i < c+1; i++) {
                    int tmp = cur.num - i * div;
                    if (tmp < 1000) continue;
                    if (arr[tmp] != -1 && arr[tmp] > cur.cnt && isPrime(tmp, arr)) {
                        arr[tmp] = cur.cnt + 1;
                        pq.offer(new Number(tmp, cur.cnt+1));
                    }
                }


                div /= 10;
            }
        }

        return -1;
    }

    static boolean isPrime(int num, int[] arr) {
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                arr[i] = -1;
                return false;
            }
        }
        return true;
    }
}