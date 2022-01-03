package com.example.demo.nonlinear;

import static java.lang.Character.isDigit;

public class Evaluate {
    String Expression;
    double X;

    public Evaluate(String expression, double x) {
        Expression = expression;
        X = x;
    }

    public void PromoteExpression(){
        Expression = Expression.replaceAll("sin", "s");
        Expression = Expression.replaceAll("cos", "c");
        for (int i = 0;i < Expression.length();i++) {
            if (Expression.charAt(i) == '(') {
                if (i != 0 && (Expression.charAt(i - 1) == 'x' || isDigit(Expression.charAt(i - 1)) || Expression.charAt(i - 1) == ')')) {
                    Expression = Expression.substring(0, i) + '*' + Expression.substring(i, Expression.length());
                    i += 1;
                }
            }
            if (i != Expression.length() - 1 && Expression.charAt(i) == ')') {
                if (Expression.charAt(i + 1) == 'x' || isDigit(Expression.charAt(i + 1)))
                    Expression = Expression.substring(0, i + 1) + '*' + Expression.substring(i + 1, Expression.length());
            }
            if (i != 0 && Expression.charAt(i) == 'x') {
                if (isDigit(Expression.charAt(i - 1)))
                    Expression = Expression.substring(0, i) + '*' + Expression.substring(i , Expression.length());
            }
            if (i != 0 && Expression.charAt(i) == 'e') {
                if (isDigit(Expression.charAt(i - 1)) || Expression.charAt(i - 1) == 'x')
                    Expression = Expression.substring(0, i) + '*' + Expression.substring(i , Expression.length());
            }
            if (i != 0 && (Expression.charAt(i) == 's' || Expression.charAt(i) == 'c')) {
                if (isDigit(Expression.charAt(i - 1)) || Expression.charAt(i - 1) == 'x' || Expression.charAt(i - 1) == ')')
                    Expression = Expression.substring(0, i) + '*' + Expression.substring(i , Expression.length());
            }
        }
        Expression = Expression.replaceAll("e", "2.718281828459045");
        Expression = Expression.replaceAll("x", X + "");
    }

    public double eval(){
        PromoteExpression();
        double result;
        for (int i = 0;i < Expression.length();i++){
            if (Expression.charAt(i) == '('){
                int LeftBracket = i;
                for (int j  = i + 1;j < Expression.length();j++){
                    if (Expression.charAt(j) == '(')
                        LeftBracket = j;
                    if (Expression.charAt(j) == ')'){
                        if (j < Expression.length() - 1) {
                            Expression = Expression.substring(0, LeftBracket) + new MathExpression(Expression.substring(LeftBracket + 1, j)).Calculate() + Expression.substring(j + 1, Expression.length());
                            eval();
                        }
                        else {
                            Expression = Expression.substring(0, LeftBracket) + new MathExpression(Expression.substring(LeftBracket + 1, Expression.length() - 1)).Calculate();
                            eval();
                        }
                    }
                }
            }
        }
        return new MathExpression(Expression).Calculate();
    }
}
