package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Contact {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 정규표현식
        // + 최소 1개 이상 발생
        // | or (또는)
        // () 한 문자로 취급
        // 0이 한번이상 나오고 그뒤로 1이 한번이상 나오거나 01이 나오는 두 조합이 한 번 이상 나오는 경우
        String regex = "(100+1+|01)+";
        int cycle = Integer.parseInt(br.readLine());
        for(int i = 0; i < cycle; i++) {
            String input = br.readLine();
            if(input.matches(regex)) {
                System.out.println("YES");
                continue;
            }
            System.out.println("NO");
        }
    }
}
