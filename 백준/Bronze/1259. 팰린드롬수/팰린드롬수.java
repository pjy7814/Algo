import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String s = br.readLine();
        while(!s.equals("0")) {
            boolean check = true;
            for (int i = 0; i < s.length()/2; i++) {
                if (s.charAt(i) != s.charAt(s.length()-i-1)) check = false;
            }

            sb.append(check ? "yes" : "no").append("\n");
            s = br.readLine();
        }

        System.out.print(sb);

    }


}