package com.bamboo.javapoet.spec;

import com.bamboo.javapoet.annotations.Author;
import com.squareup.javapoet.ArrayTypeName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import com.squareup.javapoet.TypeVariableName;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.lang.model.element.Modifier;

public class MethodSpec_Demo {
	
	public static void main(String[] args) {
		try {
			
			// 声明字段：id、name
			FieldSpec idField = FieldSpec.builder(int.class, "id").build();
			FieldSpec nameField = FieldSpec.builder(String.class, "name").build();
			
			// 构造函数
			MethodSpec constructor = MethodSpec.constructorBuilder()
				.addModifiers(Modifier.PUBLIC)
				.addParameter(ParameterSpec.builder(int.class, "id").build())
				.addParameter(ParameterSpec.builder(String.class, "name").build())
				.addStatement("this.$N=$N", idField, "id")
				.addStatement("this.$N=$N", nameField, "name")
				.build();
			
			// 常规方法
			Map<String, Object> map = new HashMap<>();
			map.put("id", 1);
			map.put("name", "tea");
			TypeVariableName t = TypeVariableName.get("T");
			TypeName subArray = ArrayTypeName.of(String.class);
			MethodSpec doSwim = MethodSpec.methodBuilder("doSwim")
				.addModifiers(Modifier.PUBLIC) // 设置修饰关键字
				.addAnnotation(Author.class) // 设置注解
				.addParameter(ParameterSpec.builder(int.class, "id").build()) // 设置参数
				.addParameter(ParameterSpec.builder(String.class, "name").build())
				.addCode("$T a = $L", String.class, "A") // 设置方法体，此方法并不添加分号和换行。
				.addCode(";")
				.addCode("\n")
				.addComment("$L", "这是单行注释") // 设置单行注释
				.addException(IndexOutOfBoundsException.class) // 设置方法抛出的异常
				.addJavadoc("$L", "这里方法的文档注释") // 设置文档注释
				.addCode("$T desc = ", String.class)
				.addNamedCode("\"id: $id:L, name: $name:L\"", map) // 设置代码块，通过命名参数方式格式化字符串
				.addStatement("") // 设置分号/换行符
				.addTypeVariable(t) // 设置类型参数变量
				.addStatement("$T t", t) // 使用类型参数 声明局部变量
				.addStatement("long now = $T.currentTimeMillis()", System.class) // 设置分号、换行和缩进 -> start
				.beginControlFlow("if ($T.currentTimeMillis() < now)", System.class)
				.addStatement("$T.out.println($S)", System.class, "Time travelling, woo hoo!")
				.nextControlFlow("else if ($T.currentTimeMillis() == now)", System.class)
				.addStatement("$T.out.println($S)", System.class, "Time stood still!")
				.nextControlFlow("else")
				.addStatement("$T.out.println($S)", System.class, "Ok, time still moving forward")
				.addStatement("$T i = $L", int.class, 0)
				.beginControlFlow("do")
				.addStatement("i++")
				.endControlFlow("while(i < 5)") // 设置分号、换行和缩进 -> end
				.returns(t) // 设置返回值类型
				.addStatement("return t")
				.addParameter(subArray, "subjects")
				.varargs() // 设置最后一个参数为可变参数，注意，如果参数为可变参数，其数据类型必须为数组
				.build();
			
			TypeSpec person = TypeSpec.classBuilder("Person") // 使用classBuilder创建类
				.addField(idField)
				.addField(nameField)
				.addMethod(constructor)
				.addMethod(doSwim)
				.build();
			
			JavaFile javaFile = JavaFile.builder("com.teaphy.poet.generated", person)
				.build();
			javaFile.writeTo(System.out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
