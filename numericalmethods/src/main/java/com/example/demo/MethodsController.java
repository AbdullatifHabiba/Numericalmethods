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

String resalut [];
    @GetMapping("/GaussElimination")
    public String JaussElimation(@RequestParam double[][] Matrix,@RequestParam double[] B) throws ParseException {

        return AH.toJSONString();

    }
    @GetMapping("/GaussJordan")
    public String GaussJordan(@RequestParam double[][] Matrix,@RequestParam double[] B){
        return resalut.toString();

    }
    @GetMapping("/LUDecomposition")
    public String LUDecomposition(@RequestParam double[][] Matrix,@RequestParam double[] B,@RequestParam String parameter){
        return resalut.toString();

    }
    @GetMapping("/Jacobi")
    public String Jacobi(@RequestParam double[][] Matrix,@RequestParam double[] B,@RequestParam String parameter){
        return resalut.toString();

    }

    @GetMapping("/Seidal")
    public String Seidal(@RequestParam double[][] Matrix,@RequestParam double[] B,@RequestParam String parameter){
        return resalut.toString();

    }

}
