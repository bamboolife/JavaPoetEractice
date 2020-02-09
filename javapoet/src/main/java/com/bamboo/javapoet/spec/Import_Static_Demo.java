package com.bamboo.javapoet.spec;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;

public class Import_Static_Demo {


	public static void main(String[] args) {
		ClassName className = ClassName.get("com.teaphy.poet.module", "Student");
		TypeSpec importSpec = TypeSpec.classBuilder("ImportStaticSpec")
			.build();

	}
}
