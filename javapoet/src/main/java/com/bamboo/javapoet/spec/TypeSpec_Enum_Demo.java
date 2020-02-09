package com.bamboo.javapoet.spec;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;
import java.io.IOException;

public class TypeSpec_Enum_Demo {

	public static void main(String[] args) {
		try {
			TypeSpec enumBuilder = TypeSpec.enumBuilder("SexKind")
				.addEnumConstant("MAN")
				.addEnumConstant("WOMAN")
				.build();
			JavaFile javaFile = JavaFile.builder("com.teaphy.poet.generated", enumBuilder)
				.build();
			javaFile.writeTo(System.out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
