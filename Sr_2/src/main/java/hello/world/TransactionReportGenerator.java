package hello.world;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransactionReportGenerator {
    public void printBalanceReport(double totalBalance) {
        System.out.println("Загальний баланс: " + totalBalance);
    }

    public void printTransactionsCountByMonth(String monthYear, int count) {
        System.out.println("Кількість транзакцій за " + monthYear + ": " + count);
    }

    public void printTopExpensesReport(List<Transaktion> topExpenses) {
        System.out.println("10 найбільших витрат:");
        for (Transaktion expense : topExpenses) {
            System.out.println(expense.getDescription() + ": " + expense.getAmount());
        }
    }

    public  void printTransaction(Transaktion transaction){
        System.out.println(transaction.getDate() + " | " + transaction.getDescription() + " | " + transaction.getAmount());
    }

    public void generateExpenseReport(List<Transaktion> transactions) {

        Map<String, Double> totalExpensesByCategory = calculateTotalExpensesByCategory(transactions);
        Map<String, Double> totalExpensesByMonth = calculateTotalExpensesByMonth(transactions);

        System.out.println("Сумарні витрати по категоріям:");
        printVisualizedExpenses(totalExpensesByCategory);
        System.out.println("\nСумарні витрати по місяцях:");
        printVisualizedExpenses(totalExpensesByMonth);
    }

    private Map<String, Double> calculateTotalExpensesByCategory(List<Transaktion> transactions) {
        Map<String, Double> totalExpensesByCategory = new HashMap<>();
        for (Transaktion transaction : transactions) {
            if (transaction.getAmount() < 0) {
                String category = transaction.getDescription();
                totalExpensesByCategory.put(category, totalExpensesByCategory.getOrDefault(category, 0.0) + transaction.getAmount());
            }
        }
        return totalExpensesByCategory;
    }

    private Map<String, Double> calculateTotalExpensesByMonth(List<Transaktion> transactions) {
        Map<String, Double> totalExpensesByMonth = new HashMap<>();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        for (Transaktion transaction : transactions) {
            if (transaction.getAmount() < 0) {
                LocalDate date = LocalDate.parse(transaction.getDate(), dateFormatter);
                String monthYear = date.format(DateTimeFormatter.ofPattern("MM-yyyy"));
                totalExpensesByMonth.put(monthYear, totalExpensesByMonth.getOrDefault(monthYear, 0.0) + transaction.getAmount());
            }
        }
        return totalExpensesByMonth;
    }

    private void printVisualizedExpenses(Map<String, Double> expensesMap) {
        for (Map.Entry<String, Double> entry : expensesMap.entrySet()) {
            String category = entry.getKey();
            Double amount = entry.getValue();
            int visualizationLevel = (int) Math.abs(amount / 1000);
            String visualization = "*".repeat(visualizationLevel);
            System.out.printf("%s: %s (%.2f)%n", category, visualization, amount);
        }
    }

}
