import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));

        String channel = br.readLine();
        int N = Integer.parseInt(channel);    // 채널 번호
        int M = Integer.parseInt(br.readLine());

        Boolean[] buttons = new Boolean[10];
        Arrays.fill(buttons, true);

        if (M != 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                buttons[Integer.parseInt(st.nextToken())] = false;
            }
        }

        int result = Math.abs(N-100);
        for (int i = 0; i < 999_999; i++) {
            String s = Integer.toString(i);

            Boolean flag = false;
            // 버튼으로 누를 수 있는지 확인
            for (int j = 0; j < s.length(); j++) {
                if (!buttons[s.charAt(j)-'0']) {
                    flag = true;
                    break;
                }
            }

            // 계산
            if (!flag) {
                result = Math.min(result, Math.abs(i-N)+s.length());
            }
        }

        System.out.println(result);

    }
}