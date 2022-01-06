package com.example.demo.nonlinear;


//import require classes and packages
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;

public class Evalute {

    public static void main(String[] args) {

        NewtonRephson NW=new NewtonRephson();
       // Evaluate E= new Evaluate("sin(x)",-20,5);
        //System.out.println(E.eval());
        NW.setFunction("e^x");
        NW.setX(1);
        NW.setprec(5);
        NW.solve(10,.001);
        JSONArray JS=new JSONArray();
        JSONObject j=new JSONObject();
         j.put("root",NW.root);
            JS.add(j);
       for (double i=-20;i<20; i=i+.1)
        {
            try {


            JSONObject jo=new JSONObject();
            i=new Precision(5,i).Value();
            jo.put("x",i);
            jo.put("y",new Evaluate("e^x",i,5).eval());
            jo.put("yd",new Getdrivitieves().derive("e^x",i,5));

            JS.add(jo);
            }catch (Exception e){System.out.println("ERROR");}

        }

        System.out.println(JS);
//
       /* NW.setFunction("x^3-.165x^2+3.993*10^-4");
        NW.setX(0.05);
        NW.setprec(5);
        NW.solve(10,.001);
        System.out.println(NW.root);*/
        /*for (int i=0;i<NW.result.size();i++) {

            System.out.println(i+">"+NW.result.get(i)[0][0]+" , "+NW.result.get(i)[0][1]+" , "+NW.result.get(i)[0][2]);

        }*/

    }

}
