package com.github.x7fffffff;

import java.util.*;

public class FunctionsVisitor {

    private String name;
    private Map<String, String> params;
    private Map<String, String> localVars;
    private final List<Function> calls = new ArrayList<>();
    private final Set<Function> functions = new LinkedHashSet<>();
    private final Set<Function> simpleFunctions = new LinkedHashSet<>();

    void functionBegin(String name, Map<String, String> params, Map<String, String> localVars) {
        this.name = name;
        this.params = params;
        this.localVars = localVars;
    }

    void functionCall(String name, List<String> argNames) {
        System.out.println("name = [" + name + "], arguments = [" + argNames + "]");
        calls.add(createCall(name, argNames));


    }

    private Function createCall(String name, List<String> argNames) {
        Map<String, String> args = new LinkedHashMap<>();
        for (String argName : argNames) {
            final String type = this.params.get(argName);
            if (type != null) {
                args.put(argName, type);
            } else {
                final String type2 = localVars.get(argName);
                if (type2 != null) {
                    args.put(argName, type2);
                }
            }
        }


        return new Function(name, args);
    }

    void functionEnd(String signature, Map<String, String> params, String source) {
        System.out.println("name = [" + signature + "], source = [" + source + "]");
        if (calls.isEmpty()) {
            simpleFunctions.add(new Function(name, params, localVars, source, Collections.emptyList()));
        } else {
            functions.add(new Function(name, params, localVars, source, new ArrayList<>(calls)));
        }


        this.name = null;
        this.params = null;
        this.localVars = null;
        calls.clear();
    }

    public String getName() {
        return name;
    }

    public Set<Function> getFunctions() {
        return functions;
    }

    public Set<Function> getSimpleFunctions() {
        return simpleFunctions;
    }
}
