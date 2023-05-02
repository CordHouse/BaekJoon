package backTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class 스도쿠 {
    private static String[][] map;
    private static List<Point> zeroPoint = new ArrayList<>(); // 0의 자리를 담아줄 리스트
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        map = new String[9][9];
        // map 을 초기화 함과 동시에 0이 담긴 위치를 찾아 리스트에 담아준다.
        for(int i = 0; i < 9 ; i++) {
            String[] line = br.readLine().split(" ");
            for(int j = 0; j < 9; j++) {
                if(line[j].equals("0")) {
                    zeroPoint.add(new Point(i, j));
                }
                map[i][j] = line[j];
            }
        }

        backTracking(0);
    }
    public static void backTracking(int depth) {
        // 리스트 안에 0의 개수가 맞아 떨어질 경우 종료의 조건이 된다.
        // 기존 코드에서는 System.exit(0) 코드를 작성하지 않았었다.
        // 그렇게 되면 주어지는 스도쿠 조건에서 0이 무수히 많은 경우엔 경우의 수가 너무 많아 정확한 값을 추리기 어려워진다.
        // 따라서 어떤 경우라도 완성이 되면 되기 때문에 한번이라도 완성되면 종료시켜 값을 찾아준다.
        // return (x), System.exit(0) (o)
        if(depth == zeroPoint.size()) {
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < 9 ; i++) {
                sb.append(map[i][0]);
                for(int j = 1; j < 9; j++) {
                    sb.append(" ").append(map[i][j]);
                }
                sb.append("\n");
            }
            System.out.println(sb);
            System.exit(0);
        }

        // 저장된 0의 위치에 따라 1~9까지 넣어보면서 중복되지 않게 값을 찾는다.
        // 확인해야할 정보는 다음과 같다.
        // 가로, 세로가 일치한 값이 있는가?
        // 자기영역 3*3안에 일치하는 값이 있는가?
        Point point = zeroPoint.get(depth);
        for(int i = 1; i <= 9; i++) {
            if(!sameNumberCheck(point.x, point.y, String.valueOf(i))) {
                map[point.x][point.y] = String.valueOf(i);
                backTracking(depth+1);
                map[point.x][point.y] = "0";
            }
        }
    }

    public static boolean sameNumberCheck(int x, int y, String value) {
        for(int i = 0; i < 9; i++) {
            // 행에 대한 비교
            if(map[x][i].equals(value)) {
                return true;
            }
            // 열에 대한 비교
            else if(map[i][y].equals(value)) {
                return true;
            }
        }

        // 3*3 영역 안의 비교
        x = (x/3)*3;
        y = (y/3)*3;
        for(int i = x; i < x+3; i++) {
            for(int j = y; j < y+3; j++) {
                if(map[i][j].equals(value)) {
                    return true;
                }
            }
        }
        return false;
    }
    static class Point{
        int x;
        int y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
