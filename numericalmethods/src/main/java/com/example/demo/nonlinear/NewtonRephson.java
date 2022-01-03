package com.example.demo.nonlinear;

import java.awt.*;
import java.util.ArrayList;

public class NewtonRephson {
    double X;
    String function;
    double root;
    public NewtonRephson(){

    }

    public double getroot() {
        return root;
    }


    public void setX(double x) {
        X = x;
    }



    public void setFunction(String function) {
        this.function = function;
    }
    ArrayList<double[][]> result=new ArrayList<>();
    void solve(int iteration,double Es ){

        point p=new point();
       Getdrivitieves G= new Getdrivitieves();
       double Ea=Es;
        for (int i=0;(i<iteration);i++){
            double[][] o=new double[1][3];

            double xtemp=X;
            double dr=G.derive(function,X);
           double y=new Evaluate(function,X).eval();
            o[0][0]=xtemp;
            o[0][1]=y;
            o[0][2]=dr;
            X= X-y/dr;
           // System.out.println(X);

            result.add(o);
            Ea=(X-xtemp)/X;
           if(Ea<Es)break;


        }
        root=X;

    }



}
