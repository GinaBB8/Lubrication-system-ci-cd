import helpers.DatabaseTestHelper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


//Refactored test GREEN
class LubricantsTest {

 @Test
 void testLubricantExists_shouldFailWhenNotFound() throws SQLException {
  try (Connection conn = DatabaseTestHelper.getConnection()) {
   PreparedStatement stmt = conn.prepareStatement(
           "SELECT * FROM lubricants WHERE lubricant_id = ?"
   );
   stmt.setInt(1, 9999); // ID that does NOT exist
   ResultSet rs = stmt.executeQuery();

   Assertions.assertTrue(
           rs.next(),
           "Expected lubricant with ID 9999 to exist, but it does not."
   );

  }
 }
}
