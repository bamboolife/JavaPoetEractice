package com.bamboo.javapoet.spec;

import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;
import java.io.IOException;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.lang.model.element.Modifier;

public class TypeSpec_Annotation_Demo {

	public static void main(String[] args) {
		try {

			AnnotationSpec annoDoc = AnnotationSpec.builder(Documented.class).build();
			AnnotationSpec annoRet = AnnotationSpec.builder(Retention.class)
				.addMember("value", "$T.$L", RetentionPolicy.class, RetentionPolicy.RUNTIME)
				.build();
			AnnotationSpec annoTarget = AnnotationSpec.builder(Target.class)
				// $T 标识枚举类， $L 标识 枚举值
				.addMember("value", "$T.$L", ElementType.class, ElementType.TYPE)
				.addMember("value", "$T.$L", ElementType.class, ElementType.METHOD)
				.addMember("value", "$T.$L", ElementType.class, ElementType.FIELD)
				.build();

			MethodSpec nameMeth = MethodSpec.methodBuilder("name")
				.addModifiers(Modifier.PUBLIC, Modifier.ABSTRACT)
				.returns(String.class)
				.defaultValue("$S", "tea") // 设置默认值
				.build();


			TypeSpec authorBuilder = TypeSpec.annotationBuilder("Author")
				.addModifiers(Modifier.PUBLIC)
				.addAnnotation(annoDoc)
				.addAnnotation(annoRet)
				.addAnnotation(annoTarget)
				.addMethod(nameMeth)
				.build();

			JavaFile javaFile = JavaFile.builder("com.teaphy.poet.generated", authorBuilder)
				.build();
			javaFile.writeTo(System.out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
