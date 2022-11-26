package BusinessLogic;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Order implements Serializable{  //can have menuItems associated -HOW?!
    public static final long serialVersionUID = 1152562510189369288L;
    private int orderID; //ar trebui cumva sa fie o variabila globala peste tot
    private String clientID;
    private LocalDateTime orderDate;

    public Order(int orderID, String clientID) {
        this.orderID = orderID;
        this.clientID = clientID;
        this.orderDate = LocalDateTime.now();
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getClientID() {
        return clientID;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }


    @Override
    public int hashCode(){
       return  (orderID)*(orderDate.getYear() + orderDate.getDayOfMonth() + orderDate.getDayOfYear() +
                orderDate.getHour() + orderDate.getMinute() + orderDate.getSecond());
    }

    @Override
    public boolean equals(Object o) {
        return this.getOrderID() == ((Order) o).getOrderID();
    }
}
