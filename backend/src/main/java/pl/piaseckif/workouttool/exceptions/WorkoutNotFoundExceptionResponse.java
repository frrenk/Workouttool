package pl.piaseckif.workouttool.exceptions;

public class WorkoutNotFoundExceptionResponse {

    private String workoutNotFound;

    public WorkoutNotFoundExceptionResponse(String workoutNotFound) {
        this.workoutNotFound = workoutNotFound;
    }

    public String getWorkoutNotFound() {
        return workoutNotFound;
    }

    public void setWorkoutNotFound(String workoutNotFound) {
        this.workoutNotFound = workoutNotFound;
    }
}
