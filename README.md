# Lab Four: Design Pattern Refactor for Making Change Lab


## First Pattern Implemented: Observer Pattern

### Define the observer interface `RegisterObserver`
This interface creates a contract for any class that wants to "observe" the changes made from the `Register`. It uses a single method `update()`

### How does this integrate with existing classes?
* Any class that wants to "observe" the `Register` class can implement this interface
* `PursePanel` is implementing `RegisterObserver` to receive updates whenever the `makeChange()` method is called in the `Register` class
* Uses the `addObserver()` and `notifyObservers()` methods

### Why is this better?
* Previously, the `RegisterPanel` was tightly tied to the `PursePanel`, meaning if you wanted to replace `PursePanel` with something else you would have to modify the `InputListener`
* Using the Observer Pattern, you can now plug in any number of `"Observers"` without actually modifying the code inside the `RegisterPanel` or `InputListener`, meaning you can add any number of panels that will receive the updated change from the `Register`
* Using the Observer Pattern also improves separation of concerns across these classes. e.g, previously the `InputListener` inside the `RegisterPanel` didn't just handle the input from the user, it also was responsible for updating the `PursePanel`, which caused unnecessary dependencies. Now Each class has its clear role and responsibilities


## Second Pattern Implemented: Strategy Pattern

### Define the strategy interface `ChangeStrategy` 
This interface creates a way for the register to have more than one algorithm to give change back. It uses a single method `makeChange()`

### How does this integrate with existing classes?
* The `Register` starts with a default strategy (e.g., `MinimalChangeStrategy`) but allows for the strategy to be updated at runtime. This enables flexibility in handling different scenarios within the system.
* It's very easy to change the strategy. Just update the strategy in the register constructor, and boom!

### Why is this better?
* `ChangeStrategy` interface allows the system to easily swap different change calculation algorithms **without modifying the `Register` class** or its core logic. New strategies can simply implement the `ChangeStrategy` interface and be passed to the `Register`.


* By delegating the change calculation logic to the strategy, the `Register` itself doesn't need to know the specific details of how the change is calculated. Instead, it can use different strategies dynamically by setting the desired strategy using `setChangeStrategy()` or in its constructor.


* Extensibility: Adding additional strategies (like rounding, maximizing coins, or custom behaviors) is as simple as implementing the `ChangeStrategy` interface.