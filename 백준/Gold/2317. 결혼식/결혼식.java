import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] lions = new int[K];
        int minH = Integer.MAX_VALUE;
        int maxH = 0;
        for (int i = 0; i < K; i++) {
            lions[i] = Integer.parseInt(br.readLine());
            minH = Math.min(minH, lions[i]);
            maxH = Math.max(maxH, lions[i]);
        }


        int totalH = 0;
        for (int i = 0; i < K-1; i++) {
            totalH += Math.abs(lions[i] - lions[i+1]);
        }

        int maxPerson = 0;
        int minPerson = Integer.MAX_VALUE;
        for (int i = 0; i < N-K; i++) {
            int height= Integer.parseInt(br.readLine());
            minPerson = Math.min(minPerson, height);
            maxPerson = Math.max(maxPerson, height);
        }

        if (maxPerson > maxH) {
            int tmp = 0;
            if (maxH == lions[0] || maxH == lions[K - 1]) tmp = (maxPerson - maxH);
            else tmp = (maxPerson - maxH) * 2;

            tmp = Math.min(tmp, maxPerson - lions[0]);
            tmp = Math.min(tmp, maxPerson - lions[K - 1]);

            totalH += tmp;
        }

        if (minPerson < minH) {
            int tmp = 0;
            if (minH == lions[0] || minH == lions[K - 1]) tmp = (minH - minPerson);
            else tmp = (minH - minPerson) * 2;

            tmp = Math.min(tmp, lions[0] - minPerson);
            tmp = Math.min(tmp, lions[K - 1] - minPerson);

            totalH += tmp;
        }

        System.out.println(totalH);
    }
}