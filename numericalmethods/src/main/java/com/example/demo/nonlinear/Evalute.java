package com.example.demo.nonlinear;


//import require classes and packages
import org.json.simple.JSONObject;

import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;

public class Evalute {

    public static void main(String[] args) {

        NewtonRephson NW=new NewtonRephson();
        Evaluate E= new Evaluate("sin(x)",-20,5);
        System.out.println(E.eval());
        for (double i=-20;i<20; i=i+.01)
        {
            i=new Precision(5,i).Value();
            System.out.println(i);

         System.out.println(new Evaluate("sin(x)",i,5).eval());
        }
//
       /* NW.setFunction("x^3-.165x^2+3.993*10^-4");
        NW.setX(0.05);
        NW.setprec(5);
        NW.solve(10,.001);
        System.out.println(NW.root);*/
        for (int i=0;i<NW.result.size();i++) {

            System.out.println(i+">"+NW.result.get(i)[0][0]+" , "+NW.result.get(i)[0][1]+" , "+NW.result.get(i)[0][2]);

        }

    }

}
