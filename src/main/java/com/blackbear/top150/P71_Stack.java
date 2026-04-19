package com.blackbear.top150;

import java.util.Stack;

public class P71_Stack {

    private final static Character SLASH = '/';
    private final static Character DOT = '.';

    public static void main(String[] args) {
        String path = "/home/../usr//bin/./script";
        P71_Stack stack = new P71_Stack();
        System.out.printf(stack.simplifyPath(path));
    }

    public String simplifyPath2(String path) {

        String[] components = path.split("/");
        Stack<String> stack = new Stack<>();

        for (String component : components) {
            if (component.equals(".") || component.isEmpty()) {
                continue;
            } else if (component.equals("..")) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else {
                stack.push(component);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (String component : stack) {
            sb.append("/");
            sb.append(component);
        }

        if (stack.isEmpty()) {
            sb.append("/");
        }

        return sb.toString();

    }


    public String simplifyPath(String path) {

        Stack<Character> stack = new Stack<>();

        if (path.isEmpty() || !SLASH.equals(path.charAt(0))) {
            throw new IllegalArgumentException();
        }

        stack.push(SLASH);

        for (int i = 1; i < path.length(); ) {
            char ch = path.charAt(i);
            //1. case1 not /, not .
            if (!SLASH.equals(ch) && !DOT.equals(ch)) {
                stack.push(ch);
                i++;
                continue;
            }

            //2. case1 /
            if (SLASH.equals(ch)) {
                //mute multi slash
                Character last = stack.peek();
                if (last != SLASH) {
                    stack.push(ch);
                }

                i++;
                continue;
            }

            //2. case3 .
            Character last = stack.peek();
            if (!SLASH.equals(last)) {
                stack.push(ch);
                i++;
                continue;
            } else {
                if (i + 1 >= path.length()) {
                    i++;
                    continue;
                } else {
                    Character next = path.charAt(i + 1);
                    if (SLASH.equals(next)) {
                        //ignore this one, keep going
                        i++;
                        continue;
                    } else if (DOT.equals(next)) {
                        if (i + 2 >= path.length()) {
                            //前面出栈.出到上上个slash为止
                            stack.pop();//除掉第一个/
                            if (stack.isEmpty()) {
                                stack.push(SLASH);
                            } else {
                                Character tempLast = stack.peek();
                                while (!SLASH.equals(tempLast)) {
                                    stack.pop();
                                    tempLast = stack.peek();
                                }
                            }
                            i++;
                            continue;
                        }

                        Character nextNext = path.charAt(i + 2);
                        if (DOT.equals(nextNext)) {
                            stack.push(ch);
                            stack.push(next);
                            stack.push(nextNext);
                            i = i + 3;
                            //找到下一个不是点的
                            for (; i < path.length(); i++) {
                                if (DOT.equals(path.charAt(i))) {
                                    stack.push(path.charAt(i));
                                } else {
                                    break;
                                }
                            }
                        } else if (SLASH.equals(nextNext)) {
                            //前面出栈.出到上上个slash为止
                            stack.pop();//除掉第一个/

                            if (stack.isEmpty()) {
                                stack.push(SLASH);
                            } else {
                                Character tempLast = stack.peek();
                                while (!SLASH.equals(tempLast)) {
                                    stack.pop();
                                    tempLast = stack.peek();
                                }
                            }
                            i++;
                        } else {
                            stack.push(ch);
                            stack.push(next);
                            stack.push(nextNext);
                            i = i + 3;
                        }
                    } else {
                        stack.push(ch);
                        i++;
                        continue;
                    }
                }
            }
        }

        if (stack.size() > 1) {
            Character last = stack.peek();
            if (SLASH.equals(last)) {
                stack.pop();
            }
        }

        StringBuilder sb = new StringBuilder();
        for (Character character : stack) {
            sb.append(character);
        }
        return sb.toString();
    }
}
