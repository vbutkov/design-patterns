package jun.vbytkov.adapter;


import jun.vbytkov.TaskData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class FindTaskByIdSpiMappingSqlQueryTest {

    @Spy
    FindTaskByIdSpiMappingSqlQuery adapter;

    @Test
    void findTaskById_CallsFindObjectByNameParam_ReturnsOptional() {
        //give
        UUID id = UUID.randomUUID();
        TaskData taskData = new TaskData(id);

        doReturn(taskData).when(this.adapter).findObjectByNamedParam(Map.of("id", id));

        //when
        Optional<TaskData> optionalTaskData = this.adapter.findTaskById(id);

        //then

        assertEquals(Optional.of(taskData), optionalTaskData);
    }

}