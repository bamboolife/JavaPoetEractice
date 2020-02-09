package com.bamboo.javapoet.spec;

import com.bamboo.javapoet.annotations.Author;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;
import com.squareup.javapoet.TypeVariableName;

import java.io.IOException;

import javax.lang.model.element.Modifier;

public class TypeSpec_Class_Demo {

	public static void main(String[] args) {

		try {

			// 字段：id
			FieldSpec id = FieldSpec.builder(int.class, "id", Modifier.PUBLIC).build();

			// 初始化代码块
			CodeBlock initBlock = CodeBlock.builder()
				.add("$N = $L", id, 10)
				.build();
			// 注释块
			CodeBlock docBlock = CodeBlock.builder()
				.add("This is the example for generating class")
				.build();
			// 方法
			MethodSpec getIdMeth = MethodSpec.methodBuilder("getId")
				.returns(int.class)
				.addStatement("return this.$N", id)
				.build();
			// 静态代码块
			CodeBlock staticBlock = CodeBlock.builder()
				.add("$T EKY = $S", String.class, "key_tea")
				.build();

			// 接口
			ClassName swimInterface = ClassName.get("com.teaphy.poet.interfaces", "ISwim");
			// 超类
			ClassName supperClass = ClassName.get("com.teaphy.poet.module", "Student");

			// 类型变量
			TypeVariableName tTypeVariable = TypeVariableName.get("T");
			TypeVariableName vTypeVariable = TypeVariableName.get("V", Number.class);

			TypeSpec inner = TypeSpec.classBuilder("Inner").build();

			// 使用classBuilder创建类
			TypeSpec tea = TypeSpec.classBuilder("Tea")
				.addModifiers(Modifier.PUBLIC) // 设置修饰符
				.addAnnotation(Author.class) // 设置注解
				.addField(id) // 设置字段
				.addInitializerBlock(initBlock) // 设置初始化代码块
				.addJavadoc(docBlock) // 设置注释
				.addMethod(getIdMeth) // 设置方法
				.addStaticBlock(staticBlock) // 设置静态代码块
				.addSuperinterface(swimInterface) // 设置实现的接口
				.superclass(supperClass) // 设置父类
				.addTypeVariable(tTypeVariable) // 设置类型变量
				.addTypeVariable(vTypeVariable) // 设置类型变量
				.addType(inner) // 设置内部类
				.build();

			JavaFile javaFile = JavaFile.builder("com.teaphy.poet.generated", tea)
				.build();
			javaFile.writeTo(System.out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
