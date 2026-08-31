package helpers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Service class used for TDD demonstration.
 * Provides simple database logic that can be tested and refactored.
 */
public class LubricantService {

    /**
     * Carries out check whether a lubricant exists in the database by ID.
     *
     * @param id The lubricant_id to check.
     * @return true if the lubricant exists, false otherwise.
     * @throws SQLException if a database error occurs.
     */
    public boolean lubricantExists(int id) throws SQLException {
        try (Connection conn = DatabaseTestHelper.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(
                    "SELECT 1 FROM lubricants WHERE lubricant_id = ?"
            );
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            return rs.next(); // true if a row exists
        }
    }
}
