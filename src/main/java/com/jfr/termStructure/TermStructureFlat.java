package com.jfr.termStructure;

import com.jfr.FinRecipes;

public class TermStructureFlat extends TermStructure {
    private double R; // interest rate

    public String name() {
        return "flat";
    }

    public int no_parameters() {
        return 1;
    }

    // default interest rate
    public void init() {
        R = 0.10;
    }

    public TermStructureFlat() {
        init();
    }

    public TermStructureFlat(double r) {
        init();
        R = r;
    }

    public double yield(double T) {
        if (T >= 0) {
            return R;
        }
        return 0;
    }

    public double discount_factor(double T) {
        return FinRecipes.term_structure_discount_factor_from_yield(R, T);
    }

    public void set_parameters(double[] x) {
        if (x.length > 0) {
            R = x[0];
        }
    }

    public double[] get_parameters() {
        double[] x = new double[1];
        x[0] = R;
        return x;
    }

    public void set_int_rate(double r) {
        R = r;
    }

    public double[] parameter_lower_bounds() {
        double[] x = new double[no_parameters()];
        x[0] = -0.2;
        return x; // hope to not have interest rates below -20%
    }

    public double[] parameter_upper_bounds() {
        double[] x = new double[no_parameters()];
        x[0] = 1.0;
        return x;  // hope to not have interest rates above 100%
    }
}
