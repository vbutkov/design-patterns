package jun.vbytkov.adapter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class FindTaskByIdSpiAdapterTest {

    @Mock
    NamedParameterJdbcOperations jdbcOperations;

    @InjectMocks
    FindTaskByIdSpiAdapter adapter;

    @Test
    void findTaskById_CallsQuery_ReturnsOptional() {
        // given
        UUID id = UUID.randomUUID();
        TaskData taskData = new TaskData(id);

        doReturn(List.of(taskData)).when(this.jdbcOperations)
                .query("select * from t_task where id = :id",
                        Map.of("id", id), this.adapter);

        //when
        Optional<TaskData> optionalTaskData = this.adapter.findTaskById(id);

        //then
        assertEquals(Optional.of(taskData), optionalTaskData);
    }

}