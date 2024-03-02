package jun.vbytkov.decorator;

import jun.vbytkov.TaskData;
import org.springframework.cache.Cache;

import java.util.Optional;
import java.util.UUID;

public class SpringCachingFindTaskByIdDecorator extends CachingFindTaskByIdDecorator {
    private final Cache cache;

    SpringCachingFindTaskByIdDecorator(FindTaskByIdSpi delegate, Cache cache) {
        super(delegate);
        this.cache = cache;
    }

    @Override
    Optional<TaskData> retrieveFromCache(UUID id) {
        return Optional.ofNullable(this.cache.get(id, TaskData.class));
    }

    @Override
    void storeInCache(TaskData task) {
        this.cache.put(task.id(), task);
    }
}
