package helpers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TaskService {

    // Fetch employee role for RBAC
    private String getEmployeeRole(Connection conn, int employee_Id) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement(
                "SELECT role FROM employee WHERE employee_Id = ?"   // <-- FIXED COLUMN NAME
        );
        stmt.setInt(1, employee_Id);

        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return rs.getString("role");
        } else {
            throw new SQLException("Employee not found");
        }
    }

    // Create a task with RBAC enforcement
    public int createTask(int scheduleId, int employeeId) throws SQLException {
        try (Connection conn = DatabaseTestHelper.getConnection()) {

            // RBAC check
            String role = getEmployeeRole(conn, employeeId);

            if (!role.equalsIgnoreCase("Technician") &&
                    !role.equalsIgnoreCase("Admin")) {
                throw new SecurityException("User not authorised to create tasks");
            }

            // Insert task
            PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO tasks (schedule_id, status, responsible_person_id) VALUES (?, 'Pending', ?)",
                    PreparedStatement.RETURN_GENERATED_KEYS
            );

            stmt.setInt(1, scheduleId);
            stmt.setInt(2, employeeId);
            stmt.executeUpdate();

            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) {
                return keys.getInt(1); // return task_id
            } else {
                throw new SQLException("Task ID not generated");
            }
        }
    }
}
