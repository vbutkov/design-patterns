package jun.vbytkov.decorator;

import jun.vbytkov.TaskData;

import java.util.Optional;
import java.util.UUID;

public interface FindTaskByIdSpi {
    Optional<TaskData> findTaskById(UUID taskId);
}
