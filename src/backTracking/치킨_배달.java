package backTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class 치킨_배달 {
    private static int result = Integer.MAX_VALUE;
    private static int answer;
    private static List<Point> chickenChoice = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]); // 도시의 크기
        int M = Integer.parseInt(input[1]); // 확인할 치킨집의 개수

        List<Point> house = new ArrayList<>();
        List<Point> chicken = new ArrayList<>();

        // 도시에서 1 에 해당하는 위치는 집에 담아주고, 2에 해당하는건 치킨집을 담아준다.
        // 여기서 중요한 건 좌표로 담아줘야 한다는 점이다.
        for(int i = 0; i < N; i++) {
            String[] row = br.readLine().split(" ");
            for(int j = 0; j < row.length; j++) {
                if(row[j].equals("1")) {
                    house.add(new Point(i, j));
                }
                else if(row[j].equals("2")) {
                    chicken.add(new Point(i, j));
                }
            }
        }

        backTracking(0, 0, M, house, chicken);
        System.out.println(result);
    }

    public static void backTracking(int depth, int idx, int M, List<Point> house, List<Point> chicken) {
        if(depth == M) {
            // 원하는건 집과 치킨 집의 거리가 가장 짧은 것의 합이 최소가 되어야 하는 점이다.
            // 남길 치킨 집에서 찾은 최소의 거리의 합을 저장할 변수
            int sum = 0;
            for(Point h : house) {
                // 거리의 최소 값을 찾기 위해 선언
                answer = Integer.MAX_VALUE;
                for(Point c : chickenChoice) {
                    int distance = Math.abs(c.x - h.x) + Math.abs(c.y - h.y);
                    answer = Math.min(answer, distance);
                }
                sum += answer;
            }
            // 폐업시키지 않을 치킨집만 골라서 집과의 거리를 최소로한 결과 값들을 담아준다.
            // 주의할 점은 폐업하지 않을 치킨집은 다를 수 있기 때문에 많은 가능성 중에서 가장 작은 값만 찾아야한다.
            result = Math.min(result, sum);
            return;
        }
        // depth 와 idx 를 입력받아 중복되지 않는 좌표 값들을 담아준다 -> boolean 의 효과
        for(int i = idx; i < chicken.size(); i++) {
            Point chickenPoint = chicken.get(i);
            chickenChoice.add(chickenPoint);
            backTracking(depth+1, i+1, M, house, chicken);
            // 꼭 뒤에 제거를 해줘야한다. 리스트 안에 값만 추가되어 이상한 값이 도출된다.
            chickenChoice.remove(chickenPoint);
        }
    }
}

class Point {
    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
