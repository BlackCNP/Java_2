package hello.world;

public class Transaktion {
    private String date;
    private double amount;
    private  String description;

    public Transaktion(String date, double amount, String description){
        this.amount = amount;
        this.date = date;
        this.description = description;
    }

    public String getDate() {

        return date;
    }

    public void  setDate(String date){

        this.date = date;
    }

    public double getAmount(){

        return  amount;
    }

    public  void  setAmount(double amount){

        this.amount = amount;
    }

    public String getDescription(){

        return  description;
    }

    public void  setDescription(){

        this.description = description;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "date='" + date + '\'' +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                '}';
    }
}
