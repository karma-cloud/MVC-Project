package View;

import Controller.Controller;
import Model.Category;
import Model.Dish;
import Model.Serialize;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Класс - представление
 */
public class View {

    /** Поле для сканнера */
    private static Scanner in = new Scanner(System.in);
    /** Поле для правила выбора пунктов меню */
    private static String selectionRule = " *выберите пункт меню, чтобы вы хотели сделать";
    /** Поле для выбора пункта меню пользователем */
    private static String selection;
    /** Поле для неправильного выбора пункта меню */
    private static String wrongChoice = "\n\t!!Вы неправильно выбрали пункт меню!!" +
            "\n\tХотите попробовать снова?\n\t1 - да\n\t2 - нет";
    /** Поле для пустого меню */
    private static String emptyMenu = "\n\tВы не можете выполнить операцию. Так как меню пустое!\n" +
            "\tПопробуйте снова.";
    /** Поле для проверки наличия меню в ресторане. true - блюда есть, false - блюд нет */
    private static  boolean ok = false;
    /** Поле для файла */
    private static File file;


    /**
     *Метод главного меню
     */
    public static void mainMenu(){
        System.out.println(selectionRule);
        System.out.println("\n\t\t---Главное меню:---");
        System.out.println("\t1 - Загрузить меню ресторана\n" +
                "\t2 - Cохранить меню ресторана\n" +
                "\t3 - Посмотреть меню ресторана\n" +
                "\t4 - Редактировать меню ресторана\n" +
                "\t5 - Выйти из программы" );
        System.out.print("\n---Ваш выбор: ");
        selection = in.next();
        switch(selection){
            case "1":
                download();
                break;
            case "2":
                if (!ok){
                    System.out.println(emptyMenu);
                    mainMenu();
                }
                else{
                    save();
                }
                break;
            case "3":
                if (!ok){
                    System.out.println(emptyMenu);
                    mainMenu();
                }
                else{
                    showMenu();
                }
                break;
            case "4":
                editMenu();
                break;
            case "5":
                return;
            default:
                System.out.println(wrongChoice);
                System.out.print("\n---Ваш выбор: ");
                selection = in.next();
                if (selection.equals("1")){
                    mainMenu();
                }
        }
    }

    /**
     *Метод для загрузки меню ресторана
     * @throws IOException
     */
    public static void download(){
        System.out.println("\n\t\t---Загрузка меню ресторона---");
        System.out.print("\nПуть к файлу: ");
        String nameFile = in.next();
        file = new File(nameFile);
        try {
            Serialize.deserialize(file);
            System.out.println("\t\tЗагрузка выполнена успешно!\n" +
                    "\t1 - посмотреть меню ресторана\n" +
                    "\t2 - вернуться в главное меню");
            selection = in.next();
            ok = true;
            if (selection.equals("1")){
                showMenu();
            }
            else{
                mainMenu();
            }
        }
        catch(IOException e) {
            System.out.println("\t\tЧто-то пошло не так при загрузке...:(\n" +
                    "\t1 - попробовать снова\n" +
                    "\tлюбая клавиша - вернуться в главное меню");
            selection = in.next();
            if (selection.equals("1")){
                download();
            }
            else{
                mainMenu();
            }
        }

    }

    /**
     *Метод для сохранения меню ресторона в выбранный файл
     *@throws IOException
     */
    public static void save(){
        System.out.println("\n\t\t---Сохранение меню ресторона---");
        System.out.print("\nПуть к файлу: ");
        String nameFile = in.next();
        File file0 = new File(nameFile);
        try {
            Serialize.serialize(Serialize.deserialize(file),file0);
            System.out.println("\t\tСохранение выполнено успешно!\n" +
                    "\tлюбая клавиша - вернуться в главное меню");
            selection = in.next();
            mainMenu();
        }
        catch(IOException e) {
            System.out.println("\t\tЧто-то пошло не так при сохранении...:(\n" +
                    "\t1 - попробовать снова\n" +
                    "\tлюбая клавиша - вернуться в главное меню");
            selection = in.next();
            if (selection.equals("1")){
                save();
            }
            else{
                mainMenu();
            }
        }
    }

    /**
     *Метод просмотра меню ресторана
     */
    public static void showMenu(){
        System.out.println("\n\t\tПросмотр меню ресторона: ");
        Controller.print(file);
        System.out.print("\n\tВведите любое значение, чтобы выйти в главное меню - ");
        in.next();
        mainMenu();
    }

    /**
     *Метод редактирования меню ресторана
     */
    public static void editMenu(){
        if (!ok){
            System.out.println("\n\t\tВ меню ресторана нет блюд!\n" +
                    "\t1 - добавить блюдо\n" +
                    "\tлюбая клавиша - главное меню");
            System.out.print("\n\n---Ваш выбор: ");
            if (in.next().equals("1")){
                System.out.print("\nПуть к файлу для сохранения: ");
                String nameFile = in.next();
                file = new File(nameFile);
                System.out.print("Название блюда: ");
                String name5 = in.next();
                System.out.print("Цена блюда: ");
                double price5 = in.nextDouble();
                System.out.print("Категория блюда: ");
                Category category5 = new Category(in.next());
                Dish dish0 = new Dish(name5, category5, price5);
                Controller.addData(dish0,file);
                ok = true;
            }
            else{
                mainMenu();
                return;
            }
            System.out.println("\n\t\tБлюдо успешно добавлено!\n" +
                    "\t1 - продолжить редактирование\n" +
                    "\tлюбая клавиша - главное меню");
            if (in.next().equals("1")){
                editMenu();
            }
            else{
                mainMenu();
                return;
            }
        }
        else {
            System.out.println(selectionRule);
            System.out.println("\n\t\t---Редактор меню ресторана:---");
            System.out.println("\t1 - Изменить блюдо по номеру\n" +
                    "\t2 - Изменить блюдо по имени\n\t" +
                    "\t3 - Изменить категорию блюда\n" +
                    "\t4 - Изменить цену блюда\n" +
                    "\t5 - Добавить новое блюдо\n" +
                    "\t6 - удалить блюдо\n" +
                    "\t7 - вернуться в главное меню");
            System.out.print("\n---Ваш выбор: ");
            selection = in.next();
            switch (selection) {
                case "1": {
                    System.out.print("\nНомер блюда: ");
                    int i = in.nextInt();
                    System.out.print("Название блюда: ");
                    String name = in.next();
                    System.out.print("Цена блюда: ");
                    double price = in.nextDouble();
                    System.out.print("Категория блюда: ");
                    Category category = new Category(in.next());
                    Dish dish = new Dish(name, category, price);
                    Controller.setDataByNumber(i,dish,file);
                    break;
                }
                case "2": {
                    System.out.print("Название блюда: ");
                    String name1 = in.next();
                    System.out.print("Название нового блюда: ");
                    String name = in.next();
                    System.out.print("Цена нового блюда: ");
                    double price = in.nextDouble();
                    System.out.print("Категория нового блюда: ");
                    Category category = new Category(in.next());
                    Dish dish = new Dish(name, category, price);
                    Controller.setDataByName(name1,dish,file);
                    break;
                }
                case "3": {
                    System.out.print("Название блюда: ");
                    String name = in.next();
                    System.out.print("Категория блюда: ");
                    Category category = new Category(in.next());
                    Controller.setCategoryByName(name,category,file);
                    break;
                }
                case "4": {
                    System.out.print("Название блюда: ");
                    String name = in.next();
                    System.out.print("Новая цена блюда: ");
                    double price = in.nextDouble();
                    Controller.setPriceByName(name,price,file);
                    break;
                }
                case "5": {
                    System.out.print("Название блюда: ");
                    String name = in.next();
                    System.out.print("Цена блюда: ");
                    double price = in.nextDouble();
                    System.out.print("Категория блюда: ");
                    Category category = new Category(in.next());
                    Dish dish5 = new Dish(name, category, price);
                    Controller.addData(dish5,file);
                    break;
                }
                case "6": {
                    System.out.print("Название блюда: ");
                    String name = in.next();
                    Controller.deleteData(name,file);
                    break;
                }
                case "7": {
                    mainMenu();
                    return;
                }
                default: {
                    System.out.println(wrongChoice);
                    System.out.print("\n---Ваш выбор: ");
                    selection = in.next();
                    if (selection.equals("1")) {
                        editMenu();
                        return;
                    }
                }
            }
            System.out.println("\n\t\tХотите продолжить редактирование?\n\t1 - да\n\tлюбая клавиша - вернуться в меню");
            if (in.next().equals("1")) {
                editMenu();
            } else {
                mainMenu();
            }
        }
    }
}