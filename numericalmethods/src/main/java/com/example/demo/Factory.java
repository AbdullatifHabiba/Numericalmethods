package com.example.demo;

public class Factory {
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
        //for LUdecompostion
        Factory F = new Factory();
        double[][] EQUs = {{2,0,0,0},{-1,2,0,0},{1,-5,2,0},{1,1,1,2}};
        double[] B = {8,2,-7,11};
        double[] result = F.ForWard(EQUs, B);
        System.out.println(result[0]);
        System.out.println(result[1]);
        System.out.println(result[2]);
        System.out.println(result[3]);
        //for seidel
        double a[][]={{12,3,-5},{1,5,3},{3,7,13}};
        double[] i={1,0,1};
        double[] b={1,28,76};
        Stop s=new Stop();
        s.setRelativeerror(0.0);
        s.setIterativenum(6);

        Seidelsolver solver=new Seidelsolver(a,i,b,s);
        for ( int p=0;p< b.length;p++) {
            System.out.println("x"+p+"="+solver.solve()[p]);
        }
    }
}
