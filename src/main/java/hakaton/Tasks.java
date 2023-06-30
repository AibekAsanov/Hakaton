package hakaton;

import java.net.Socket;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ConcurrentModificationException;
import java.util.Date;
import java.util.Scanner;

public class Tasks {
    String name;
    String description;
    String date;
    String priority;
    String status;

    public Tasks(String name, String description, String date, String priority, String status) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.priority = priority;
        this.status = status;
    }

    public static void printAll() {
        String url= "jdbc:postgresql://localhost:5432/books";
        String userName = "postgres";
        String password = "kstupoks2021";

        try (Connection connection = DriverManager.getConnection(url, userName, password)) {
            // Установка соединения с базой данных


            // Создание SQL-запроса для выборки записей из таблицы
            String sql = "SELECT * FROM tasks";

            // Создание объекта Statement для выполнения запроса
            Statement statement = connection.createStatement();

            // Выполнение запроса и получение результирующего набора данных
            ResultSet resultSet = statement.executeQuery(sql);

            // Обход результатов запроса
            while (resultSet.next()) {
                // Получение значений столбцов по их индексу или имени
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                Date deadline = resultSet.getDate("deadline");
                String priority = resultSet.getString("priority");
                String status = resultSet.getString("status");

                // Вывод значений на консоль
                System.out.println("Name: " + name + ", Description: " + description + ", Deadline: " + deadline + ", Priority: " + priority + ", Status: " + status);
            }

            // Закрытие ресурсов
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void printByPriority() {
        /*String url= "jdbc:postgresql://localhost:5432/books";
        String userName = "postgres";
        String password = "kstupoks2021";

        Scanner sc = new Scanner(System.in);

        try (Connection connection = DriverManager.getConnection(url, userName, password)) {
            // Установка соединения с базой данных

            String tempPriority, choose = null;
            boolean flag = true;

            while (true) {
            System.out.println("Выберите приоритет:");
            System.out.println("Для выбора вводятся целые числа 1 - низкий, 2 - средний, 3 - высокий");
                System.out.print("Выбор: ");
                choose = sc.nextLine();
                if (choose == "1") {
                    System.out.println("Выбран низкий приоритет");
                    tempPriority = Priorities.Low.getText();
                    flag = false;
                } else if (choose == "2") {
                    System.out.println("Выбран средний приоритет");
                    tempPriority = Priorities.Middle.getText();
                    flag = false;
                } else if (choose == "3") {
                    System.out.println("Выбран высокий приоритет");
                    tempPriority = Priorities.High.getText();
                    flag = false;
                }
            }

            // Создание SQL-запроса для выборки записей из таблицы
            String sql = "SELECT * from tasks where priority = ?";

            // Создание объекта Statement для выполнения запроса
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, tempPriority);

            ResultSet resultSet = statement.executeQuery();

            // Обход результатов запроса
            while (resultSet.next()) {
                // Получение значений столбцов по их индексу или имени
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                Date deadline = resultSet.getDate("deadline");
                String priorityTemp = resultSet.getString("priority");
                String status = resultSet.getString("status");

                // Вывод значений на консоль
                System.out.println("Name: " + name + ", Description: " + description + ", Deadline: " + deadline + ", Priority: " + priorityTemp + ", Status: " + status);
            }

            // Закрытие ресурсов
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
        Scanner sc = new Scanner(System.in);
        String url= "jdbc:postgresql://localhost:5432/books";
        String userName = "postgres";
        String password = "kstupoks2021";
        try {
            // Установка соединения с базой данных
            Connection connection = DriverManager.getConnection(url, userName, password);

            // Создание SQL-запроса для выборки записей из таблицы по статусу
            String sql = "SELECT * FROM tasks WHERE priority = ?";

            // Создание объекта PreparedStatement для выполнения запроса
            PreparedStatement statement = connection.prepareStatement(sql);

           /* int choose; String tempPriority = "";
            // Установка значения параметра для статуса*/
            /*while (true) {
                System.out.println("Выберите приоритет:");
                System.out.println("Для выбора вводятся целые числа 1 - низкий, 2 - средний, 3 - высокий");
                System.out.print("Выбор: ");
                choose = sc.nextInt();
                if (choose == 1) {
                    System.out.println("Выбран низкий приоритет");
                    tempPriority = Priorities.Low.getText();
                    break;
                } else if (choose == 2) {
                    System.out.println("Выбран средний приоритет");
                    tempPriority = Priorities.Middle.getText();
                    break;
                } else if (choose == 3) {
                    System.out.println("Выбран высокий приоритет");
                    tempPriority = Priorities.High.getText();
                    break;
                }
            }*/
            statement.setString(1, "Средний");

            // Выполнение запроса и получение результирующего набора данных
            ResultSet resultSet = statement.executeQuery();

            // Обход результатов запроса
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                Date deadline = resultSet.getDate("deadline");
                String priority = resultSet.getString("priority");
                String status = resultSet.getString("status");

                System.out.println("Список, отсортированный по дедлайну");
                System.out.println("Name: " + name + ", Description: " + description + ", Deadline: " + deadline + ", Priority: " + priority + ", Status: " + status);

            }

            // Закрытие ресурсов
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

        public static void sortingByDeadline() {
            String url= "jdbc:postgresql://localhost:5432/books";
            String userName = "postgres";
            String password = "kstupoks2021";

            String query = "SELECT * FROM tasks ORDER BY deadline ASC";

            try (Connection conn = DriverManager.getConnection(url, userName, password);
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(query)) {

                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String description = rs.getString("description");
                    Date deadline = rs.getDate("deadline");
                    String priority = rs.getString("priority");
                    String status = rs.getString("status");

                    System.out.println("Список, отсортированный по дедлайну");
                    System.out.println("Name: " + name + ", Description: " + description + ", Deadline: " + deadline + ", Priority: " + priority + ", Status: " + status);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    public static void sortingByPriority() {
        String url= "jdbc:postgresql://localhost:5432/books";
        String userName = "postgres";
        String password = "kstupoks2021";

        String query = "SELECT * FROM tasks ORDER BY priority ASC";

        try (Connection conn = DriverManager.getConnection(url, userName, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                Date deadline = rs.getDate("deadline");
                String priority = rs.getString("priority");
                String status = rs.getString("status");

                System.out.println("Список, отсортированный по приоритету");
                System.out.println("Name: " + name + ", Description: " + description + ", Deadline: " + deadline + ", Priority: " + priority + ", Status: " + status);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createTASK(){
        Scanner sc = new Scanner(System.in);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        String url= "jdbc:postgresql://localhost:5432/books";
        String userName = "postgres";
        String password = "kstupoks2021";

        try (Connection connection = DriverManager.getConnection(url, userName, password)) {
            System.out.print("Введите название: ");
            String name = sc.nextLine();

            System.out.print("Введите описание: ");
            String discription = sc.nextLine();

            System.out.println("Введите дату: ");
            String date = sc.nextLine();
            java.util.Date utilDate = dateFormat.parse(date);
            java.util.Date sqlDate = new java.sql.Date(utilDate.getTime());

            System.out.println("Выберите приоритет:");
            System.out.println("Для выбора вводятся целые числа 1 - низкий, 2 - средний, 3 - высокий");
            String priority = null, choose = null;
            while (true) {
                System.out.print("Выбор: ");
                choose = sc.nextLine();
                if (choose == "1") {
                    System.out.println("Выбран низкий приоритет");
                    priority = Priorities.Low.getText();
                    break;
                } else if (choose == "2") {
                    System.out.println("Выбран средний приоритет");
                    priority = Priorities.Middle.getText();
                    break;
                } else if (choose == "3") {
                    System.out.println("Выбран высокий приоритет");
                    priority = Priorities.High.getText();
                    break;
                }
            }
            System.out.println("Введите статус работы: ");
            System.out.println("Для выбора статуса вводятся целые числа 1 - низкий, 2 - средний, 3 - высокий");
            String status = null, chooseStat = null;
            while (true) {
                System.out.print("Выбор: ");
                chooseStat = sc.nextLine();
                if (chooseStat == "1") {
                    System.out.println("Выбран статус в процессе");
                    status = Statuses.InProgress.getText();
                    break;
                } else if (chooseStat == "2") {
                    System.out.println("Выбран статус запланировано");
                    status = Statuses.Planned.getText();
                    break;
                } else if (chooseStat == "3") {
                    System.out.println("Выбран статус выполнено");
                    status = Statuses.Done.getText();
                    break;
                }
            }

            String sql = "insert into tasks (name, description, deadline, priority, status) values (?, ?, ?, ?, ?)";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, discription);
            statement.setDate(3, (java.sql.Date) sqlDate);
            statement.setString(4, priority);
            statement.setString(5, status);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Запись успешно добавлена");
            }
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static void editTASKbyName() {
        Scanner sc = new Scanner(System.in);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        String url = "jdbc:postgresql://localhost:5432/books";
        String userName = "postgres";
        String password = "kstupoks2021";

        try (Connection connection = DriverManager.getConnection(url, userName, password)) {
            System.out.print("Введите название: ");
            String name = sc.nextLine();

            System.out.print("Введите название: ");
            String nameFromTable = sc.nextLine();
            System.out.print("Введите описание: ");
            String description = sc.nextLine();
            System.out.println("Введите дату: ");
            String deadline = sc.nextLine();

            java.util.Date utilDate = dateFormat.parse(deadline);

            java.util.Date sqlDate = new java.sql.Date(utilDate.getTime());

            System.out.println("Выберите приоритет:");
            System.out.println("Для выбора вводятся целые числа 1 - низкий, 2 - средний, 3 - высокий");
            String priority = null, choose = null;
            while (true) {
                System.out.print("Выбор: ");
                choose = sc.nextLine();
                if (choose == "1") {
                    System.out.println("Выбран низкий приоритет");
                    priority = Priorities.Low.getText();
                    break;
                } else if (choose == "2") {
                    System.out.println("Выбран средний приоритет");
                    priority = Priorities.Middle.getText();
                    break;
                } else if (choose == "3") {
                    System.out.println("Выбран высокий приоритет");
                    priority = Priorities.High.getText();
                    break;
                }
            }
            System.out.println("Введите статус работы: ");
            System.out.println("Для выбора статуса вводятся целые числа 1 - низкий, 2 - средний, 3 - высокий");
            String status = null, chooseStat = null;
            while (true) {
                System.out.print("Выбор: ");
                chooseStat = sc.nextLine();
                if (chooseStat == "1") {
                    System.out.println("Выбран статус в процессе");
                    status = Statuses.InProgress.getText();
                    break;
                } else if (chooseStat == "2") {
                    System.out.println("Выбран статус запланировано");
                    status = Statuses.Planned.getText();
                    break;
                } else if (chooseStat == "3") {
                    System.out.println("Выбран статус выполнено");
                    status = Statuses.Done.getText();
                    break;
                }
            }

            String sql = "update tasks set name = (?), description = (?), deadline = (?), priority = (?), status = (?) where name = (?)";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, nameFromTable);
            statement.setString(2, description);
            statement.setDate(3, java.sql.Date.valueOf(deadline));
            statement.setString(4, priority);
            statement.setString(5, status);
            statement.setString(6, name);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Запись успешно изменена");
            } else {
                System.out.println("Запись не найдена");
            }
            statement.close();
            connection.close();
            sc.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteTASKbyName() {
        Scanner sc = new Scanner(System.in);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        String url = "jdbc:postgresql://localhost:5432/books";
        String userName = "postgres";
        String password = "kstupoks2021";

        try (Connection connection = DriverManager.getConnection(url, userName, password)) {
            System.out.print("Введите название: ");
            String name = sc.nextLine();


            String sql = "delete from tasks where name = (?)";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Запись успешно удалена");
            } else {
                System.out.println("Запись не найдена");
            }
            statement.close();
            connection.close();
            sc.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDiscription() {
        return description;
    }

    public void setDiscription(String discription) {
        this.description = discription;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}

