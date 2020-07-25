package edu.ptu.java.process;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.inject.Provider;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

import edu.ptu.javatest._20_ooad._50_dynamic.AptAnno;
import edu.ptu.javatest._20_ooad._50_dynamic._03_AnotationTest;
import edu.ptu.javatest._20_ooad._50_dynamic._04_AnotationAtCompileTest;

@SupportedAnnotationTypes({"java.lang.Deprecated", "anotation.A", "edu.ptu.javatest._20_ooad._50_dynamic.AptAnno"})
@SupportedSourceVersion(SourceVersion.RELEASE_8)
//Annotation processors must be explicitly declared now
public class ElemProcessor extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        Iterator<? extends TypeElement> iterator = set.iterator();
        while (iterator.hasNext()) {
            TypeElement next = iterator.next();
            Element enclosingElement = next.getEnclosingElement();
            List<? extends AnnotationMirror> annotationMirrors = enclosingElement.getAnnotationMirrors();
            System.out.println("ElemProcessor   getQualifiedName " + next.getQualifiedName());
            System.out.println("ElemProcessor   Element " + next);
            System.out.println("ElemProcessor   getKind " + next.asType().getKind());

//            //获取annotation值
            if (enclosingElement.getAnnotation(Deprecated.class)!=null){

            }

        }
        if (roundEnvironment.getElementsAnnotatedWith(Deprecated.class) != null) {
            Set<? extends Element> elementsAnnotatedWith = roundEnvironment.getElementsAnnotatedWith(Deprecated.class);
            for (Element e : elementsAnnotatedWith) {
                new _04_AnotationAtCompileTest().testGetAnnotation(e);
            }

        }
        System.out.println("ElemProcessor   " + roundEnvironment.processingOver());
        return false;
    }




}
