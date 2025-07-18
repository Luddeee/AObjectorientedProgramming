package Lab3;

import java.lang.reflect.*;
import java.util.*;

public class Saver {
    public String save(Object o) throws IllegalAccessException, InvocationTargetException {
        return save(o, 0);
    }

    private String save(Object o, int jumps) throws IllegalAccessException, InvocationTargetException {
        if (o == null) return ""; //if no objects exist

        Class<?> clas = o.getClass();
        if (!clas.isAnnotationPresent(Element.class)) {
            System.out.println(clas.getName() + "have to be annotated"); //If no annotation exists //errorhandling
        }

        Element element = clas.getAnnotation(Element.class);
        StringBuilder xml = new StringBuilder(); //Creates the main string
        addIndent(xml, jumps); //Adds indents to make output pretty
        xml.append("<").append(element.name());

        StringBuilder attributes = new StringBuilder(); //creates a stringbuilder
        for (Method method : clas.getDeclaredMethods()) {
            if (method.isAnnotationPresent(ElementField.class)) {
                ElementField field = method.getAnnotation(ElementField.class);
                Object value = method.invoke(o);
                attributes.append(String.format(" %s=\"%s\"", field.name(), value));
                //xml.append(String.format(" %s=\"%s\"", field.name(), value.toString()));
            }
        }
        xml.append(attributes);

        boolean hasSubelements = false;
        StringBuilder subElements = new StringBuilder(); //Creates a string for the subelements
        for (Method method : clas.getDeclaredMethods()) {
            if (method.isAnnotationPresent(SubElements.class) && method.invoke(o) != null) {               
                Object[] subObjects = (Object[]) method.invoke(o);
                //addIndent(xml, jumps + 1);
                if (subObjects.length > 0) {
                    hasSubelements = true;
                    SubElements sub = method.getAnnotation(SubElements.class);
                    subElements.append(String.format("\n%s<%s>\n", indent(jumps + 1), sub.name()));
                    for (Object subObj : subObjects) {
                        subElements.append(save(subObj, jumps + 2)); //recursivly calls function for subelements
                    }
                    //subElements.append(String.format("%s</%s>\n", indent(jumps + 1), sub.name()));
                    subElements.append(indent(jumps + 1)).append("</").append(sub.name()).append(">");
                }
            }
        }

        if (hasSubelements) {
            xml.append(">").append(subElements);
            xml.append("\n").append(indent(jumps)).append("</").append(element.name()).append(">\n");
        } else {
            xml.append("/>\n");
        }

        return xml.toString();
    }

    private void addIndent(StringBuilder xml, int jumps) { //Adds indents to the string to create a pretty output
        for (int i = 0; i < jumps; i++) {
            xml.append("  ");
        }
    }
    private String indent(int jumps) { //Allows us to indent other than to a set string
        return "  ".repeat(jumps);
    }
}
