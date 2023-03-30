package comp3350.recimeal.persistence.hsqldb;

public class PersistenceException extends RuntimeException{

    String userMessage;

    public PersistenceException(String errMessage, Exception e){
        super(errMessage, e);
    }

    public String toString(){
        return this.userMessage;
    }
}
