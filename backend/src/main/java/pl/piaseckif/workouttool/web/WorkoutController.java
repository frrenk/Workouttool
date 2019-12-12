package pl.piaseckif.workouttool.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.piaseckif.workouttool.domain.Workout;
import pl.piaseckif.workouttool.services.WorkoutService;

@RestController
@RequestMapping("/api/workout")
@CrossOrigin
public class WorkoutController {

    private WorkoutService workoutService;

    @Autowired
    public WorkoutController(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }


    @PostMapping("")
    public ResponseEntity<?> createNewWorkout(@RequestBody Workout workout, BindingResult result) {
        Workout workout1 = workoutService.saveOrUpdateWorkout(workout);
        return new ResponseEntity<>(workout1, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getWorkoutById(@PathVariable String id) {
        Workout workout = workoutService.findWorkoutById(Long.parseLong(id));
        return new ResponseEntity<>(workout, HttpStatus.OK);
    }

    @GetMapping("/all")
    public Iterable<Workout> getAllWorkouts() {
        return workoutService.findAllWorkouts();
    }

    @DeleteMapping("/{id")
    public ResponseEntity<?> deleteWorkoutById(@PathVariable String id) {
        workoutService.deleteWorkoutById(Long.parseLong(id));
        return new ResponseEntity<>("Workout deleted", HttpStatus.OK);

    }


    public WorkoutService getWorkoutService() {
        return workoutService;
    }

    public void setWorkoutService(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }
}
