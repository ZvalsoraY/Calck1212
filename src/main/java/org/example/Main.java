package org.example;

import com.mypac.rpn.RPN;

public class Main {
    public static void main(String[] args) {
        String inputData = "(2 + 3 / 2) * 80";

        RPN rpn =new RPN();
        var x = rpn.GetRPNstring(inputData);
        System.out.println(x);
        System.out.println(rpn.counting(x));
    }
}