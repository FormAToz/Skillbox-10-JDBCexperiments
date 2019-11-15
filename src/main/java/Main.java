import java.sql.*;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/skillbox?useUnicode=true&serverTimezone=UTC";
        String user = "root";
        String pass = "testformat";

        try {
            Connection connection = DriverManager.getConnection(url, user, pass);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT course_name, " +
                    "COUNT(MONTH(subscription_date)) / COUNT(DISTINCT MONTH(subscription_date)) AS buy_per_month " +
                    "FROM purchaselist GROUP BY course_name");

            while (resultSet.next()) {
                String courseName = resultSet.getString("course_name");
                String buyPerMonth = resultSet.getString("buy_per_month");
                System.out.println(courseName + ". Покупок в месяц: " + buyPerMonth);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
