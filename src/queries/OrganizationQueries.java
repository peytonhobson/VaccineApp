package queries;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import model.Organization;
import model.User;


public class OrganizationQueries {

    private static final String URL = "jdbc:";
    private static final String USERNAME = "swd_group028";
    private static final String PASSWORD = "swd_group028-xyz-21";

    private Connection connection;
    private PreparedStatement selectAllOrganizations;
    private PreparedStatement selectOrganizationByUsername;
    private PreparedStatement selectOrganizationByType;
    private PreparedStatement selectOrganizationByName;
    private PreparedStatement insertNewOrganization;
    private PreparedStatement deleteOrganizationByUsername;
    private PreparedStatement updateOrganizationByUsername;

    public OrganizationQueries() {
        try {
            connection =
                    DriverManager.getConnection(URL, USERNAME, PASSWORD);


            selectAllOrganizations = connection.prepareStatement(
                    "SELECT * FROM Organizations ORDER BY name");


            selectOrganizationByUsername = connection.prepareStatement(
                    "SELECT * FROM Organizations WHERE username LIKE ? " +
                            "ORDER BY name");

            selectOrganizationByType = connection.prepareStatement(
                    "Select * FROM Organizations WHERE organizonType LIKE ?" +
                            "ORDER BY name");

            selectOrganizationByName = connection.prepareStatement(
                    "Select * FROM Organizations WHERE name LIKE ?" +
                            "ORDER BY organizationType");

            // create insert that adds a new entry into the database
            insertNewOrganization = connection.prepareStatement(
                    "INSERT INTO Organizations " +
                            "(username, name, email, phone, " +
                            "profilePic, password, vaccine, mask, test, organizationType) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

            deleteOrganizationByUsername = connection.prepareStatement(
                    "DELETE FROM Organizations WHERE username LIKE ?");

            updateOrganizationByUsername = connection.prepareStatement(
                    "UPDATE Organizations SET name = ?, email = ?," +
                            "phone = ?, profilePic = ?, password = ?, vaccine = ?," +
                            "mask = ?, test = ?, organizationType = ? WHERE username LIKE ?");
        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Executes Select query by username
     * @param username  Username of organization
     * @return  Single organization because usernames are unique
     */
    public Organization getOrganizationByUserName(String username) {
        try {
            selectOrganizationByUsername.setString(1, username); // set last name
        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null;
        }

        // executeQuery returns ResultSet containing matching entries
        try (ResultSet resultSet = selectOrganizationByUsername.executeQuery()) {

            resultSet.next();

            return new Organization(
                    resultSet.getString("username"),
                    resultSet.getString("name"),
                    resultSet.getString("email"),
                    resultSet.getString("phone"),
                    resultSet.getString("profilePic"),
                    resultSet.getString("password"),
                    resultSet.getBoolean("vaccine"),
                    resultSet.getBoolean("mask"),
                    resultSet.getBoolean("test"),
                    resultSet.getString("organizationType")
            );
        }
        catch (SQLException | NoSuchAlgorithmException sqlException) {
            sqlException.printStackTrace();
            return null;
        }
    }

    public List<Organization> getAllOrganizations() {
        // executeQuery returns ResultSet containing matching entries
        return getOrganizations(selectAllOrganizations);
    }

    public List<Organization> getOrganizationsByType() {
        // executeQuery returns ResultSet containing matching entries
        return getOrganizations(selectOrganizationByType);
    }

    public List<Organization> getOrganizationsByName() {
        // executeQuery returns ResultSet containing matching entries
        return getOrganizations(selectOrganizationByName);
    }

    private List<Organization> getOrganizations(PreparedStatement selectOrganizationByType) {
        try (ResultSet resultSet = selectOrganizationByType.executeQuery()) {
            List<Organization> results = new ArrayList<Organization>();

            while (resultSet.next()) {
                results.add(new Organization(
                        resultSet.getString("username"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("phone"),
                        resultSet.getString("profilePic"),
                        resultSet.getString("password"),
                        resultSet.getBoolean("vaccine"),
                        resultSet.getBoolean("mask"),
                        resultSet.getBoolean("test"),
                        resultSet.getString("organizationType")
                ));
            }

            return results;
        }
        catch (SQLException | NoSuchAlgorithmException sqlException) {
            sqlException.printStackTrace();
        }

        return null;
    }

    public int addOrganization(String username, String name, String email, String phone, String profilePic,
                               String password, boolean vaccine, boolean mask, boolean test,
                               String organizationType) {

        try {
            // set parameters
            insertNewOrganization.setString(1, username);
            insertNewOrganization.setString(2, name);
            insertNewOrganization.setString(3, email);
            insertNewOrganization.setString(4, phone);
            insertNewOrganization.setString(5, profilePic);
            insertNewOrganization.setString(6, User.hashPassword(password));
            insertNewOrganization.setBoolean(7, vaccine);
            insertNewOrganization.setBoolean(8, mask);
            insertNewOrganization.setBoolean(9, test);
            insertNewOrganization.setString(10, organizationType);

            return insertNewOrganization.executeUpdate();
        }
        catch (SQLException | NoSuchAlgorithmException sqlException) {
            sqlException.printStackTrace();
            return 0;
        }

    }

    public void deleteOrganization(String username) {

        try {
            // set parameters
            deleteOrganizationByUsername.setString(1, username);
            deleteOrganizationByUsername.executeQuery();
        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    // TODO: When calling this function, first a get query will need to be called to get the original organization
    //  to fill in any empty parameters.
    public void updateOrganization(String username, String name, String email, String phone, String profilePic,
                                   String password, boolean vaccine, boolean mask, boolean test,
                                   String organizationType) {
        try {
            updateOrganizationByUsername.setString(1, name);
            updateOrganizationByUsername.setString(2, email);
            updateOrganizationByUsername.setString(3, phone);
            updateOrganizationByUsername.setString(4, profilePic);
            updateOrganizationByUsername.setString(5, User.hashPassword(password));
            updateOrganizationByUsername.setBoolean(6,vaccine);
            updateOrganizationByUsername.setBoolean(7,mask);
            updateOrganizationByUsername.setBoolean(8, test);
            updateOrganizationByUsername.setString(9,organizationType);
            updateOrganizationByUsername.setString(10, username);

            updateOrganizationByUsername.executeQuery();
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
