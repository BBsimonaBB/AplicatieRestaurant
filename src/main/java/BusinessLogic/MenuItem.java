package BusinessLogic;

import java.io.Serializable;
import java.util.function.Predicate;

public abstract class MenuItem implements Serializable {

    //pe langa produsele normale tre sa am si un meniu al zilei!
    private CompositeProduct dailyMenu;
    //private static final long serialVersionUID = 42L;
    private static final long  serialVersionUID = 3760037083587391746L;
    public MenuItem() {
    }
    public abstract String getName();
    public abstract double getRating();
    public abstract int getPrice();
    public abstract int getSodium();
    public abstract int getCalories();
    public abstract int getProtein();
    public abstract int getFat();

    public Predicate<MenuItem> makeLambaCriteriaRating(String rating) {
        Predicate<MenuItem> pred1 = null;
        if (rating.equals(""))
            pred1 = (e) -> e.getRating() > 0;
        else {
            if(rating.charAt(0) == '<')
                pred1 = (e) -> e.getRating() < Double.parseDouble(rating.substring(1));
            else if(rating.charAt(0) == '>')
                pred1 = (e) -> e.getRating() > Double.parseDouble(rating.substring(1));
            else pred1 = (e) -> e.getRating() == Double.parseDouble(rating);

        }
        return pred1;
    }
    public Predicate<MenuItem> makeLambaCriteriaCalories(String calories) {
        Predicate<MenuItem> pred1 = null;
        if (calories.equals(""))
            pred1 = (e) -> e.getCalories() > 0;
        else {
            if(calories.charAt(0) == '<')
                pred1 = (e) -> e.getCalories() < Integer.parseInt(calories.substring(1));
            else if(calories.charAt(0) == '>')
                pred1 = (e) -> e.getCalories() > Integer.parseInt(calories.substring(1));
            else pred1 = (e) -> e.getCalories() == Integer.parseInt(calories);
        }
        return pred1;
    }
    public Predicate<MenuItem> makeLambaCriteriaProtein(String t) {
        Predicate<MenuItem> pred1 = null;
        if (t.equals(""))
            pred1 = (e) -> e.getProtein() > 0;
        else {
            if(t.charAt(0) == '<')
                pred1 = (e) -> e.getProtein() < Integer.parseInt(t.substring(1));
            else if(t.charAt(0) == '>')
                pred1 = (e) -> e.getProtein() > Integer.parseInt(t.substring(1));
            else pred1 = (e) -> e.getProtein() == Integer.parseInt(t);
        }
        return pred1;
    }
    public Predicate<MenuItem> makeLambaCriteriaFat(String t) {
        Predicate<MenuItem> pred1 = null;
        if (t.equals(""))
            pred1 = (e) -> e.getFat() > 0;
        else {
            if(t.charAt(0) == '<')
                pred1 = (e) -> e.getFat() < Integer.parseInt(t.substring(1));
            else if(t.charAt(0) == '>')
                pred1 = (e) -> e.getFat() > Integer.parseInt(t.substring(1));
            else pred1 = (e) -> e.getFat() == Integer.parseInt(t);
        }
        return pred1;
    }
    public Predicate<MenuItem> makeLambaCriteriaSodium(String t) {
        Predicate<MenuItem> pred1 = null;
        if (t.equals(""))
            pred1 = (e) -> e.getSodium() > 0;
        else {
            if(t.charAt(0) == '<')
                pred1 = (e) -> e.getSodium() < Integer.parseInt(t.substring(1));
            else if(t.charAt(0) == '>')
                pred1 = (e) -> e.getSodium() > Integer.parseInt(t.substring(1));
            else pred1 = (e) -> e.getSodium() == Integer.parseInt(t);
        }
        return pred1;
    }
    public Predicate<MenuItem> makeLambaCriteriaPrice(String t) {
        Predicate<MenuItem> pred1 = null;
        if (t.equals(""))
            pred1 = (e) -> e.getPrice() > 0;
        else {
            if(t.charAt(0) == '<')
                pred1 = (e) -> e.getPrice() < Integer.parseInt(t.substring(1));
            else if(t.charAt(0) == '>')
                pred1 = (e) -> e.getPrice() > Integer.parseInt(t.substring(1));
            else pred1 = (e) -> e.getPrice() == Integer.parseInt(t);
        }
        return pred1;
    }
}
