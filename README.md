# Packer App
This application finds the optimum items to be packaged and fulfills the following conditions :
* Total weight is less or equal the capacity of the package
* Price is as high as possible
* In case of equal weight of 2 items, higher priced one is given priority.

This problem has been solved with the `greedy approximation` technique.
Where the choice of items are sorted based on price in descending order.
The algorithm then places the item in the package if it's weight + weight of already selected items less than or equal to the package capacity.
This ensures that the package always consists of highest priced items.

## HOW-TO
> Windows
```bash
git clone 
mvnw.cmd clean install
java -jar target\optimalpacker-1.0.0-SNAPSHOT-jar-with-dependencies.jar
```
> Linux/Mac/Git Bash
```shell
git clone 
./mvnw clean install
java -jar target/optimalpacker-1.0.0-SNAPSHOT-jar-with-dependencies.jar
```