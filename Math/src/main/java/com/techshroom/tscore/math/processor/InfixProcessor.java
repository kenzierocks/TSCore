package com.techshroom.tscore.math.processor;

import java.math.BigDecimal;

import com.techshroom.tscore.math.exceptions.EvalException;

public class InfixProcessor extends ExpressionProcessor {

    protected InfixProcessor(String proc) throws EvalException {
        super(proc);
    }

    @Override
    protected void preprocess() {

    }

    @Override
    public BigDecimal process() {
        return null;
    }
}
