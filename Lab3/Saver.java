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
        xml.append(String.format("<%s", element.name()));

        // Handling fields and properties
        for (Method method : clas.getDeclaredMethods()) {
            if (method.isAnnotationPresent(ElementField.class)) {
                ElementField field = method.getAnnotation(ElementField.class);
                Object value = method.invoke(o);
                xml.append(String.format(" %s=\"%s\"", field.name(), value.toString()));
            }
        }

        xml.append(">\n");

        // Handling sub-elements
        boolean hasSubelements = false;
        for (Method method : clas.getDeclaredMethods()) {
            if (method.isAnnotationPresent(SubElements.class)) {
                hasSubelements = true;
                SubElements sub = method.getAnnotation(SubElements.class);
                Object[] subObjects = (Object[]) method.invoke(o);
                addIndent(xml, jumps + 1);
                xml.append(String.format("<%s>\n", sub.name()));
                if (subObjects != null) {
                    for (Object subObj : subObjects) {
                        xml.append(save(subObj, jumps + 2)); // Recursive call for nested objects with increased jumps
                    }
                }
                addIndent(xml, jumps + 1);
                xml.append(String.format("</%s>\n", sub.name()));
            }
        }

        if (!hasSubelements) {
            xml.append("\n");
        }
        addIndent(xml, jumps);
        xml.append(String.format("</%s>\n", element.name()));

        return xml.toString();
    }

    private void addIndent(StringBuilder xml, int jumps) {
        for (int i = 0; i < jumps; i++) {
            xml.append("  ");
        }
    }
}
