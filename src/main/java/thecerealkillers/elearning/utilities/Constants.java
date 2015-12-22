package thecerealkillers.elearning.utilities;


import java.util.ArrayList;

/**
 * Created by Dani.
 */
public class Constants {

    public static final String ADMIN = "administrator";
    public static final String STUDENT = "student";
    public static final String TEACHER = "profesor";

    public static final ArrayList<String> ROLE_LIST = new ArrayList<String>() {{
        add(ADMIN);
        add(STUDENT);
        add(TEACHER);
    }};

}