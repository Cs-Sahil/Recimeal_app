package comp3350.recimeal.objects;

public class Contains {

    private int amount;

    private final String measurement;

    private final String preparation;

    //constructor for Contains class
    public Contains(int amount, String measurement, String preparation){
        this.amount = amount;
        this.measurement = measurement;
        this.preparation = preparation;
    }

    //get amount method
    public int getAmount(){
        return  this.amount;
    }

    //set amount
    public void setAmount(int newAmount){
        amount = newAmount;
    }

    //get measurement
    public String getMeasurement(){
        return this.measurement;
    }

    //get preparation
    public String getPreparation(){
        return this.preparation;
    }


}
