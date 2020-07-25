package edu.ptu.javatest._20_ooad._50_dynamic;

import org.junit.Assert;

import java.util.List;

import javax.lang.model.element.Element;
import javax.lang.model.element.PackageElement;
import javax.lang.model.util.Elements;

//PackageElement TypeElement ExecutableElement VariableElement
//TypeParameterElement
public class _04_AnotationAtCompileTest {

    public void testGetAnnotation(Element element) {
        Deprecated annotation = element.getAnnotation(Deprecated.class);
        System.out.println("ElemProcessor get Deprecated Annotation");
    }

    public void testGetAnnotation(PackageElement element) {
        List<? extends Element> enclosedElements = element.getEnclosedElements();//该类的子元素。TypeElement
        Assert.assertNull(element.getEnclosingElement());//该类的父类元素

        //获取包名


    }
}
