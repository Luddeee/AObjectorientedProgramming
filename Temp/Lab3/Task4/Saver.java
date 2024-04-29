package Temp.Lab3.Task4;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.Annotation;

public class Saver {

    String xml;
    int depth;

    ElementField value;
    SubElements subnodes;
    Object top;
    Object[] test123;
    Object node_a;
    Object o;

    // methods
    Method getValueMethod = null;
    Method getChildrenMethod = null;

    public String save(Object o) throws NoSuchMethodException, SecurityException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException {

        value = null;
        subnodes = null;
        top = null;
        test123 = null;
        node_a = o.getClass().getAnnotation(Element.class).name();
        this.o = o;

        // methods
        getValueMethod = null;
        getChildrenMethod = null;

        // used to require all methods
        Method[] methods = o.getClass().getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(ElementField.class)) {
                value = method.getAnnotation(ElementField.class);
                top = method.invoke(o); // gets value parameter
                getValueMethod = method; // getValue()

            } else if (method.isAnnotationPresent(SubElements.class)) {
                subnodes = method.getAnnotation(SubElements.class);
                getChildrenMethod = method; // getChildren()
            }
        }

        depth = 0;
        xml = "<" + node_a + " " + value.name() + "=\"" + top.toString() + '\"' + ">\n";
        depth = 1;
        xml += depthIndent() + "<" + subnodes.name() + ">\n";

        // root subnodes
        Object[] childArray = (Object[]) getChildrenMethod.invoke(o);
        for (Object child : childArray) {
            visitNode(child);
        }

        xml += depthIndent() + "</" + subnodes.name() + ">\n" + "</" + node_a + ">";

        return xml;
    }

    public void visitNode(Object node)
            throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        depth++;
        xml += depthIndent() + "<" + node_a + " " + value.name() + "=\"" + getValueMethod.invoke(node) + '\"' + "";

        Object[] nodes = null;
        try {
            nodes = (Object[]) getChildrenMethod.invoke(node);

        } catch (Exception e) {
            // TODO: handle exception
        }

        if (nodes != null) {
            xml += ">\n";
            depth++;
            xml += depthIndent() + "<" + subnodes.name() + ">\n";

            for (Object object : nodes) {
                visitNode(object);
            }

            xml += depthIndent() + "</" + subnodes.name() + ">\n";
            depth--;
            xml += depthIndent() + "</" + node_a + ">\n";

        } else {
            xml += "/>\n";
        }

        depth--;

    }

    public String depthIndent() {
        String indent = "";
        for (int i = 0; i < depth; i++) {
            indent += " ";
        }

        return indent;
    }

}
