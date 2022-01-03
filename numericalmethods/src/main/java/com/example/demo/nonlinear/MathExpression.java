package com.example.demo.nonlinear;

import java.util.ArrayList;
import java.util.List;

public class MathExpression {
    String Expression;

    public MathExpression(String expression) {
        Expression = expression;
    }

    boolean IsOperator(int i){
        if (Expression.charAt(i) == '+'){
            return true;
        }
        else if (Expression.charAt(i) == '*') {
            return true;
        }
        else if (Expression.charAt(i) == '/') {
            return true;
        }
        else if (Expression.charAt(i) == '^') {
            return true;
        }
        else if (Expression.charAt(i) == 's') {
            return true;
        }
        else if (Expression.charAt(i) == 'c') {
            return true;
        }
        else if (Expression.charAt(i) == '-') {
            if (i == 0)
                return false;
            else if (IsOperator(i - 1))
                return false;
            else
                return true;
        }
        else
            return false;
    }

    double Calculate() {
        List<Double> numbers = new ArrayList<>();
        List<Character> operations = new ArrayList<>();
        int x = 0;
        String a;
        for (int i = 0; i < Expression.length(); i++) {
            if (IsOperator(i)) {
                operations.add(Expression.charAt(i));
                if (x != i) {
                    a = Expression.substring(x, i);
                    numbers.add(Double.parseDouble(a));
                }
                x = i + 1;
                if (i == 0)
                    x = 1;
            }
        }
        if (x < Expression.length()) {
            a = Expression.substring(x, Expression.length());
            numbers.add(Double.parseDouble(a));
        }
        for (int i = 0; i < operations.size(); i++) {
            if (operations.get(i) == 's') {
                double num = numbers.get(i);
                num = Math.sin(num);
                numbers.set(i, num);
                operations.remove(i);
                continue;
            }
            if (operations.get(i) == 'c') {
                double num = numbers.get(i);
                num = Math.cos(num);
                numbers.set(i, num);
                operations.remove(i);
                continue;
            }
        }
        for (int i = 0; i < operations.size(); i++) {
            if (operations.get(i) == '^') {
                double num = numbers.get(i);
                num = Math.pow(num, numbers.get(i + 1));
                numbers.set(i, num);
                operations.remove(i);
                numbers.remove(i + 1);
                i -= 1;
                continue;
            }
        }
        for (int i = 0; i < operations.size(); i++) {
            if (operations.get(i) == '*') {
                double num = numbers.get(i);
                num = num * numbers.get(i + 1);
                numbers.set(i, num);
                operations.remove(i);
                numbers.remove(i + 1);
                i -= 1;
                continue;
            }
            if (operations.get(i) == '/') {
                double num = numbers.get(i);
                num = num / numbers.get(i + 1);
                numbers.set(i, num);
                operations.remove(i);
                numbers.remove(i + 1);
                i -= 1;
                continue;
            }
        }
        for (int i = 0; i < operations.size(); i++) {
            if (operations.get(i) == '+') {
                double num = numbers.get(i);
                num = num + numbers.get(i + 1);
                numbers.set(i, num);
                operations.remove(i);
                numbers.remove(i + 1);
                i -= 1;
                continue;
            }
            if (operations.get(i) == '-') {
                double num = numbers.get(i);
                num = num - numbers.get(i + 1);
                numbers.set(i, num);
                operations.remove(i);
                numbers.remove(i + 1);
                i -= 1;
                continue;
            }
        }
        return numbers.get(0);
    }
}
