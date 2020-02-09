package com.github.x7fffffff;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.printer.PrettyPrinterConfiguration;

import java.io.InputStream;

public class Main {


    public static void main(String[] args) throws Exception {

        try (final InputStream is = Main.class.getClassLoader().getResourceAsStream("Test.java")) {
            final FunctionsVisitor context = getPSMCode(is);
            System.out.println(context.toString());



        }

    }

    public static FunctionsVisitor getPSMCode(InputStream is) {
        final CompilationUnit cu = StaticJavaParser.parse(is);
        final PrettyPrinterConfiguration configuration = new PrettyPrinterConfiguration();
        final SourcePrinter sourcePrinter = new SourcePrinter(configuration);
        final JavaVisitor prettyPrintVisitor = new JavaVisitor(sourcePrinter, configuration, new SQLPSQMDialect(configuration));
        final FunctionsVisitor context = new FunctionsVisitor();
        cu.accept(prettyPrintVisitor, context);
        return context;
    }


}
