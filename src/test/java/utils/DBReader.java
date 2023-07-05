package utils;

import com.google.errorprone.annotations.Var;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBReader {

    private final static String URL = "jdbc:postgresql://localhost:5432/postgres";
    private final static String USER_NAME = "postgres";
    private final static String USER_PASSWORD = "postgres";

    private final static String GETSTUDENTS = "select * from students";
    private final static String SELECT = "select * from students where id=?";
    private final static String INSERT = "insert into students values(?,?,?)";
    private final static String UPDATE = "update students set firstname=? where id=?";

    private final static String DELETE = "delete from students where id=?";

    public static List<String> getStudentsFromDB() {

        List<String> students = new ArrayList<>();


        try(Connection connection = DriverManager.getConnection(URL, USER_NAME, USER_PASSWORD)) {
            /*Statement sqlStetement = connection.createStatement();
            ResultSet resultSet = sqlStetement.executeQuery(QUERY);*/

            PreparedStatement preparedStatement = connection.prepareStatement(GETSTUDENTS);
            ResultSet resultSet = preparedStatement.executeQuery(GETSTUDENTS);

            while(resultSet.next()){
                String userName = new String(resultSet.getString("firstname"));
                String userSurname = new String(resultSet.getString("lastname"));

                students.add(userName);
                students.add(userSurname);
            }

        }catch(SQLException exception){
            throw new RuntimeException(String.format("Please check connection" + ". URL[%s], name[%s], password[%s]", URL, USER_NAME, USER_PASSWORD));
        }
        return students;
    }

    public static List<String> selectStudentsFromDB() {

        List<String> students = new ArrayList<>();


        try(Connection connection = DriverManager.getConnection(URL, USER_NAME, USER_PASSWORD)) {

            PreparedStatement preparedStatement = connection.prepareStatement(SELECT);
            preparedStatement.setInt(1, 2);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                String userName = new String(resultSet.getString("firstname"));
                String userSurname = new String(resultSet.getString("lastname"));

                students.add(userName);
                students.add(userSurname);
            }

        }catch(SQLException exception){
            throw new RuntimeException(String.format("Please check connection" + ". URL[%s], name[%s], password[%s]", URL, USER_NAME, USER_PASSWORD));
        }
        return students;
    }

    public static List<String> insertStudents() {
        List<String> students = new ArrayList<>();


        try(Connection connection = DriverManager.getConnection(URL, USER_NAME, USER_PASSWORD)) {

            PreparedStatement preparedStatement = connection.prepareStatement(INSERT);
            preparedStatement.setInt(1,4);
            preparedStatement.setString(2,"Jack");
            preparedStatement.setString(3,"Black");
            preparedStatement.executeUpdate();

        }catch(SQLException exception){
            throw new RuntimeException(String.format("Please check connection" + ". URL[%s], name[%s], password[%s]", URL, USER_NAME, USER_PASSWORD));
        }
        return students;
    }

    public static List<String> updateStudents() {
        List<String> students = new ArrayList<>();


        try(Connection connection = DriverManager.getConnection(URL, USER_NAME, USER_PASSWORD)) {

            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setString(1,"New name");
            preparedStatement.setInt(2,1);
            preparedStatement.executeUpdate();

        }catch(SQLException exception){
            throw new RuntimeException(String.format("Please check connection" + ". URL[%s], name[%s], password[%s]", URL, USER_NAME, USER_PASSWORD));
        }
        return students;
    }

    public static List<String> deleteStudents() {
        List<String> students = new ArrayList<>();

        try(Connection connection = DriverManager.getConnection(URL, USER_NAME, USER_PASSWORD)) {

            PreparedStatement preparedStatement = connection.prepareStatement(DELETE);
            preparedStatement.setInt(1,4);
            preparedStatement.executeUpdate();

        }catch(SQLException exception){
            throw new RuntimeException(String.format("Please check connection" + ". URL[%s], name[%s], password[%s]", URL, USER_NAME, USER_PASSWORD));
        }
        return students;
    }

    public static void main(String[] args) {
        getStudentsFromDB();
        selectStudentsFromDB();
        insertStudents();
        updateStudents();
        deleteStudents();
    }
}
