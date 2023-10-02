import java.io.*;
import java.util.*;

public class Main {
    static class Problem implements Comparable<Problem>{
        int deadline, cupCnt;

        public Problem(int deadline, int cupCnt) {
            this.deadline = deadline;
            this.cupCnt = cupCnt;
        }

        @Override
        public int compareTo(Problem o) {
            if (this.deadline== o.deadline) {
                return Integer.compare(o.cupCnt, this.cupCnt);
            } else {
                return Integer.compare(this.deadline, o.deadline);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Problem> pq = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            pq.offer(new Problem(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));

        }

        PriorityQueue<Integer> q = new PriorityQueue<>();
        long totalCupCnt = 0;
        while(!pq.isEmpty()) {
            Problem p = pq.poll();

            if (p.deadline > q.size()) {
                q.offer(p.cupCnt);
            } else if (p.deadline == q.size()) {
                if (q.peek() < p.cupCnt) {
                    q.poll();
                    q.add(p.cupCnt);
                }
            }
        }
        while(!q.isEmpty()) {
            totalCupCnt += q.poll();
        }


        System.out.print(totalCupCnt);

    }

}