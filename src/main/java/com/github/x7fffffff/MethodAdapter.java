package com.github.x7fffffff;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class MethodAdapter extends  MethodVisitor implements Opcodes {
    public MethodAdapter(final MethodVisitor mv) {
        super(ASM6, mv);
    }

    @Override
    public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
        /* TODO: System.err.println("CALL" + name); */

        /* do call */
        mv.visitMethodInsn(opcode, owner, name, desc, itf);

        /* TODO: System.err.println("RETURN" + name);  */
    }
}
