package task2;

import task1.Stack;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static task2.FileUtils.readFile;

public class Algorithm {
    public static Double calculate(String s1) {
        Stack<Double> values = new Stack<>();
        Stack<Character> operators = new Stack<>();

        int i = 0;
        while (i < s1.length()) {
            char c = s1.charAt(i);
            if (c == ' ') {
                i++;
                continue;
            } else if (Character.isDigit(c) || c == '.') {
                StringBuilder sb = new StringBuilder();
                while (i < s1.length() && (Character.isDigit(s1.charAt(i)) || s1.charAt(i) == '.')) {
                    sb.append(s1.charAt(i));
                    i++;
                }
                double num = Double.parseDouble(sb.toString());
                values.push(num);
            } else if (c == '(') {
                operators.push(c);
                i++;
            } else if (c == ')') {
                while (operators.peek() != '(') {
                    char operator = operators.pop();
                    double[] operands;
                    if (operator == '√') {
                        double num1 = values.pop();
                        operands = new double[]{num1};
                    } else {
                        double num2 = values.pop();
                        double num1 = values.pop();
                        operands = new double[]{num1, num2};
                    }
                    double result = executeOperation(operator, operands);
                    values.push(result);
                }
                operators.pop();
                i++;
            } else {
                while (!operators.isEmpty() && precedence(c) <= precedence(operators.peek())) {
                    char operator = operators.pop();
                    double[] operands;
                    if (operator == '√') {
                        double num1 = values.pop();
                        operands = new double[]{num1};
                    } else {
                        double num2 = values.pop();
                        double num1 = values.pop();
                        operands = new double[]{num1, num2};
                    }
                    double result = executeOperation(operator, operands);
                    values.push(result);
                }
                operators.push(c);
                i++;
            }
        }
        return values.pop();
    }

    public static ArrayList<Double> calculateFromFile(String filePath) throws FileNotFoundException {
        ArrayList<String> expressions = readFile(filePath);
        ArrayList<Double> results = new ArrayList<>();

        for(String exp : expressions) {
            Double result = calculate(exp);
            results.add(result);
        }

        return results;
    }

    // helper methods
    private static double executeOperation(char operator, double[] operands) {
        switch (operator) {
            case '+':
                return (operands[0] + operands[1]);
            case '-':
                return (operands[0] - operands[1]);
            case '*':
                return (operands[0] * operands[1]);
            case '/':
                if (operands[1] == 0) {
                    throw new IllegalArgumentException("Cannot divide with zero");
                }
                return (operands[0] / operands[1]);
            case '%':
                if (operands[1] == 0) {
                    throw new IllegalArgumentException("Cannot divide with zero");
                }
                return (operands[0] % operands[1]);
            case '^':
                return Math.pow(operands[0], operands[1]);
            case '√':
                if (operands[0] < 0) {
                    throw new IllegalArgumentException("Cannot take square root of a negative number");
                }
                return Math.sqrt(operands[0]);
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }

    private static int precedence(char operator) {
        switch (operator) {
            case '+':
                return 1;
            case '-':
                return 1;
            case '*':
                return 2;
            case '/':
                return 2;
            case '%':
                return 2;
            case '^':
                return 3;
            case '√':
                return 3;
            case '(':
                return 0;
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }
}
