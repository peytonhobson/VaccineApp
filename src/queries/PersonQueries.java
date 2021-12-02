package queries;

import model.Person;
import model.User;

import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that contains functions to call queries for
 * the Persons table
 */
public class PersonQueries {

    /**
     * URL of DB
     */
    private static final String URL = "jdbc:";

    /**
     * Username to access DB
     */
    private static final String USERNAME = "swd_group028";

    /**
     * Password to access DB
     */
    private static final String PASSWORD = "swd_group028-xyz-21";

    private Connection connection;
    private PreparedStatement selectAllPersons;
    private PreparedStatement selectPersonByUsername;
    private PreparedStatement selectPersonByName;
    private PreparedStatement insertNewPerson;
    private PreparedStatement deletePersonByUsername;
    private PreparedStatement updatePersonByUsername;

    public PersonQueries() {
        try {
            connection =
                    DriverManager.getConnection(URL, USERNAME, PASSWORD);


            selectAllPersons = connection.prepareStatement(
                    "SELECT * FROM Persons ORDER BY name");


            selectPersonByUsername = connection.prepareStatement(
                    "SELECT * FROM Persons WHERE username LIKE ? " +
                            "ORDER BY name");

            selectPersonByName = connection.prepareStatement(
                    "Select * FROM Persons WHERE name LIKE ?" +
                            "ORDER BY username");

            // create insert that adds a new entry into the database
            insertNewPerson = connection.prepareStatement(
                    "INSERT INTO Persons " +
                            "(username, name, email, phone, " +
                            "profilePic, vaccine, password, test, vaccineCard, id) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

            deletePersonByUsername = connection.prepareStatement(
                    "DELETE FROM Persons WHERE username LIKE ?");

            updatePersonByUsername = connection.prepareStatement(
                    "UPDATE Organizations SET name = ?, email = ?," +
                            "phone = ?, profilePic = ?, vaccine = ?, password = ?," +
                            "test = ?, vaccineCard = ?, id = ? WHERE username LIKE ?");
        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Executes Select query by username
     * @param username  Username of Person
     * @return  Single Person because usernames are unique
     */
    public Person getPersonByUserName(String username) {
        try {
            selectPersonByUsername.setString(1, username); // set last name
        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null;
        }

        // executeQuery returns ResultSet containing matching entries
        try (ResultSet resultSet = selectPersonByUsername.executeQuery()) {

            resultSet.next();

            return new Person(
                    resultSet.getString("username"),
                    resultSet.getString("name"),
                    resultSet.getString("email"),
                    resultSet.getString("phone"),
                    resultSet.getString("profilePic"),
                    resultSet.getBoolean("vaccine"),
                    resultSet.getString("password"),
                    resultSet.getDate("test"),
                    resultSet.getString("vaccineCard"),
                    resultSet.getString("id")
            );
        }
        catch (SQLException | NoSuchAlgorithmException sqlException) {
            sqlException.printStackTrace();
            return null;
        }
    }

    public List<Person> getAllPersons() {
        // executeQuery returns ResultSet containing matching entries
        return getPersons(selectAllPersons);
    }

    public List<Person> getPersonsByName(String name) throws SQLException {
        // executeQuery returns ResultSet containing matching entries
        selectPersonByName.setString(1, name);
        return getPersons(selectPersonByName);
    }

    private List<Person> getPersons(PreparedStatement preparedStatement) {
        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            List<Person> results = new ArrayList<>();

            while (resultSet.next()) {
                results.add(new Person(
                        resultSet.getString("username"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("phone"),
                        resultSet.getString("profilePic"),
                        resultSet.getBoolean("vaccine"),
                        resultSet.getString("password"),
                        resultSet.getDate("test"),
                        resultSet.getString("vaccineCard"),
                        resultSet.getString("id")
                ));
            }

            return results;
        }
        catch (SQLException | NoSuchAlgorithmException sqlException) {
            sqlException.printStackTrace();
        }

        return null;
    }

    public int addPerson(String username, String name, String email, String phone, String profilePic,
                               String password, boolean vaccine, boolean test, String vaccineCard, String id) {

        try {
            // set parameters
            insertNewPerson.setString(1, username);
            insertNewPerson.setString(2, name);
            insertNewPerson.setString(3, email);
            insertNewPerson.setString(4, phone);
            insertNewPerson.setString(5, profilePic);
            insertNewPerson.setBoolean(6, vaccine);
            insertNewPerson.setString(7, User.hashPassword(password));
            insertNewPerson.setBoolean(8, test);
            insertNewPerson.setString(9, vaccineCard);
            insertNewPerson.setString(10, id);

            return insertNewPerson.executeUpdate();
        }
        catch (SQLException | NoSuchAlgorithmException sqlException) {
            sqlException.printStackTrace();
            return 0;
        }

    }

    public void deleteOrganization(String username) {

        try {
            // set parameters
            deletePersonByUsername.setString(1, username);
            deletePersonByUsername.executeQuery();
        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    // TODO: When calling this function, first a get query will need to be called to get the original person
    //  to fill in any empty parameters.
    public void updateOrganization(String username, String name, String email, String phone, String profilePic,
                                   String password, boolean vaccine, boolean test, String vaccineCard, String id) {
        try {
            updatePersonByUsername.setString(1, name);
            updatePersonByUsername.setString(2, email);
            updatePersonByUsername.setString(3, phone);
            updatePersonByUsername.setString(4, profilePic);
            updatePersonByUsername.setBoolean(5,vaccine);
            updatePersonByUsername.setString(6, User.hashPassword(password));
            updatePersonByUsername.setBoolean(7, test);
            updatePersonByUsername.setString(8, vaccineCard);
            updatePersonByUsername.setString(9, id);
            updatePersonByUsername.setString(10, username);


            updatePersonByUsername.executeQuery();
        }
        catch (SQLException | NoSuchAlgorithmException sqlException) {
            sqlException.printStackTrace();
        }
    }

    // close the database connection
    public void close() {
        try {
            connection.close();
        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
}
