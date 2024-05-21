package deque;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class 덱_10866 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int idx = Integer.parseInt(br.readLine());

        Deque<String> deque = new ArrayDeque<>();

        while(idx --> 0) {
            String cmd = br.readLine();
            String[] cmdSplit = cmd.split(" ");
            if(cmd.contains("push_front")) {
                deque.addLast(cmdSplit[1]);
            }
            else if(cmd.contains("push_back")) {
                deque.addFirst(cmdSplit[1]);
            }
            else if(cmd.contains("pop_front")) {
                if(deque.isEmpty()) {
                    System.out.println(-1);
                } else {
                    System.out.println(deque.removeLast());
                }
            }
            else if(cmd.contains("pop_back")) {
                if(deque.isEmpty()) {
                    System.out.println(-1);
                } else {
                    System.out.println(deque.removeFirst());
                }
            }
            else if(cmd.contains("size")) {
                System.out.println(deque.size());
            }
            else if(cmd.contains("empty")) {
                if(deque.isEmpty()) {
                    System.out.println(1);
                } else {
                    System.out.println(0);
                }
            }
            else if(cmd.contains("front")) {
                if(deque.isEmpty()) {
                    System.out.println(-1);
                } else {
                    System.out.println(deque.peekLast());
                }
            }
            else if(cmd.contains("back")) {
                if(deque.isEmpty()) {
                    System.out.println(-1);
                } else {
                    System.out.println(deque.peekFirst());
                }
            }
        }
    }
}
