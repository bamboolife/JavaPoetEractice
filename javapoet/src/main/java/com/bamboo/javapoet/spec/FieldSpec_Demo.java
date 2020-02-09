package com.bamboo.javapoet.spec;

import com.bamboo.javapoet.annotations.Author;
import com.bamboo.javapoet.module.Student;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;
import java.io.IOException;
import javax.lang.model.element.Modifier;

public class FieldSpec_Demo {

	public static void main(String[] args) {
		try {

			// // 创建一个 int 类型 的 成员变量 id,
			FieldSpec idField = FieldSpec.builder(int.class, "age")
				.build();

			// 创建一个成员变量并初始化 (name = "AAA")
			FieldSpec nameField = FieldSpec.builder(String.class, "name")
				.addAnnotation(Author.class)
				.addModifiers(Modifier.PUBLIC) // 添加修饰符
				.initializer("$S", "AAA") // 成员变量实例化
				.build();
			// 创建一个引用类型并初始化
			FieldSpec stuField = FieldSpec.builder(Student.class, "student")
				.addModifiers(Modifier.PRIVATE)
				.initializer("new $T()", Student.class)
				.build();

			TypeSpec person = TypeSpec.classBuilder("Person") // 使用classBuilder创建类
				.addField(idField)
				.addField(nameField)
				.addField(stuField)
				.build();

			JavaFile javaFile = JavaFile.builder("com.teaphy.poet.generated", person)
				.build();
			javaFile.writeTo(System.out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
