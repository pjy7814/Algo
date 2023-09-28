import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        PriorityQueue<Integer> pqA = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> pqB = new PriorityQueue<>();
        int N = Integer.parseInt(br.readLine());

        int num = Integer.parseInt(br.readLine());
        sb.append(num).append("\n");
        for (int i = 1; i < N; i++) {
            int tmp = Integer.parseInt(br.readLine());

            if (num > tmp) pqA.add(tmp);
            else pqB.add(tmp);
            
            if (pqA.size() > pqB.size()) {
                pqB.add(num);
                num = pqA.poll();
            } else if (pqA.size()+2 == pqB.size()) {
                pqA.add(num);
                num = pqB.poll();
            }

            sb.append(num).append("\n");
        }
        System.out.println(sb);


    }

}