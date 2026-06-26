package FINKINPBook.OOP;

import java.util.*;

class Bank {
    private String name;
    private Account[] accounts;

    private double totalTransfers;
    private double totalProvision;

    public Bank(String name, Account[] accounts) {
        this.name = name;
        this.accounts = Arrays.copyOf(accounts, accounts.length);
        this.totalTransfers = 0.0;
        this.totalProvision = 0.0;
    }

    public boolean makeTransaction(Transaction transaction) {
        Optional<Account> from = findAccount(transaction.getFromId());
        Optional<Account> to = findAccount(transaction.getToId());
        if (from.isPresent() && to.isPresent()){
            Account fromAccount = from.get();
            Account toAccount = to.get();
            double fromBalance = fromAccount.getBalance();
            double toBalance = toAccount.getBalance();
            double amount = transaction.getAmount();
            double provision = transaction.getProvision();
            if (provision + amount <= fromBalance) {
                if (fromAccount.getId() == toAccount.getId()) {
                    fromBalance =
                            toBalance = (fromBalance - provision);
                } else {
                    fromBalance -= provision + amount;
                    toBalance += amount;
                }
                fromAccount.setBalance(fromBalance);
                toAccount.setBalance(toBalance);
                updateTotals(amount, provision);
                return true;
            }
        }
        return false;
    }

    void updateTotals(double amount, double provision) {
        this.totalTransfers += amount;
        this.totalProvision += provision;
    }

    Optional<Account> findAccount(long id) {
        return Arrays.stream(accounts).filter(each -> each.getId() == id).findAny();
    }

    public static String toString(long amount) {
        return String.format("%.2f$", amount / 100.0);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Name: ");
        result.append(name);
        result.append("\n\n");
        Arrays.stream(accounts).forEach(each -> result.append(each.toString()));
        return result.toString();
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bank bank = (Bank) o;
        return Objects.equals(name, bank.name) && Arrays.equals(accounts, bank.accounts)
                && Objects.equals(totalTransfers, bank.totalTransfers)
                && Objects.equals(totalProvision, bank.totalProvision);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, accounts, totalTransfers, totalProvision);
    }

    public double totalProvision(){
        return totalProvision;
    }

    public double totalTransfers() {
        return totalTransfers;
    }

    public Account[] getAccounts() {
        return accounts;
    }
}

class Account {
    private String name;
    private long id;
    private double balance;

    public Account (String name, double balance){
        this.name = name;
        this.balance = balance;
        this.id = new Random().nextLong();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance){
        this.balance = balance;
    }

    @Override
    public String toString() {
        return String.format("Name: %s\nBalance: %s\n", name, balance);
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(id, account.id);
    }

    @Override
    public int hashCode(){
        return Objects.hash(id);
    }
}

class FlatAmountProvisionTransaction extends Transaction {
    private final double flatAmount;
    public FlatAmountProvisionTransaction(long fromId, long toId, double amount, double flatAmount) {
        super(fromId, toId, amount, "FlatAmount");
        this.flatAmount = flatAmount;
    }

    @Override
    public double getProvision() {
        return flatAmount;
    }

    public double getFlatAmount() {
        return flatAmount;
    }
}

class FlatPercentProvisionTransaction extends Transaction {
    private final int percent;

    public FlatPercentProvisionTransaction(long fromId, long toId, double amount, int percent) {
        super(fromId, toId, amount, "FlatPercent");
        this.percent = percent;
    }

    @Override
    public double getProvision() {
        long amount = (long) this.amount / 100;
        return percent * amount;
    }

    public int getPercent() {
        return percent;
    }
}

abstract class Transaction {
    final long fromId;
    final long toId;
    final double amount;
    final String description;

    public Transaction(long fromId, long toId, double amount, String description){
        this.fromId = fromId;
        this.toId = toId;
        this.amount = amount;
        this.description = description;
    }

    public long getFromId(){
        return fromId;
    }

    public long getToId() {
        return toId;
    }

    public double getAmount(){
        return amount;
    }

    public abstract double getProvision();

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(fromId, that.fromId) && Objects.equals(toId, that.toId)
                && Objects.equals(amount, that.amount) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fromId, toId, amount, description);
    }

    public String getDescription() {
        return description;
    }
}

public class BankTester {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String bank_name = sc.nextLine();
        int num_accounts = sc.nextInt();
        Account accounts[] = new Account[num_accounts];
        for (int i = 0; i < num_accounts; ++i) {
            accounts[i] = new Account(sc.nextLine(), Double.parseDouble(sc.nextLine()));
        }
        Bank bank = new Bank(bank_name, accounts);
        while (true) {
            String line = sc.nextLine();
            switch (line) {
                case "stop":
                    return;
                case "transaction":
                    String description = sc.nextLine();
                    double amount = Double.parseDouble(sc.nextLine());
                    double parameter = Double.parseDouble(sc.nextLine());
                    int from_idx = sc.nextInt();
                    int to_idx = sc.nextInt();
                    sc.nextLine();
                    Transaction t = getTransaction(description, from_idx, to_idx, amount, parameter, bank);
                    System.out.println("Transaction amount: " + t.getAmount());
                    System.out.println("Transaction description: " + t.getDescription());
                    System.out.println("Transaction successful? " + bank.makeTransaction(t));
                    break;
                case "print":
                    System.out.println(bank.toString());
                    System.out.println("Total provisions: " + bank.totalProvision());
                    System.out.println("Total transfers: " + bank.totalTransfers());
                    System.out.println();
                    break;
            }
        }
    }

    private static Transaction getTransaction(String description, int from_idx, int to_idx, double amount, double o, Bank bank) {
        switch (description) {
            case "FlatAmount":
                return new FlatAmountProvisionTransaction(bank.getAccounts()[from_idx].getId(), bank.getAccounts()[to_idx].getId(), amount, o);
            case "FlatPercent":
                return new FlatPercentProvisionTransaction(bank.getAccounts()[from_idx].getId(), bank.getAccounts()[to_idx].getId(), amount, (int) o);
        }
        return null;
    }
}