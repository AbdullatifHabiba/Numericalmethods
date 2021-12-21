package com.example.demo;
import org.json.simple.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import  org.json.simple.parser.*;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins="http://localhost:4200/")
@RequestMapping("/method")

public class MethodsController {
    JSONParser convertjson=new JSONParser();
    GetMatrices GetMatrices ;

String resalut [];
    @GetMapping("/GaussElimination")
    public double[] JaussElimation(@RequestParam String[] Matrix)  throws Exception{
        gaussElimination ga=new gaussElimination();
        GetMatrices GetMatrices =new GetMatrices(Matrix);
        return ga.JaussElimination(GetMatrices.setmatrix(),GetMatrices.B);

    }
    @GetMapping("/GaussJordan")
    public double[]  GaussJordan(@RequestParam String[] Matrix) throws Exception {
        GaussJordanSolution gj=new GaussJordanSolution();
        GetMatrices GetMatrices =new GetMatrices(Matrix);
        return gj.GaussJordan(GetMatrices.setmatrix(),GetMatrices.B);
    }
    @GetMapping("/LUDecomposition")
    public double[] LUDecomposition(@RequestParam String[] Matrix){
        Factory f=new Factory();
        GetMatrices GetMatrices =new GetMatrices(Matrix);

        return f.ForWard(GetMatrices.setmatrix(),GetMatrices.B);

    }
    @GetMapping("/Jacobi")
    public double[] Jacobi(@RequestParam String[] Matrix,@RequestParam double[] Intial,@RequestParam double numbOfIter,@RequestParam double relativeErr){
        GetMatrices GetMatrices =new GetMatrices(Matrix);
        Stop s=new Stop();
        s.setIterativenum((int)numbOfIter);
        s.setRelativeerror(relativeErr);
        jacobisolver SOLVE=new jacobisolver(GetMatrices.setmatrix(),Intial,GetMatrices.B ,s);
        return SOLVE.solve();
    }

    @GetMapping("/Seidel")
    public double[] Seidal(@RequestParam String[] Matrix,@RequestParam double[] Intial,@RequestParam double numbOfIter,@RequestParam double relativeErr){
       GetMatrices GetMatrices =new GetMatrices(Matrix);
        Stop s=new Stop();
        s.setIterativenum((int)numbOfIter);
        s.setRelativeerror(relativeErr);
        Seidelsolver SOLVE=new Seidelsolver(GetMatrices.setmatrix(),Intial,GetMatrices.B ,s);
        return SOLVE.solve();

    }

}
