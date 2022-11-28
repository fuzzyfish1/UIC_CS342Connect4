cd C:\Users\Zain\Documents\UIC_CS342Connect4
call mvn clean compile
cd Connect4-Server
start "" mvn exec:java
cd ../Connect4-Client
start "" mvn exec:java
start "" mvn exec:java
start "" mvn exec:java