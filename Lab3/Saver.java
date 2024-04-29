package Lab3;

import java.lang.reflect.*;
import java.util.*;

public class Saver {
    public String save(Object o) throws IllegalAccessException, InvocationTargetException {
        return save(o, 0);
    }

    private String save(Object o, int depth) throws IllegalAccessException, InvocationTargetException {
        if (o == null) return "";

        Class<?> clas = o.getClass();
        if (!clas.isAnnotationPresent(Element.class)) {
            System.out.println(clas.getName() + "have to be annotated");
        }

        Element element = clas.getAnnotation(Element.class);
        StringBuilder xml = new StringBuilder();
        appendIndentation(xml, depth);
        xml.append(String.format("<%s", element.name()));

        // Handling fields and properties
        Map<String, String> fieldsXml = new HashMap<>();
        for (Method method : clas.getDeclaredMethods()) {
            if (method.isAnnotationPresent(ElementField.class)) {
                ElementField field = method.getAnnotation(ElementField.class);
                Object value = method.invoke(o);
                fieldsXml.put(field.name(), value.toString());
            }
        }

        // Append attributes or single value content to XML
        boolean hasFields = !fieldsXml.isEmpty();
        if (hasFields) {
            for (Map.Entry<String, String> entry : fieldsXml.entrySet()) {
                xml.append(String.format(" %s=\"%s\"", entry.getKey(), entry.getValue()));
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
                appendIndentation(xml, depth + 1);
                xml.append(String.format("<%s>\n", sub.name()));
                if (subObjects != null) {
                    for (Object subObj : subObjects) {
                        xml.append(save(subObj, depth + 2)); // Recursive call for nested objects with increased depth
                    }
                }
                appendIndentation(xml, depth + 1);
                xml.append(String.format("</%s>\n", sub.name()));
            }
        }

        if (!hasSubelements && !hasFields) {
            xml.append("\n");
        }
        appendIndentation(xml, depth);
        xml.append(String.format("</%s>\n", element.name()));

        return xml.toString();
    }

    private void appendIndentation(StringBuilder xml, int depth) {
        for (int i = 0; i < depth; i++) {
            xml.append("  ");
        }
    }
}
