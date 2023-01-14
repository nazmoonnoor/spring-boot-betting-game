
# Betting API

API offers endpoint for player to send a bet and whole number from 1 to 100 to a server. Server generates random whole number from 1 to 100 and if players number is greater, calculates win and sends it back to client.



## Tech

- Java 17
- Spring boot
- Maven
- REST API


## Features

Following features and constrains are implemented.

- Win depends on chance = bet * (99 / (100 - number)), as an example if player selected number 50 and bet 40.5, win would be 80,19


## Demo

Test requests file has been attached in root folder
```bash
test.http
```

Unit Test that is going to play 1 million rounds in parallel in 24 threads and will calculate how much money player is recieving back (RTP)
```bash
GameControllerTests.oneMillionRoundRTPCalculation
```