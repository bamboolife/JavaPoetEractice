package com.bamboo.javapoet.spec;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;
import java.io.IOException;
import java.util.Date;

public class PlaceHolder_$T_Demo {

	public static void main(String[] args) {
		try {

			ClassName className = ClassName.get("com.teaphy.poet.module", "Student");

			MethodSpec stuMethod = MethodSpec.methodBuilder("getStudent")
				.returns(className)
				.addStatement("return $T()", className)
				.build();

			MethodSpec dateMethod = MethodSpec.methodBuilder("getDate")
				.returns(Date.class)
				.addStatement("return $T()", Date.class)
				.build();

			TypeSpec person = TypeSpec.classBuilder("Person") // 使用classBuilder创建类
				.addMethod(stuMethod)
				.addMethod(dateMethod)
				.build();

			JavaFile javaFile = JavaFile.builder("com.teaphy.poet.generated", person)
				.build();
			javaFile.writeTo(System.out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
