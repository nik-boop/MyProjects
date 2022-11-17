import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.util.*;


public class Task1 {

    public static boolean isBalanced = true;
    static boolean areBracketsBalanced(String expr) {
        Deque<Character> stack = new ArrayDeque<Character>();
        HashMap<Character, Character> brackets = new HashMap<>(
                Map.of(')','(', ']', '[', '}', '{')
        );

        for (int i = 0; i < expr.length(); i++) {
            char x = expr.charAt(i);
            if (brackets.containsValue(x)) {
                stack.push(x);
                continue;
            }

            char check;
            if (brackets.containsKey(x)){
                check = stack.pop();
                if (brackets.get(x) != check){
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        System.out.print("Введите математическое выражение: ");
        Scanner in = new Scanner(System.in);
        String expr = in.nextLine();
        System.out.println("Результат ввода: " + expr);
        if (areBracketsBalanced(expr)) {
            System.out.println("Balanced well done!");
            Expression expression = new ExpressionBuilder(expr).build();
            double result = expression.evaluate();
            System.out.println(result);

        } else {
            System.out.println("Not Balanced!");
        }
    }
}
