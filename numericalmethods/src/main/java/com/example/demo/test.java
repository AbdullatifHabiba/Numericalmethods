package com.example.demo;

import static java.lang.Character.isDigit;
import static java.lang.Character.isLetter;
import java.util.ArrayList;
import java.util.Scanner;

public class test {
    double[][] Coeff(String l){
        ArrayList<Character> unkowns = new ArrayList<>();
        l.replaceAll("","+");
        for (int i = 0;i < l.length();i++){
            if (isLetter(l.charAt(i))){
                unkowns.add(l.charAt(i));
            }
        }
        for (int i = 0;i < unkowns.size() - 1;i++){
            for (int j = i + 1;j < unkowns.size();j++){
                if (unkowns.get(i).equals(unkowns.get(j))){
                    unkowns.remove(j);
                    j -= 1;
                }
            }
        }
        String c = l.replaceAll("=",",");
        String[] C = c.split(",");
        double[][] coeff = new double[C.length / 2][C.length / 2 + 1];
        for (int i = 0;i < C.length / 2;i++){
            coeff[i][C.length / 2] = Double.parseDouble(C[2 * i + 1]);
        }
        for (int i = 0;i < C.length / 2;i++){
            for (int k = 0;k < C[2 * i].length();k++){
                if (isLetter(C[2 * i].charAt(k))){
                    if (k == 0)
                        coeff[i][unkowns.indexOf(C[2 * i].charAt(k))] = 1;
                    else if (C[2 * i].charAt(k - 1) == '-'){
                        coeff[i][unkowns.indexOf(C[2 * i].charAt(k))] = -1;
                    }
                    else if (C[2 * i].charAt(k - 1) == '+'){
                        coeff[i][unkowns.indexOf(C[2 * i].charAt(k))] = 1;
                    }
                    else {
                        int j = k - 1;
                        while (!isLetter(C[2 * i].charAt(j)) && C[2 * i].charAt(j) != '+' && j != 0){
                            j -= 1;
                        }
                        if (j == 0 && !isLetter(C[2 * i].charAt(0)))
                            coeff[i][unkowns.indexOf(C[2 * i].charAt(k))] = Double.parseDouble(C[2 * i].substring(0,k));
                        else
                            coeff[i][unkowns.indexOf(C[2 * i].charAt(k))] = Double.parseDouble(C[2 * i].substring(j + 1,k));
                    }
                }
            }
        }
        return coeff;
    }


}
