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