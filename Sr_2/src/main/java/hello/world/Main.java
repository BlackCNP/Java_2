package hello.world;

import java.util.List;

public class Main {

    public  static  void main(String[] args){
        String filePath = "https://informer.com.ua/dut/java/pr2.csv";
        TransaktionCSVReader csvReader = new TransaktionCSVReader();
        List<Transaktion> transaktions = csvReader.readTransactions(filePath);

        TransactionAnalyzer analyzer = new TransactionAnalyzer(transaktions);
        TransactionReportGenerator reportGenerator = new TransactionReportGenerator();

        double totalBalance = analyzer.calculateTotalBalance();
        reportGenerator.printBalanceReport(totalBalance);
        String monthYear = "01-2024";
        int transactionsCount = analyzer.countTransactionsByMonth(monthYear);
        reportGenerator.printTransactionsCountByMonth(monthYear, transactionsCount);

        List<Transaktion> topExpenses = analyzer.findTopExpenses();
        reportGenerator.printTopExpensesReport(topExpenses);

        Transaktion topExpenseInPeriod = analyzer.findTopExpenseForPeriod("01-01-2024", "30-01-2024");
        Transaktion lowExpenseInPeriod = analyzer.findLowestExpenseForPeriod("01-01-2024", "30-01-2024");

        System.out.println("Найбільша витрата за період: ");
        reportGenerator.printTransaction(topExpenseInPeriod);

        System.out.println("Найменша витрата за період: ");
        reportGenerator.printTransaction(lowExpenseInPeriod);

        reportGenerator.generateExpenseReport(transaktions);

        TransactionAnalyzerTest test = new TransactionAnalyzerTest();
        test.testReadTransactions();
        test.testFindTopExpenses();
    }
}
