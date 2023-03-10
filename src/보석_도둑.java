import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 보석_도둑 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        int N = Integer.parseInt(temp[0]);
        int K = Integer.parseInt(temp[1]);
        Jewel[] jewels = new Jewel[N];
        int[] bag = new int[K];
        long answer  = 0; // 계산 영역이 int 의 범위를 벗어나기 때문에 주의

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            jewels[i] = new Jewel(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        // 무게가 같을 때 가격에 대해 내림차 순
        // 그 외 무게에 대해 오름차 순
        Arrays.sort(jewels, new Comparator<Jewel>() {
            @Override
            public int compare(Jewel o1, Jewel o2) {
                if(o1.m == o2.m) {
                    return o2.v - o1.v;
                }
                return o1.m - o2.m;
            }
        });

        for(int i = 0; i < K; i++) {
            bag[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(bag);

        PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder()); // 우선 순위 큐 내림차순 정렬
        int num = 0;
        for(int i = 0; i < K; i++) {
            // jewels 과 bag 에는 이미 정렬된 값이 있기 때문에 가장 무게가 낮은 가방에 담을 수 있는 무게면 그 위에 가방에는 무조건 담을 수 있다.
            // queue.isEmpty() 를 추가하면 단축 될 것 같지만, 아니다 왜냐하면 가방의 무게에 기준으로 담아놓기 때문에 뒤로 갈수록 더 큰 무게가 담긴다.
            // 더 큰 무게에는 더 큰 값이 있을 수 있기 때문에 무조건 이렇게 해야한다.
            // ex) 보석 [1, 10] [1, 5] [2, 50]
            // 가방 10, 10
            // 위의 예시처럼 둘다 가방 보다 작기 때문에 담을 수 있는데 첫번째 반복에서 1, 5가 담기기 때문에 문제가 되기 때문이다.
            // 원래는 10과 50이 담겨 60이 되어야 한다.
            while(num < N && bag[i] >= jewels[num].m) {
                queue.add(jewels[num].v);
                num++;
            }

            if(!queue.isEmpty()) {
                answer += queue.poll();
            }
        }
        System.out.println(answer);
    }
}

class Jewel {
    int m;
    int v;

    public Jewel(int m, int v) {
        this.m = m;
        this.v = v;
    }
}
