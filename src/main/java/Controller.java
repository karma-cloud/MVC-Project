import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Класс - контроллер
 */
public class Controller {
    /**
     * Метод изменения блюда по его номеру
     * @param i - номер блюда по его положению в списке
     * @param dish - блюдо
     * @param file - файл
     * @throws IOException
     */
    public static void setDataByNumber(int i,Dish dish,File file){
        try {
            List<Dish> dishes=Serialize.deserialize(file);
            dishes.set(i,dish);
            Serialize.serialize(dishes,file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Метод изменения блюда по названию
     * @param name - название блюда
     * @param dish - блюдо
     * @param file - файл
     * @throws IOException
     */
    public static void setDataByName(String name,Dish dish,File file){
        try {
            List<Dish> dishes=Serialize.deserialize(file);
            int i=0;
            while(i<dishes.size()){
                if(dishes.get(i).getName().equals(name)){
                    dishes.set(i,dish);
                    i= dishes.size();
                }
                i++;
            }
            Serialize.serialize(dishes,file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Метод изменения категории блюда по названию
     * @param name - название блюда
     * @param category - категория блюда
     * @param file - файл
     * @throws IOException
     */
    public static void setCategoryByName(String name,Category category,File file){
        try {
            List<Dish> dishes=Serialize.deserialize(file);
            Dish dish = new Dish(name,category,0);
            int i=0;
            while(i<dishes.size()){
                if(dishes.get(i).getName().equals(dish.getName())){
                    dishes.get(i).setCategory(dish.getCategory());
                    i= dishes.size();
                }
                i++;
            }
            Serialize.serialize(dishes,file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Метод изменения цены блюда по названию
     * @param name - название блюда
     * @param price - цена блюда
     * @param file - файл
     * @throws IOException
     */
    public static void setPriceByName(String name,Double price,File file){
        try {
            List<Dish> dishes=Serialize.deserialize(file);
            int i=0;
            while(i<dishes.size()){
                if(dishes.get(i).getName().equals(name)){
                    dishes.get(i).setPrice(price);
                    i= dishes.size();
                }
                i++;
            }
            Serialize.serialize(dishes,file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Метод добавления блюда
     * @param dish - блюдо
     * @param file - файл
     * @throws IOException
     */
    public static void addData(Dish dish, File file) {
        try {
            if (file.length()!=0) {
                List<Dish> dishes = Serialize.deserialize(file);
                dishes.add(dish);
                Serialize.serialize(dishes, file);
            }else {
                List<Dish>dishes= List.of(dish);
                Serialize.serialize(dishes, file);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Метод удаления блюда по названию
     * @param name - название блюда
     * @param file - файл
     * @throws IOException
     */
    public static void deleteData(String name, File file) {
        try {
            List<Dish> dishes=Serialize.deserialize(file);
            int i=0;
            while(i<dishes.size()){
                if(dishes.get(i).getName().equals(name)){
                    dishes.remove(i);
                    i= dishes.size();
                }
                i++;
            }
            Serialize.serialize(dishes,file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Метод просмотра данных
     * @param file - файл
     * @return возвращает строку содержащую список блюд
     * @throws IOException
     */
    public static String print(File file){
        try {
            String s=Serialize.deserialize(file).toString();
            String s1="";
            char[] t=s.toCharArray();
            for(int i=1;i<s.length()-1;i++){
                if('D' == t[i]){
                    if(t[i+1] == 'i'){
                        if(t[i+2] == 's'){
                            if(t[i+3]=='h'){
                                s1+='\n';
                            }
                        }
                    }
                }
                s1+=t[i];
            }
            s=s1;
            return s;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }
    /**
     * Метод проверки данных
     * @param o1 - блюдо одного из файлов
     * @param o2 - блюдо из другого файла
     * @return возвращает значение истина или ложь
     */
    private static boolean compare(Dish o1,Dish o2){
        if(o1.getName().equals(o2.getName())){
            return false;
        }else{
            return true;
        }
    }
    /**
     * Метод проверки данных из другого файла
     * @param o1 - меню одного из файлов
     * @param o2 - блюдо из другого файла
     * @return возвращает значение истина или ложь
     */
    private static boolean compare1(List<Dish> o1,Dish o2){
        int i=0;
        boolean bool=true;
        while(i<o1.size() && bool){
            bool=compare(o1.get(i),o2);
            i++;
        }
        return bool;
    }
    /**
     * Метод добавления данных из другого файла
     * @param file - файл для добавления данных
     * @param file1 - файл
     * @throws IOException
     */
    public static void addFile(File file,File file1){
        try {
            if(file.length()!=0 && file1.length()!=0) {
                List<Dish> dishes = Serialize.deserialize(file);
                List<Dish> dishes1 = Serialize.deserialize(file1);
                int i = 0;
                while (i < dishes1.size()) {
                    if (compare1(dishes, dishes1.get(i))) {
                        dishes.add(dishes1.get(i));
                    }
                    i++;
                }
                Serialize.serialize(dishes, file);
            }else {
                if(file1.length()!=0){
                    List<Dish> dishes = Serialize.deserialize(file1);
                    Serialize.serialize(dishes, file);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}