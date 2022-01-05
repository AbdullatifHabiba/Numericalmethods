package com.example.demo.nonlinear;

public class FalsePosition {
    double start;
    double stop;
    String Function;
    double e;
    int precision;

    public FalsePosition(double start, double stop, String function, double e, int precision) {
        this.start = start;
        this.stop = stop;
        Function = function;
        this.e = e;
        this.precision = precision;
    }

    //Precision P = new Precision(precision);

    double Solve(){
        double xl,xu,xr;
        Evaluate evaluate = new Evaluate(Function, start, precision);
        if(evaluate.eval() > 0){
            xu = start;
            xl = stop;
        }
        else{
            xl = start;
            xu = stop;
        }
        Evaluate evaluatexu = new Evaluate(Function, xu, precision);
        Evaluate evaluatexl = new Evaluate(Function, xl, precision);
        xr = (xl * evaluatexu.eval() - xu * evaluatexl.eval()) / (evaluatexu.eval() - evaluatexl.eval());
        double oldxr = start;
        while (Math.abs((xr - oldxr) / xr) > e){
            evaluate = new Evaluate(Function, xr, precision);
            if (evaluate.eval() > 0)
                xu = xr;
            else
                xl = xr;
            oldxr = xr;
            evaluatexu = new Evaluate(Function, xu, precision);
            evaluatexl = new Evaluate(Function, xl, precision);
            xr = (xl * evaluatexu.eval() - xu * evaluatexl.eval()) / (evaluatexu.eval() - evaluatexl.eval());
        }
        return new Precision(precision, xr).Value();
    }
}
