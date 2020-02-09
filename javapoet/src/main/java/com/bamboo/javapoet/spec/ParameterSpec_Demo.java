package com.bamboo.javapoet.spec;

import com.bamboo.javapoet.annotations.Author;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;

import javax.lang.model.element.Modifier;

public class ParameterSpec_Demo {
	
	public static void main(String[] args) {
		try {
			
			ParameterSpec namePram = ParameterSpec.builder(String.class, "name")
				.addAnnotation(Author.class)
				.build();
			
			ParameterSpec sexPram = ParameterSpec.builder(int.class, "gender")
				.addModifiers(Modifier.FINAL)
				.build();
			
			// 构造函数
			MethodSpec constructor = MethodSpec.constructorBuilder()
				.addModifiers(Modifier.PUBLIC)
				.addParameter(int.class, "id")
				.addParameter(namePram)
				.addParameter(sexPram)
				.build();
			
			TypeSpec person = TypeSpec.classBuilder("Person") // 使用classBuilder创建类
				.addMethod(constructor)
				.build();
			
			JavaFile javaFile = JavaFile.builder("com.teaphy.poet.generated", person)
				.build();
			javaFile.writeTo(System.out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
