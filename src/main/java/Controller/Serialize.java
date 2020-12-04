package Controller;

import Model.Dish;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.List;

/** Класс предназначенный для сериализации и десериализации
 */
public class Serialize {

    /*public static void serialize(Model.Dish dish,File file) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, dish);
    }*/

    /**
     * Метод серилизации в JSON
     * @param dishes - список блюд
     * @param file - файл
     * @throws IOException
     */
    public static void serialize(List<Dish> dishes,File file) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, dishes);
    }
    /*public static Model.Dish deserialize(File file) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Model.Dish dish = mapper.readValue(file, Model.Dish.class);
        System.out.println(dish);
        return dish;
    }*/

    /**
     * Метод десерилизации из JSON
     * @param file - файл
     * @return возвращает список блюд
     * @throws IOException
     */
    public static List<Dish> deserialize(File file) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        List<Dish> dishes =mapper.readValue(file, mapper.getTypeFactory().constructCollectionType(List.class, Dish.class));
        return dishes;
    }

}