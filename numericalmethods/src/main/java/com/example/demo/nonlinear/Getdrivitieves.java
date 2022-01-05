package com.example.demo.nonlinear;



import java.util.ArrayList;

public class Getdrivitieves {

    String Function;
    String drevitive;


    private static final double DX = 0.0001;

    public double derive( String f,double x,int p) {
        Evaluate E1=new Evaluate(f,x+DX,p);
        Evaluate E2=new Evaluate(f,x,p);
        double dr= ((E1.eval()- E2.eval())/DX);

        return dr;
    }










}
