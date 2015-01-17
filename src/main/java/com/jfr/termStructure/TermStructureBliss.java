package com.jfr.termStructure;


import com.jfr.FinRecipes;

public class TermStructureBliss extends TermStructure {
    private double gamma0;
    private double gamma1;
    private double gamma2;
    private double lambda1;
    private double lambda2;
    private double[] lbounds;
    private double[] ubounds;

    private void init() {
        gamma0 = 0.06;
        gamma1 = -0.01;
        gamma2 = 0.01;
        lambda1 = 1.5;
        lambda2 = 0.5;
    }

    public TermStructureBliss() {
        init();
    }

    public TermStructureBliss(double g0, double g1, double g2,
                              double l1, double l2) {
        gamma0 = g0;
        gamma1 = g1;
        gamma2 = g2;
        lambda1 = l1;
        lambda2 = l2;
    }

    public String name() {
        return "bliss";
    }

    public double yield(double T) {
        return FinRecipes.term_structure_yield_bliss(T,
                gamma0,
                gamma1,
                gamma2,
                lambda1,
                lambda2);
    }

    public int no_parameters() {
        return 5;
    }

    public void set_parameters(double[] x) {
        gamma0 = x[0];
        gamma1 = x[1];
        gamma2 = x[2];
        lambda1 = x[3];
        lambda2 = x[4];
    }

    public double[] get_parameters() {
        double[] x = new double[no_parameters()];
        x[0] = gamma0;
        x[1] = gamma1;
        x[2] = gamma2;
        x[3] = lambda1;
        x[4] = lambda2;
        return x;
    }

    public double[] parameter_lower_bounds() {
        lbounds[0] = 0;
        lbounds[1] = 0;
        lbounds[2] = 0;
        lbounds[3] = 0;
        lbounds[4] = 0;
        return lbounds;
    }

    public double[] parameter_upper_bounds() {
        ubounds[0] = 10;
        ubounds[1] = 10;
        ubounds[2] = 10;
        ubounds[3] = 10;
        ubounds[4] = 10;
        return ubounds;
    }
}


