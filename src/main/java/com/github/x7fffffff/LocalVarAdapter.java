package com.github.x7fffffff;

import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.HashMap;
import java.util.Map;

public class LocalVarAdapter extends VoidVisitorAdapter<Void> {
    final Map<String,String> localVars = new HashMap<>();
    @Override
    public void visit(VariableDeclarationExpr n, Void arg) {
        super.visit(n, arg);
    }

    @Override
    public void visit(VariableDeclarator n, Void arg) {
        localVars.put(n.getNameAsString(), n.getTypeAsString());
        super.visit(n, arg);
    }
}
