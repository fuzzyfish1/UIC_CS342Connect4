cd C:\Users\Zain\Documents\UIC_CS342Connect4
cd Connect4-Server
call mvn clean install compile
start "" mvn exec:java
cd ../Connect4-Client
call mvn clean install compile
start "" mvn exec:java
start "" mvn exec:java