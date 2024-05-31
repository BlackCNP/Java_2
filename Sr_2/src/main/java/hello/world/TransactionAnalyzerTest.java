package hello.world;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import java.util.Arrays;
import java.util.List;

public class TransactionAnalyzerTest {
    @Test
    public void testCalculateTotalBalance() {
        Transaktion transaction1 = new Transaktion("2023-01-01", 100.0, "Дохід");
        Transaktion transaction2 = new Transaktion("2023-01-02", -50.0, "Витрата");
        Transaktion transaction3 = new Transaktion("2023-01-03", 150.0, "Дохід");
        List<Transaktion> transactions = Arrays.asList(transaction1, transaction2, transaction3);

        TransactionAnalyzer analyzer = new TransactionAnalyzer(transactions);

        double result = analyzer.calculateTotalBalance();

        Assertions.assertEquals(200.0, result, "Розрахунок загального балансу неправильний");
    }

    @Test
    public void testCountTransactionsByMonth() {
        // Підготовка тестових даних
        Transaktion transaction1 = new Transaktion("01-02-2023", 50.0, "Дохід");
        Transaktion transaction2 = new Transaktion("15-02-2023", -20.0, "Витрата");
        Transaktion transaction3 = new Transaktion("05-03-2023", 100.0, "Дохід");
        List<Transaktion> transactions = Arrays.asList(transaction1, transaction2, transaction3);

        // Створення екземпляру TransactionAnalyzer з тестовими даними
        TransactionAnalyzer analyzer = new TransactionAnalyzer(transactions);

        int countFeb = analyzer.countTransactionsByMonth("02-2023");
        int countMar = analyzer.countTransactionsByMonth("03-2023");

        // Перевірка результатів
        Assertions.assertEquals(2, countFeb, "Кількість транзакцій за лютий неправильна");
        Assertions.assertEquals(1, countMar, "Кількість транзакцій за березень неправильна");
    }

    @Test
    public void testReadTransactions() {
        String filePath = "https://informer.com.ua/dut/java/pr2.csv";
        TransaktionCSVReader csvReader = new TransaktionCSVReader();
        List<Transaktion> transactions = csvReader.readTransactions(filePath);

        Assertions.assertNotNull(transactions, "Список транзакцій є пустим");
    }

    @Test
    public void testFindTopExpenses() {
        Transaktion transaction1 = new Transaktion("2023-01-01", -50.0, "Витрата1");
        Transaktion transaction2 = new Transaktion("2023-01-02", -50.0, "Витрата2");
        Transaktion transaction3 = new Transaktion("2023-01-03", -250.0, "Витрата3");
        Transaktion transaction4 = new Transaktion("2023-01-04", -300.0, "Витрата4");
        Transaktion transaction5 = new Transaktion("2023-01-05", -450.0, "Витрата5");
        Transaktion transaction6 = new Transaktion("2023-01-06", -500.0, "Витрата6");
        Transaktion transaction7 = new Transaktion("2023-01-07", -600.0, "Витрата7");
        Transaktion transaction8 = new Transaktion("2023-01-08", -700.0, "Витрата8");
        Transaktion transaction9 = new Transaktion("2023-01-09", -400.0, "Витрата9");
        Transaktion transaction10 = new Transaktion("2023-01-10", -300.0, "Витрата10");
        Transaktion transaction11 = new Transaktion("2023-01-10", -230.0, "Витрата11");


        List<Transaktion> transactions = Arrays.asList(transaction1, transaction2, transaction3, transaction4, transaction5, transaction6, transaction7, transaction8,transaction9,transaction10,transaction11);

        TransactionAnalyzer analyzer = new TransactionAnalyzer(transactions);

        List<Transaktion> topExpenses = analyzer.findTopExpenses();

        Assertions.assertEquals(10, topExpenses.size(), "Кількість топових витрат неправильна");
        Assertions.assertEquals(-700.0, topExpenses.get(0).getAmount(), "Перша топова витрата неправильна");
        Assertions.assertEquals(-50.0, topExpenses.get(9).getAmount(), "Десята топова витрата неправильна");
    }
}
