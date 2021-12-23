package com.example.demo;

import java.math.BigDecimal;
import java.util.ArrayList;
import static java.lang.Character.isDigit;
import static java.lang.Character.isLetter;

public class GetMatrices {
    private String equs;
    public GetMatrices(String equs) {
        this.equs = equs;
    }
    static double[][] aug;
    double[][] matrix;
    static double[] B;
    ArrayList<Character> result = new ArrayList<>();
    ArrayList<String> variables=new ArrayList<>();
    ArrayList<String> output=new ArrayList<>();
    ArrayList<double[]> coef=new ArrayList<>();
    boolean error = false;
    test getmatrix = new test();
    public double[][] setmatrix(){
        aug = getmatrix.Coeff(equs);
        B = new double[aug.length];
        matrix = new double[aug.length][aug.length];
        for (int i = 0;i < aug.length;i++){
            for (int j = 0;j < aug[0].length;j++){
                if(j != aug[0].length-1) {
                    matrix[i][j] = aug[i][j];
                }
                else{
                    B[i] = aug[i][j];
                }
            }
        }
        return matrix;
    }
    double[][]AUG(){
        return aug;
    }
    double[]B(){
        return B;
    }
}
