package Data;

import BusinessLogic.BaseProduct;
import BusinessLogic.CompositeProduct;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class Reflection {
    public static Object[][] retrieveData(ArrayList<?> arrayList) {
        Object[][] obj = new Object[arrayList.size() + 1][arrayList.getClass().getDeclaredFields().length];
        int jndex = 0;
        for (Object o : arrayList) {
            int index = 0;
            if(o instanceof CompositeProduct)
            {
                BaseProduct b = new BaseProduct(((CompositeProduct)o).getName(), ((CompositeProduct)o).getRating(),((CompositeProduct)o).getCalories(),
                        ((CompositeProduct)o).getProtein(), ((CompositeProduct)o).getFat(), ((CompositeProduct)o).getSodium(), ((CompositeProduct)o).getPrice());

                for (Field field : b.getClass().getDeclaredFields()) {
                    if ((!field.getName().equals("serialVersionUID")) )  {
                        field.setAccessible(true);
                        try {
                            obj[jndex][index] = field.get(b);
                            index++;
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            else
            for (Field field : o.getClass().getDeclaredFields()) {
                if ((!field.getName().equals("serialVersionUID")) )  {
                    field.setAccessible(true);
                    try {
                        obj[jndex][index] = field.get(o);
                        index++;
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
            jndex++;
        }
        return obj;
    }
}
