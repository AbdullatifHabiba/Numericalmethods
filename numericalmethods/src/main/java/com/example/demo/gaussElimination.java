package com.example.demo;

public class gaussElimination {

    public double[] JaussElimination(double Arr[][],double[] B)throws Exception
    {

        for(int i=0;i<B.length;i++)
        {
            //finding the pivot
            double pivot=Math.abs(Arr[i][i]);
            int pivotIndx=i;
            for(int j=i+1;j<B.length;j++)
            {
                if(Math.abs(Arr[j][i])>pivot)
                {
                    pivot=Math.abs(Arr[j][i]);
                    pivotIndx=j;
                }
            }
            // change rows in coef array
            double[] temp=Arr[i];
            Arr[i]=Arr[pivotIndx];
            Arr[pivotIndx]=temp;
            //change rows in B Matrix
            double temp2=B[i];
            B[i]=B[pivotIndx];
            B[pivotIndx]=temp2;

            for(int k=i+1;k<B.length;k++)
            {
                if(Arr[i][i]==0)
                {
                    throw new Exception("Error");
                }
                double factor=Arr[k][i]/Arr[i][i];
                B[k]=B[k]-factor*B[i];

                for(int c=i;c<B.length;c++)
                {
                    Arr[k][c]=Arr[k][c]-factor*Arr[i][c];
                }
            }
        }
        //Back substitution
        Factory F =new Factory();
        double[] result=new double[B.length];

        for(int i=B.length-1;i>=0;i-- )
        {
            double sum=0;

            for(int j=i+1;j<B.length;j++)
            {
                sum+=Arr[i][j]*result[j];
            }
            if(Arr[i][i]==0)
            {
                throw new Exception("Error");
            }
            result[i]=(B[i]-sum)/Arr[i][i];
        }
        return result;
    }
}