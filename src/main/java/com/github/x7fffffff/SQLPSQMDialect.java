package com.github.x7fffffff;

import com.github.javaparser.printer.PrettyPrinterConfiguration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SQLPSQMDialect {
    private static final Map<String, String> convMap = new HashMap<>();

    static {
        convMap.put("int", "int");
        convMap.put("Integer", "int");
    }

    private final PrettyPrinterConfiguration configuration;

    public SQLPSQMDialect(PrettyPrinterConfiguration configuration) {

        this.configuration = configuration;
    }


    String paramsDelim() {
        return ", ";
    }

    String expressionDelim() {
        return ";";
    }

    String functionSignatureBegin(String name) {
        return "CREATE FUNCTION " + name + "(";
    }

    String functionSignatureEnd(String returnType) {
        return ") RETURNS " + returnType;

    }

    String convertFromJavaType(String javaType) {
        return convMap.get(javaType);
    }

    String paramTemplate(String name, String javaType) {
        return name + " " + convertFromJavaType(javaType);
    }

    public String beginBlock() {
        return "BEGIN";
    }

    public String endBlock() {
        return "END";
    }


    public String declareBlockBegin() {
        return "DECLARE";
    }

    public boolean hasDeclareBlock(){
        return false;
    }


    public String functionCall() {
        return "Call ";
    }
}
