package com.example.demo;

public class Factory {
    public double[][] GetA(String equations, int unkwons){
        double[][] A = new double[unkwons][unkwons];
        String[] coeff = equations.replaceAll("\\[|\\]", "").split(",");
        int j = 0;
        for (int i = 0;i < unkwons;i++){
            for (int k = 0;k < unkwons;k++){
                A[i][k] = Double.parseDouble(coeff[j]);
                j += 1;
            }
            j += 1;
        }
        return A;
    }
    public double[] GetB(String equations, int unkwons){
        double[] B = new double[unkwons];
        String[] coeff = equations.replaceAll("\\[|\\]", "").split(",");
        int j = 0;
        for (int i = 0;i < unkwons;i++){
            B[i] = Double.parseDouble(coeff[unkwons + i * (unkwons + 1)]);
        }
        return B;
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
        LUdecomposition solve = new LUdecomposition();
        double[][] EQUs = {{2,4,1,9},{1,-5,2,-2},{7,1,3,2},{2,4,1,9}};
        double[] B = {0,0,0,0};
        double[] result = solve.result(solve.GetL(EQUs), solve.GetU(EQUs), B);
        System.out.println(result[0]);
        System.out.println(result[1]);
        System.out.println(result[2]);
        System.out.println(result[3]);
    }
}
