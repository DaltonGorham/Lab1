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
                new Denomination("One Hundred", 100.00, "Bill", "./images/One Hundred.jpg"),
                new Denomination("Fifty", 50.00, "Bill", "./images/Fifty.jpg"),
                new Denomination("Twenty", 20.00, "Bill", "./images/Twenty.jpg"),
                new Denomination("Ten", 10.00, "Bill", "./images/Ten.jpg"),
                new Denomination("Five", 5.00, "Bill", "./images/Five.jpg"),
                new Denomination("One", 1.00, "Bill", "./images/One.jpg"),
                new Denomination("Quarter", .25, "Coin", "./images/Quarter.jpg"),
                new Denomination("Dime", .10, "Coin", "./images/Dime.jgp"),
                new Denomination("Nickel", .05, "Coin", "./images/Nickel.jpg"),
                new Denomination("Penny", .01, "Coin", "./images/Penny.jpg")
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


        BigDecimal precisionMax = BigDecimal.valueOf(.00001);

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
                remainingChange = remainingChange.subtract(changeBack);
                change.add(type, billsNeeded);
            }


        }
        return change;
    }

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
