package cs280a1.hw1;
import java.util.ArrayList;
import java.util.Arrays;
public class Operations {
    public static String hexNum, decNum, binNum; // Strings of numbers once parsed, without headers
    public static void main(String[] args) {
        if(parseArgs(args)) {
            // Rest of main should reside in here (once args have successfully been parsed.)
            System.out.println("Task 4");
            System.out.println("Start=" + decNum + ",Binary=0b" + decToBin(decNum) + ",Decimal=" + decNum + ",Hexadecimal=0x" + decToHex(decNum));
            System.out.println("Start=0b" + binNum + ",Binary=0b" + binNum + ",Decimal=" + binToDec(binNum) + ",Hexadecimal=0x" + binToHex(binNum));
            System.out.println("Start=0x" + hexNum + ",Binary=0b" + hexToBin(hexNum) + ",Decimal=" + hexToDec(hexNum) + ",Hexadecimal=0x" + hexNum);
            System.out.println();
            System.out.println("Task 5");
            System.out.println(decNum + "=" + decToBin(decNum) + "=>" + onesCompliment(decToBin(decNum)));
            System.out.println("0b" + binNum + "=" + binNum + "=>" + onesCompliment(binNum));
            System.out.println("0x" + hexNum + "=" + hexToBin(hexNum) + "=>" + onesCompliment(hexToBin(hexNum)));
            System.out.println();
            System.out.println("Task 6");
            System.out.println(decNum + "=" + decToBin(decNum) + "=>" + twosCompliment(decToBin(decNum)));
            System.out.println("0b" + binNum + "=" + binNum + "=>" + twosCompliment(binNum));
            System.out.println("0x" + hexNum + "=" + hexToBin(hexNum) + "=>" + twosCompliment(hexToBin(hexNum)));
            System.out.println();
            System.out.println("Task 7");
            System.out.println(hexToBin(hexNum) + "|" + decToBin(decNum) + "|" + binNum + "=" + bitwiseOr(bitwiseOr(hexToBin(hexNum), decToBin(decNum)), binNum));
            System.out.println(hexToBin(hexNum) + "&" + decToBin(decNum) + "&" + binNum + "=" + bitwiseAnd(bitwiseAnd(hexToBin(hexNum), decToBin(decNum)), binNum));
            System.out.println(hexToBin(hexNum) + "^" + decToBin(decNum) + "^" + binNum + "=" + bitwiseXor(bitwiseXor(hexToBin(hexNum), decToBin(decNum)), binNum));
            System.out.println();
            System.out.println("Task 8");
            System.out.println(hexToBin(hexNum) + "<<2=" + leftShift(hexToBin(hexNum)) + "," + hexToBin(hexNum) + ">>2=" + rightShift(hexToBin(hexNum)));
            System.out.println(binNum + "<<2=" + leftShift(binNum) + "," + binNum + ">>2=" + rightShift(binNum));
            System.out.println(hexToBin(decNum) + "<<2=" + leftShift(hexToBin(decNum)) + "," + hexToBin(decNum) + ">>2=" + rightShift(hexToBin(decNum)));
        }
    }
    // All arguments should be strings (otherwise how would they combine letters and numbers?)
    public static boolean parseArgs(String[] args) {
        // Task 1
        System.out.println("Task 1");
        if (args.length != 3) {
            System.out.println("Error: Must run with exactly three arguments.");
            return false;
        }
        System.out.println("Correct number of arguments given.\n");
        // Task 2
        System.out.println("Task 2");
        for (int i = 0; i < args.length; i++) {
            if (args[i].length() > 1 && args[i].substring(0, 2).equals("0b")) {
                System.out.println(args[i] + "=Binary");
                binNum = args[i].substring(2); // Takes off prefix
            }
            else if (args[i].length() > 1 && args[i].substring(0, 2).equals("0x")) {
                System.out.println(args[i] + "=Hexadecimal");
                hexNum = args[i].substring(2).toUpperCase(); // Takes off prefix and force uppercase (for easier math later)
            }
            else {
                System.out.println(args[i] + "=Decimal");
                decNum = args[i]; // No prefix to remove
            }
        }
        System.out.println();
        // Task 3
        System.out.println("Task 3");
        // Valid char arraylists
        ArrayList<Character> validDec = new ArrayList<>(Arrays.asList('0','1','2','3','4','5','6','7','8','9'));
        ArrayList<Character> validHex = new ArrayList<>(Arrays.asList('0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'));
        // Check binary number
        for (char c : binNum.toCharArray()) {
            if (c != '0' && c != '1') {
                System.out.println("Error: Binary number contains invalid characters.");
                return false;
            }
        }
        System.out.println("0b" + binNum + "=true");
        // Check decimal & hex number
        for (char c : decNum.toCharArray()) {
            if (!validDec.contains(c)) {
                System.out.println("Error: Decimal number contains invalid characters.");
                return false;
            }
        }
        System.out.println(decNum + "=true");
        for (char c : hexNum.toCharArray()) {
            if (!validHex.contains(c)) {
                System.out.println("Error: Hexadecimal number contains invalid characters.");
                return false;
            }
        }
        System.out.println("0x" + hexNum + "=true\n");
        return true;
    } 

    // Task 4: Conversion Methods
    public static String binToDec(String binary) {
        // This implementation is simpler and can be done in a single method
        char[] c = binary.toCharArray();
        int decimalInt = 0;
        int power = 0;
        // In a for loop, check every character in the array and subtract the unicode value
        for (int i = c.length - 1; i >= 0; i--) {
            // Add the number to decimalInt, first multiplying by 10 for every position to the left.
            decimalInt += (c[i] - 48) * Math.pow(2, power);
            power++;
        }
        return Integer.toString(decimalInt);
    }
    public static String binToHex(String binary) {
        // Just use the methods you already made lol
        return decToHex(binToDec(binary));
    }
    public static String decToBin(String decimal) {
        int decimalInt = decStringToInt(decimal);
        String binString = "";
        while (decimalInt > 0) {
            binString = (decimalInt % 2) + binString;
            decimalInt /= 2;
        }
        if (binString == "") binString = "0";
        return binString;
    }
    public static String decToHex(String decimal) {
        // Get decimal number as Int
        int decimalInt = decStringToInt(decimal);
        // Divide by 16, convert remainder if 10-15 to A-F, add remainder to left of output, repeat until quotient is 0
        String hexString = "";
        while (decimalInt > 0) {
            if (decimalInt % 16 < 10){
                hexString = (decimalInt % 16) + hexString;
            }
            else {
                // Add to character to get unicode capital letter representing decimal number
                hexString = (char) ((decimalInt % 16) + 55) + hexString;
            }
            decimalInt /= 16;
        }
        if (hexString == "") hexString = "0";
        return hexString;
    }
    public static String hexToBin(String hexadecimal) {
        return decToBin(hexToDec(hexadecimal));
    }
    public static String hexToDec(String hexadecimal) {
        char[] c = hexadecimal.toCharArray();
        int decimalInt = 0;
        int power = 0;
        for (int i = c.length - 1; i >= 0; i--) {
            if (c[i] > 47 && c[i] < 58) {
                decimalInt += (c[i] - 48) * Math.pow(16, power);
            }
            else {
                // All other cases the char is A-F, subtract off the unicode garbage and add 10.
                decimalInt += ((c[i] - 65) + 10) * Math.pow(16, power);
            }
            power++;
        }
        return Integer.toString(decimalInt);
    }
    // String to int conversion helper
    private static int decStringToInt(String decimal) {
        // Turn string into character array
        char[] c = decimal.toCharArray();
        int decimalInt = 0;
        int power = 0;
        // In a for loop, check every character in the array and subtract the unicode value
        for (int i = c.length - 1; i >= 0; i--) {
            // Add the number to decimalInt, first multiplying by 10 for every position to the left.
            decimalInt += (c[i] - 48) * Math.pow(10, power);
            power++;
        }
        return decimalInt;
    }
    // Task 5
    public static String onesCompliment(String binary) {
        String onesCompliment = "";
        char[] c = binary.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == '0') onesCompliment += '1';
            else onesCompliment += '0';
        }
        return onesCompliment;
    }
    // Task 6
    public static String twosCompliment(String binary) {
        String twosCompliment = onesCompliment(binary);
        char[] c = twosCompliment.toCharArray();
        for (int i = c.length - 1; i >= 0; i--) {
            if (c[i] == '0') {
                c[i] = '1';
                return String.valueOf(c);
            }
            else {
                c[i] = '0';
            }
        }
        return '1' + String.valueOf(c);
    }
    // Task 7
    public static String bitwiseOr(String num1, String num2) {
        // Match the length first, then turn into character arrays, then do the operation!
        while (num1.length() > num2.length()) {
            num2 = "0" + num2;
        }
        while (num1.length() < num2.length()) {
            num1 = "0" + num1;
        }
        char[] c1 = num1.toCharArray();
        char[] c2 = num2.toCharArray();
        String result = "";
        for (int i = 0; i < num1.length(); i++) {
            if (c1[i] == '1' || c2[i] == '1') result += "1";
            else result += "0";
        }
        // Return the result, do extra string formatting in the main method
        return result;
    }
    public static String bitwiseAnd(String num1, String num2) {
        while (num1.length() > num2.length()) {
            num2 = "0" + num2;
        }
        while (num1.length() < num2.length()) {
            num1 = "0" + num1;
        }
        char[] c1 = num1.toCharArray();
        char[] c2 = num2.toCharArray();
        String result = "";
        for (int i = 0; i < num1.length(); i++) {
            if (c1[i] == '1' && c2[i] == '1') result += "1";
            else result += "0";
        }
        return result;
    }
    public static String bitwiseXor(String num1, String num2) {
        while (num1.length() > num2.length()) {
            num2 = "0" + num2;
        }
        while (num1.length() < num2.length()) {
            num1 = "0" + num1;
        }
        char[] c1 = num1.toCharArray();
        char[] c2 = num2.toCharArray();
        String result = "";
        for (int i = 0; i < num1.length(); i++) {
            if ((c1[i] == '1' || c2[i] == '1') && !(c1[i] == '1' && c2[i] == '1')) result += "1";
            else result += "0";
        }
        return result;
    }
    // Task 8
    public static String leftShift(String num) {
        return num + "00";
    }
    public static String rightShift(String num) {
        if (num.length() >= 2) {
            return num.substring(0, num.length() - 2);
        }
        else {
            return "0";
        }
    }
}
