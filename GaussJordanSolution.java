package com.example.demo;

public class GaussJordanSolution {
    Factory F = new Factory();

    public double[] GaussJordan(double[][] Arr,double[] B)throws Exception {

        for(int i = 0;i < B.length;i++) {
            //finding the pivot
            double pivot = Math.abs(Arr[i][i]);
            int pivotIndx = i;
            for(int j = i + 1;j < B.length;j++) {
                if(Math.abs(Arr[j][i]) > pivot) {
                    pivot = Math.abs(Arr[j][i]);
                    pivotIndx = j;
                }
            }
            // change rows in coef array
            double[] temp = Arr[i];
            Arr[i] = Arr[pivotIndx];
            Arr[pivotIndx] = temp;
            //change rows in B Matrix
            double temp2 = B[i];
            B[i] = B[pivotIndx];
            B[pivotIndx] = temp2;
            //forward Elimination
            for(int k = i + 1;k < B.length;k++) {
                if(Arr[i][i] == 0) {
                    throw new Exception("Error");
                }
                double factor = F.precision(F.precision(Arr[k][i]) / F.precision(Arr[i][i]));
                B[k] = F.precision(F.precision(B[k]) - F.precision(factor) * F.precision(B[i]));
                for(int c = i;c < B.length;c++) {
                    Arr[k][c] = F.precision(F.precision(Arr[k][c])-F.precision(factor) * F.precision(Arr[i][c]));
                }
            }
            //Backward Elimination
            for(int k= i - 1;k >= 0;k--) {
                if(Arr[i][i] == 0) {
                    throw new Exception("Error");
                }
                double factor = F.precision(F.precision(Arr[k][i]) / F.precision(Arr[i][i]));
                B[k] = F.precision(F.precision(B[k]) - F.precision(factor) * F.precision(B[i]));
                for(int c = 0;c < B.length;c++) {
                    Arr[k][c] = F.precision(F.precision(Arr[k][c]) - F.precision(factor) * F.precision(Arr[i][c]));
                }
            }
        }
        //result
        for(int i = 0;i < B.length;i++) {
            B[i] = F.precision(F.precision(B[i]) / F.precision(Arr[i][i]));
        }
        return B;
    }
}
