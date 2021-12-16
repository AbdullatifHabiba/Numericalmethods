package com.example.demo;

public class LUdecomposition {
    Factory F = new Factory();
    double[][] GetU (double[][] Matrix){
        double[][] U = Matrix;
        for (int i = 0;i < U.length - 1;i++){
            for (int j = i + 1;j < U.length;j++){
                double a = U[j][i] / U[i][i];
                for (int k = 0;k < U.length;k++){
                    U[j][k] = U[j][k] - a * U[i][k];
                }
            }
        }
        return U;
    }

    double[][] GetL (double[][] Matrix){
        double[][] U = Matrix;
        double[][] L = new double[Matrix.length][Matrix.length];
        for (int i = 0;i < U.length - 1;i++){
            for (int j = i + 1;j < U.length;j++){
                L[i][j] = 0;
                double a = U[j][i] / U[i][i];
                L[j][i] = a;
                for (int k = 0;k < U.length;k++){
                    U[j][k] = U[j][k] - a * U[i][k];
                    L[k][k] = 1;
                }
            }
        }
        return L;
    }

    double[] result(double[][] L, double[][] U, double[] B){
        return F.BackWard(U, F.ForWard(L, B));
    }
}
