Here is the complete banking-focused exercise series, fully grounded in digital banking scenarios from account 
creation all the way through to a production-style simulation.---

# Digital Internet Banking System — Java Exercise Series

---

## Section 1 — Core Banking Storage
*Arrays and ArrayLists to store customers, accounts, and balances*

---

### Exercise 1.1 — Fixed-Size Account Register

**Banking scenario:** A small credit union opens with exactly 10 account slots. They need to register customers and 
track balances in a fixed-size ledger.

**Objective:** Practise creating, populating, and reading from a fixed-size array in a banking context.

**Problem description:**
Create a banking register using two parallel arrays: `String[] accountHolders` (names) and `double[] balances` (amounts), 
each of size 10. Write methods to:
1. `register(index, name, balance)` — store a new account
2. `printLedger()` — print all accounts with their index and balance
3. `totalDeposits()` — return the sum of all balances
4. `highestBalance()` — return the name and balance of the wealthiest account

**Required data structures:** Arrays

**Expected output:**
```
=== Credit Union Ledger ===
[0] Alice Marais       $12,500.00
[1] Ben Okafor         $4,200.00
[2] Carmen Liu         $88,000.00
...
Total deposits held:   $210,350.00
Highest balance:       Carmen Liu — $88,000.00
```

---

### Exercise 1.2 — Dynamic Customer Onboarding List

**Banking scenario:** A new digital bank is onboarding customers. The number of signups is unknown, so the system must 
grow dynamically as new customers register.

**Objective:** Understand why `ArrayList` is superior to arrays when the data size is not fixed at compile time.

**Problem description:**
Create a `CustomerOnboarding` class using `ArrayList<String>` to store customer names. Implement:
1. `addCustomer(name)` — add a new customer
2. `removeCustomer(name)` — remove a customer who cancelled their application
3. `isRegistered(name)` — check if a name is already in the list
4. `printQueue()` — display all pending customers with their position number
5. After completing the exercise, write a short comment explaining why an array would have been problematic here.

**Required data structures:** ArrayList, performance trade-offs (arrays vs ArrayList)

**Expected output:**
```
Onboarding queue:
1. Alice Marais
2. Ben Okafor
3. Carmen Liu

Removed: Ben Okafor

Updated queue:
1. Alice Marais
2. Carmen Liu

"Alice Marais" registered: true
"Ben Okafor" registered: false
```

---

### Exercise 1.3 — Branch ATM Grid

**Banking scenario:** A bank operates a network of branches, each with multiple ATMs. The bank needs a 2D data structure 
to record the cash balance loaded into each ATM across all branches.

**Objective:** Use a 2D array to model a real-world banking grid.

**Problem description:**
Model 4 branches, each with 3 ATMs, using a `double[][] atmCash` 2D array. Write methods to:
1. `loadCash(branch, atm, amount)` — set the cash for a specific ATM
2. `printGrid()` — display the grid as a branch × ATM table
3. `branchTotal(branch)` — return total cash across all ATMs in a branch
4. `findLowCash(threshold)` — list all ATMs below a given cash threshold

**Required data structures:** 2D arrays

**Expected output:**
```
ATM Cash Grid (Branch × ATM):
           ATM-1       ATM-2       ATM-3
Branch A:  $20,000     $15,000     $5,000
Branch B:  $30,000     $0          $18,000
Branch C:  $12,000     $22,000     $9,000
Branch D:  $8,000      $16,000     $25,000

Branch A total: $40,000
Low cash alerts (< $10,000): Branch A ATM-3, Branch B ATM-2, Branch C ATM-1, Branch D ATM-1
```

---

## Section 2 — Dynamic Banking Records
*Linked lists for transaction histories and activity logs*

---

### Exercise 2.1 — Transaction History Log (Singly Linked List)

**Banking scenario:** Every time a customer makes a deposit or withdrawal, the bank appends it to their personal 
transaction history. New entries are always added to the front (most recent first).

**Objective:** Implement a singly linked list from scratch to represent a customer's transaction log.

**Problem description:**
Build a `TransactionLog` class backed by a singly linked list. Each node stores: transaction type (`DEPOSIT`/`WITHDRAWAL`), 
amount, and date string. Implement:
1. `addTransaction(type, amount, date)` — add at the head (most recent first)
2. `printHistory()` — print all transactions in order
3. `countTransactions()` — return total number of entries
4. `totalDeposited()` — sum only DEPOSIT entries

**Required data structures:** Singly linked list, node/head pointer traversal

**Expected output:**
```
Transaction history for: Alice Marais
[1] DEPOSIT    $500.00   2025-03-15
[2] WITHDRAWAL $120.00   2025-03-10
[3] DEPOSIT    $2,000.00 2025-02-28
[4] DEPOSIT    $1,500.00 2025-02-01

Total entries: 4
Total deposited: $4,000.00
```

---

### Exercise 2.2 — Account Statement Navigator (Doubly Linked List)

**Banking scenario:** A bank's online portal allows customers to scroll forward and backward through their monthly account 
statements, one month at a time.

**Objective:** Use a doubly linked list to model bidirectional navigation through monthly bank statements.

**Problem description:**
Build a `StatementNavigator` class where each node represents one monthly statement (month name + closing balance). Implement:
1. `addStatement(month, closingBalance)` — append a new monthly statement to the end
2. `next()` — move to the next (more recent) month
3. `previous()` — move to the previous month
4. `current()` — display the currently viewed statement
5. `printAll()` — print all statements from oldest to newest

**Required data structures:** Doubly linked list, prev/next pointers, cursor node

**Expected output:**
```
Viewing: January 2025 | Closing balance: $3,200.00
→ next()
Viewing: February 2025 | Closing balance: $4,800.00
→ next()
Viewing: March 2025 | Closing balance: $3,950.00
→ previous()
Viewing: February 2025 | Closing balance: $4,800.00

Full statement history:
  January 2025   $3,200.00
  February 2025  $4,800.00
  March 2025     $3,950.00
```

---

## Section 3 — Banking Operations Processing
*Stacks for undo functionality, Queues for payment processing*

---

### Exercise 3.1 — Undo Last Transaction (Stack)

**Banking scenario:** A bank teller application needs an "undo" button. When a teller accidentally posts the wrong transaction, 
they can undo it — restoring the account to the previous state.

**Objective:** Apply Stack (LIFO) behaviour to implement a transaction undo system.

**Problem description:**
Create a `BankAccount` class with a current balance and a `Stack<Double>` of previous balances. Implement:
1. `deposit(amount)` — push the current balance onto the stack, then add amount
2. `withdraw(amount)` — push current balance, then deduct amount (reject if insufficient funds)
3. `undoLastTransaction()` — pop the previous balance from the stack to restore it
4. `getBalance()` — print the current balance
5. Attempt to undo when no history exists and handle it gracefully.

**Required data structures:** Stack (LIFO), `java.util.Stack`, push/pop

**Expected output:**
```
Balance: $1,000.00
Deposit $500 → Balance: $1,500.00
Withdraw $200 → Balance: $1,300.00
UNDO → Balance restored to: $1,500.00
UNDO → Balance restored to: $1,000.00
UNDO → Error: No transaction history to undo.
```

---

### Exercise 3.2 — Payment Processing Queue

**Banking scenario:** A bank receives hundreds of pending inter-bank payment requests throughout the day. They must be 
processed strictly in the order they arrived — first in, first out.

**Objective:** Model a real-time payment processing pipeline using a Queue (FIFO).

**Problem description:**
Create a `PaymentQueue` system. Each payment has a sender name, recipient name, and amount. Use a `Queue<Payment>` 
(via `LinkedList`) to:
1. `submitPayment(sender, recipient, amount)` — enqueue a payment
2. `processNext()` — dequeue and "process" the next payment (print a confirmation)
3. `processAll()` — process every queued payment
4. `pendingCount()` — return how many are still waiting

**Required data structures:** Queue (FIFO), `LinkedList` as Queue, enqueue/dequeue

**Expected output:**
```
Submitted: Alice → Bob $250.00
Submitted: Bob → Carmen $1,000.00
Submitted: Carmen → Alice $75.00

Pending payments: 3

Processing: Alice → Bob $250.00         [CONFIRMED]
Processing: Bob → Carmen $1,000.00      [CONFIRMED]
Processing: Carmen → Alice $75.00       [CONFIRMED]

Pending payments: 0
```

---

## Section 4 — Banking Data Retrieval
*HashMap, LinkedHashMap, and TreeMap for efficient account storage*

---

### Exercise 4.1 — Account Lookup by Account Number (HashMap)

**Banking scenario:** When a customer calls the bank, the support agent types in an account number and needs to instantly 
retrieve the customer profile. Speed is critical — there can be millions of accounts.

**Objective:** Use a `HashMap` to achieve O(1) average-time account lookups by account number.

**Problem description:**
Build an `AccountDirectory` class using `HashMap<String, CustomerProfile>`. Each `CustomerProfile` stores: name, account 
type, and balance. Implement:
1. `openAccount(accountNum, name, type, balance)` — put into the map
2. `lookup(accountNum)` — get and display the profile
3. `closeAccount(accountNum)` — remove from the map
4. `totalCustomers()` — return the number of active accounts

**Required data structures:** HashMap, O(1) lookup

**Expected output:**
```
Account opened: ACC-1001 | Alice Marais | SAVINGS | $5,000.00
Account opened: ACC-1002 | Ben Okafor   | CURRENT | $12,000.00

Lookup ACC-1001:
  Name:    Alice Marais
  Type:    SAVINGS
  Balance: $5,000.00

Closed: ACC-1002
Total active accounts: 1
```

---

### Exercise 4.2 — Chronological Activity Audit Log (LinkedHashMap)

**Banking scenario:** For compliance and fraud detection, the bank must maintain an audit trail of every login event, 
preserving the exact order in which they occurred.

**Objective:** Use `LinkedHashMap` to store login events keyed by session ID while preserving insertion (chronological) order.

**Problem description:**
Build a `LoginAuditLog` using `LinkedHashMap<String, LoginEvent>`. Each `LoginEvent` stores: account number, timestamp, 
IP address, and status (`SUCCESS`/`FAILED`). Implement:
1. `logEvent(sessionId, accountNum, timestamp, ip, status)` — add to the log
2. `printAuditTrail()` — print all events in the order they were logged
3. `failedAttempts()` — count and list all FAILED login events

**Required data structures:** LinkedHashMap, insertion-order preservation

**Expected output:**
```
=== Audit Trail (chronological) ===
[SID-001] ACC-1001 | 2025-03-15 09:01 | 192.168.1.10 | SUCCESS
[SID-002] ACC-1003 | 2025-03-15 09:04 | 10.0.0.55    | FAILED
[SID-003] ACC-1001 | 2025-03-15 09:12 | 192.168.1.10 | SUCCESS
[SID-004] ACC-1003 | 2025-03-15 09:14 | 10.0.0.55    | FAILED

Failed login attempts: 2
  SID-002 — ACC-1003 from 10.0.0.55
  SID-004 — ACC-1003 from 10.0.0.55
```

---

### Exercise 4.3 — Sorted Wealth Report (TreeMap)

**Banking scenario:** The bank's compliance team needs a monthly wealth report listing all customers sorted alphabetically 
by name, alongside their current balances.

**Objective:** Use `TreeMap` to maintain accounts in automatically sorted key order without manual sorting.

**Problem description:**
Build a `WealthReport` class using `TreeMap<String, Double>` where the key is the customer's full name and the value is 
their balance. Implement:
1. `addAccount(name, balance)` — insert into the TreeMap
2. `printReport()` — print all customers in alphabetical order
3. `richestCustomer()` — use `lastKey()` / iterate to find the highest balance
4. `customersAbove(threshold)` — list all customers with balance above a given amount

**Required data structures:** TreeMap, natural key ordering, sorted iteration

**Expected output:**
```
=== Monthly Wealth Report (A–Z) ===
Alice Marais     $5,200.00
Ben Okafor       $14,000.00
Carmen Liu       $91,000.00
David Mbeki      $3,750.00

Customers above $10,000:
  Ben Okafor    $14,000.00
  Carmen Liu    $91,000.00
```

---

## Section 5 — Searching in Banking Systems
*Linear and Binary Search on customer and transaction data*

---

### Exercise 5.1 — Find a Customer by Name (Linear Search)

**Banking scenario:** A fraud officer needs to search for a customer by full name in an unsorted list of flagged accounts. 
The list is small and unsorted, so a simple scan is appropriate.

**Objective:** Implement linear search on an unsorted `ArrayList` of customer records and analyse its O(n) behaviour.

**Problem description:**
Given an `ArrayList<Customer>` (each with a name, account number, and balance) in no particular order, implement `linearSearchByName(name)` that scans each record and returns the matching `Customer`, or `null` if not found. Display the number of comparisons made in each search.

**Required algorithms:** Linear Search, O(n) time complexity

**Expected output:**
```
Searching for "Carmen Liu"...
  Checked: Alice Marais
  Checked: Ben Okafor
  Checked: Carmen Liu → FOUND at index 2 (3 comparisons)
  Account: ACC-1003 | Balance: $91,000.00

Searching for "Zara Ahmed"...
  (5 comparisons) → NOT FOUND
```

---

### Exercise 5.2 — Fast Account Lookup by Account Number (Binary Search)

**Banking scenario:** The bank's core system holds a sorted master list of account numbers. When processing a payment, it 
must verify that the target account exists quickly — even with millions of accounts.

**Objective:** Implement binary search on a sorted array of account numbers. Compare its O(log n) performance against linear search.

**Problem description:**
Given a sorted `String[]` of account numbers (e.g., `["ACC-1001", "ACC-1002", ..., "ACC-1020"]`), implement 
`binarySearch(accounts, target)` manually (no `Arrays.binarySearch()`). Print low/mid/high at every step. Then run the same 
lookup with linear search and compare total comparisons.

**Required algorithms:** Binary Search, O(log n), sorted prerequisite

**Expected output:**
```
Searching for "ACC-1015" in 20 accounts:
  Step 1: low=0  high=19 mid=9  → ACC-1010, go right
  Step 2: low=10 high=19 mid=14 → ACC-1015, FOUND!

Binary search: 2 comparisons
Linear search: 15 comparisons

Searching for "ACC-1021":
  Binary search: 5 comparisons → NOT FOUND
  Linear search: 20 comparisons → NOT FOUND
```

---

## Section 6 — Sorting Banking Data
*All five sorting algorithms applied to banking records*

---

### Exercise 6.1 — Sort Transactions by Amount (Bubble Sort)

**Banking scenario:** A customer service tool needs to display a customer's recent transactions from smallest to largest 
so analysts can easily spot micro-transactions (potentially fraudulent) at the top.

**Objective:** Implement bubble sort on a transaction amount array and trace how values bubble upward.

**Problem description:**
Given an array of 8 transaction amounts (doubles), sort them ascending using bubble sort. After each full pass, print the 
current array state. Count and display total swaps.

**Required algorithms:** Bubble Sort, O(n²), pass-by-pass tracing

**Expected output:**
```
Transactions: [250.0, 12.5, 1500.0, 75.0, 900.0, 33.0, 4200.0, 8.0]
Pass 1: [12.5, 250.0, 75.0, 900.0, 33.0, 1500.0, 8.0, 4200.0]
Pass 2: [12.5, 75.0, 250.0, 33.0, 900.0, 8.0, 1500.0, 4200.0]
...
Sorted: [8.0, 12.5, 33.0, 75.0, 250.0, 900.0, 1500.0, 4200.0]
Total swaps: 16
```

---

### Exercise 6.2 — Sort Daily Transactions as They Arrive (Insertion Sort)

**Banking scenario:** Throughout the day, transactions arrive one at a time and must be inserted into a running sorted log 
by timestamp (represented as an integer hour-minute value like 0930, 1145). Insertion sort mirrors the natural "insert in the right place" behaviour.

**Objective:** Apply insertion sort to a growing list of transaction timestamps.

**Problem description:**
Simulate 7 transactions arriving with timestamps in random order. Use insertion sort to maintain the array in sorted order. 
After each new insertion, print the current sorted state of the list.

**Required algorithms:** Insertion Sort, O(n²), efficient for nearly-sorted / streaming data

**Expected output:**
```
Arrive 1430 → [1430]
Arrive 0920 → [0920, 1430]
Arrive 1145 → [0920, 1145, 1430]
Arrive 0805 → [0805, 0920, 1145, 1430]
Arrive 1600 → [0805, 0920, 1145, 1430, 1600]
Arrive 1012 → [0805, 0920, 1012, 1145, 1430, 1600]
Arrive 0730 → [0730, 0805, 0920, 1012, 1145, 1430, 1600]
```

---

### Exercise 6.3 — Rank Customers by Balance (Selection Sort)

**Banking scenario:** A bank's relationship manager wants a ranked list of their 9 premium clients sorted by account 
balance from lowest to highest, to identify who is approaching the threshold for a higher-tier account.

**Objective:** Implement selection sort to rank customers by balance, printing which minimum was selected each pass.

**Problem description:**
Given an array of 9 `Customer` objects (each with a name and balance), sort by balance using selection sort. After each 
pass, print which customer was selected as the minimum and where they were placed.

**Required algorithms:** Selection Sort, O(n²), minimum selection each pass

**Expected output:**
```
Balances: [12000, 85000, 4500, 37000, 9200, 62000, 1800, 48000, 21000]

Pass 1: Selected 1800  (Fatima)   → placed at index 0
Pass 2: Selected 4500  (George)   → placed at index 1
Pass 3: Selected 9200  (Hana)     → placed at index 2
...
Sorted: [1800, 4500, 9200, 12000, 21000, 37000, 48000, 62000, 85000]
```

---

### Exercise 6.4 — End-of-Month Transaction Report Sort (Merge Sort)

**Banking scenario:** At month-end, the bank generates a full transaction report containing thousands of entries. The report 
must be sorted by amount. Merge sort is preferred because of its guaranteed O(n log n) performance.

**Objective:** Implement merge sort recursively on transaction amounts and print each merge step.

**Problem description:**
Sort a `double[]` of 16 transaction amounts using merge sort. Print each merge operation showing the two sub-arrays being 
combined. Add a comment explaining why this is preferred over bubble/insertion/selection for large transaction datasets.

**Required algorithms:** Merge Sort, O(n log n), divide-and-conquer, stable sorting

**Expected output:**
```
Merging [120.0, 450.0] + [30.0, 200.0]   → [30.0, 120.0, 200.0, 450.0]
Merging [75.0, 900.0]  + [15.0, 600.0]   → [15.0, 75.0, 600.0, 900.0]
Merging [30.0, 120.0, 200.0, 450.0] + [15.0, 75.0, 600.0, 900.0]
       → [15.0, 30.0, 75.0, 120.0, 200.0, 450.0, 600.0, 900.0]
...
Final sorted report: [15.0, 30.0, 75.0, ... , 4200.0]
```

---

### Exercise 6.5 — Real-Time Leaderboard of Top Earners (QuickSort)

**Banking scenario:** The bank's analytics dashboard ranks high-net-worth clients by total annual deposits. QuickSort is 
used for its average-case speed on this regularly refreshed dataset.

**Objective:** Implement QuickSort with partition tracing. Identify where each pivot lands and note the worst-case input 
scenario for a banking dataset.

**Problem description:**
Sort an array of 10 annual deposit totals using QuickSort (last element as pivot). After each partition, print the array 
state and pivot's final index. Add a note: what banking dataset would cause O(n²) worst-case behavior?

**Required algorithms:** QuickSort, partition logic, O(n log n) average, O(n²) worst case

**Expected output:**
```
Initial: [84000, 12000, 55000, 92000, 7000, 38000, 120000, 29000, 67000, 45000]

Partition pivot=45000: [29000, 12000, 38000, 7000, 45000, 92000, 120000, 55000, 67000, 84000]
  Pivot landed at index 4
...
Sorted: [7000, 12000, 29000, 38000, 45000, 55000, 67000, 84000, 92000, 120000]

Worst case: already-sorted deposit history (ascending or descending) triggers O(n²)
```

---

## Section 7 — Performance and Optimization
*Choosing the right structure and explaining time complexity*

---

### Exercise 7.1 — Sorting Algorithm Performance Benchmark

**Banking scenario:** The bank's engineering team is evaluating which sorting algorithm to use for nightly batch processing 
of transaction records. They need empirical data across different dataset sizes.

**Objective:** Benchmark all five sorting algorithms on transaction amount arrays of sizes 1,000, 5,000, and 10,000. Use 
`System.nanoTime()` to measure duration and produce a comparison table.

**Problem description:**
Generate random `double[]` arrays of sizes 1,000 / 5,000 / 10,000 simulating transaction amounts. Run all five algorithms 
on each size. Record execution time. Print a comparison table and conclude: which algorithm should the bank use for (a) 
small intraday batches, (b) large nightly reports, (c) nearly-sorted incremental updates?

**Required concepts:** All 5 sorting algorithms, time complexity, Big-O comparison, performance trade-offs

**Expected output:**
```
=== Sorting Benchmark: Transaction Records ===
Algorithm      | n=1,000  | n=5,000  | n=10,000
Bubble Sort    | 8.1ms    | 201ms    | 815ms
Insertion Sort | 2.3ms    | 52ms     | 209ms
Selection Sort | 3.8ms    | 91ms     | 366ms
Merge Sort     | 0.7ms    | 4.1ms    | 9.0ms
QuickSort      | 0.5ms    | 2.9ms    | 6.3ms

Recommendation:
  Small intraday batches:   Insertion Sort (low overhead)
  Large nightly reports:    QuickSort (fastest average)
  Incremental sorted feed:  Insertion Sort (O(n) on nearly-sorted)
```

---

### Exercise 7.2 — Choosing the Right Data Structure for Each Banking Feature

**Banking scenario:** The bank's architecture team must select the correct data structure for five new feature implementations. 
Every choice must be justified with a time complexity argument and demonstrated with a Java snippet.

**Objective:** Evaluate and justify data structure choices for real banking operations using Big-O reasoning.

**Problem description:**
For each scenario below, select the most appropriate data structure from: `Array`, `ArrayList`, `LinkedList`, `Stack`, 
`Queue`, `HashMap`, `LinkedHashMap`, `TreeMap`. Justify your answer (one sentence + Big-O note), then write a 5–10 line Java snippet.

Scenarios:
1. Instantly retrieve a customer profile given an account number (millions of accounts)
2. Process wire transfer requests in submission order
3. Undo the last 5 teller actions during a session
4. Print all clients in alphabetical order for a compliance report
5. Maintain a chronological login event log for fraud forensics

**Required concepts:** All data structures, performance trade-offs, time complexity

**Expected output:**
```
1. HashMap       → O(1) avg lookup by account number key
2. Queue         → FIFO guarantees order-of-submission processing
3. Stack         → LIFO gives access to most-recent action first
4. TreeMap       → keys auto-sorted, O(log n) insert, O(n) traversal
5. LinkedHashMap → preserves insertion order, O(1) access
```

---

## Final Project — Digital Internet Banking System Simulation

---

### Project Overview

Build a fully integrated **console-based Digital Internet Banking System** in Java that connects every concept from Sections 
1–7. The system simulates account management, payment processing, fraud detection, undo functionality, customer search, 
and data reporting.

---

### System Architecture

**1. Account registry — HashMap + TreeMap**
Store all customer accounts in a `HashMap<String, BankAccount>` for O(1) lookup by account number. Mirror the same 
accounts in a `TreeMap<String, BankAccount>` keyed by customer name for alphabetically sorted compliance reports.

**2. Transaction history — Singly Linked List**
Each `BankAccount` owns a `LinkedList<Transaction>` (Java's doubly linked list implementation) as its personal transaction 
history. Each node records: type, amount, date, and resulting balance.

**3. Statement navigation — Doubly Linked List**
Implement a `StatementNavigator` backed by a custom doubly linked list of monthly closing balances, supporting `next()` 
and `previous()` navigation.

**4. Payment processing — Queue**
All incoming payment requests enter a `Queue<Payment>` via `submitPayment()`. A `processPaymentBatch()` method dequeues and 
executes them in FIFO order, updating balances and appending to transaction histories.

**5. Teller undo system — Stack**
Each balance change pushes the prior `AccountSnapshot` (account number + balance) onto a `Stack<AccountSnapshot>`. `undoLastAction()` 
pops the most recent snapshot and restores the account.

**6. Fraud search — Linear Search + Binary Search**
- `scanFlaggedAccounts(name)` — linear search on an unsorted `ArrayList` of flagged profiles
- `verifyAccount(accNum)` — binary search on a sorted `String[]` of all account numbers

**7. Transaction report sorting — Merge Sort + Insertion Sort**
- Month-end batch report: extract the account's transaction amounts into an array and sort with merge sort
- Intraday sorted feed: use insertion sort as new transactions arrive during the day

**8. Audit log — LinkedHashMap**
All login events are stored in a `LinkedHashMap<String, LoginEvent>` (sessionId → event), preserving chronological order 
for forensic investigation.

**9. Wealth ranking — ArrayList + QuickSort**
Periodically extract all `BankAccount` balances into an `ArrayList`, convert to array, and sort with QuickSort to produce 
a ranked wealth leaderboard.

---

### Class Structure

```java
class Transaction      { String type; double amount; String date; double resultingBalance; }
class AccountSnapshot  { String accountNumber; double balance; }
class BankAccount {
    String accountNumber, ownerName;
    double balance;
    LinkedList<Transaction> history;
}
class Payment          { String sender, recipient; double amount; }
class LoginEvent       { String accountNumber, timestamp, ipAddress, status; }

class DigitalBank {
    HashMap<String, BankAccount>         accountRegistry;
    TreeMap<String, BankAccount>         sortedByName;
    Queue<Payment>                       paymentQueue;
    Stack<AccountSnapshot>               undoStack;
    LinkedHashMap<String, LoginEvent>    auditLog;
    ArrayList<BankAccount>               allAccounts;

    void openAccount(String accNum, String name, double initial);
    void submitPayment(String sender, String recipient, double amount);
    void processPaymentBatch();
    void undoLastAction(String accNum);
    BankAccount scanFlaggedAccounts(String name);
    boolean verifyAccount(String accNum);
    void printMonthEndReport(String accNum);
    void printWealthLeaderboard();
    void printAuditLog();
    void printComplianceReport();
}
```

---

### Sample Console Session

```
=== Digital Internet Banking System ===

[OPEN]    ACC-1001 | Alice Marais   | SAVINGS | $10,000.00
[OPEN]    ACC-1002 | Ben Okafor     | CURRENT | $5,500.00
[OPEN]    ACC-1003 | Carmen Liu     | SAVINGS | $92,000.00

[SUBMIT]  Payment: Alice → Ben $1,200.00
[SUBMIT]  Payment: Carmen → Alice $500.00
[SUBMIT]  Payment: Ben → Carmen $300.00
Pending payments: 3

[PROCESS BATCH]
  Alice → Ben    $1,200.00 [CONFIRMED] | Ben balance: $6,700.00
  Carmen → Alice $500.00   [CONFIRMED] | Alice balance: $10,500.00
  Ben → Carmen   $300.00   [CONFIRMED] | Carmen balance: $92,300.00

[UNDO]    ACC-1002 last action reversed → Ben balance restored: $5,500.00

[SEARCH]  Linear scan for "Alice Marais"   → Found: ACC-1001 (2 comparisons)
[VERIFY]  Binary search for "ACC-1003"     → Confirmed: Carmen Liu (3 steps)

[MONTH-END REPORT: Alice — sorted by amount (Merge Sort)]
  CREDIT  $500.00   2025-03-14
  DEBIT   $1,200.00 2025-03-12
  OPENING $10,000.00 2025-01-01

[WEALTH LEADERBOARD (QuickSort)]
  1. Carmen Liu    $92,300.00
  2. Alice Marais  $10,500.00
  3. Ben Okafor    $5,500.00

[COMPLIANCE REPORT (TreeMap A–Z)]
  Alice Marais   ACC-1001  $10,500.00
  Ben Okafor     ACC-1002  $5,500.00
  Carmen Liu     ACC-1003  $92,300.00

[AUDIT LOG (chronological — LinkedHashMap)]
  SID-001 ACC-1001 09:01 192.168.1.10 SUCCESS
  SID-002 ACC-1003 09:04 10.0.0.55    FAILED
  SID-003 ACC-1001 09:12 192.168.1.10 SUCCESS
```

---

### Concept Coverage Checklist

| Concept | Banking exercise / feature |
|---|---|
| Array | ATM cash grid (1.3), sorting scratch space |
| ArrayList | Customer onboarding (1.2), wealth leaderboard |
| Singly linked list | Transaction history log (2.1) |
| Doubly linked list | Statement navigator (2.2) |
| Stack | Teller undo system (3.1, capstone) |
| Queue | Payment processing pipeline (3.2, capstone) |
| HashMap | Account lookup by number (4.1, capstone) |
| LinkedHashMap | Login audit log (4.2, capstone) |
| TreeMap | Wealth report A–Z (4.3, capstone) |
| Linear search | Flagged account scan by name (5.1) |
| Binary search | Account number verification (5.2) |
| Bubble sort | Sort transactions by amount (6.1) |
| Insertion sort | Sort by arrival timestamp (6.2) |
| Selection sort | Rank customers by balance (6.3) |
| Merge sort | Month-end batch report (6.4) |
| QuickSort | Real-time wealth leaderboard (6.5) |
| Time complexity | Benchmark section (7.1), structure selection (7.2) |
| Performance trade-offs | Array vs ArrayList (1.2), structure choice (7.2) |

---

> **Instructor note:** Encourage students to annotate every method with a `// O(?)` comment indicating its time complexity, 
> and to test edge cases throughout: an empty transaction queue, an undo with no history, a binary search on a single account, 
> and a TreeMap collision when two customers share the same name (use a compound key such as `name + "|" + accountNum`).