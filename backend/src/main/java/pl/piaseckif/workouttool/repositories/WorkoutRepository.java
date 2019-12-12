package pl.piaseckif.workouttool.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.piaseckif.workouttool.domain.Workout;

@Repository
public interface WorkoutRepository extends CrudRepository<Workout, Long> {
}
