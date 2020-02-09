package com.bamboo.javapoet.spec;

import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;

public class PlaceHolder_$S_Demo {

	public static void main(String[] args) {
		try {

			// 创建一个成员变量并初始化 (name = "AAA")
			FieldSpec nameField = FieldSpec.builder(String.class, "name")
				.initializer("$S", "AAA") // 成员变量实例化
				.build();

			TypeSpec person = TypeSpec.classBuilder("Person") // 使用classBuilder创建类
				.addField(nameField)
				.build();

			JavaFile javaFile = JavaFile.builder("com.teaphy.poet.generated", person)
				.build();
			javaFile.writeTo(System.out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
