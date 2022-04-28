package com.company;

public class Main {
    public static void main(String[] args) {
        Tests tests = new Tests();
        tests.assertSanityChecks();
        tests.runTests(50, 8);
    }
}
