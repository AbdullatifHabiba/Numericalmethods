package com.example.demo.nonlinear;

import java.awt.*;
import java.util.ArrayList;

public class NewtonRephson {
    double X;
    String function;
    double root;
    int perc;
    public NewtonRephson(){

    }

    public double getroot() {
        return root;
    }

    public void setprec(int x) {
        perc = x;
    }

    public void setX(double x) {
        X = x;
    }



    public void setFunction(String function) {
        this.function = function;
    }
   public ArrayList<double[][]> result=new ArrayList<>();
   public double solve(int iteration,double Es ){

       Getdrivitieves G= new Getdrivitieves();
       double Ea=Es;
        for (int i=0;(i<iteration);i++){
            double[][] o=new double[1][3];

            double xtemp=X;
            double dr=G.derive(function,X,perc);
           double y=new Evaluate(function,X,perc).eval();
            o[0][0]=xtemp;
            o[0][1]=new Precision(perc,y).Value();
            o[0][2]=dr;
            X=new Precision(perc, X-y/dr).Value();
            //System.out.println(X);

            result.add(o);
            Ea=(X-xtemp)/X;
            if(Ea<Es)break;


        }
        root=X;
   return root;
    }



}
