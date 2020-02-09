package com.github.x7fffffff;

import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class LocalVarAdapter extends VoidVisitorAdapter<FunctionsVisitor> {
    final Map<String,String> localVars = new LinkedHashMap<>();
    @Override
    public void visit(VariableDeclarationExpr n, FunctionsVisitor arg) {
        super.visit(n, arg);
    }

    @Override
    public void visit(VariableDeclarator n, FunctionsVisitor arg) {
        localVars.put(n.getNameAsString(), n.getTypeAsString());
        super.visit(n, arg);
    }
}
