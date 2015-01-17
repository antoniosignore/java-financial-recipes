package com.jfr.termStructure;

import com.jfr.FinRecipes;

public class TermStructureNelsonSiegel extends TermStructure {
    private double beta0;
    private double beta1;
    private double beta2;
    private double lambda;
    private double[] lbounds;
    private double[] ubounds;

    public String name() {
        return "nelson_siegel";
    }

    public int no_parameters() {
        return 4;
    }

    public void init() { // set default parameter values to something reasonable
        beta0 = 0.01;
        beta1 = 0.01;
        beta2 = 0.01;
        lambda = 5.0;
    }

    public TermStructureNelsonSiegel() {
        init();
    }

    public TermStructureNelsonSiegel(double b0, double b1, double b2, double l) {
        beta0 = b0;
        beta1 = b1;
        beta2 = b2;
        lambda = l;
    }

    public double yield(double t) {
        if (t <= 0.0) {
            return beta0;
        }
        return FinRecipes.term_structure_yield_nelson_siegel(t, beta0, beta1, beta2, lambda);
    }

    public void set_parameters(double[] x) {
        while (x.length < no_parameters()) {
            x[x.length - 1] = 0;
        }
        beta0 = x[0];
        beta1 = x[1];
        beta2 = x[2];
        lambda = x[3];
    }

    public void get_parameters(double[] x) {
        while (x.length < no_parameters()) {
            x[x.length - 1] = 0;
        }
        if (x.length >= no_parameters()) {
            x[0] = beta0;
            x[1] = beta1;
            x[2] = beta2;
            x[3] = lambda;
        } else {
            ;
        } //should throw exception;
    }

    public double[] parameter_lower_bounds() {
        lbounds[0] = 0.00001;
        lbounds[1] = 0.00001;
        lbounds[2] = 0.00001;
        lbounds[3] = 0.00001;
        return lbounds;
    }

    public double[] parameter_upper_bounds() {
        ubounds[0] = 10;
        ubounds[1] = 10;
        ubounds[2] = 10;
        ubounds[3] = 10;
        return ubounds;
    }
}

