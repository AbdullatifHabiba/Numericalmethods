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




   static double[][] aug ;

    double[][] matrix ;
     static double[]B ;


    ArrayList<Character> result = new ArrayList<>();
    ArrayList<String> variables=new ArrayList<>();
    ArrayList<String> output=new ArrayList<>();
    ArrayList<double[]> coef=new ArrayList<>();

    boolean error=false;
    /*boolean isoperator(char x){
        if(x=='#'||x=='*'||x=='/'||x=='-')return  true;
        return  false;
    }
    public  String[] Equation(String equation ) {

        char[]c= equation.toCharArray();
        for(int j=0;j<c.length;j++) {result.add(c[j]);}

        for(int i=0;i<result.size()-1;i++) {
            if (result.get(i).equals('='))
            {
                for(int j=i;j<result.size()-1;j++){
                    output.add(String.valueOf(result.get(i+1)));
                    result.remove(j);
                }
                //System.out.println(output);
                result.remove(result.size()-1);
                break;
            }
            if(isoperator(result.get(i))&&isoperator(result.get(i+1))) error=true ;
            if (isLetter(result.get(i+1))&&isDigit(result.get(i))) {
                variables.add(result.get(i+1).toString()) ;result.remove(i+1);}
            if (result.get(i).equals('-')&&isLetter(result.get(i+1))) {
                variables.add(result.get(i+1).toString()) ;result.set(i+1,'1');}
            if (isLetter(result.get(i))) {variables.add(result.get(i).toString()) ;result.set(i,'1');}
        }
        String [] sum=  result.toString().replace("[","").replace("]","")
                .replace("#,","").replace("-, ","-").split(",");
  result.clear();
        return sum;
    }*/
test getmatrix=new test();
    public double[][] setmatrix(){
        aug=getmatrix.Coeff(equs);
        B=new double[aug.length];

        matrix =new double[aug.length][aug.length];


        for (int i = 0; i < aug.length; i++){

            for (int j = 0; j < aug[0].length; j++){
            if(j!=aug[0].length-1)
            {
            matrix[i][j]= aug[i][j];
            }else{
                B[i]=aug[i][j];
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
