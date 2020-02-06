package com.github.x7fffffff;

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

    String lineDelim() {
        return "\r\n";
    }

    String paramsDelim() {
        return ", ";
    }

    String expressionDelim() {
        return ";";
    }

    String functionSignatureBegin(String name) {
        return "CREATE FUNCTION " + name + "("; //+ parameterList + ") RETURNS " + returnType + lineDelim() +
        //"BEGIN" + lineDelim();
              /*  + body + lineDelim()
                + "END" + lineDelim();*/
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

    public String declareBlock(Map<String, String> localVars) {
        final StringBuilder declare = new StringBuilder("DECLARE").append(lineDelim());
        localVars.forEach((name, type) -> {
            declare.append(paramTemplate(name, type)).append(expressionDelim()).append(lineDelim());
        });
        return declare.toString();
    }

    public class ParamListBuilder {
        final List<String> params = new ArrayList<>();

        public ParamListBuilder add(String name, String javaType) {
            params.add(paramTemplate(name, javaType));
            return this;
        }

        public String build() {
            return String.join(paramsDelim(), params);
        }
    }


}
