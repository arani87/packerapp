# Packer App
This application finds the optimum items to be packaged and fulfills the following conditions :
* Total weight is less or equal the capacity of the package
* Price is as high as possible
* In case of equal weight of 2 items, higher priced one is given priority.

This problem has been solved with the `greedy approximation` technique.
Where the choice of items are sorted based on price in descending order.
The algorithm then places the item in the package if it's weight + weight of already selected items less than or equal to the package capacity.
This ensures that the package always consists of highest priced items.

#### Why this algorithm is chosen?
Performance. For majority of the cases, this algorithm is good enough to find the optimal solution in a reasonable amount of time.
Of-course this can be debated. However being a firm believer of the 80/20 principle, 
in my opinion it is better to use an algorithm that sufficiently solves 80% of the cases, rather than premature optimization for the remaining 20% cases.
Also, the code is written in such a manner that it should be very easy to substitute with an alternate algorithm if need be.

## HOW-TO
> Windows
```bash
git clone https://github.com/arani87/packerapp.git
cd packerapp
mvnw.cmd clean install
java -jar target\optimalpacker-1.0.0-SNAPSHOT-jar-with-dependencies.jar
```
> Linux/Mac/Git Bash
```shell
git clone https://github.com/arani87/packerapp.git
cd packerapp
./mvnw clean install
java -jar target/optimalpacker-1.0.0-SNAPSHOT-jar-with-dependencies.jar
```