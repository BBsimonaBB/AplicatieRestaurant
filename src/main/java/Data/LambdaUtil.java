package Data;

import BusinessLogic.BaseProduct;
import BusinessLogic.CompositeProduct;
import BusinessLogic.MenuItem;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class LambdaUtil {

    public static List<MenuItem> makeCriteria(String title, String rating, String cal,
                                              String protein, String fat, String sodium, String price){
        ArrayList<MenuItem> products = new ArrayList<>();
        FileCsvUtil file = new FileCsvUtil("D:\\An2\\Sem2\\TP\\PT2022_30223_Tivadar_Maria_Assignment_4\\baseproducts\\");
        products = file.deserialization();
        MenuItem b = new BaseProduct();
        Predicate<MenuItem> p1 = p -> p.getName().contains(title); //
        //acuma merga la un BaseProduct = da nu mi gaseste DailyMenus
        Predicate<MenuItem> p2 = b.makeLambaCriteriaRating(rating);
        Predicate<MenuItem> p3 = b.makeLambaCriteriaCalories(cal);
        Predicate<MenuItem> p4 = b.makeLambaCriteriaProtein(protein);
        Predicate<MenuItem> p5 = b.makeLambaCriteriaFat(fat);
        Predicate<MenuItem> p6 = b.makeLambaCriteriaSodium(sodium);
        Predicate<MenuItem> p7 = b.makeLambaCriteriaPrice(price);
         return (products.stream()
                .filter(p1.and(p2).and(p3).and(p4).and(p5).and(p6).and(p7)).collect(Collectors.toList()));
    }
}
