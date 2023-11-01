import java.io.*;
import java.util.*;

public class Main {
    static class Node {
        int y, x;
        public Node(int y, int x) {
            this.y = y;
            this.x = x;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "y=" + y +
                    ", x=" + x +
                    '}';
        }
    }

    static int[] dy = {1, 1, 1, 0, 0, 0, -1, -1, -1};
    static int[] dx = {-1, 0, 1, -1, 0, 1, -1, 0, 1};
    static char[][] map;
    static int[][] robotsMap;
    static ArrayList<Node> robots;
    static Node jongsu;
    static int R, C;
    static final String END = "kraj ";
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        robotsMap = new int[R][C];
        robots = new ArrayList<>();
        for (int i = 0; i < R; i++) {
            String s = br.readLine();
            for (int j = 0; j < C; j++) {
                if (s.charAt(j) == 'I') jongsu = new Node(i, j);
                else if (s.charAt(j) == 'R') robots.add(new Node(i, j));
            }
        }

        String move = br.readLine();
        boolean flag = true;
        for (int i = 0; i < move.length(); i++) {
            for (int j = 0; j < R; j++) {
                Arrays.fill(map[j], '.');
            }
            int dir = move.charAt(i) - '0' -1;
            if (!moveJongsu(dir) || !moveRobots()) {
                sb.append(END).append(i+1);
                flag = false;
                break;
            }
        }

        if (flag) {
            for (int i = 0; i < R; i++) {
                Arrays.fill(map[i], '.');
            }

            map[jongsu.y][jongsu.x] = 'I';

            for(Node cur : robots) {
                map[cur.y][cur.x] = 'R';
            }

            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    sb.append(map[i][j]);
                }
                sb.append("\n");
            }
        }

        System.out.println(sb);

    }

    // 로봇 이동
    static boolean moveRobots() {
        for (int j = 0; j < R; j++) {
            Arrays.fill(robotsMap[j], 0);
        }
        for(int j = 0; j < robots.size(); j++) {
            Node cur = robots.get(j);
            int minY = 0;
            int minX = 0;
            int minDist = Integer.MAX_VALUE;

            for (int i = 0; i < 9; i++) {
                int ny = cur.y + dy[i];
                int nx = cur.x + dx[i];

                if (!possible(ny, nx)) continue;
                int dist = Math.abs(ny- jongsu.y) + Math.abs(nx- jongsu.x);
                if (dist < minDist) {
                    minDist = dist;
                    minY = ny;
                    minX = nx;
                }
            }
            robots.set(j, new Node(minY, minX));
            if (robots.get(j).y == jongsu.y && robots.get(j).x == jongsu.x) return false;

            robotsMap[minY][minX]++;
        }

        robots.clear();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (robotsMap[i][j] == 1) robots.add(new Node(i, j));
                else robotsMap[i][j] = 0;
            }
        }

        return true;
    }

    static boolean moveJongsu(int dir) {
        int ny = jongsu.y + dy[dir];
        int nx = jongsu.x + dx[dir];

        jongsu = new Node(ny, nx);
        if (robotsMap[jongsu.y][jongsu.x] > 0) return false;
        return true;
    }

    static boolean possible(int y, int x) {
        if (y >= 0 && y < R && x >= 0 && x < C) return true;
        return false;
    }
}