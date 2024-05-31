package hello.world;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TransactionAnalyzer {
    private List<Transaktion> transactions;
    private DateTimeFormatter dateFormatter;

    public TransactionAnalyzer(List<Transaktion> transactions) {
        this.transactions = transactions;
        this.dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    }

    public double calculateTotalBalance() {
        double balance = 0;
        for (Transaktion transaction : transactions) {
            balance += transaction.getAmount();
        }
        return balance;
    }

    public int countTransactionsByMonth(String monthYear) {
        int count = 0;
        for (Transaktion transaction : transactions) {
            LocalDate date = LocalDate.parse(transaction.getDate(), dateFormatter);
            String transactionMonthYear = date.format(DateTimeFormatter.ofPattern("MM-yyyy"));
            if (transactionMonthYear.equals(monthYear)) {
                count++;
            }
        }
        return count;
    }

    public List<Transaktion> findTopExpenses() {
        return transactions.stream()
                .filter(t -> t.getAmount() < 0)
                .sorted(Comparator.comparing(Transaktion::getAmount))
                .limit(10)
                .collect(Collectors.toList());
    }

    public Transaktion findTopExpenseForPeriod(String startDate, String endDate) {
        LocalDate dateStart = LocalDate.parse(startDate, dateFormatter);
        LocalDate dateEnd = LocalDate.parse(endDate, dateFormatter);
        return transactions.stream()
                .filter(t -> t.getAmount() < 0 && isInPeriod(t.getDate(), dateStart, dateEnd))
                .sorted(Comparator.comparing(Transaktion::getAmount))
                .findFirst()
                .orElse(null);
    }

    public Transaktion findLowestExpenseForPeriod(String startDate, String endDate) {
        LocalDate dateStart = LocalDate.parse(startDate, dateFormatter);
        LocalDate dateEnd = LocalDate.parse(endDate, dateFormatter);
        return transactions.stream()
                .filter(t -> t.getAmount() < 0 && isInPeriod(t.getDate(), dateStart, dateEnd))
                .sorted(Comparator.comparing(Transaktion::getAmount).reversed())
                .findFirst()
                .orElse(null);
    }

    private boolean isInPeriod(String dateString, LocalDate startDate, LocalDate endDate) {
        LocalDate date = LocalDate.parse(dateString, dateFormatter);
        return !date.isBefore(startDate) && !date.isAfter(endDate);
    }
}
