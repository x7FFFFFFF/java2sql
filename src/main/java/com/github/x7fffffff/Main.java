package com.github.x7fffffff;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.printer.PrettyPrinterConfiguration;

import java.io.File;
import java.io.InputStream;

public class Main {


    public static void main(String[] args) throws Exception {

        try (final InputStream is = Main.class.getClassLoader().getResourceAsStream("Test.java")) {
            final CompilationUnit cu = StaticJavaParser.parse(is);
            final JavaVisitor prettyPrintVisitor = new JavaVisitor(new PrettyPrinterConfiguration(), new SQLPSQMDialect());
            cu.accept(prettyPrintVisitor, null);
            System.out.println(prettyPrintVisitor.toString());


        }

    }


}
