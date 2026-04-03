
---
**Final class skeleton**
```
Enums
SplitType         — EQUAL, EXACT, PERCENTAGE

Models
User              — id, name, email
Group (Builder)   — id, name, members, expenses, balances, isOneOnOne
Expense (Builder) — id, paidBy, amount, description, splits, splitType, createdAt
ExpenseSplit      — user, share
Balance           — fromUser, toUser, amount
Settlement        — id, paidBy, paidTo, amount, timestamp

Strategy
SplitStrategy             — List<ExpenseSplit> split(List<User>, double, List<Double>)
EqualSplitStrategy
ExactSplitStrategy
PercentageSplitStrategy
SimplifyDebtsStrategy     — List<Settlement> simplify(Map<String, Double> netBalances)
GreedySimplifyStrategy

Observer
ExpenseObserver           — onExpenseAdded(Expense), onSettlementDone(Settlement)
NotificationObserver      — prints/sends notification

Services
UserService       — userId → User registry
GroupService      — create group, add members, get group
ExpenseService    — orchestrates add expense flow
SettlementService — record settlement, update balances
BalanceService    — update balances, net balance per user, simplify debts