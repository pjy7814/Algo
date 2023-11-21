import java.io.*;
import java.util.*;

/*
ABBA
tmp = BCAA
 */
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        char[] arr = br.readLine().toCharArray();
        String tmp = "";
        tmp += arr[0];
        for (int i = 1; i < arr.length; i++) {
            char c = arr[i];
            if (tmp.charAt(tmp.length()-1) < c) tmp = c + tmp;
            else tmp = tmp + c;
        }

        for (int i = arr.length-1; i >= 0; i--) {
            sb.append(tmp.charAt(i));
        }
        System.out.println(sb);
    }
}