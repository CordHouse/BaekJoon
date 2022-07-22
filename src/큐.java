import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class 큐 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int count = Integer.parseInt(br.readLine());
        ArrayList<Integer> queue = new ArrayList<>();

        for(int i = 0; i<count; i++){
            String in = br.readLine();
            if(in.contains("push")){
                queue.add(Integer.parseInt(in.substring(in.indexOf(" ")+1)));
            }
            else if(in.contains("pop")){
                if(queue.size() == 0)
                    System.out.println(-1);
                else
                    System.out.println(queue.remove(0));
            }
            else if(in.contains("size")){
                System.out.println(queue.size());
            }
            else if(in.contains("empty")){
                if(queue.size() == 0)
                    System.out.println(1);
                else
                    System.out.println(0);
            }
            else if(in.contains("front")){
                if(queue.size() == 0)
                    System.out.println(-1);
                else
                    System.out.println(queue.get(0));
            }
            else if(in.contains("back")){
                if(queue.size() == 0)
                    System.out.println(-1);
                else
                    System.out.println(queue.get(queue.size()-1));
            }
        }
    }
}
