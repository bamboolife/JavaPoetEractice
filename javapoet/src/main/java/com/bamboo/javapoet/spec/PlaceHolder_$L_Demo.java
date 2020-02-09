package com.bamboo.javapoet.spec;

import com.bamboo.javapoet.annotations.Author;
import com.bamboo.javapoet.module.Student;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;
import java.io.IOException;
import java.lang.annotation.Annotation;

public class PlaceHolder_$L_Demo {

	public static void main(String[] args) {

		try {

			FieldSpec priField = FieldSpec.builder(int.class, "pri")
				.initializer("$L", 1) // 字面值为基本数据类型
				.build();

			FieldSpec strField = FieldSpec.builder(Class.class, "str")
				.initializer("str", "tea") // 字面值为String
				.build();

			FieldSpec clzField = FieldSpec.builder(Class.class, "clz")
				.initializer("$L", Student.class) // 字面值为类型声明
				.build();

			Annotation annoAuthor = Student.class.getAnnotation(Author.class);
			FieldSpec annoField = FieldSpec.builder(Class.class, "anno")
				.initializer("$L", annoAuthor) // 字面值为注解
				.build();

			CodeBlock codeBlock = CodeBlock.builder()
				.beginControlFlow("if (a > b)")
				.addStatement("return a")
				.nextControlFlow("else")
				.addStatement("return b")
				.endControlFlow()
				.build();
			FieldSpec blockField = FieldSpec.builder(int.class, "block")
				.initializer("$L", codeBlock) // 字面值为代码块
				.build();

			TypeSpec person = TypeSpec.classBuilder("Person") // 使用classBuilder创建类
				.addField(priField)
				.addField(strField)
				.addField(clzField)
				.addField(annoField)
				.addField(blockField)
				.build();

			JavaFile javaFile = JavaFile.builder("com.teaphy.poet.generated", person)
				.build();
			javaFile.writeTo(System.out);
		} catch (
			IOException e) {
			e.printStackTrace();
		}
	}
}
