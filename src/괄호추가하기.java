import java.util.Arrays;
import java.util.Scanner;

public class 괄호추가하기 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int loop = sc.nextInt(); // 반복 횟수

        char[] a = new char[loop];
        int[] value = new int[loop];
        String buffer = sc.next();
        for(int i = 0; i<loop; i++) {
            a[i] = buffer.charAt(i);
            value[i] = a[i] - '0'; // char 형 0을 빼주어야 숫자 값으로 반환됨
        }
        System.out.println(Arrays.toString(value));
        for(int i = 1; i<loop; i+=2){
            if(a[i] == '*'){
                value[i+1] = value[i-1] * value[i+1];
            }
            else if(a[i] == '+'){
                value[i+1] = value[i-1] + value[i+1];
            }
            else if(a[i] == '-'){
                value[i+1] = value[i-1] - value[i+1];
            }
        }
        System.out.println(value[value.length-1]);
    }
}
