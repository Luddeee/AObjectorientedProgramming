package Temp.Lab3.Task4;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface ElementField {

    String name();
}
