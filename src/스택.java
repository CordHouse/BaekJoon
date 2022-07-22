import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class 스택 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int buffer = Integer.parseInt(br.readLine());
        ArrayList<Integer> stack = new ArrayList<>();
        for(int i = 0; i < buffer; i++){
            String in = br.readLine();
            if(in.contains("push")){
                int number = Integer.parseInt(in.substring(in.indexOf(" ")+1));
                stack.add(number);
            }
            else if(in.contains("pop")){
                if(stack.size() == 0)
                    System.out.println("-1");
                else {
                    System.out.println(stack.remove(stack.size()-1));
                }
            }
            else if(in.contains("size")){
                System.out.println(stack.size());
            }
            else if(in.contains("empty")){
                if(stack.size() == 0)
                    System.out.println(1);
                else
                    System.out.println(0);
            }
            else if(in.contains("top")){
                if(stack.size() == 0)
                    System.out.println(-1);
                else
                    System.out.println(stack.get(stack.size()-1));
            }
        }
    }
}
