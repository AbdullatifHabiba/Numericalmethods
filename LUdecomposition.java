package com.example.demo;

public class LUdecomposition {
    Factory F = new Factory();

    double[][] GetLUDoolittle (double[][] Matrix, char A){
        double[][] U = new double[Matrix.length][Matrix.length];
        for (int i = 0;i < Matrix.length;i++){
            for (int k = 0;k < Matrix.length;k++){
                U[i][k] = F.precision(Matrix[i][k]);
            }
        }
        double[][] L = new double[Matrix.length][Matrix.length];
        for (int i = 0;i < U.length - 1;i++){
            for (int j = i + 1;j < U.length;j++){
                L[i][j] = 0;
                double a = F.precision(F.precision(U[j][i]) / F.precision(U[i][i]));
                L[j][i] = a;
                for (int k = 0;k < U.length;k++){
                    U[j][k] = F.precision(F.precision(U[j][k]) - F.precision(a) * F.precision(U[i][k]));
                    L[k][k] = 1;
                }
            }
        }
        if (A == 'L')
            return L;
        else if (A == 'U')
            return U;
        else
            return null;
    }

    double[][] GetLUCrout(double[][] Matrix, char A){
        double[][] U = new double[Matrix.length][Matrix[0].length];
        double[][] L = new double[Matrix.length][Matrix[0].length];
        for (int i = 0;i < Matrix.length;i++){
            L[i][0] = F.precision(Matrix[i][0]);
            U[0][i] = F.precision(F.precision(Matrix[0][i]) / F.precision(Matrix[0][0]));
            U[i][i] = 1;
            for (int k = i + 1;k < Matrix.length;k++){
                L[i][k] = 0;
            }
            for (int k = 0;k < i;k++){
                U[i][k] = 0;
            }
        }
        for (int j = 1;j < Matrix.length - 1;j++){
            for (int i = j;i < Matrix.length;i++){
                double sum = 0;
                for (int k = 0;k < j;k++){
                    sum += F.precision(F.precision(L[i][k]) * F.precision(U[k][j]));
                }
                L[i][j] = F.precision(F.precision(Matrix[i][j]) - F.precision(sum));

            }
            for (int k = j + 1;k < Matrix.length;k++){
                double sum = 0;
                for (int i = 0;i < j;i++){
                    sum += F.precision(F.precision(L[j][i]) * F.precision(U[i][k]));
                }
                U[j][k] = F.precision(F.precision((F.precision(Matrix[j][k]) - F.precision(sum)) / F.precision(L[j][j])));
            }
        }
        double sum = 0;
        for (int k = 0;k < Matrix.length - 1;k++){
            sum += F.precision(F.precision(L[Matrix.length - 1][k]) * F.precision(U[k][Matrix.length - 1]));
        }
        L[Matrix.length - 1][Matrix.length - 1] = F.precision(F.precision(Matrix[Matrix.length - 1][Matrix.length - 1]) -F.precision(sum));
        if (A == 'L')
            return L;
        else if (A == 'U')
            return U;
        else
            return null;
    }

    double[] SolveDoolittle(double[][] A, double[] B){
        double[][] L = GetLUDoolittle(A,'L');
        double[][] U = GetLUDoolittle(A,'U');
        return F.BackWard(U, F.ForWard(L, B));
    }

    double[] SolveCrout(double[][] A, double[] B){
        double[][] L = GetLUCrout(A,'L');
        double[][] U = GetLUCrout(A,'U');
        return F.BackWard(U, F.ForWard(L, B));
    }
}
