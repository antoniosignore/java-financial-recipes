package com.jfr.termStructure;

import com.jfr.FinRecipes;

class TermStructureCIR extends TermStructure {
    private double r_;                       // interest rate
    private double kappa_;                   // mean reversion parameter
    private double lambda_;                  // risk aversion
    private double theta_;                   // long run mean
    private double sigma_;                   // volatility

    public String name() {
        return "CIR";
    }

    public int no_parameters() {
        return 5;
    }

    public void init() {
        // initialize the term structure at reasonable values
        r_ = 0.05;
        kappa_ = 0.01;
        sigma_ = 0.1;
        theta_ = 0.08;
        lambda_ = 0.0;
    }

    public TermStructureCIR() {
        init();
    }


    public TermStructureCIR(double r, double k, double l, double th, double sigma) {
        r_ = r;
        kappa_ = k;
        lambda_ = l;
        theta_ = th;
        sigma_ = sigma;
    }

    public double discount_factor(double T) {
        return FinRecipes.term_structure_discount_factor_cir(T, r_, kappa_, lambda_, theta_, sigma_);
    }

    public void set_parameters(double[] x) {
        r_ = x[0];
        kappa_ = x[1];
        lambda_ = x[2];
        theta_ = x[3];
        sigma_ = x[4];
    }

    public double[] get_parameters() {
        double[] x = new double[no_parameters()];
        x[0] = r_;
        x[1] = kappa_;
        x[2] = lambda_;
        x[3] = theta_;
        x[4] = sigma_;
        return x;
    }
}

