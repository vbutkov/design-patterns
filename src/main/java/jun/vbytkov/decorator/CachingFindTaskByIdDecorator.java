package jun.vbytkov.decorator;

import jun.vbytkov.TaskData;

import java.util.Optional;
import java.util.UUID;

abstract class CachingFindTaskByIdDecorator implements FindTaskByIdSpi {

    private final FindTaskByIdSpi delegate;

    CachingFindTaskByIdDecorator(FindTaskByIdSpi delegate) {
        this.delegate = delegate;
    }

    abstract Optional<TaskData> retrieveFromCache(UUID id);

    abstract void storeInCache(TaskData task);

    @Override
    public Optional<TaskData> findTaskById(UUID id) {
        return this.retrieveFromCache(id)
                .or(() -> this.delegate.findTaskById(id)
                        .map(task -> {
                            this.storeInCache(task);
                            return task;
                        }));
    }


}
