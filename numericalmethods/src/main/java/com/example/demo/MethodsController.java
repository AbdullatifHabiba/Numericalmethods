package com.example.demo;
import com.example.demo.nonlinear.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import  org.json.simple.parser.*;
import org.springframework.web.bind.annotation.*;

import java.awt.*;


@RestController
@CrossOrigin(origins="http://localhost:4200/")
@RequestMapping("/method")

public class MethodsController {
    static int p = 2;
    Factory F = new Factory();
    GetMatrices GetMatrices ;

    @GetMapping("/GaussElimination")
    public double[] JaussElimation(@RequestParam String Matrix, @RequestParam double Precision)  throws Exception{
        p =( int)(Precision);


        gaussElimination ga = new gaussElimination();
        GetMatrices GetMatrices = new GetMatrices(Matrix);
        return ga.JaussElimination(GetMatrices.setmatrix(),GetMatrices.B());

    }
    @GetMapping("/GaussJordan")
    public double[]  GaussJordan(@RequestParam String Matrix, @RequestParam double Precision) throws Exception {
        p =( int)(Precision);
        GaussJordanSolution gj = new GaussJordanSolution();
        GetMatrices GetMatrices = new GetMatrices(Matrix);
        return gj.GaussJordan(GetMatrices.setmatrix(),GetMatrices.B());
    }
    @GetMapping("/LUDecomposition")
    public double[] LUDecomposition(@RequestParam String Matrix, @RequestParam String type, @RequestParam double Precision){
        p =( int)(Precision);
        GetMatrices GetMatrices = new GetMatrices(Matrix);
        LUdecomposition LU = new LUdecomposition();
        if(type.equalsIgnoreCase("doolittle"))
            return LU.SolveDoolittle(GetMatrices.setmatrix(),GetMatrices.B());
          else  return LU.SolveCrout(GetMatrices.setmatrix(),GetMatrices.B());

    }
    @GetMapping("/Jacobi")
    public double[] Jacobi(@RequestParam String Matrix,@RequestParam double[] Intial,@RequestParam double numbOfIter,@RequestParam double relativeErr, @RequestParam double Precision){
        p =( int)(Precision);
        GetMatrices GetMatrices =new GetMatrices(Matrix);
        Stop s=new Stop();
        s.setIterativenum((int)numbOfIter);
        s.setRelativeerror(relativeErr);
        jacobisolver SOLVE=new jacobisolver(GetMatrices.setmatrix(),Intial,GetMatrices.B(),s);
        return SOLVE.solve();
    }

    @GetMapping("/Seidel")
    public double[] Seidal(@RequestParam String Matrix,@RequestParam double[] Intial,@RequestParam double numbOfIter,@RequestParam double relativeErr, @RequestParam double Precision){
        p =( int)(Precision);
        GetMatrices GetMatrices =new GetMatrices(Matrix);
        Stop s=new Stop();
        s.setIterativenum((int)numbOfIter);
        s.setRelativeerror(relativeErr);
        Seidelsolver SOLVE = new Seidelsolver(GetMatrices.setmatrix(),Intial,GetMatrices.B() ,s);
        return SOLVE.solve();
    }
     @GetMapping("/fixed")
    public double fixed(@RequestParam String equation,@RequestParam double initial,@RequestParam int iterations,@RequestParam double eps,@RequestParam int precision) throws Exception {
        FixedPoint fx=new FixedPoint();

        //System.out.print(fx.FixedPointMethod(equation,initial,eps,iterations,precision));
        return  fx.FixedPointMethod(equation,initial,eps,iterations,precision);
    }
    @GetMapping("/newton")
    public JSONArray newton(@RequestParam String equation,@RequestParam double initial,@RequestParam int iterations,@RequestParam double eps,@RequestParam int precision ){
       System.out.println(equation);
       equation=equation.replace('p','+');
        NewtonRephson nw=new NewtonRephson();
        nw.setX(initial);
        nw.setFunction(equation);
        nw.setprec(precision);
        JSONArray JS=new JSONArray();
        JSONObject j=new JSONObject();
        double n=nw.solve((int)iterations,eps);
       // System.out.println(n);
        //put root
        j.put("rootx",n);
        j.put("rooty",new Evaluate(equation,n,precision).eval());

        JS.add(j);
        for (double i=-20;i<20; i=i+.1)
        {
            try {

                JSONObject jo=new JSONObject();
                i=new Precision(5,i).Value();
                //put x
                jo.put("x",i);
                //put main function
                jo.put("y",new Evaluate(equation,i,precision).eval());
                //put derivitive function
                jo.put("yd",new Getdrivitieves().derive(equation,i,precision));

                JS.add(jo);
            }catch (Exception e){System.out.println("ERROR");}

        }
        System.out.println(JS);


        return JS ;
    }


   

}
