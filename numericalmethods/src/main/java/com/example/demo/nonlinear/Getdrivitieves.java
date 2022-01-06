package com.example.demo.nonlinear;



import java.util.ArrayList;

public class Getdrivitieves {

    String Function;
    String drevitive;


    private static final double DX = 0.001;

    public double derive( String f,double x,int p) {
        x=new Precision(p,x).Value();

        Evaluate E1=new Evaluate(f,new Precision(p,x+DX).Value(),p);

        Evaluate E2=new Evaluate(f,new Precision(p,x).Value(),p);
        double dr= ((new Precision(p,E1.eval()).Value()-new Precision(p,E2.eval()).Value())/DX);


        return dr;
    }










}
