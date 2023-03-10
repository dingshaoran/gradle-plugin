package com.android.visitor.factory;

import com.android.build.api.instrumentation.AsmClassVisitorFactory;
import com.android.build.api.instrumentation.ClassContext;
import com.android.build.api.instrumentation.ClassData;

import org.objectweb.asm.ClassVisitor;

public abstract class CopyFramesVisitorFactory implements AsmClassVisitorFactory<Parameters> {

    @Override
    public ClassVisitor createClassVisitor(ClassContext classContext, ClassVisitor classVisitor) {
        return new CopyFramesAllVisitor(classContext, classVisitor, getParameters().get().getVisit().get());
    }

    @Override
    public boolean isInstrumentable(ClassData classData) {
        return true;
    }

}