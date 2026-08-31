import helpers.TaskService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TaskIntegrationTest {

    @Test
    void taskIntegrationTest() throws Exception {
        TaskService taskService = new TaskService();

        int scheduleId = 1;        // must exist in DB
        int technicianId = 1;      // must exist AND role must be 'Technician' or 'Admin'

        int newTaskId = taskService.createTask(scheduleId, technicianId);

        Assertions.assertTrue(newTaskId > 0, "Task ID should be generated");
    }
}
