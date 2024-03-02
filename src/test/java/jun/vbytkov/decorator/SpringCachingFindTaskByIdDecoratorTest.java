package jun.vbytkov.decorator;

import jun.vbytkov.TaskData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.cache.Cache;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SpringCachingFindTaskByIdDecoratorTest {

    @Mock
    FindTaskByIdSpi delegate;

    @Mock
    Cache cache;

    @InjectMocks
    SpringCachingFindTaskByIdDecorator decorator;

    @Test
    void findTaskById_TaskIsCached_ReturnsNotEmptyOptional() {
        //given
        UUID taskId = UUID.randomUUID();
        TaskData task = new TaskData(taskId);

        doReturn(task).when(this.cache).get(taskId, TaskData.class);

        //when
        Optional<TaskData> optionalTask = this.decorator.findTaskById(taskId);

        //then
        assertEquals(Optional.of(task), optionalTask);

        verify(this.cache, never()).put(any(), any());
    }

    @Test
    void findTaskById_TaskIsNotCachedButExists_ReturnsNotEmptyOptional() {
        //given
        UUID taskId = UUID.randomUUID();
        TaskData task = new TaskData(taskId);

        doReturn(Optional.of(task)).when(this.delegate).findTaskById(taskId);

        //when
        Optional<TaskData> optionalTask = this.decorator.findTaskById(taskId);

        //then
        assertEquals(Optional.of(task), optionalTask);

        verify(this.cache).put(taskId, task);

    }

    @Test
    void findTaskById_TaskDoesNotExists_ReturnsEmptyOptional() {
        //given
        UUID taskId = UUID.randomUUID();

        //when
        Optional<TaskData> optionalTask = this.decorator.findTaskById(taskId);

        //then
        assertEquals(Optional.empty(), optionalTask);

        verify(this.cache, never()).put(any(), any());
    }


}