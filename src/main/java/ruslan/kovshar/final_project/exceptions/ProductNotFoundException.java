package ruslan.kovshar.final_project.exceptions;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String message){
        super(message);
    }
}
