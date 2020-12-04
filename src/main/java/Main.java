import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
       /* File file = new File("file.json");
        Category desert = new Category("десерты");
        Dish dish1 = new Dish("наполеон", desert, 150);
        Dish dish2 = new Dish("мороженое",desert,100);
        Category drink = new Category("напитки");
        Dish dish3 = new Dish("сок",drink,200);
        //Лист блюд
        List<Dish> dishes = List.of(dish1,dish2,dish3);
        //Сериализация  десериализация
        Serialize.serialize(dishes,file);
        System.out.println(Serialize.deserialize(file));*/

        View.mainMenu();

    }
}