package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class AC {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cycle = Integer.parseInt(br.readLine());

        for(int i = 0; i < cycle; i++) {
            String[] cmd = br.readLine().split("");
            boolean right = false, check = false; // default 앞
            ArrayDeque<String> deque = initList(Integer.parseInt(br.readLine()), br.readLine());

            for(int index = 0; index < cmd.length; index++) {
                // R 이 제시된 경우 반전을 줘야하는데, 전반적으로 뒤집으면 시간초과나기 때문에
                // 뒤집은 것처럼 보이는 효과가 필요함
                if(cmd[index].equals("R")) {
                    right = !right;
                }
                // D 가 제시된 경우 하나씩 빼줘야함 단, 덱을 쓰는 만큼 앞과 뒤의 판단이 중요!
                else if(!right) { // right = false 앞
                    if(deque.peekFirst()==null) { // null 은 덱이 비었을 때
                        System.out.println("error");
                        check = true; // check 는 [ ] 빈 배열값이 포문 종료 이후 돌면 안되기 때문에 선언
                        break;
                    }
                    deque.pollFirst();
                }
                else { // right = true 뒤
                    if(deque.peekLast()==null) {
                        System.out.println("error");
                        check = true;
                        break;
                    }
                    deque.pollLast();
                }
            }
            if(check) { // 덱에서 널이 나온 경우는 패스
                continue;
            }
            // 덱안에 값을 통해 [1,2,3,4] 와 같이 만들기 위해서 StringBuilder 선언
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            if(!deque.isEmpty()) {
                if(!right) {
                    sb.append(deque.pollFirst());
                    while (!deque.isEmpty()) {
                        sb.append(",").append(deque.pollFirst());
                    }
                }
                else {
                    sb.append(deque.pollLast());
                    while (!deque.isEmpty()) {
                        sb.append(",").append(deque.pollLast());
                    }
                }
            }
            sb.append("]");
            System.out.println(sb);
        }
    }

    // 덱 초기화 -> [ ] 안의 값을 입력받음
    public static ArrayDeque<String> initList(int len, String input) {
        ArrayDeque<String> deque = new ArrayDeque<>();
        String[] value = input.substring(1, input.length()-1).split(",");
        for(int i = 0; i < len; i++) {
            deque.add(value[i]);
        }
        return deque;
    }
}
