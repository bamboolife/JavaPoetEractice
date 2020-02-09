package com.bamboo.javapoet.spec;

import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;
import java.io.IOException;
import javax.lang.model.element.Modifier;

public class TypeSpec_Interface_Demo {

	public static void main(String[] args) {
		try {

			// 接口中声明的属性必须为被`public static final`
			FieldSpec versionField = FieldSpec.builder(int.class, "Version")
				.addModifiers(Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL)// 属性默认为Public Static Final.
				.initializer("$L", 1)
				.build();

			// 接口中声明的方法必须被[abstract, static, default]的其中之一修饰
			MethodSpec abstractMeth = MethodSpec.methodBuilder("swim")
				.addModifiers(Modifier.ABSTRACT, Modifier.PUBLIC) // abstract方法必须被[public, private]其中之一修饰
				.build();
			MethodSpec defaultMeth = MethodSpec.methodBuilder("tintDefault")
				.addModifiers(Modifier.DEFAULT, Modifier.PRIVATE)// default方法必须被[public, private]其中之一修饰
				.addStatement("$T.out.println($S);", System.class, "This is the example of the default method in the interface")
				.build();
			MethodSpec staticMeth = MethodSpec.methodBuilder("tintStatic")
				.addModifiers(Modifier.STATIC, Modifier.PUBLIC)// static方法必须被[public, private]其中之一修饰
				.addStatement("$T.out.println($S);", System.class, "This is the example of the static method in the interface")
				.build();

			TypeSpec faceBuilder = TypeSpec.interfaceBuilder("ISwim")
				.addModifiers(Modifier.PUBLIC) // 必须设置修饰符，public/private,否则报错`java.lang.IllegalArgumentException: modifiers [] must contain one of [public, private]`
				.addField(versionField)
				.addMethod(abstractMeth)
				.addMethod(defaultMeth)
				.addMethod(staticMeth)
				.build();
			JavaFile javaFile = JavaFile.builder("com.teaphy.poet.generated", faceBuilder)
				.build();
			javaFile.writeTo(System.out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
