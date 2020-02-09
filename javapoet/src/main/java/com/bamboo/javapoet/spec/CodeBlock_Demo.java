package com.bamboo.javapoet.spec;

import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CodeBlock_Demo {
	
	public static void main(String[] args) {
		try {
			
			
			
			// 常规方法
			Map<String, Object> map = new HashMap<>();
			map.put("id", 1);
			map.put("name", "tea");
			
			CodeBlock codeBlock = CodeBlock.builder()
				
				.add("$T a = $L", String.class, "A") // 设置代码，此方法并不添加分号和换行。
				.add(";")
				.add("\n")
				
				.addNamed("\"id: $id:L, name: $name:L\"", map) // 设置代码块，通过命名参数方式格式化字符串，此方法并不添加分号和换行。
				.add("\n")
				
				.addStatement("int result = 1") // for
				.beginControlFlow("for (int i = 0; i < 10; i++)")
				.addStatement("result = result +  i")
				.endControlFlow()
				
				.addStatement("long now = $T.currentTimeMillis()",
					System.class) // if - else if - ... - else
				.beginControlFlow("if ($T.currentTimeMillis() < now)", System.class)
				.addStatement("$T.out.println($S)", System.class, "Time travelling, woo hoo!")
				.nextControlFlow("else if ($T.currentTimeMillis() == now)", System.class)
				.addStatement("$T.out.println($S)", System.class, "Time stood still!")
				.nextControlFlow("else")
				.addStatement("$T.out.println($S)", System.class, "Ok, time still moving forward")
				.endControlFlow()
				.addStatement("$T.out.println($S)", System.class, "Ok, time still moving forward")
				.addStatement("$T i = $L", int.class, 0)
				
				.addStatement("int id = 10") // while
				.beginControlFlow("while( id > 0)")
				.addStatement("$T.out.println(\"id: \" + $L)", System.class, "id")
				.addStatement("id--")
				.endControlFlow()
				
				.beginControlFlow("do") // do while
				.addStatement("i++")
				.endControlFlow("while(i < 5)")
				
				.beginControlFlow("try") // try - catch
				.addStatement("throw new Exception($S)", "Failed")
				.nextControlFlow("catch ($T e)", Exception.class)
				.addStatement("throw new $T(e)", RuntimeException.class)
				.endControlFlow()
				
				.build();
			
			TypeSpec person = TypeSpec.classBuilder("Person") // 使用classBuilder创建类
				.addInitializerBlock(codeBlock)
				.build();
			
			JavaFile javaFile = JavaFile.builder("com.teaphy.poet.generated", person)
				.build();
			javaFile.writeTo(System.out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
