package MakingChange;
import java.util.*;

public class Purse {

    private HashMap<Denomination, Integer> cash;

    public Purse() {
        cash = new HashMap<>();
    }

    public void add(Denomination type, int amount) {
        cash.put(type, amount);
    }

    public double remove(Denomination type, int amount) {
        if (cash.isEmpty()) {
            return 0;
        }

        int currentAmount = cash.get(type);
        cash.put(type, (currentAmount - amount));
        return ((double) type.amt() * amount);
    }

    public double getValue(){
        double totalCash = 0;
        for (Denomination type : cash.keySet()) {
            totalCash += type.amt() * cash.get(type);
        }
        return totalCash;
    }

        /*
            This method is for the console version
            It creates the output
         */
    public String toString() {
        StringBuilder string = new StringBuilder();

        if (cash.isEmpty()) {
            string.append("Empty Purse");
            return string.toString();
        }
        // create a list of the entries in the map, so they can be sorted in descending order
        List<HashMap.Entry<Denomination, Integer>> sortedCash = new ArrayList<>(cash.entrySet());
        sortedCash.sort((bill1, bill2) -> Double.compare(bill2.getKey().amt(), bill1.getKey().amt()));

        for (HashMap.Entry<Denomination, Integer> entry : sortedCash) {
            if (entry.getKey().form().equals("Bill")){
                string.append("\n").append(entry.getValue()).append(" ").append(entry.getKey().name()).append("-Dollar Note");
            }
            else {
                string.append("\n").append(entry.getValue()).append(" ").append(entry.getKey().name());
            }
        }
        return string.toString();

    }

    public boolean isEmpty() {
        return cash.isEmpty();
    }

    public Set<Map.Entry<Denomination, Integer>> entrySet() {
        return cash.entrySet();
    }
}
