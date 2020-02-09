package com.github.x7fffffff;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Function {


    private final String name;
    private final Map<String, String> params;
    private final Map<String, String> localVars;
    private final String source;
    private final List<Function> calls;

    public Function(String name, Map<String, String> params, Map<String, String> localVars, String source, List<Function> calls) {
        this.name = name;
        this.params = Collections.unmodifiableMap(params);
        this.localVars = Collections.unmodifiableMap(localVars);
        this.source = source;
        this.calls = calls;
    }

    public Function(String name, Map<String, String> params) {
        this.name = name;
        this.params = Collections.unmodifiableMap(params);
        this.localVars = null;
        this.source = null;
        this.calls = null;
    }


    public String getName() {
        return name;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public Map<String, String> getLocalVars() {
        return localVars;
    }

    public String getSource() {
        return source;
    }

    public List<Function> getCalls() {
        return calls;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Function function = (Function) o;
        return name.equals(function.name) &&
                areEqual(params, function.params);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, params);
    }

    private boolean areEqual(Map<String, String> first, Map<String, String> second) {
        if (first.size() != second.size()) {
            return false;
        }

        return first.entrySet().stream()
                .allMatch(e -> e.getValue().equals(second.get(e.getKey())));
    }
}
