package hakaton;

import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean flag = true;
        while (flag) {
            System.out.println("Выберите операцию");
            System.out.println("1. Создание задачи");
            System.out.println("2. Удаление задачи");
            System.out.println("3. Изменение задачи");
            System.out.println("4. Просмотр списка задач");
            System.out.println("5. Просмотр списка задач по приоритету"); //Необходимо отредактировать метод
            System.out.println("6. Сортировать по дате");
            System.out.println("7. Сортировать по приоритету");
            System.out.println("0. Выход из программы");

            System.out.print("Выбор: ");
            String choose = sc.nextLine();

            switch (choose) {
                case "1":
                    Tasks.createTASK();
                    break;
                case "2":
                    Tasks.deleteTASKbyName();
                    break;
                case "3":
                    Tasks.editTASKbyName();
                    break;
                case "4":
                    Tasks.printAll();
                    break;
                case "5":
                    Tasks.printByPriority();
                    //Необходимо отредактировать метод
                    break;
                case "6":
                    Tasks.sortingByDeadline();
                    break;
                case "7":
                    Tasks.sortingByPriority();
                    break;
                case "0":
                    flag = false;
                    break;
                default:
                    System.out.println("Выбрана неправильная операция!");
                    break;
            }
        }
    }
}
