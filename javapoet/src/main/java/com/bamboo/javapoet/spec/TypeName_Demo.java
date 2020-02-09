package com.bamboo.javapoet.spec;

import com.bamboo.javapoet.module.Student;
import com.squareup.javapoet.ArrayTypeName;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import com.squareup.javapoet.TypeVariableName;
import com.squareup.javapoet.WildcardTypeName;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.List;

public class TypeName_Demo {

	private List<? extends Student> stuList;

	public static void main(String[] args) {
		try {

			// 声明类型
			ClassName stu = ClassName.get("com.teaphy.poet.module", "Student");

			MethodSpec stuMethod = MethodSpec.methodBuilder("getStudent")
				.returns(stu)
				.addStatement("return $T()", stu)
				.build();


			ClassName list = ClassName.get("java.util", "List");
			ClassName arrayList = ClassName.get("java.util", "ArrayList");
			// 参数化类型
			TypeName listOfStudent = ParameterizedTypeName.get(list, stu);

			MethodSpec allMethod = MethodSpec.methodBuilder("getAll")
				.returns(listOfStudent)
				.addStatement("$T result = new $T()", listOfStudent, arrayList)
				.addStatement("result.add(new $T())", stu)
				.addStatement("result.add(new $T())", stu)
				.addStatement("result.add(new $T())", stu)
				.addStatement("return result")
				.build();

			// 数组
			// 1. of - 通过指定元素类型生成包含该元素的数组
			// 2. get - 获取mirror/type等效的数组类型的标识符
			TypeName stus = ArrayTypeName.of(Student.class);
			FieldSpec stusField = FieldSpec.builder(stus, "stus")
				.initializer("new $T()", stus)
				.build();

			// 类型变量
			TypeVariableName tTypeVariable = TypeVariableName.get("T");
			FieldSpec tField = FieldSpec.builder(tTypeVariable, "t").build();
			MethodSpec tMethod = MethodSpec.methodBuilder("tyepVar")
				.addTypeVariable(tTypeVariable)
				.returns(tTypeVariable)
				.addStatement("return $N", tField)
				.build();

			TypeSpec tvSpec = TypeSpec.classBuilder("Sex")
				.addTypeVariable(tTypeVariable)
				.addField(tField)
				.addMethod(tMethod)
				.build();

			// 通配符类型
			Field stuListField = TypeName_Demo.class.getDeclaredField("stuList");
			Type stuLisGenericType = stuListField.getGenericType();
			ParameterizedType parameterizedType = (ParameterizedType) stuLisGenericType;
			Type[] typesNum = parameterizedType.getActualTypeArguments();
			WildcardType wildcardType = (WildcardType) typesNum[0];
			TypeName wildcardTypeName = WildcardTypeName.get(wildcardType);
			// 参数化类型
			TypeName wildcardTypeNameList = ParameterizedTypeName.get(list, wildcardTypeName);
			FieldSpec clzField = FieldSpec.builder(wildcardTypeNameList, "clzStudent")
				.build();


			TypeSpec person = TypeSpec.classBuilder("Person") // 使用classBuilder创建类
				.addField(clzField)
				.addField(stusField)
				.addMethod(stuMethod)
				.addMethod(allMethod)
				.addType(tvSpec)
				.build();

			JavaFile javaFile = JavaFile.builder("com.teaphy.poet.generated", person)
				.build();
			javaFile.writeTo(System.out);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
	}
}
