package MakingChange;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Register {

    private List<Denomination> denominations;

    // initialize new Register object with a list of bills/coins
    public Register() {
        this.denominations = new ArrayList<>(Arrays.asList(
                new Denomination("One Hundred", 100.00, "Bill", "One Hundred.jpg"),
                new Denomination("Fifty", 50.00, "Bill", "Fifty.jpg"),
                new Denomination("Twenty", 20.00, "Bill", "Twenty.jpg"),
                new Denomination("Ten", 10.00, "Bill", "Ten.jpg"),
                new Denomination("Five", 5.00, "Bill", "Five.jpg"),
                new Denomination("One", 1.00, "Bill", "One.jpg"),
                new Denomination("Quarter", .25, "Coin", "Quarter.jpg"),
                new Denomination("Dime", .10, "Coin", "Dime.jpg"),
                new Denomination("Nickel", .05, "Coin", "Nickel.jpg"),
                new Denomination("Penny", .01, "Coin", "Penny.jpg")
        ));
    }

    /* Using the BigDecimal to get more accurate change back
       BigDecimal[] result is an array that holds the quotient
       at result[0] and the remainder at result[1]
    */
    public Purse makeChange(BigDecimal amount) {
        Purse change = new Purse();
        BigDecimal remainingChange = amount;

        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            return change;
        }


        BigDecimal precisionMax = BigDecimal.valueOf(.004);

        if (remainingChange.compareTo(precisionMax) < 0) {
            return change;
        }

        remainingChange = remainingChange.setScale(2, RoundingMode.HALF_UP);

        for (Denomination type : denominations) {

            BigDecimal denominationValue = BigDecimal.valueOf(type.amt());

            BigDecimal[] result = remainingChange.divideAndRemainder(denominationValue);
            int billsNeeded = result[0].intValue();
            BigDecimal changeBack = denominationValue.multiply(BigDecimal.valueOf(billsNeeded));

            if (billsNeeded > 0) {
                remainingChange = remainingChange.subtract(changeBack).setScale(2, RoundingMode.HALF_UP);
                change.add(type, billsNeeded);
            }

        }
        return change;
    }

    /*
        This is the main method for the console version
     */
    public static void main(String[] args) {
        Register register = new Register();
        double changeToReceive;
        do {
            Scanner scanner = new Scanner(System.in);

            try {
                System.out.println("Please enter the requested amount of change to receive back or 0 to quit: ");
                changeToReceive = scanner.nextDouble();
                Purse change = register.makeChange(BigDecimal.valueOf(changeToReceive));
                System.out.println(change.toString());
                System.out.println(change.getValue());
            }catch(Exception e) {
                System.out.println("Please enter a valid amount of change to receive back or 0 to quit: ");
                changeToReceive = scanner.nextDouble();
            }

        } while (changeToReceive != 0);
    }
}
