package MakingChange;

import java.math.BigDecimal;
import java.util.List;


// use a strategy pattern so we can use more than one change back option for future implementations.
public interface ChangeStrategy {
    Purse makeChange(BigDecimal amount, List<Denomination> denominations);
}
