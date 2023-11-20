import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        HashMap<String, Integer> set = new HashMap();

        for (int i = 0; i < N; i++) {
            String name = br.readLine();
            if (!set.containsKey(name)) set.put(name, 1);
            else set.replace(name, set.get(name)+1);
        }

        for (int i = 0; i < N-1; i++) {
            String name = br.readLine();
            if (set.containsKey(name)) {
                if (set.get(name) == 1) set.remove(name);
                else set.replace(name, set.get(name)-1);
            }
        }

        Iterator<String> iter = set.keySet().iterator();
        System.out.println(iter.next());
    }
}