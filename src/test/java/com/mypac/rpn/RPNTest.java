package com.mypac.rpn;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RPNTest {
    RPN rpnForTest;

    @BeforeEach
    public void initEach(){
        rpnForTest = new RPN();
    }


    @Test
    void countingPlusTest() {
        assertEquals(280, rpnForTest.counting("2 3 2 / + 80 * "), 0.000001);
    }

    @Test
    void countingMinusTest() {
        assertEquals(-520, rpnForTest.counting("2 3 2 / 10 - + 80 * "), 0.000001);
    }

    @Test
    void plusBinaryOperatorTest(){
        assertEquals(5, rpnForTest.plusBinaryOperator.apply(2., 3.), 0.000001);
    }

    @Test
    void minusBinaryOperatorTest(){
        assertEquals(-1, rpnForTest.minusBinaryOperator.apply(2., 3.), 0.000001);
    }

    @Test
    void multipleBinaryOperatorTest(){
        assertEquals(6, rpnForTest.multipleBinaryOperator.apply(2., 3.), 0.000001);
    }

    @Test
    void divideBinaryOperatorTest(){
        assertEquals(2, rpnForTest.divideBinaryOperator.apply(4., 2.), 0.000001);
    }

    @Test
    void getRPNstringTest() {
        String inputString;
        inputString = "(2 + 3 / 2) * 80";
        assertEquals("2 3 2 / + 80 * ", rpnForTest.GetRPNstring(inputString));
    }

    @Test
    void getRPNstringDoubleParenthesesTest() {
        String inputString;
        inputString = "(2 + 3 / 2 - 10 * (2 - 1)) * 80";
        assertEquals("2 3 2 / 10 2 1 - * - + 80 * ", rpnForTest.GetRPNstring(inputString));
    }
}