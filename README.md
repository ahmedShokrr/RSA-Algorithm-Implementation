# RSA Algorithm Implementation

## Overview

This project is part of the Discrete Mathematics course. It implements the RSA encryption algorithm in Java. The application allows users to encrypt and decrypt text files using RSA encryption.

## Features

- **Encrypt File**: Encrypts a text file and saves the encrypted message to a new file.
- **Decrypt File**: Decrypts an encrypted file and saves the decrypted message to a new file.
- **Prime Number Generation**: Generates large prime numbers required for RSA encryption.
- **Validation**: Ensures that the decrypted text matches the original text.

## Requirements

- Java Development Kit (JDK) 8 or later
- A text editor or Integrated Development Environment (IDE) for Java development

## Installation

1. **Clone the repository**:
    ```bash
    git clone https://github.com/ahmedShokrr/RSA-Algorithm-Implementation.git
    ```
2. **Navigate to the project directory**:
    ```bash
    cd rsa-algorithm
    ```

## Usage

1. **Compile the program**:
    ```bash
    javac -d bin src/projecttt/*.java
    ```

2. **Run the program**:
    ```bash
    java -cp bin projecttt.Main
    ```

3. **Encrypt a file**:
    - Run the program and select option (1) Encrypt file.
    - Enter the path of the file to be encrypted.
    - The encrypted message will be saved to `Ecrypted_File.txt` on your Desktop.

4. **Decrypt a file**:
    - Run the program and select option (2) Decrypt file.
    - Enter the path of the encrypted file.
    - The decrypted message will be saved to `Decrypted_File.txt` on your Desktop.

## Code Structure

- `RSA_Algorithm.java`: Contains the RSA algorithm implementation.
  - **Constructors**:
    - `RSA_Algorithm(BigInteger p, BigInteger q)`: Initializes the algorithm with two prime numbers.
    - `RSA_Algorithm(List<String> list)`: Initializes the algorithm with an encrypted message.
  - **Methods**:
    - `encryption(String text)`: Encrypts a given text.
    - `decryption()`: Decrypts the encrypted message.
    - `gcd(BigInteger e, BigInteger k)`: Calculates the greatest common divisor.
    - `e_Generator()`: Generates the public key exponent.
    - `encryptionTest(char input)`: Tests encryption of a single character.
    - `decryptionTest(BigInteger output)`: Tests decryption of a single BigInteger.

- `Main.java`: Contains the main method and file handling logic.
  - **Methods**:
    - `main(String[] args)`: The entry point of the application.
    - `encryptFile(RSA_Algorithm rsa, Scanner console)`: Encrypts the contents of a file.
    - `decryptFile(Scanner console)`: Decrypts the contents of a file.
    - `primeNumberGenerator()`: Generates a large prime number.
    - `isPrime(BigInteger n)`: Checks if a number is prime.
    - `millerTest(BigInteger n)`: Performs the Miller-Rabin primality test.



## License

This project is licensed under the MIT License.


