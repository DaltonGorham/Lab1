package MakingChange;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;


// define the minimal change strategy which is used to give the least amount of bills and coins back
public class MinimalChangeStrategy implements ChangeStrategy {
    @Override
    public Purse makeChange(BigDecimal amount, List<Denomination> denominations) {
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
}
