package edu.ptu.java.process;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.inject.Provider;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
@SupportedAnnotationTypes({ "java.lang.Deprecated" ,"anotation.A"})
public class ElemProcessor extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        Iterator<? extends TypeElement> iterator = set.iterator();
        while (iterator.hasNext()){
            TypeElement next = iterator.next();
            Element enclosingElement = next.getEnclosingElement();
            List<? extends AnnotationMirror> annotationMirrors = enclosingElement.getAnnotationMirrors();
            System.out.println("ElemProcessor   getQualifiedName "+next.getQualifiedName());
            System.out.println("ElemProcessor   Element "+next);
            System.out.println("ElemProcessor   getKind "+next.asType().getKind());

        }
        System.out.println("ElemProcessor   "+roundEnvironment.processingOver());
        return false;
    }
}
