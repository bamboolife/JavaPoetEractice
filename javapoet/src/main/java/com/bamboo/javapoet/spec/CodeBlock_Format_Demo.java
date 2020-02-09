package com.bamboo.javapoet.spec;

import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CodeBlock_Format_Demo {
	
	public static void main(String[] args) {
		try {
			CodeBlock relative = CodeBlock.builder()
				.add("I eat $L $L", 3, "apple")
				.addStatement("") // 添加换行
				.build();
			
			CodeBlock position = CodeBlock.builder()
				.add("I eat $2L $1L", "apple", 3)
				.addStatement("")
				.build();
			
			Map<String, Object> map = new HashMap<>();
			map.put("count", 3);
			map.put("name", "apple");
			
			CodeBlock named = CodeBlock.builder()
				.addNamed("I eat $count:L $name:L", map)
				.addStatement("")
				.build();
			
			TypeSpec person = TypeSpec.classBuilder("Person") // 使用classBuilder创建类
				.addInitializerBlock(relative)
				.addInitializerBlock(position)
				.addInitializerBlock(named)
				.build();
			
			JavaFile javaFile = JavaFile.builder("com.teaphy.poet.generated", person)
				.build();
			javaFile.writeTo(System.out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
