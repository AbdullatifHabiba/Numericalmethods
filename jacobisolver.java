package com.example.demo;

import java.util.ArrayList;

public class jacobisolver {
    Factory F = new Factory();

    private double[][] matrix;
    private ArrayList<double[]> res = new ArrayList<>();
    private double[] b;
    private double[] intial;
    private  Stop num;
    double[] x;

    public jacobisolver(double arr[][], double[] i, double[] b,Stop num) {
        this.matrix = arr;
        this.b = b;
        this.intial = i;
        this.num=num;
    }

    boolean converge(double[][] arr) {
        double sum = 0;
        int gr = 0;
        for (int i = 0; i < arr[0].length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if(i != j)
                    sum += Math.abs(arr[i][j]);
            }
            if(sum <= arr[i][i]){
                if(sum < arr[i][i])
                    gr++;
            }
            else
                return false;
            sum=0;
        }
        return gr >= 1;
    }

    double max(double A[]){
        double max = A[0];
        for(int i = 0;i < A.length - 1;i++){
            if (A[i + 1] >= max)max = A[i + 1];
        }
        return max;
    }

    double[] solve() {
        x = new double[b.length];
        for (int i = 0; i < x.length; i++) {
            x[i] = 0;
        }
        double xo = 0;
        double per = num.getRelativeerror();
        double[] relativeError = new double[b.length];
        boolean f = true;
        int stop = num.getIterativenum();
        if (stop != 0 && per == 0.0){
            for (int k = 0;k < stop;k++) {
                for (int i = 0;i < matrix.length;i++) {
                    for (int j = 0;j < matrix[i].length;j++) {
                        if (i != j)
                            xo += F.precision(F.precision(matrix[i][j]) * F.precision(intial[j]));
                    }
                    x[i] = F.precision(F.precision(F.precision(b[i]) - F.precision(xo)) / F.precision(matrix[i][i]));
                    xo = 0;
                }
                intial = x;
                res.add(x) ;
            }
        }//for  number of iteration
        if(per != 0.0){
            for(int k = 0;(f && k < stop);k++) {
                for(int i = 0;i < matrix.length;i++) {
                    for(int j = 0;j < matrix[i].length;j++) {
                        if (i != j)
                            xo += F.precision(F.precision(matrix[i][j]) * F.precision(intial[j]));
                    }
                    x[i] = F.precision(F.precision(F.precision(b[i]) - F.precision(xo)) / F.precision(matrix[i][i]));
                     xo = 0;
                }
                intial = x;
                res.add(x);
                for(int j = 0;j < res.get(0).length && k >= 1;j++) {
                    relativeError[j] = F.precision(F.precision(res.get(k)[j]) - F.precision(res.get(k-1)[j]));
                }
                if (max(relativeError) <= per)
                    f = false;
            }

        }//for relative error
        return res.get(res.size()-1);
    }

}