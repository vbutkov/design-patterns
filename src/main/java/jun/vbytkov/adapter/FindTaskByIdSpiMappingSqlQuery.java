package jun.vbytkov.adapter;

import jun.vbytkov.TaskData;
import org.springframework.jdbc.object.MappingSqlQuery;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class FindTaskByIdSpiMappingSqlQuery extends MappingSqlQuery<TaskData>
        implements FindTaskByIdSpi {

    @Override
    public Optional<TaskData> findTaskById(UUID id) {
        return Optional.ofNullable(this.findObjectByNamedParam(Map.of("id", id)));
    }

    @Override
    protected TaskData mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new TaskData(rs.getObject("id", UUID.class));
    }
}
