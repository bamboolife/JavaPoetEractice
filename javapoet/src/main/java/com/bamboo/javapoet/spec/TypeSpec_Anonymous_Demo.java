package com.bamboo.javapoet.spec;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;

import javax.lang.model.element.Modifier;

public class TypeSpec_Anonymous_Demo {

	public static void main(String[] args) {
		try {

			// 匿名内部类实现的接口
			ClassName swimInterface = ClassName.get("com.teaphy.poet.interfaces", "ISwim");

			// 重写的方法
			MethodSpec swimMeth = MethodSpec.methodBuilder("swim")
				.addModifiers(Modifier.PUBLIC)
				.addAnnotation(Override.class)
				.build();
			// 创建匿名内部类
			TypeSpec swimBuilder = TypeSpec.anonymousClassBuilder("")
				.addSuperinterface(swimInterface)
				.addMethod(swimMeth)
				.build();

			FieldSpec fieldSwim = FieldSpec.builder(swimInterface, "swim")
				.initializer("$L", swimBuilder)
				.build();

			// 匿名内部类的超类
			ClassName frutesClass = ClassName.get("com.teaphy.poet.module", "Fruits");

			String nameFru = "Apple";
			float priceFru = 10.5f;
			// 用于传递参数
			CodeBlock pramBlock = CodeBlock.builder()
				.add("$S, $L", nameFru, priceFru)
				.build();

			MethodSpec printDesc = MethodSpec.methodBuilder("printDesc")
				.addAnnotation(Override.class)
				.addStatement("$T.out.println($T.format(\"the price of the %s is %s\", $N, $N))", System.class,
					String.class,
					"name",
					"price")
				.build();

			// 创建匿名内部类
			TypeSpec fruitBuilder = TypeSpec.anonymousClassBuilder(pramBlock)
				.superclass(frutesClass)
				.addMethod(printDesc)
				.build();
			FieldSpec fieldFru = FieldSpec.builder(frutesClass, "fruit")
				.initializer("$L", fruitBuilder)
				.build();

			TypeSpec personBuilder = TypeSpec.classBuilder("Person")
				.addField(fieldSwim)
				.addField(fieldFru)
				.build();

			JavaFile javaFile = JavaFile.builder("com.teaphy.poet.generated", personBuilder)
				.build();
			javaFile.writeTo(System.out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
