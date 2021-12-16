package com.example.demo;

public class Stop {
    int iterativenum;
    double relativeerror;

    public Stop() {
        this.relativeerror = 0;
        this.iterativenum = 0;

    }

    public double getRelativeerror() {
        return relativeerror;
    }

    public void setRelativeerror(double relativeerror) {
        this.relativeerror = relativeerror;
    }

    public int getIterativenum() {
        return iterativenum;
    }

    public void setIterativenum(int iterativenum) {
        this.iterativenum = iterativenum;
    }
}
