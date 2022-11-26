package BusinessLogic;

import java.io.Serializable;
import java.util.ArrayList;

public class CompositeProduct extends MenuItem implements Serializable {
    private static final long  serialVersionUID = -8903508081735228602l;
    private String name;
    private ArrayList<MenuItem> components;
    //private int price;

    public CompositeProduct(String name,ArrayList<MenuItem> components) {
        this.name = name;
        this.components = components;
        }

    public ArrayList<MenuItem> getComponents() {
        return components;
    }

    public void setComponents(ArrayList<MenuItem> components) {
        this.components = components;
    }

    @Override
    public double getRating() {
        double rating = 0.0;
        for(MenuItem m : components) {
            rating = rating + m.getRating();
        }

        return rating / components.size();
    }
    @Override
    public int getPrice() {
        int price = 0;
        for(MenuItem m : components) {
            price = price + m.getPrice();
        }
        return price;
    }

    @Override
    public int getSodium() {
        int sodium = 0;
        for(MenuItem m : components) {
            sodium = sodium + m.getSodium();
        }
        return sodium;
    }

    @Override
    public int getCalories() {
        int calories = 0;
        for(MenuItem m : components) {
            calories = calories + m.getCalories();
        }
        return calories;
    }

    @Override
    public int getProtein() {
        int protein = 0;
        for(MenuItem m : components) {
            protein = protein + m.getProtein();
        }
        return protein;
    }

    @Override
    public int getFat() {
        int fat = 0;
        for(MenuItem m : components) {
            fat = fat + m.getFat();
        }
        return fat;
    }
    @Override
    public String getName() {
        return name;
    }

    public int compositePrice() {
        int total = 0;
        for(MenuItem m : components)
            total = total + ((BaseProduct)m).getPrice();

        return total;
    }

}
