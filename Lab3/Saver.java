package Lab3;

import java.lang.reflect.*;
import java.util.*;

public class Saver {
    public String save(Object o) throws IllegalAccessException, InvocationTargetException {
        return save(o, 0);
    }

    private String save(Object o, int jumps) throws IllegalAccessException, InvocationTargetException {
        if (o == null) return "";

        Class<?> clas = o.getClass();
        if (!clas.isAnnotationPresent(Element.class)) {
            System.out.println(clas.getName() + "have to be annotated");
        }

        Element element = clas.getAnnotation(Element.class);
        StringBuilder xml = new StringBuilder();
        addIndent(xml, jumps);
        xml.append("<").append(element.name());

        // Handling fields and properties
        StringBuilder attributes = new StringBuilder();
        for (Method method : clas.getDeclaredMethods()) {
            if (method.isAnnotationPresent(ElementField.class)) {
                ElementField field = method.getAnnotation(ElementField.class);
                Object value = method.invoke(o);
                attributes.append(String.format(" %s=\"%s\"", field.name(), value));
                //xml.append(String.format(" %s=\"%s\"", field.name(), value.toString()));
            }
        }
        xml.append(attributes);

        // Handling sub-elements
        boolean hasSubelements = false;
        StringBuilder subElementsXml = new StringBuilder();
        for (Method method : clas.getDeclaredMethods()) {
            if (method.isAnnotationPresent(SubElements.class) && method.invoke(o) != null) {               
                Object[] subObjects = (Object[]) method.invoke(o);
                //addIndent(xml, jumps + 1);
                if (subObjects.length > 0) {
                    hasSubelements = true;
                    SubElements sub = method.getAnnotation(SubElements.class);
                    subElementsXml.append(String.format("\n%s<%s>\n", indent(jumps + 1), sub.name()));
                    for (Object subObj : subObjects) {
                        subElementsXml.append(save(subObj, jumps + 2)); // Recursive call for nested objects with increased jumps
                    }
                    //subElementsXml.append(String.format("%s</%s>\n", indent(jumps + 1), sub.name()));
                    subElementsXml.append(indent(jumps + 1)).append("</").append(sub.name()).append(">");
                }
            }
        }

        if (hasSubelements) {
            xml.append(">").append(subElementsXml);
            xml.append("\n").append(indent(jumps)).append("</").append(element.name()).append(">\n");
        } else {
            xml.append("/>\n");
        }

        return xml.toString();
    }

    private void addIndent(StringBuilder xml, int jumps) {
        for (int i = 0; i < jumps; i++) {
            xml.append("  ");
        }
    }
    private String indent(int jumps) {
        return "  ".repeat(jumps);
    }
}
