package jun.vbytkov.adapter;

import java.util.Optional;
import java.util.UUID;

public interface FindTaskByIdSpi {
    Optional<TaskData> findTaskById(UUID taskId);
}
