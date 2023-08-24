import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            String p = br.readLine();       // 수행할 함수
            int n = Integer.parseInt(br.readLine());
            String tmpListString = br.readLine();

            tmpListString = tmpListString.substring(1, tmpListString.length()-1);
            StringTokenizer st = new StringTokenizer(tmpListString, ",");
            Deque<Integer> q = new ArrayDeque<>();

            for (int j = 0; j < n; j++) {
                q.addLast(Integer.parseInt(st.nextToken()));
            }

            boolean isError = false;
            boolean isReverse = false;

            for (int j = 0; j < p.length(); j++) {
                char tmpP = p.charAt(j);
                if (tmpP == 'R') {
                    isReverse = !isReverse;
                } else {        // 'D'
                    if (q.isEmpty()) {
                        isError = true;
                        break;
                    }
                    if (!isReverse) {
                        q.pollFirst();
                    } else {
                        q.pollLast();
                    }
                }
            }

            // print
            if (isError) sb.append("error\n");
            else if (q.size() == 0) sb.append("[]\n");
            else {
                sb.append('[');
                if (isReverse) {
                    while(q.size()>1) {
                        sb.append(q.pollLast()).append(",");
                    }
                    sb.append(q.pollLast());
                } else {
                    while(q.size()>1) {
                        sb.append(q.pollFirst()).append(",");
                    }
                    sb.append(q.pollFirst());
                }
                sb.append("]\n");
            }
        }

        System.out.println(sb.toString());


    }

}