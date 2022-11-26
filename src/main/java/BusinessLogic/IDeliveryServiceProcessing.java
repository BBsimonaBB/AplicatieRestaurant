package BusinessLogic;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public interface IDeliveryServiceProcessing {
    /**
     * @param name of the product to be deleted
     * @return 0 if delete is done with success
     *        -1 if no file could have been deleted
     *
     * **/
    public int deleteProduct(String name);

    /**
     * @param name of product to be edited
     * @param field of the product to be edited
     * @param value the new value that will be updated
     *
     * @return 0 if edit was done successfully
     *        -1 if problem was encountered
     * **/
    public int editProduct(String name, String field, int value);

    /**
     * @param m MenuItem to be added to the Menu
     *
     * @return 1 if insert was done successfully
     *        -1 if problem sau encountered
     *
     * @throws IOException exception
     * **/
    public int insertProduct(MenuItem m) throws IOException;

    /**
     * @param o Order that will be added to the hashMap
     * @param items the MenuItems that will be added as values for the previous key o
     *
     * **/
    public void addElemToMap(Order o, ArrayList<MenuItem> items);

    /**
     * @param name to choose which one of the items in the menu will be added to the order
     * @param dishes represents all the other dihes already in the order
     * **/
    public void addToOrder(String name, ArrayList<MenuItem> dishes);

    /**
     * @param name to choose which one of the items in the menu will be added to the order
     * @param dishes represents all the other dihes already in the order
     * **/
    public void deleteFromOrder(String name, ArrayList<MenuItem> dishes);

    /**
     * @param fileWriter represnts the destination where the info shall be written
     * @param s is the start time (hour) for the report
     * @param f is the finish time (hour) for the report
     *
     * @throws IOException exception
     * **/
    public void generateType1Report(FileWriter fileWriter, int s, int f) throws IOException;

    /**
     * @param fileWriter represnts the destination where the info shall be written
     * @param amount is the minimum of each product in quantitiy that needs to be ordered
     *
     * @throws IOException exception
     * **/
    public void generateType2Report(FileWriter fileWriter, int amount) throws IOException;

    /**
     * @param fileWriter represnts the destination where the info shall be written
     * @param ordersNo is the minimum number of orders a user has to do
     * @param miniSum is the minimum amount that each order has to sum, in order that the client will be chosen for us
     *
     * @throws IOException exception
     * **/
    public void generateType3Report(FileWriter fileWriter, int ordersNo, int miniSum) throws IOException;

    /**
     * @param fileWriter represnts the destination where the info shall be written
     * @param month is part of the date for which we want to generate the report
     * @param day i.e. day of month, month is part of the date for which we want to generate the report
     *
     * @throws IOException exception
     * **/
    public void generateType4Report(FileWriter fileWriter, String month, int day) throws IOException;

}
