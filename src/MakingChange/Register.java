package MakingChange;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Register {

    private List<Denomination> denominations;
    private RegisterObserver observer;
    private ChangeStrategy changeStrategy;

    // initialize new Register object with a list of bills/coins
    public Register() {
        // initialize register with the minimal change strategy
        this.setChangeStrategy(new MinimalChangeStrategy());
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


    public Purse makeChange(BigDecimal amount) {
        if (this.changeStrategy == null) {
            System.out.println("Change Strategy must be set before making change.");
        }
        // use the specified strategy to get the change back
        Purse change = changeStrategy.makeChange(amount, denominations);
        // notify the purse panel and return the change back
        notifyObservers(change);
        return change;
    }

    // use to set the change strategy that can be easily changed
    public void setChangeStrategy(ChangeStrategy changeStrategy) {
        this.changeStrategy = changeStrategy;
    }


    public void addObserver(RegisterObserver observer) {
        this.observer = observer;
    }

    private void notifyObservers(Purse purse) {
        observer.update(purse);
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
