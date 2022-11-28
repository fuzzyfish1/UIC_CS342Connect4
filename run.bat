cd C:\Users\Zain\Documents\UIC_CS342Connect4
echo "temp I hate windows bat shit"
mvn clean
echo "completed clean"
mvn compile
echo "temp I hate windows bat shit"
cd Connect4-Server
mvn exec:java
cd ../Connect4-Client
mvn exec:java
mvn exec:java
mvn exec:java