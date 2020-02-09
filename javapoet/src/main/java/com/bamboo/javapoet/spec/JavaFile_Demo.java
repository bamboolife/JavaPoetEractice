package com.bamboo.javapoet.spec;

import com.bamboo.javapoet.globle.CommonConst;
import com.bamboo.javapoet.globle.Gendar;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
public class JavaFile_Demo {
	
	public static void main(String[] args) {
		try {
			
			
			TypeSpec person = TypeSpec.classBuilder("Person") // 使用classBuilder创建类
				.addField(FieldSpec.builder(int.class, "id").build())
				.addField(FieldSpec.builder(String.class, "name").build())
				.build();
			
			JavaFile javaFile = JavaFile.builder("com.teaphy.poet.generated", person)
				.addFileComment("这是Person类的注释") // 添加注释
				.addStaticImport(CommonConst.class, "VERSION", "VERSION_CODE") // 通过指定类和静态方法/常量名静态导入方法/常量
				.addStaticImport(Gendar.MAN) // 静态导入枚举值
				.indent("--->") // 设置表示缩进的字符串
				.skipJavaLangImports(true) // 设置是否省略`java.lang`中类的导入。此时省略了`java.lang.String`的导入
				.build();
			javaFile.writeTo(System.out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
