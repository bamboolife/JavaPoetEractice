package com.bamboo.javapoet.spec;

import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;
import java.io.IOException;

public class Javadoc_Demo {

	public static void main(String[] args) {
		try {

			String nameField = "name";
			CodeBlock nameAnno = CodeBlock.builder()
				.add("$L$L", nameField, "表示姓名")
				.build();
			FieldSpec nameFieldSpec = FieldSpec.builder(String.class, nameField)
				.addJavadoc(nameAnno)
				.build();


			TypeSpec personSpec = TypeSpec.classBuilder("Person") // 使用classBuilder创建类
				.addJavadoc("$L", "这是一段类的注释")
				.addField(nameFieldSpec)
				.build();

			JavaFile javaFile = JavaFile.builder("com.teaphy.poet.generated", personSpec)
				.build();
			javaFile.writeTo(System.out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
