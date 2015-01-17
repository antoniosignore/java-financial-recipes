package com.jfr.termStructure;


import com.jfr.FinRecipes;

public class TermStructureCubicSpline extends TermStructure implements Cloneable {
    private double b_;
    private double c_;
    private double d_;
    private double[] f_;
    private double[] knots_;
    private int no_knots_;

    public String name() {
        return "cub_spline";
    }

    public int no_knots() {
        return no_knots_;
    }

    public void init() {  // initialize at default values
        no_knots_ = 3;
        knots_[0] = 1.0; // default is 3  knots.  This is the usual spline used in finance
        knots_[1] = 5.0; // you can change this with the set_knots function
        knots_[2] = 9.0;
        b_ = -0.05;
        c_ = 0.0007;
        d_ = -0.00002;
        f_[0] = (0.0008);
        f_[1] = (-0.0003);
        f_[2] = (0.00001);
    }

    public TermStructureCubicSpline() {
        init();
    }

    public TermStructureCubicSpline(TermStructureCubicSpline t) {
        init();
        b_ = t.b_;
        c_ = t.c_;
        d_ = t.d_;
        for (int i = 0; i < t.no_knots_; ++i) {
            f_[i] = t.f_[i];
            knots_[i] = t.knots_[i];
        }
    }

    public TermStructureCubicSpline clone(TermStructureCubicSpline t) {
        TermStructureCubicSpline term = new TermStructureCubicSpline(t);
        return term;
    }

    public TermStructureCubicSpline(double b, double c, double d, double[] f, double[] knots) {
        init();
        b_ = b;
        c_ = c;
        d_ = d;
        for (int i = 0; i < no_knots_; ++i) {
            f_[i] = f[i];
            knots_[i] = knots[i];
        }
    }

    public double discount_factor(double T) {
        return FinRecipes.term_structure_discount_factor_cubic_spline(T, b_, c_, d_, f_, knots_);
    }

    public int no_parameters() {
        return 3 + no_knots_;
    }

    public void set_parameters(double[] x) {
        int i = 0;
        while (i < 3 + no_knots_) {
            x[i] = 0;
            i++;
        }
        b_ = x[0];
        c_ = x[1];
        d_ = x[2];
        for (int j = 0; j < no_knots_; ++j) {
            f_[j] = x[3 + j];
        }
    }

    public double[] get_parameters() {
        double[] x = new double[no_knots_];
        x[0] = b_;
        x[1] = c_;
        x[2] = d_;
        for (int i = 0; i < no_knots_; ++i) {
            x[3 + i] = f_[i];
        }
        return x;
    }

    public void set_knots(int no_knots, double[] knots) {
        if (knots.length < no_knots) {
            return;
        }
        no_knots_ = no_knots;
        int i = 0;
        while (i < no_knots) {
            knots_[i] = 0;
            i++;
        }
        for (int j = 0; j < no_knots; ++j) {
            knots_[j] = knots[j];
        }
    }

    public double[] parameter_lower_bounds() {
        double[] lb = new double[3 + no_knots()];
        for (int i = 0; i < 3 + no_knots(); i++) {
            lb[i] = -1;
        }
        return lb;
    }

    public double[] parameter_upper_bounds() {
        double[] ub = new double[3 + no_knots()];
        for (int i = 0; i < 3 + no_knots(); i++) {
            ub[i] = 1;
        }
        return ub;
    }
}
