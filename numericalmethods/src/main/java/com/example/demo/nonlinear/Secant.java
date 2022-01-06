package com.example.demo.nonlinear;

public class Secant {
    double first_assumption ,second_assumption ,result = 0 , least_error ;
    int precision,iteration;
    String function ;

    public Secant(String expression, double first, double second,double e, int prec,int iter) {
        function = expression;
        first_assumption = first;
        second_assumption = second;
        least_error = e;
        precision = prec;
        iteration=iter;
    }
    public double solve(){
        double error = 100;
        // while loop until the error get very small
        for (int i=0;error >= least_error&&i<iteration;i++){
            // compute f(Xi-1)
            Evaluate eval_1 = new Evaluate(function,first_assumption,precision);
            double f_1 =eval_1.eval();
            // compute f(Xi)
            Evaluate eval_2 = new Evaluate(function,second_assumption,precision);
            double f_2 =eval_2.eval();
            // Xi+1 = f(Xi)*Xi-1-Xi)/f(Xi-1)-f(Xi)
            result= new Precision(precision, second_assumption-((f_2* (first_assumption-second_assumption))/(f_1-f_2))).Value();
            // Error  = (Xi+1)-Xi/(Xi+1)
            error=Math.abs(((result-second_assumption)/result)*100);
            // changing the values with the new ones
            first_assumption=second_assumption;
            second_assumption=result;
        }
        // return the result
       return result;
    }
    }

