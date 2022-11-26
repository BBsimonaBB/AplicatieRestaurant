package BusinessLogic;

import java.io.*;
import java.util.function.Predicate;

public class BaseProduct extends MenuItem implements Serializable, Comparable<BaseProduct>{
    //Title,Rating,Calories,Protein,Fat,Sodium,Price
    private static final long  serialVersionUID = 5072643112405408030l;
    private String name;
    private double rating;
    private int calories;
    private int protein;
    private int fat;
    private int sodium;
    private int price;
    public BaseProduct(){

    }
    public BaseProduct(String name, double rating, int calories, int protein, int fat, int sodium, int price) {
        this.name = name;
        this.rating = rating;
        this.calories = calories;
        this.protein = protein;
        this.fat = fat;
        this.sodium = sodium;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getProtein() {
        return protein;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }

    public int getFat() {
        return fat;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

    public int getSodium() {
        return sodium;
    }

    public void setSodium(int sodium) {
        this.sodium = sodium;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }


    @Override
    public int compareTo(BaseProduct o) {
        return name.compareTo(o.name);
    }


}

