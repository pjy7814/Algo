import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();


        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            StringTokenizer sound = new StringTokenizer(br.readLine());
            HashSet<String> set = new HashSet<>();
            String s = br.readLine();
            while(!s.equals("what does the fox say?")) {
                StringTokenizer animals = new StringTokenizer(s);
                if (!animals.nextToken().equals("fox")) {
                    animals.nextToken();
                    set.add(animals.nextToken());
                }
                s = br.readLine();
            }

            while(sound.hasMoreTokens()) {
                s = sound.nextToken();

                if (!set.contains(s)) {
                    sb.append(s).append(" ");
                }
            }
            sb.append("\n");
        }

        System.out.println(sb);


    }
}