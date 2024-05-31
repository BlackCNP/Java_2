package hello.world;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class TransaktionCSVReader {

    public List<Transaktion> readTransactions(String filePath) {
        List<Transaktion> transactions = new ArrayList<>();
        try {
            URL url = new URL(filePath);
            // Відкриття потоку для читання з URL
            try (BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] values = line.split(",");
                    Transaktion transaction = new Transaktion(values[0], Double.parseDouble(values[1]), values[2]);
                    transactions.add(transaction);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return transactions;
    }

}
