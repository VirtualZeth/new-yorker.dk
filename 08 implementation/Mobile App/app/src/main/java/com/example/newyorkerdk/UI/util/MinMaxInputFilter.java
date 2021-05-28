package com.example.newyorkerdk.UI.util;

import android.text.InputFilter;
import android.text.Spanned;

public class MinMaxInputFilter implements InputFilter {

    private double min, max;

    public MinMaxInputFilter(double min, double max) {
        this.min = min;
        this.max = max;
    }

    private boolean isInRange(double a, double b, double c) {

        return b > a ? c >= a && c <= b : c >= b && c <= a;
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {

        Double input = Double.parseDouble(dest.toString() + source.toString());

        if (isInRange(min, max, input)){
            return null;
        }

        return "";
    }
}
