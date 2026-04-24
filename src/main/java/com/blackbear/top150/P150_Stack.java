package com.blackbear.top150;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class P150_Stack {

    public static void main(String[] args) {
        String[] inputs = new String[]{"10","6","9","3","+","-11","*","/","*","17","+","5","+"};
        P150_Stack stack = new P150_Stack();
        System.out.println(stack.evalRPN(inputs));
    }

    public int evalRPN(String[] tokens) {

        if (tokens.length == 1) {
            return Integer.parseInt(tokens[0]);
        }

        Set<String> operators = new HashSet<>();
        operators.add("+");
        operators.add("-");
        operators.add("*");
        operators.add("/");

        Stack<String> stores = new Stack<>();
        stores.push(tokens[0]);
        stores.push(tokens[1]);

        for (int index = 2; index < tokens.length; index++) {
            String token = tokens[index];
            if (operators.contains(token)) {
                Integer opRight = Integer.parseInt(stores.pop());
                Integer opLeft = Integer.parseInt(stores.pop());
                Integer result = null;
                switch (token) {
                    case "+":
                        result = opLeft + opRight;
                        break;
                    case "-":
                        result = opLeft - opRight;
                        break;
                    case "*":
                        result = opLeft * opRight;
                        break;
                    case "/":
                        result = opLeft / opRight;
                        break;
                }
                stores.push(String.valueOf(result));
            } else {
                stores.push(token);
            }
        }

        return Integer.parseInt(stores.pop());

    }

}
