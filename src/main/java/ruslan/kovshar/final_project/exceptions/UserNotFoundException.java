package ruslan.kovshar.final_project.exceptions;

public class UserNotFoundException extends RuntimeException {

    private String msg;

    public UserNotFoundException() {
        super();
    }
}
