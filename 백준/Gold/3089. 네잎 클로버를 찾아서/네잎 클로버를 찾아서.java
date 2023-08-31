import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        TreeSet<Integer>[] xSet = new TreeSet[200001];
        TreeSet<Integer>[] ySet = new TreeSet[200001];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken())+100000;
            int y = Integer.parseInt(st.nextToken())+100000;

            if (xSet[x] == null) xSet[x] = new TreeSet<>();
            if (ySet[y] == null) ySet[y] = new TreeSet<>();
            xSet[x].add(y);
            ySet[y].add(x);
        }

        String dir = br.readLine();

        // 현재 위치
        int nowX = 100000;
        int nowY = 100000;
        for (int i = 0; i < M; i++) {
            char d = dir.charAt(i);

            switch (d) {
                case 'U':
                    nowY = xSet[nowX].higher(nowY);
                    break;
                case 'D':
                    nowY = xSet[nowX].lower(nowY);
                    break;
                case 'R':
                    nowX = ySet[nowY].higher(nowX);
                    break;
                case 'L':
                    nowX = ySet[nowY].lower(nowX);
                    break;
            }
        }

        System.out.print((nowX-100000)+" "+(nowY-100000));
    }


}