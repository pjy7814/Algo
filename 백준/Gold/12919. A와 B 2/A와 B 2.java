import java.io.*;
import java.util.*;

public class Main {
    static int possible = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();
        String target = br.readLine();

        findString(s, target);
        System.out.println(possible);
    }

    static void findString(String s, String target) {
        if (s.equals(target) || possible == 1) {
            possible = 1;
            return;
        }

        if (target.length() == 1) return;

        if (target.charAt(target.length()-1) == 'A') {
            findString(s, target.substring(0, target.length() - 1));
        }

        if (target.charAt(0) == 'B') {
            findString(s, reverse(target.substring(1)));
        }
    }

    static String reverse(String s) {
        String result = "";
        for (int i = s.length()-1; i >= 0 ; i--) {
            result += s.charAt(i);
        }
        return result;
    }

}