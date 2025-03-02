# UI Automation Test

This project implements automation tests for the [Practice Software Testing](https://practicesoftwaretesting.com/) website using **Selenium**, **Playwright**, and **RestAssured for API testing** with Java.

## 📌 Features
- Searches for the **"Pliers"** product on the platform.
- Verifies that the displayed results contain only relevant products.
- Uses **Selenium WebDriver** and **Playwright** to compare performance.
- Fetches and validates Pokémon details using **REST API testing** with RestAssured.

---

## 🛠️ Technologies Used
- **Java 11+**
- **Selenium WebDriver**
- **Playwright for Java**
- **RestAssured** (for API testing)
- **JUnit 5** (for Selenium and API testing)
- **Allure** (for test reporting)
- **Maven** (dependency management)

---

## 🚀 Installation and Setup

### **1️⃣ Prerequisites**
Make sure you have:
- **Java 11+** installed (`java -version` to check)
- **Maven** installed (`mvn -version` to check)
- **ChromeDriver** compatible with your Chrome version ([Download here](https://sites.google.com/chromium.org/driver/))
- **Playwright for Java** installed via Maven
- **RestAssured for API testing**

### **2️⃣ Clone the Repository**
```sh
 git clone https://github.com/your-repository/tools-shop-automation.git
 cd tools-shop-automation
```

### **3️⃣ Add Dependencies to `pom.xml`**
If not present, add the following dependencies to **pom.xml**:

```xml
<dependencies>
    <!-- Selenium -->
    <dependency>
        <groupId>org.seleniumhq.selenium</groupId>
        <artifactId>selenium-java</artifactId>
        <version>4.8.0</version>
    </dependency>

    <!-- Playwright -->
    <dependency>
        <groupId>com.microsoft.playwright</groupId>
        <artifactId>playwright</artifactId>
        <version>1.42.0</version>
    </dependency>

    <!-- RestAssured for API Testing -->
    <dependency>
        <groupId>io.rest-assured</groupId>
        <artifactId>rest-assured</artifactId>
        <version>5.0.1</version>
    </dependency>

    <!-- JUnit 5 -->
    <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter-api</artifactId>
        <version>5.9.0</version>
        <scope>test</scope>
    </dependency>

    <!-- Allure for Test Reporting -->
    <dependency>
        <groupId>io.qameta.allure</groupId>
        <artifactId>allure-junit5</artifactId>
        <version>2.14.0</version>
    </dependency>
</dependencies>
```

### **4️⃣ Configure ChromeDriver**
Ensure the **ChromeDriver** path is correct in the Java code:
```java
System.setProperty("webdriver.chrome.driver", "G:\\projects\\pokemon_app\\pokemonapi\\chromedriver-win64\\chromedriver.exe");
```
Modify the path according to your operating system.

---

## 📂 Project Structure
```
/tools-shop-automation
│── src/main/java/uiAutomation
│   ├── ToolsShopAutomationTest.java  # Test with Selenium and Playwright
│── src/main/java/stepDefinition
│   ├── PokemonSteps.java  # API Test with RestAssured
│── pom.xml  # Maven dependencies
│── README.md  # This file
```

---

## 🧪 Test Implementation

### **Selenium & Playwright Tests**
- Opens the browser and navigates to the website.
- Searches for the "Pliers" product.
- Waits for the search results to load.
- Verifies that all displayed products contain "Pliers".
- Closes the browser.

### **Pokémon API Test with RestAssured**
- Fetches all Pokémon from the **PokéAPI**.
- Selects a random Pokémon and retrieves its details.
- Extracts one of its abilities and fetches related details.
- Validates that the Pokémon appears in the ability's list.
- Uses **Allure Reporting** for enhanced test logs.

---

## 🎯 Running the Tests

### **Using Maven**
```sh
mvn test
```

### **Run the Test Manually**
If you want to execute the Java class directly:
```sh
javac -cp .:selenium-java.jar:playwright-java.jar src/main/java/uiAutomation/ToolsShopAutomationTest.java
java -cp .:selenium-java.jar:playwright-java.jar uiAutomation.ToolsShopAutomationTest
```

For API testing:
```sh
mvn test -Dtest=PokemonSteps
```

---

## ✅ Expected Output
```
Running Selenium Test...
🔍 Selenium - Products found:
📌 Pliers 1
📌 Pliers 2
✅ Selenium Test Passed: Only 'Pliers' are present in the results.

Running Playwright Test...
🔍 Playwright - Products found:
📌 Pliers 1
📌 Pliers 2
✅ Playwright Test Passed: Only 'Pliers' are present in the results.

Running API Test...
📌 Selected Pokémon: Pikachu
🔹 Selected Ability: Static
✅ API Test Passed: Pikachu is listed under the Static ability.
```
If the test fails, you will see a `❌` message.

---

## 📖 Notes
- Ensure **ChromeDriver is up to date** to avoid compatibility issues.
- Playwright has better support for **parallel testing**.
- Use `Thread.sleep(2000);` only when necessary; prefer `wait.until(...)`.
- API tests are performed using **RestAssured** and validated with **JUnit 5**.

---

## 📩 Contact
If you have issues or suggestions, open an **Issue** on GitHub! 🚀

