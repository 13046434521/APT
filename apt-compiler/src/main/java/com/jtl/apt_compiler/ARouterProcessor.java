package com.jtl.apt_compiler;

import com.google.auto.service.AutoService;
import com.jtl.apt_annotation.ARouter;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic;


import static javax.lang.model.element.Modifier.FINAL;
import static javax.lang.model.element.Modifier.PUBLIC;
import static javax.lang.model.element.Modifier.STATIC;


@AutoService(Processor.class)
@SupportedAnnotationTypes("com.jtl.apt_annotation.ARouter")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class ARouterProcessor extends AbstractProcessor {
    Map<String, String> options;
    // 打印信息
    Messager messager;
    // 生成文件工具
    Filer filer;
    // 操作Element工具
    Elements elementUtils;

    Types typeUtils;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        options = processingEnv.getOptions();
        messager = processingEnv.getMessager();
        filer = processingEnv.getFiler();
        elementUtils = processingEnv.getElementUtils();
        typeUtils = processingEnv.getTypeUtils();

        String msg = options.get("student");
//        messager.printMessage(Diagnostic.Kind.NOTE,"-----------------------"+msg);
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        Set<? extends Element> aRouterSet = roundEnvironment.getElementsAnnotatedWith(ARouter.class);
        messager.printMessage(Diagnostic.Kind.NOTE, aRouterSet.size() + " /////");

        // aRouterSet 中每有一个被ARouter修饰的，就会成为一个element，被添加进set集合中
        for (Element element : set) {
            // 方法
            MethodSpec methodSpec = MethodSpec.methodBuilder("main") // 方法名
                    .returns(void.class) // 返回类型
                    .addModifiers(PUBLIC, STATIC) // 方法的权限及关键字
                    .addParameter(String[].class, "args") // 参数类型与名称
                    .addStatement("$S,") // 方法体
                    .build(); // build

            // 类
            TypeSpec typeSpec = TypeSpec.classBuilder("JTLARouter")
                    .addModifiers(PUBLIC, FINAL)
                    .addMethod(methodSpec)
                    .build();
            // 包
//            JavaFile javaFile = JavaFile.builder(element.getSimpleName().toString()).build();


            messager.printMessage(Diagnostic.Kind.NOTE, element.getSimpleName().toString());
        }
        return true;
    }
}