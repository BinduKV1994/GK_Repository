import java.util.*;
import java.math.BigDecimal;

public class CustomerCashReturn {

    //Using BigDecimal over double due to formatting and rounding issues
    //Load the available bills, coins, coins(without 0,02 & 0,01) separately
    public static BigDecimal[] bdBillsArray= {
            new BigDecimal("500"),
            new BigDecimal("200"),
            new BigDecimal("100"),
            new BigDecimal("50"),
            new BigDecimal("20"),
            new BigDecimal("10"),
            new BigDecimal("5"),
    };

    public static BigDecimal[] bdCoinsArray= {
            new BigDecimal("2"),
            new BigDecimal("1"),
            new BigDecimal("0.50"),
            new BigDecimal("0.20"),
            new BigDecimal("0.10"),
            new BigDecimal("0.05"),
            new BigDecimal("0.02"),
            new BigDecimal("0.01")
    };

    public static BigDecimal[] bdCoinsWithoutCentsArray = {
            new BigDecimal("2"),
            new BigDecimal("1"),
            new BigDecimal("0.50"),
            new BigDecimal("0.20"),
            new BigDecimal("0.10"),
            new BigDecimal("0.05"),
    };

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in); //console input
            System.out.println("The customer bought items with total value of (€)");
            BigDecimal totalBillAmount = scanner.nextBigDecimal();
            System.out.println("The customer pays with (€)");
            BigDecimal givenAmount = scanner.nextBigDecimal();
            BigDecimal returnAmount = givenAmount.subtract(totalBillAmount);

            //if customer gives correct or less amount
            if (returnAmount.compareTo(BigDecimal.ZERO) == 0) {
                System.out.println("Customer gave the exact amount!");
            } else if (returnAmount.compareTo(BigDecimal.ZERO) < 0) {
                System.out.println("Amount given is not sufficient!");
            } else {
                System.out.println("The cashier has to return to the customer: " + getFormattedCoin(returnAmount) + "€");
                System.out.println("\nStep 1: Output with 0,02 and 0,01 cents ");

                //Method to calculate the num of bills and coins
                calculateBillNum(bdCoinsArray, returnAmount);

                System.out.println("\nStep 2: Output without 0,02 and 0,01 cents ");

                //Method to calculate the num of bills and coins without 0,02,0,01
                calculateBillNum(bdCoinsWithoutCentsArray, returnAmount);
            }
        }
        catch (InputMismatchException exception){
            System.out.println("Invalid Input! Please provide valid number");
        }
    }

    /**
     * Calculates the number of bills & coins to be returned and adding them into Linked Hashmap
     * @param bdCoins Array of coins / coins without 0,02 & 0,01 €
     * @param returnAmount Amount to be returned to the customer
     */
    private static void calculateBillNum(BigDecimal[] bdCoins, BigDecimal returnAmount) {
        Map<BigDecimal, Integer> mapForBills = new LinkedHashMap<>();
        Map<BigDecimal, Integer> mapForCoins = new LinkedHashMap<>();
        for (BigDecimal billDenomination : bdBillsArray) {
            if (billDenomination.compareTo(returnAmount) <= 0) {
                BigDecimal[] resultArray = returnAmount.divideAndRemainder(billDenomination);//method that returns both count and remainder
                int count = resultArray[0].intValue();
                returnAmount = resultArray[1];
                mapForBills.put(billDenomination, count);
            }
            else {
                mapForBills.put(billDenomination, 0);
            }
        }

        for (BigDecimal coinDenomination : bdCoins) {
            if (coinDenomination.compareTo(returnAmount) <= 0) {
                BigDecimal[] resultArrayCoins = returnAmount.divideAndRemainder(coinDenomination);
                int count = resultArrayCoins[0].intValue();
                returnAmount = resultArrayCoins[1];
                mapForCoins.put(coinDenomination, count);
            }
            else {
                mapForCoins.put(coinDenomination, 0);
            }
        }
        printResult(mapForBills,mapForCoins,returnAmount);
    }

    /**
     * Prints the number of bills and coins to be returned to customer
     * @param mapForBills Linked Hashmap for number of bills count
     * @param mapForCoins Linked Hashmap for number of coins count
     * @param returnAmount Amount to be returned to the customer in case of 0,02 & 0,01 € unavailability
     */
    private static void printResult(Map<BigDecimal, Integer> mapForBills, Map<BigDecimal, Integer> mapForCoins, BigDecimal returnAmount) {
        System.out.println("BILLS:");
        for (Map.Entry<BigDecimal,Integer> billEntry : mapForBills.entrySet()){
            System.out.println(billEntry.getKey()+ " Euro bills: "+ billEntry.getValue());
        }

        System.out.println("COINS:");
        for (Map.Entry<BigDecimal,Integer> coinEntry : mapForCoins.entrySet()){
            System.out.println(getFormattedCoin(coinEntry.getKey())+ " Euro coins: "+ coinEntry.getValue());
        }

        if (returnAmount.compareTo(BigDecimal.ZERO) > 0){
            System.out.println("Unable to return this amount due to cents unavailability: "+ getFormattedCoin(returnAmount)+"€");
        }
    }

    /**
     *
     * @param coinEntry Coin amount to be formatted
     * @return formatted amount
     */
    private static String getFormattedCoin(BigDecimal coinEntry) {
        return coinEntry.toPlainString().replace('.', ',');
    }
}