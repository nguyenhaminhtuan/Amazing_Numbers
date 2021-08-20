package numbers;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static final Properties[][] mutualExclusiveProperties = new Properties[][] {
            {Properties.EVEN, Properties.ODD},
            {Properties.DUCK, Properties.SPY},
            {Properties.SUNNY, Properties.SQUARE},
            {Properties.HAPPY, Properties.SAD}
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        displayWelcomeAndInstructions();

        while (true) {
            System.out.print("\nEnter a request: ");
            String[] params = scanner.nextLine().split(" ");

            if (!isNatural(params[0])) {
                System.out.println("The first parameter should be a natural number or zero.");

                if (params.length == 1) {
                    continue;
                }
            }

            long number1 = Long.parseLong(params[0]);

            if (number1 == 0) {
                System.out.println("\nGoodbye!");
                break;
            }

            if (params.length == 1) {
                printProperties(Long.parseLong(params[0]), 1);
                continue;
            }

            if (!isNatural(params[1])) {
                System.out.println("The second parameter should be a natural number.");
                continue;
            }

            long number2 = Long.parseLong(params[1]);

            if (params.length == 2) {
                printListProperties(number1, number2);
                continue;
            }

            String[] properties = Arrays.copyOfRange(params, 2, params.length);

            if (!isValidProperties(properties)) {
                continue;
            }

            printListProperties(number1, number2, properties);
        }
    }

    public static void displayWelcomeAndInstructions() {
        System.out.println("Welcome to Amazing Numbers!");

        System.out.println("\nSupported requests:");
        System.out.println("- enter a natural number to know its properties;");
        System.out.println("- enter two natural numbers to obtain the properties of the list:");
        System.out.println("  * the first parameter represents a starting number;");
        System.out.println("  * the second parameter shows how many consecutive numbers are to be printed;");
        System.out.println("- two natural numbers and properties to search for;");
        System.out.println("- a property preceded by minus must not be present in numbers;");
        System.out.println("- separate the parameters with one space;");
        System.out.println("- enter 0 to exit.");
    }

    public static boolean isNatural(String str) {
        return !(Utility.removeDigit(str).length() > 0 || Long.parseLong(str) < 0);
    }

    public static void printProperties(long number, int type) {
        Properties even = Properties.EVEN;
        Properties odd = Properties.ODD;
        Properties buzz = Properties.BUZZ;
        Properties duck = Properties.DUCK;
        Properties palindromic = Properties.PALINDROMIC;
        Properties gapful = Properties.GAPFUL;
        Properties spy = Properties.SPY;
        Properties square = Properties.SQUARE;
        Properties sunny = Properties.SUNNY;
        Properties jumping = Properties.JUMPING;
        Properties happy = Properties.HAPPY;
        Properties sad = Properties.SAD;

        boolean isEven = even.isProperty(number);
        boolean isOdd = odd.isProperty(number);
        boolean isBuzz = buzz.isProperty(number);
        boolean isDuck = duck.isProperty(number);
        boolean isPalindromic = palindromic.isProperty(number);
        boolean isGapful = gapful.isProperty(number);
        boolean isSpy = spy.isProperty(number);
        boolean isSquare = square.isProperty(number);
        boolean isSunny = sunny.isProperty(number);
        boolean isJumping = jumping.isProperty(number);
        boolean isHappy = happy.isProperty(number);
        boolean isSad = sad.isProperty(number);

        if (type == 1) {
            System.out.println("\nProperties of " + number);
            System.out.println("        " + even.getName() + ": " + isEven);
            System.out.println("         " + odd.getName() + ": " + isOdd);
            System.out.println("        " + buzz.getName() + ": " + isBuzz);
            System.out.println("        " + duck.getName() + ": " + isDuck);
            System.out.println(" " + palindromic.getName() + ": " + isPalindromic);
            System.out.println("      " + gapful.getName() + ": " + isGapful);
            System.out.println("         " + spy.getName() + ": " + isSpy);
            System.out.println("      " + square.getName() + ": " + isSquare);
            System.out.println("       " + sunny.getName() + ": " + isSunny);
            System.out.println("     " + jumping.getName() + ": " + isJumping);
            System.out.println("        " + happy.getName() + ": " + isHappy);
            System.out.println("         " + sad.getName() + ": " + isSad);
        } else {
            StringBuilder sb = new StringBuilder();

            if (isEven) sb.append(even.getName());
            if (isOdd) Utility.toArrayStringWithComma(sb, odd.getName());
            if (isBuzz) Utility.toArrayStringWithComma(sb, buzz.getName());
            if (isDuck) Utility.toArrayStringWithComma(sb, duck.getName());
            if (isPalindromic) Utility.toArrayStringWithComma(sb, palindromic.getName());
            if (isGapful) Utility.toArrayStringWithComma(sb, gapful.getName());
            if (isSpy) Utility.toArrayStringWithComma(sb, spy.getName());
            if (isSquare) Utility.toArrayStringWithComma(sb, square.getName());
            if (isSunny) Utility.toArrayStringWithComma(sb, sunny.getName());
            if (isJumping) Utility.toArrayStringWithComma(sb, jumping.getName());
            if (isHappy) Utility.toArrayStringWithComma(sb, happy.getName());
            if (isSad) Utility.toArrayStringWithComma(sb, sad.getName());

            sb.insert(0, "\t\t\t" + number + " is ");
            System.out.println(sb);
        }
    }

    public static void printListProperties(long number, long loop) {
        for (long i = number; i < number + loop; i++) {
            printProperties(i, 2);
        }
    }

    public static void printListProperties(long number, long loop, String... properties) {
        int i = 0;
        long j = number;

        while (i < loop) {
            if (haveProperties(j, properties)) {
                printProperties(j, 2);
                i++;
            }
            j++;
        }
    }

    public static boolean isValidProperties(String... properties) {
        StringBuilder sb = new StringBuilder();
        int count = 0;

        for (String str : properties) {
            boolean isMatch = false;

            for (Properties p : Properties.values()) {
                if (str.equalsIgnoreCase(p.getName()) || str.equalsIgnoreCase("-" + p.getName())) {
                    isMatch = true;
                    break;
                }
            }

            if (!isMatch) {
                count++;
                Utility.toArrayStringWithComma(sb, str.toUpperCase());
            }
        }

        if (count >= 1) {
            if (count == 1) {
                sb.insert(0, "\nThe property [");
                sb.append("] is wrong");
            } else {
                sb.insert(0, "\nThe properties [");
                sb.append("] are wrong.");
            }

            System.out.println(sb);
            System.out.println("Available properties: " + Arrays.toString(Properties.values()).toUpperCase());

            return false;
        }

        if (isMutuallyExclusiveProperties(properties)) {
            return false;
        }

        return true;
    }

    public static boolean haveProperties(long number, String... properties) {
        for (String str : properties) {
            for (Properties p : Properties.values()) {
                if (str.equalsIgnoreCase(p.getName()) || str.equalsIgnoreCase("-" + p.getName())) {
                    if ((!(p.isProperty(number)) && str.charAt(0) != '-')
                            || (p.isProperty(number) && str.charAt(0) == '-')) {
                        return false;
                    }
                    break;
                }
            }
        }

        return true;
    }

    public static boolean isMutuallyExclusiveProperties(String... properties) {
        for (int i = 0; i < mutualExclusiveProperties.length; i++) {
            String[] contains = new String[] {"", ""};

            for (int j = 0; j < mutualExclusiveProperties[i].length; j++) {
                for (String p : properties) {
                    String name = mutualExclusiveProperties[i][j].getName();
                    String copy = p;

                    if (p.charAt(0) == '-') copy = reverseExclusiveProperty(p);

                    if (name.equalsIgnoreCase(copy)) {
                        contains[j] = p.toUpperCase();
                        break;
                    }
                }
            }

            if (!contains[0].isEmpty() && !contains[1].isEmpty()) {
                System.out.println("\nThe request contains mutually exclusive properties: "
                        + Arrays.toString(contains));
                System.out.println("There are no numbers with these properties.");
                return true;
            }
        }

        return false;
    }

    public static String reverseExclusiveProperty(String property) {
        String p = property.replace("-", "");

        for (int i = 0; i < mutualExclusiveProperties.length; i++) {
            String p1 = mutualExclusiveProperties[i][0].getName();
            String p2 = mutualExclusiveProperties[i][1].getName();

            if (p.equalsIgnoreCase(p1)) {
                return p2;
            }

            if (p.equalsIgnoreCase(p2)) {
                return p1;
            }
        }

        return property;
    }
}

enum Properties {
    EVEN("even"),
    ODD("odd"),
    BUZZ("buzz"),
    DUCK("duck"),
    PALINDROMIC("palindromic"),
    GAPFUL("gapful"),
    SPY("spy"),
    SQUARE("square"),
    SUNNY("sunny"),
    JUMPING("jumping"),
    HAPPY("happy"),
    SAD("sad");

    private final String name;

    Properties(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public boolean isProperty(long number) {
        switch (this) {
            case EVEN:
                return isEven(number);
            case ODD:
                return isOdd(number);
            case BUZZ:
                return isBuzz(number);
            case DUCK:
                return isDuck(number);
            case PALINDROMIC:
                return isPalindromic(number);
            case GAPFUL:
                return isGapful(number);
            case SPY:
                return isSpy(number);
            case SQUARE:
                return isSquare(number);
            case SUNNY:
                return isSunny(number);
            case JUMPING:
                return isJumping(number);
            case HAPPY:
                return isHappy(number);
            case SAD:
                return isSad(number);
            default:
                return false;
        }
    }

    private boolean isEven(long number) {
        return number % 2 == 0;
    }

    private boolean isOdd(long number) {
        return !isEven(number);
    }

    private boolean isBuzz(long number) {
        return (number >= 7) && (number % 7 == 0 || number % 10 == 7);
    }

    private boolean isDuck(long number) {
        for (long i = number; i > 0; i /= 10) {
            if (i % 10 == 0) {
                return true;
            }
        }

        return false;
    }

    private boolean isPalindromic(long number) {
        return number == Utility.reverseNumber(number);
    }

    private boolean isGapful(long number) {
        String strNumber = String.valueOf(number);

        if (strNumber.length() < 3) {
            return false;
        }

        char firstDigit = strNumber.charAt(0);
        char lastDigit = strNumber.charAt(strNumber.length() - 1);

        String strDivider = String.valueOf(new char[]{firstDigit, lastDigit});
        int divider = Integer.parseInt(strDivider);

        return number % divider == 0;
    }

    private boolean isSpy(long number) {
        long sum = 0;
        long product = 1;

        for (long i = number; i > 0; i /= 10) {
            long digit = i % 10;
            sum += digit;
            product *= digit;
        }

        return sum == product;
    }

    private boolean isSquare(long number) {
        double n = (long) Math.sqrt(number);
        return number == n * n;
    }

    private boolean isSunny(long number) {
        return isSquare(number + 1);
    }

    private boolean isJumping(long number) {
        char[] digits = String.valueOf(number).toCharArray();

        for (int i = 0; i < digits.length - 1; i++) {
            int current = Character.digit(digits[i], 10);
            int next = Character.digit(digits[i + 1], 10);

            if (current != next + 1 && current != next - 1) {
                return false;
            }
        }

        return true;
    }

    private boolean isHappy(long number) {
        long i = number;

        while (i != 1) {
            char[] digits = String.valueOf(i).toCharArray();
            i = 0;

            for (char d : digits) {
                i += Math.pow(Character.digit(d, 10), 2);
            }

            if (i == number || i == 4) {
                break;
            }
        }

        return i == 1;
    }

    private boolean isSad(long number) {
        return !isHappy(number);
    }
}

class Utility {
    public static long reverseNumber(long number) {
        long reverseNumber = 0;

        for (long i = number; i > 0; i /= 10) {
            reverseNumber *= 10;
            reverseNumber += (i % 10);
        }

        return reverseNumber;
    }

    public static void toArrayStringWithComma(StringBuilder sb, String str) {
        if (sb.length() > 0) {
            sb.append(", ").append(str);
            return;
        }

        sb.append(str);
    }

    public static String removeDigit(String str) {
        return str.replaceAll("\\d", "");
    }
}