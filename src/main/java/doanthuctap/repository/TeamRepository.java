package doanthuctap.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import doanthuctap.entity.TeamEntity;

import java.util.Set;

public interface TeamRepository extends CrudRepository<TeamEntity, Integer> {

}
