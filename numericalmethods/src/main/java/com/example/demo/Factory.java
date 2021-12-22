package com.example.demo;

import java.util.ArrayList;
import java.lang.*;

import static java.lang.Character.isDigit;
import static java.lang.Character.isLetter;

public class Factory {
    void Replace(double[][] EQUs, int first, int second){
        double[] row = new double[EQUs[0].length];
        for (int i = 0;i < EQUs[first].length;i++){
            row[i] = EQUs[first][i];
            EQUs[first][i] = EQUs[second][i];
            EQUs[second][i] = row[i];
        }
    }

    void Pivoting(double[][] EQUs){
        double[][] U = new double[EQUs.length][EQUs[0].length];
        for (int i = 0;i < EQUs.length;i++){
            for (int j = 0;j < EQUs[0].length;j++)
                U[i][j] = EQUs[i][j];
        }
        for (int i = 0;i < U.length - 1;i++){
            int pivot = i;
            for (int j = i + 1;j < U.length;j++){
                if (U[j][i] > U[pivot][i])
                    pivot = j;
            }
            Replace(EQUs, i, pivot);
            for (int j = i + 1;j < U.length;j++){
                double a = U[j][i] / U[i][i];
                for (int k = 0;k < U.length;k++){
                    U[j][k] = U[j][k] - a * U[i][k];
                }
            }
        }
        /*for (int i = 0;i < U.length;i++){
            if (U[i][i] < 0.0000000001 || U[i][i] > -0.0000000001)
                EQUs = null;
        }*/
    }

    public double[][] EQUs(String equations, int unkwons){
        double[][] EQUs = new double[unkwons][unkwons + 1];
        String[] coeff = equations.replaceAll("\\[|\\]", "").split(",");
        int j = 0;
        for (int i = 0;i < unkwons;i++){
            for (int k = 0;k < unkwons + 1;k++){
                EQUs[i][k] = Double.parseDouble(coeff[j]);
                j += 1;
            }
        }
        return EQUs;
    }
    public double[] BackWard(double[][] EQUs, double[] B){
        double[] result = new double[B.length];
        result[B.length - 1] = B[B.length - 1] / EQUs[B.length - 1][B.length - 1];
        for (int i = B.length - 2;i >= 0;i--){
            result[i] = B[i];
            for (int j = B.length - 1;j > i;j--){
                result[i] = result[i] - EQUs[i][j] * result[j];
            }
            result[i] = result[i] / EQUs[i][i];
        }
        return result;
    }

    public double[] ForWard(double[][] EQUs, double[] B){
        double[] result = new double[B.length];
        result[0] = B[0] / EQUs[0][0];
        for (int i = 1;i < B.length;i++){
            result[i] = B[i];
            for (int j = 0;j < i;j++){
                result[i] = result[i] - EQUs[i][j] * result[j];
            }
            result[i] = result[i] / EQUs[i][i];
        }
        return result;
    }







    public static void main(String[] args) {
                Factory F = new Factory();

        String s ="x-2y=3,x-y=7";
        //System.out.println(S.length);
        GetMatrices GetMatrices =new GetMatrices(s);
       System.out.println("matrix"+GetMatrices.setmatrix()[1][1]);
        System.out.println("aug"+GetMatrices.AUG()[1][1]);
        System.out.println("b"+GetMatrices.B()[1]);
        double []i={1,0};
        Stop S=new Stop();
        S.setRelativeerror(0);
        S.setIterativenum(5);
        Seidelsolver se=new Seidelsolver(GetMatrices.setmatrix(),i,GetMatrices.B(),S);

        System.out.println(se.solve()[0]);



       //System.out.println("out"+GetMatrices.setmatrix()[1]);

      //double sum= GetMatrices.setmatrix()[0][0]+GetMatrices.setmatrix()[0][1];
        //System.out.println(sum);

      /*  //for LUdecompostion
        double[][] EQUs = {{2,0,0,0},{-1,2,0,0},{1,-5,2,0},{1,1,1,2}};
        double[] B = {8,2,-7,11};
        double[] result = F.ForWard(EQUs, B);
        System.out.println(result[0]);
        System.out.println(result[1]);
        System.out.println(result[2]);
        System.out.println(result[3]);*/
        //for seidel
       /* double a[][]={{12,3,-5},{1,5,3},{3,7,13}};
        double[] i={1,0,1};
        double[] b={1,28,76};
        Stop s=new Stop();
        s.setRelativeerror(0.001);
        s.setIterativenum(0);

        Seidelsolver solver=new Seidelsolver(a,i,b,s);
        for ( int p=0;p< b.length;p++) {
            System.out.println("x"+p+"="+solver.solve()[p]);
        }*/
    }
}
