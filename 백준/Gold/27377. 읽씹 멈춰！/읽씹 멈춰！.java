import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int testCase = 0; testCase < T; testCase++) {
            Long n = Long.parseLong(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            BigInteger s = new BigInteger(st.nextToken());
            BigInteger t = new BigInteger(st.nextToken());

            BigInteger minTime = BigInteger.ZERO;
            while (n > 0) {
                if (n % 2 == 1) {
                    n--;
                    minTime = minTime.add(s);
                } else {
                    n /= 2;
                    if (s.multiply(BigInteger.valueOf(n)).compareTo(t) > 0) {
                        minTime = minTime.add(t);
                    } else {
                        minTime = minTime.add(s.multiply(BigInteger.valueOf(n)));
                    }
                }
            }
            sb.append(minTime).append("\n");
        }
        System.out.println(sb);
    }
}