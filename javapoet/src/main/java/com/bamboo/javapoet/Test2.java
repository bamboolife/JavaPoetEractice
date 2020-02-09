package com.bamboo.javapoet;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.io.File;

import javax.lang.model.element.Modifier;

public class Test2 {
    public static void main(String[] args) {
        try {

            ClassName persionClassName = ClassName.get("com.bamboo.javapoet", "Persion");//这种找到的形式都和class形式一样可以用

            MethodSpec main = MethodSpec.methodBuilder("main")
                    .addModifiers(Modifier.STATIC, Modifier.PUBLIC)
                    .addParameter(String[].class, "args") //添加参数
                    .addParameter(persionClassName, "myPersion")//添加参数
                    .returns(persionClassName)
                    .addStatement("int value=$L", 10)
                    .addStatement("$T obj=new $T()", persionClassName, Persion.class)
                    .addStatement("$T.out.println($S)", System.class, "来吧创建一个类来玩玩吧!")
                    .beginControlFlow("while(value<$L)", 10)//循环开始
                    .addStatement("int value2=$L", 10)
                    .endControlFlow()
                    .addStatement("return new $T()", Class.forName("com.bamboo.javapoet.Persion"))
                    .build();
            //创建变量
            FieldSpec fieldSpec = FieldSpec.builder(String.class, "VALUE")
                    .addModifiers(Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL)
                    .initializer("$S", "AAA").build();

            TypeSpec classSpec = TypeSpec.classBuilder("Test")
                    .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                    .addField(String.class, "name", Modifier.PUBLIC)
                    .addField(fieldSpec)
                    .addMethod(main)
//                    .addType(classSpec2)//追加一个内部类
                    .build();

            JavaFile build = JavaFile.builder("com.awen.demo", classSpec).build();

            build.writeTo(System.out);
        } catch (Exception e) {
            System.out.println("发生错误:" + e.getLocalizedMessage());
        }
    }

}
