import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        Stack<Integer> stack = new Stack<>();

        long total = 0;
        for (int i = 0; i < N; i++) {
            int tmp = Integer.parseInt(br.readLine());
            if (stack.isEmpty()) {
                stack.push(tmp);
                continue;
            }

            if (stack.peek() > tmp) {   // 볼 수 있음
                total += stack.size();
                stack.push(tmp);
            } else {
                while(!stack.isEmpty() && stack.peek() <= tmp) {
                    stack.pop();
                }
                total += stack.size();
                stack.push(tmp);
            }
        }

        System.out.print(total);



    }
}