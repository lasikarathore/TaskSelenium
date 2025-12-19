# 🧪 Selenium Automation Assignment – SauceDemo

## 📌 Project Overview
This project is a Selenium WebDriver automation framework developed using **Java**, **TestNG**, and **Maven**.  
It automates the end-to-end flow of the **SauceDemo** application, covering login, product validation, sorting, cart operations, and checkout flow.

🔗 **Application URL:** https://www.saucedemo.com/

---

## 🛠️ Technology Stack
- **Language:** Java  
- **Automation Tool:** Selenium WebDriver  
- **Test Framework:** TestNG  
- **Build Tool:** Maven  
- **IDE:** IntelliJ IDEA  
- **Browser:** Google Chrome  

---

## 🔐 Test Credentials
Credentials are taken from the application login page:

- **Username:** `standard_user`  
- **Password:** `secret_sauce`

---

## 📂 Project Structure

The project follows a standard **Maven directory structure**.  
Test automation code is maintained under the `src/test/java` directory, while application-related configuration remains under `src/main/java`.

```text
Task
│
├── .mvn                     
├── pom.xml                  
├── README.md               
│
├── src
│   ├── main
│   │   └── java
│   │       └── com.example.Task   
│   │
│   └── test
│       └── java
│           └── tests             
│               └── SauceDemoTest.java  


```
## ✅ Automated Test Scenarios

### 🔹 Task 1: Login Automation
- Open browser and navigate to SauceDemo URL  
- Enter valid username and password  
- Click Login  
- Validate successful login  

**Validations:**
- URL contains `inventory.html`  
- Products page is displayed  

---

### 🔹 Task 2: Product Page Validation
- Validate Products title  
- Validate product list is displayed  
- Validate each product has:
  - Name  
  - Price  
  - Add to Cart button  
- Validate cart icon visibility  

---

### 🔹 Task 3: Sorting Validation – Name (A to Z)
- Select “Name (A to Z)” from sorting dropdown  
- Capture product names from UI  
- Sort product names using Java logic  
- Compare UI order with sorted list  

**Validation:**  
- Products are displayed in alphabetical order  

---

### 🔹 Task 4: Sorting Validation – Price (High to Low)
- Select “Price (high to low)” from dropdown  
- Capture product prices  
- Convert prices to numeric values  
- Sort prices in descending order  
- Compare with UI prices  

**Validation:**  
- Prices are displayed from high to low  

---

### 🔹 Task 5: Add Product to Cart
- Add a product to cart  
- Validate button text changes to “Remove”  
- Validate cart badge count  
- Navigate to cart  
- Validate selected product is present  

---

### 🔹 Task 6: Checkout Flow
- Click Checkout  
- Enter First Name, Last Name, Postal Code  
- Continue to overview page  
- Finish checkout  
- Validate order confirmation message  

---

## ▶️ How to Run the Project

### Prerequisites
- Java JDK 11 or higher  
- Maven installed  
- Google Chrome installed  

### Steps to Run
1. Clone the repository:
   ```bash
   git clone https://github.com/<your-username>/Selenium-Task.git
   Open the project in IntelliJ IDEA
   
2. Open the project in IntelliJ IDEA
   
3. Allow Maven to download all dependencies

4. Navigate to: src/test/java/tests/SauceDemoTest.java
   
5. Right-click on the test class and select Run.

### Key Features
- Uses WebDriverWait for synchronization
- Avoids Thread.sleep
- Proper assertions using TestNG
- Clean and readable code structure
- End-to-end automation coverage

### Notes
- Spring Boot (if used) is only for project initialization and dependency management
- No backend or server setup is required
- Tests are executed independently using TestNG

### Author 
- Lasika Rathore

