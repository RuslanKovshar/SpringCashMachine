package ruslan.kovshar.final_project.controller;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static ruslan.kovshar.final_project.textcontants.TextConstants.ERROR_PARAM;

/**
 * helps work with binding result
 */
class ControllerUtils {

    /**
     * creates map with fields names and error messages
     *
     * @param bindingResult binding result
     * @return map with fields and error messages
     */
    static Map<String, String> getErrors(BindingResult bindingResult) {
        Collector<FieldError, ?, Map<String, String>> collector = Collectors.toMap(
                fieldError -> fieldError.getField() + ERROR_PARAM,
                FieldError::getDefaultMessage
        );
        return bindingResult.getFieldErrors().stream().collect(collector);
    }
}
