package com.example.demo;

import java.util.ArrayList;

import static java.lang.Character.isDigit;
import static java.lang.Character.isLetter;

public class GetMatrices {

    private String []equs;

    public GetMatrices(String[] equs) {
        this.equs = equs;
    }






    double[][] matrix ;


    ArrayList<Character> result = new ArrayList<>();
    ArrayList<String> variables=new ArrayList<>();
    ArrayList<String> output=new ArrayList<>();
    ArrayList<String[]> coef=new ArrayList<>();

    boolean error=false;
    boolean isoperator(char x){
        if(x=='+'||x=='*'||x=='/'||x=='-')return  true;
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
                    result.remove(i);
                }
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
                .replace("+,","").replace("-, ","-").split(",");
  result.clear();
        return sum;
    }

    public double[][] setmatrix(){
         matrix =new double[equs.length][equs.length];


        for (int i = 0; i < equs.length; i++){
            for (int j = 0; j < equs.length; j++){
            matrix[i][j]= Double.parseDouble(Equation(equs[i])[j]);

            }


        }

        return matrix;
    }
}
