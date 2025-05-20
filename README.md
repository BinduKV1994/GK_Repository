## CustomerCashReturn

A Java-based utility that calculates the optimal change (in bills and coins) to return to a customer after a purchase using `BigDecimal` for accuracy in monetary values.

---

## Features

- Accepts purchase amount and payment amount via console
- Calculates and displays:
  - Number of bills and coins required for change
  - Two output modes:
    - Including 0.01€ and 0.02€ coins
    - Excluding 0.01€ and 0.02€ coins
- Handles edge cases:
  - Exact payment  
  - Insufficient payment  
  - Invalid input (e.g: non-numeric)

---

## Sample Input

```
The customer bought items with total value of (€):
16.09

The customer pays with (€):
50
```

---

## Sample Output

```
The cashier has to return to the customer: 33,91€

Step 1: Output with 0,02 and 0,01 cents 

BILLS:
500 Euro bills: 0  
200 Euro bills: 0  
100 Euro bills: 0  
50 Euro bills: 0  
20 Euro bills: 1  
10 Euro bills: 1  
5 Euro bills: 0  

COINS:
2 Euro coins: 1  
1 Euro coins: 1  
0,50 Euro coins: 1  
0,20 Euro coins: 2  
0,10 Euro coins: 0  
0,05 Euro coins: 0  
0,02 Euro coins: 0  
0,01 Euro coins: 1  

Step 2: Output without 0,02 and 0,01 cents 

BILLS:
500 Euro bills: 0  
200 Euro bills: 0  
100 Euro bills: 0  
50 Euro bills: 0  
20 Euro bills: 1  
10 Euro bills: 1  
5 Euro bills: 0  

COINS:
2 Euro coins: 1  
1 Euro coins: 1  
0,50 Euro coins: 1  
0,20 Euro coins: 2  
0,10 Euro coins: 0  
0,05 Euro coins: 0  

Unable to return this amount due to cents unavailability: 0,01€
```
