package thecerealkillers.elearning.validator;

import thecerealkillers.elearning.exceptions.InvalidModuleException;
import thecerealkillers.elearning.model.Module;

/**
 * Created by cuvidk on 12/22/2015.
 */
public class ModuleValidator extends Validator {
    public static void validateModule(Module module) throws InvalidModuleException {
        String feedback = "";

        if (!isValidTitle(module.getTitle())) {
            feedback += "Invalid title. Title cannot be empty.\n";
        }
        if (!isValidCourseName(module.getCourse())) {
            feedback += "Invalid course name. Course name cannot be empty.\n";
        }
        if (!isValidDescription(module.getDescription())) {
            feedback += "Invalid description. Description cannot be empty.\n";
        }

        if (!"".equals(feedback)) {
            throw new InvalidModuleException(feedback);
        }
    }

    private static boolean isValidTitle(String title) {
        return !isEmpty(title);
    }

    private static boolean isValidCourseName(String course) {
        return !isEmpty(course);
    }

    private static boolean isValidDescription(String description) {
        return !isEmpty(description);
    }
}
