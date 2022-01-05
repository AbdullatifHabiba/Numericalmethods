package com.example.demo.nonlinear;

public class Precision {
    int p;
    double x;

    public Precision(int p, double x) {
        this.p = p;
        this.x = x;
    }

    public double Value(){
        if (x < 0) {
            x *= -1;
            return -1 * Value();
        }
        if (x > -1 * Math.pow(10, -1 * p) && x < Math.pow(10, -1 * p))
            return 0;
        if (x >= Math.pow(10, p)){
            int h = 1;
            while (x >= Math.pow(10, p) * Math.pow(10, h))
                h += 1;
            return ((int)(x / Math.pow(10, h) + .5) * Math.pow(10, h));
        }
        else{
            int h = 1;
            while (x * Math.pow(10, h) < Math.pow(10, p))
                h += 1;
            return ((int)(x * Math.pow(10, h - 1) + .5) / Math.pow(10, h - 1));
        }
    }
}
