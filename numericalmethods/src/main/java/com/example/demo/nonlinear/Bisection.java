package com.example.demo.nonlinear;

public class Bisection {
    double start;
    double stop;
    String Function;
    double e;
    int precision;

    public Bisection(double start, double stop, String function, double e, int precision) {
        this.start = start;
        this.stop = stop;
        Function = function;
        this.e = e;
        this.precision = precision;
    }

   public double Solve(){
        Evaluate evaluate = new Evaluate(Function, start, precision);
        double xr, xu, xl;
        if(evaluate.eval() > 0){
            xu = start;
            xl = stop;
        }
        else{
            xl = start;
            xu = stop;
        }
        xr = (xl + xu) / 2;
        double oldxr = start;
        while (Math.abs((xr - oldxr) / xr) > e) {
            evaluate = new Evaluate(Function, xr, precision);
            if (evaluate.eval() > 0)
                xu = xr;
            else
                xl = xr;
            oldxr = xr;
            xr = (xl + xu) / 2;
        }
        return new Precision(precision, xr).Value();
    }
}
