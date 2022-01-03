package com.example.demo;
import com.example.demo.nonlinear.NewtonRephson;
import org.json.simple.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import  org.json.simple.parser.*;
import org.springframework.web.bind.annotation.*;


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
    @GetMapping("/newton")
    public double newton(@RequestParam String equation,@RequestParam double intial,@RequestParam double iterations,@RequestParam double eps){
        NewtonRephson nw=new NewtonRephson();
        nw.setX(intial);
        nw.setFunction(equation);
        return nw.getroot() ;
    }


}
