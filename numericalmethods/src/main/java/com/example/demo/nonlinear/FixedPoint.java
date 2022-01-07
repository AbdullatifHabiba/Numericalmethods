package com.example.demo.nonlinear;



    public class FixedPoint {
        public static double FixedPointMethod(String func,double x0, double es, int maxIteration,int preci)throws Exception {
            double xr = x0;
            double xr_old;
            int iteration = 0;
            double ea = 0;
            String fn=calculategx(func);
           // System.out.println(fn+"oo"+new Evaluate(func,x0,preci).eval());
            do {
                xr_old = xr;
                Evaluate E=new Evaluate(fn,xr_old,preci);
                xr = new Precision(preci,E.eval()).Value();
                System.out.println(xr);
                if (xr != 0)
                    ea = Math.abs((xr - xr_old) / xr) * 100;
                iteration++;
            } while (ea > es && iteration < maxIteration);
            return xr;

        }
        public static String calculategx (String fx) {

        String gx=fx+"+x";
            return gx;
        }

      /*  public static double calc(String func ,double val)
        {
            return Math.exp(-val);
        }
        public static void main(String[] args) {
           try {
               System.out.print(FixedPointMethod("sin(x)-x-6", -6, .0001, 100, 5));
           }
           catch(Exception e)
           {
               System.out.print("Error");
           }
        }*/
    }


