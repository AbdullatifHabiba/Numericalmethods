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
    public JSONArray fixed(@RequestParam String equation,@RequestParam double initial,@RequestParam int iterations,@RequestParam double eps,@RequestParam int precision) throws Exception {
         equation=equation.replace('p','+');
         JSONArray JS=new JSONArray();
         JSONObject j=new JSONObject();
         FixedPoint fx=new FixedPoint();
         double n=fx.FixedPointMethod(equation,initial,eps,iterations,precision);
         j.put("rootx",n);
         j.put("rooty",new Evaluate(equation,n,precision).eval());

         JS.add(j);
         System.out.println(JS);
         String gx=fx.calculategx(equation);
         System.out.println(gx);

         for (double i=-20;i<20; i=i+.1)
         {
             try {

                 JSONObject jo=new JSONObject();
                 i=new Precision(5,i).Value();
                 jo.put("x",i);
                 //put main function
                 jo.put("y",new Evaluate(gx,i,precision).eval());
                 //put derivitive function
                 jo.put("yd",new Evaluate("x",i,precision).eval());

                 JS.add(jo);
             }catch (Exception e){System.out.println("ERROR");}

         }
         System.out.println(JS);        return JS ;
    }
    @GetMapping("/newton")
    public JSONArray newton(@RequestParam String equation,@RequestParam double initial,@RequestParam int iterations,@RequestParam double eps,@RequestParam int precision ){
       //System.out.println(equation);
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
    @GetMapping("/secant")
    public JSONArray secant(@RequestParam String equation,@RequestParam double first,@RequestParam double second,@RequestParam int iterations,@RequestParam double eps,@RequestParam int precision ){
        //System.out.println(equation);
        equation=equation.replace('p','+');
        Secant sc=new Secant(equation,first,second,eps,precision,iterations);

        JSONArray JS=new JSONArray();
        JSONObject j=new JSONObject();
        double n=sc.solve();
        System.out.println(n);
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

    @GetMapping("/bisection")
    public JSONArray bisection(@RequestParam String equation,@RequestParam double first,@RequestParam double second,@RequestParam int iterations,@RequestParam double eps,@RequestParam int precision ){
        //System.out.println(equation);
        equation=equation.replace('p','+');
        Bisection bs=new Bisection(first,second,equation,eps,precision);

        JSONArray JS=new JSONArray();
        JSONObject j=new JSONObject();
        double n=bs.Solve();
        System.out.println(n);
        //put root
        j.put("rootx",n);
        j.put("rooty",new Evaluate(equation,n,precision).eval());


        j.put("xl",first);
        j.put("yl",new Evaluate(equation,first,precision).eval());

        j.put("xr",second);
        j.put("yr",new Evaluate(equation,second,precision).eval());
        JS.add(j);
        System.out.println(JS);


        for (double i=-20;i<20; i=i+.1)
        {
            try {

                JSONObject jo=new JSONObject();
                i=new Precision(5,i).Value();
                //put x
                jo.put("x",i);
                //put main function
                jo.put("y",new Evaluate(equation,i,precision).eval());

                JS.add(jo);
            }catch (Exception e){System.out.println("ERROR");}

        }
        System.out.println(JS);


        return JS ;
    }

    @GetMapping("/falsep")
    public JSONArray falsePosition(@RequestParam String equation,@RequestParam double first,@RequestParam double second,@RequestParam int iterations,@RequestParam double eps,@RequestParam int precision ){
        //System.out.println(equation);
        equation=equation.replace('p','+');
        FalsePosition sc=new FalsePosition(first,second,equation,eps,precision);

        JSONArray JS=new JSONArray();
        JSONObject j=new JSONObject();
        double n=sc.Solve();
        System.out.println(n);
        //put root
        j.put("rootx",n);
        j.put("rooty",new Evaluate(equation,n,precision).eval());


        j.put("xl",first);
        j.put("yl",new Evaluate(equation,first,precision).eval());

        j.put("xr",second);
        j.put("yr",new Evaluate(equation,second,precision).eval());
        JS.add(j);
        System.out.println(JS);

        for (double i=-20;i<20; i=i+.1)
        {
            try {

                JSONObject jo=new JSONObject();
                i=new Precision(5,i).Value();
                //put x
                jo.put("x",i);
                //put main function
                jo.put("y",new Evaluate(equation,i,precision).eval());

                JS.add(jo);
            }catch (Exception e){System.out.println("ERROR");}

        }
        System.out.println(JS);


        return JS ;
    }




}
