package pl.piaseckif.workouttool.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.piaseckif.workouttool.domain.Workout;
import pl.piaseckif.workouttool.exceptions.WorkoutNotFoundException;
import pl.piaseckif.workouttool.repositories.WorkoutRepository;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.Locale;

@Service
public class WorkoutService {

    private WorkoutRepository workoutRepository;

    @Autowired
    public WorkoutService(WorkoutRepository workoutRepository) {
        this.workoutRepository = workoutRepository;
    }


    public Workout saveOrUpdateWorkout(Workout workout) {
        LocalDateTime now = LocalDateTime.now();
        if (workout.getStartTime()==null && workout.getEndTime()==null) {
            workout.setEndTime(now);
            workout.setStartTime(now.minusHours(1));
        } else if (workout.getStartTime()==null) {
            workout.setStartTime(workout.getEndTime().minusHours(1));
        } else if (workout.getEndTime()==null) {
            workout.setEndTime(workout.getStartTime().plusHours(1));
        }

        int hour = workout.getEndTime().getHour();
        String timeOfDay = "";
        if (hour<5) {
            timeOfDay="Night";
        } else if (hour>=5 && hour<12) {
            timeOfDay="Morning";
        } else if (hour>=12 && hour<19) {
            timeOfDay="Afternoon";
        } else {
            timeOfDay="Evening";
        }

        if ((workout.getWorkoutName()==null || workout.getWorkoutName().length()==0)) {
            workout.setWorkoutName(now.getDayOfWeek().getDisplayName(TextStyle.FULL, new Locale("en"))+" "+timeOfDay+" Workout");
        }
        return workoutRepository.save(workout);
    }

    public Iterable<Workout> findAllWorkouts() {
        return workoutRepository.findAll();
    }

    public Workout findWorkoutById(Long id) {
        return workoutRepository.findById(id).orElseThrow(() -> new WorkoutNotFoundException("Workout id: "+id+": Not found"));
    }

    public void deleteWorkoutById(Long id) {
        workoutRepository.delete(findWorkoutById(id));
    }
}
