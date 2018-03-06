package lt.swedbank.interestcalculator;
import java.util.Scanner;

/**
 * Created by p998feq on 2018.03.05.
 */
public class CompoundInterestCalculator {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        double interestRate;
        double[] interestRateArray;
        interestRateArray = new double[10];     // kaip saugot cikle nezinant kiek galima interest rate ivest?

        //Ok, here is a detailed explanation:
        // 1. First you create an empty array (size = 0)
        // 2. After every input (interestRate) you are expanding "interestRateArray"'s size by 1 and writing new value at the end of an array.
        //    Expanding can be done by copying old array content to new one like this:
        //    ...
        //    double[] newArray = new double[oldArray.length + 1];
        //    System.arraycopy(oldArray, 0, newArray, 0, oldArray.length);
        //    newArray[oldArray.length] = value;
        //    oldArray = newArray;
        //    ...

        System.out.print("Amount: ");
        double originalAmount = scanner.nextDouble();

        int interestRateCount = -1;
        do {
            System.out.print("Interest rate (%): ");
            interestRate = scanner.nextDouble();
            ++interestRateCount;
            interestRateArray[interestRateCount] = interestRate;

        }while (interestRate != 0); //Good use of do-while statement! Nice work!

        System.out.print("Period length (years): ");
        int periodLength = scanner.nextInt();
        System.out.print("Compound  frequency: ");
        String compoundFrequency = scanner.next();

        double[][] tempIntermediateInterestAmounts;
        tempIntermediateInterestAmounts = new double[interestRateCount][periodLength];
        double[][] intermediateInterestAmounts;
        intermediateInterestAmounts = new double[interestRateCount][periodLength];

        int pos;

        for (int j = 0; j < interestRateCount; j++){
            pos = 0;
            for (int i = 1; i <= periodLength; i++) {
                tempIntermediateInterestAmounts[j][pos] = getInterestAmount(originalAmount, interestRateArray[j], i, getCompoundFrequency(compoundFrequency));

                if (pos > 0)
                    intermediateInterestAmounts[j][pos] = tempIntermediateInterestAmounts[j][pos] - tempIntermediateInterestAmounts[j][pos - 1];
                else
                    intermediateInterestAmounts[j][pos] = tempIntermediateInterestAmounts[j][pos];
                pos++;
            }
        }

        for (double[] row : intermediateInterestAmounts) {
            for (double number : row) {
                System.out.print(String.format("%.2f", number) + " ");
                //Hint hint.. This is a better way to format it - "System.out.printf("%.2f\t", number);"
            }
            System.out.println();
        }
    }

    private static double getInterestAmount(double originalAmount, double interestRate, int periodLength, int compoundFrequency) {
        return originalAmount * Math.pow((1 + (interestRate / 100) / compoundFrequency), periodLength * compoundFrequency) - originalAmount;
    }

    private static int getCompoundFrequency(String compoundFrequencyString) {
        switch (compoundFrequencyString) {
            case "D":
                return 365;
            case "W":
                return 52;
            case "M":
                return 12;
            case "Q":
                return 4;
            case "H":
                return 2;
            case "Y":
                return 1;
            default:
                return 1;
        }
    }
}
