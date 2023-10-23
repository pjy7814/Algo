import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 */
public class Main {
    static int N;
    static String[] words;
    static HashSet<String> prefix;
    static HashMap<String, Integer> map;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N =Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        prefix = new HashSet<>();
        map = new HashMap<String, Integer>();
        for (int i = 0; i < N; i++) {
            String nickname = br.readLine();
            if (map.get(nickname) == null) map.put(nickname, 1);
            else map.put(nickname, map.get(nickname)+1);

            String alias = getAlias(nickname);

            if (!alias.equals("")) {
                sb.append(alias).append("\n");
            } else if (map.get(nickname) == 1){
                sb.append(nickname).append("\n");
            } else {
                sb.append(nickname).append(map.get(nickname)).append("\n");
            }

        }

        System.out.println(sb.toString());

    }

    static String getAlias(String s) {
        String result = "";
        boolean flag = false;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            sb.append(s.charAt(i));
            if (!prefix.contains(sb.toString()) && !flag) {
                result = sb.toString();
                flag = true;
            }
            prefix.add(sb.toString());
        }

        return result;
    }
}