package com.github.x7fffffff;

import org.objectweb.asm.ClassVisitor;

import static org.objectweb.asm.Opcodes.ASM6;

public class Visitor extends ClassVisitor {
    public Visitor() {
        super(ASM6);
    }
}
