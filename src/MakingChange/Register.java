package MakingChange;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Register {

    private List<Denomination> denominations;

    public Register() {
        this.denominations = new ArrayList<>(Arrays.asList(
                new Denomination("One Hundred", 100.00, "Bill", "./images/100_dollar.jpg"),
                new Denomination("Fifty", 50.00, "Bill", "./images/50_dollar.jpg"),
                new Denomination("Twenty", 20.00, "Bill", "./images/20_dollar.jpg"),
                new Denomination("Ten", 10.00, "Bill", "./images/10_dollar.jpg"),
                new Denomination("Five", 5.00, "Bill", "./images/5_dollar.jpg"),
                new Denomination("One", 1.00, "Bill", "./images/1_dollar.jpg"),
                new Denomination("Quarter", .25, "Coin", "./images/quarter_coin.jpg"),
                new Denomination("Dime", .10, "Coin", "./images/dime_coin.jgp"),
                new Denomination("Nickel", .05, "Coin", "./images/nickel_coin.jpg"),
                new Denomination("Penny", .01, "Coin", "./images/penny_coin.jgp")
        ));
    }
}
