# COPADS
Concept of Parallel and Distributed System Projects
for **CS251 @RIT Course**

## Project Directory
### Project-1 Quadratic
#### 1. Description
A Multithreading program, that calculate the quadratic functions F(x) = ax<sup>2</sup> + bx + c, given the fixed coefficients a, b, and c for integer values of x: F(0), F(1), F(2) and so on using the **method of finite differences**.
#### 2. How to
* Go to **src** folder under **Quadratic** folder
* Compile the .java files by running `javac *.java`
* Usage: `$ java Quadratic <a> <b> <c> <max>` where a, b, c and max are integers and max value of x max is positive integer.
### Project-2 Goldbach
#### 1. Description
This is a multicore parallel program, using the [Parallel Java 2 Library](https://www.cs.rit.edu/~ark/pj2.shtml). The program find solutions using brute force search for all possibble [Goldbach numbers](https://en.wikipedia.org/wiki/Goldbach%27s_conjecture#Goldbach_number).
#### 2. How to 
* **NOTICE:** this project required parallel java 2 [@pj2](https://www.cs.rit.edu/~ark/pj2.shtml)
* Go to **src** folder under **Goldbach** folder
* Compile the .java files by running `java *.java`
* Usage: `$ java Goldbach <n>` where n is a positive even number greater than 4.
### Project-3 SixQueens
#### 1. Description
A two-player game where the players take turns placing chess queens on a 6x6 chess board. This network application uses TCP sockets for communication. The network application is a multi-session, multi-client application. The server can support any number of simultaneous sessions; each session consists of two players playing against each other.
#### 2. How to
* **NOTICE:** this project required parallel java 2 [@pj2](https://www.cs.rit.edu/~ark/pj2.shtml)
* Go to **src** folder under **SixQueens** folder
* Compile the .java files by running `java *.java`
* Usage Server: `java SixQueensServer <host> <port>` to host the server program
* Usage Players: `java SixQueens <host> <port> <playername>` to run the client programs
### Project-4 Whistleblower
#### 1. Description
The Whistleblower System consists of one Reporter program and multiple Leaker programs. Each Leaker program sends one message to the Reporter program to leak some controversial information. To protect the information while it is on the network, the message is encrypted using RSA. The Reporter's and Leaker's application protocol uses UDP at the Transport Layer.
#### 2. How to
* **NOTICE:** this project required parallel java 2 [@pj2](https://www.cs.rit.edu/~ark/pj2.shtml)
* Go to **src** folder under **Whilstleblower** folder
* Compile the .java files by running `java *.java`
* Usage Reporter: `java Reporter <rhost> <rport> <privatekeyfile>` to host a Reporter Server
* Usage Leaker: `java Leaker <rhost> <rport> <lhost> <lport> <publickeyfile> <message>` to send a private message to the Reporter Server

