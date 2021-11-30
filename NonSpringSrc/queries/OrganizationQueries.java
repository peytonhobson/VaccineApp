package queries;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;


public interface OrganizationQueries {

    private static final String URL = "jdbc:";
    private static final String USERNAME = "asdf";
    private static final String PASSWORD = "group8";

    private Connection connection;
    private PreparedStatement selectAllOrganizations;
    private PreparedStatement selectOrganizationByUsername;
    private PreparedStatement selectOrganizationByType;
    private PreparedStatement selectOrganizationByName;
    private PreparedStatement insertNewOrganization;
    private PreparedStatement deleteOrganizationByUsername;
    private PreparedStatement updateOrganizationByUsername;



}
