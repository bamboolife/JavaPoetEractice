package com.bamboo.javapoet.spec;

import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;

import javax.lang.model.element.Modifier;

public class PlaceHolder_$N_Demo {

	public static void main(String[] args) {

		try {

			FieldSpec aField = FieldSpec.builder(int.class, "a").build();
			FieldSpec bField = FieldSpec.builder(int.class, "b").build();

			MethodSpec sumSpec = MethodSpec.methodBuilder("sum")
				.addModifiers(Modifier.PRIVATE)
				.returns(int.class)
				.addStatement("return $N + $N", aField, bField) // 引用变量名称
				.build();

			MethodSpec getSum = MethodSpec.methodBuilder("getSum")
				.addModifiers(Modifier.PUBLIC)
				.returns(int.class)
				.addStatement("return $N()", sumSpec) // 引用方法名称
				.build();

			TypeSpec person = TypeSpec.classBuilder("Person") // 使用classBuilder创建类
				.addField(aField)
				.addField(bField)
				.addMethod(sumSpec)
				.addMethod(getSum)
				.build();

			JavaFile javaFile = JavaFile.builder("com.teaphy.poet.generated", person)
				.build();
			javaFile.writeTo(System.out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
