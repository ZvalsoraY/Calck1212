package com.mypac.rpn;


import java.util.Stack;
import java.util.function.BinaryOperator;

public class RPN {
    BinaryOperator<Double> plusBinaryOperator = (a1, a2) -> a1 + a2;
    BinaryOperator<Double> minusBinaryOperator = (a1, a2) -> a1 - a2;
    BinaryOperator<Double> multipleBinaryOperator = (a1, a2) -> a1 * a2;
    BinaryOperator<Double> divideBinaryOperator = (a1, a2) -> a1 / a2;

    public double counting(String inputString)
    {
        char[] input = inputString.toCharArray();
        double result = 0;
        Stack<Double> temp = new Stack<>();

        for (int i = 0; i < input.length; i++)
        {
            if (Character.isDigit(input[i]))
            {
                StringBuilder a = new StringBuilder();

                while (!isDelimiter(input[i]) && !isOperator(input[i]))
                {
                    a.append(input[i]);
                    i++;
                    if (i == input.length) break;
                }
                temp.push(Double.parseDouble(a.toString()));
                i--;
            }
            else if (isOperator(input[i]))
            {
                double a = temp.pop();
                double b = temp.pop();

                switch (input[i])
                {
                    case '+':result = plusBinaryOperator.apply(a, b); break;
                    case '-': result = minusBinaryOperator.apply(b, a); break;
                    case '*': result = multipleBinaryOperator.apply(a, b); break;
                    case '/': result = divideBinaryOperator.apply(b, a); break;
                }
                temp.push(result);
            }
        }
        return temp.peek();
    }

    public String GetRPNstring(String inputString)
    {
        StringBuilder output = new StringBuilder();
        Stack<Character> operatorStack = new Stack<>();
        char[] input = inputString.toCharArray();

        for (int i = 0; i < input.length; i++)
        {
            if (isDelimiter(input[i]))
                continue;

            if (Character.isDigit(input[i]))
            {
                while (!isDelimiter(input[i]) && !isOperator(input[i]))
                {
                    output.append(input[i]);
                    i++;

                    if (i == input.length) break;
                }
                output.append(" ");
                i--;
            }

            if (isOperator(input[i]))
            {
                if (input[i] == '(')
                    operatorStack.push(input[i]);
                else if (input[i] == ')')
                {
                    char s = operatorStack.pop();

                    while (s != '(')
                    {
                        output.append(s).append(' ');
                        s = operatorStack.pop();
                    }
                }
                else
                {
                    if (!operatorStack.isEmpty())
                        if (getPriority(input[i]) <= getPriority(operatorStack.peek()))
                            output.append(operatorStack.pop()).append(' ');

                    operatorStack.push(input[i]);

                }
            }
        }

        while (!operatorStack.isEmpty())
            output.append(operatorStack.pop()).append(' ');

        return output.toString();
    }

    private byte getPriority(char s)
    {
        switch (s)
        {
            case '(': return 0;
            case ')': return 1;
            case '+': return 2;
            case '-': return 3;
            case '*': return 4;
            case '/': return 4;
            default: return 10;
        }
    }

    private Boolean isDelimiter(char c)
    {
        if ((" =".indexOf(c) != -1))
            return true;
        return false;
    }

    private boolean isOperator(char с)
    {
        if (("+-/*^()".indexOf(с) != -1))
            return true;
        return false;
    }
}
