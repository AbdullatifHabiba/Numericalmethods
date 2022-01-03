package com.example.demo.nonlinear;


public class Evalute {

    public static void main(String[] args) {

        NewtonRephson NW=new NewtonRephson();
         Evaluate E=new Evaluate("x^3-0.165*x^2+3.993*10^-4",.05);
        System.out.println(E.eval());

//e
//-x
//- x
        NW.setFunction("x^10-1");
        NW.setX(0.5);
        NW.solve(100,.00001);
        System.out.println(NW.root);
        for (int i=0;i<NW.result.size();i++) {

            System.out.println(i+">"+NW.result.get(i)[0][0]+" , "+NW.result.get(i)[0][1]+" , "+NW.result.get(i)[0][2]);

        }

    }

}
