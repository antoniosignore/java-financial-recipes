package com.jfr.termStructure;


import com.jfr.FinRecipes;

public class TermStructureEstiCIR extends TermStructure {
    private double r_;
    private double phi1_;
    private double phi2_;
    private double phi3_;

    public String name() {
        return "esti_CIR";
    }

    public int no_parameters() {
        return 4;
    }

    public void init() {
        // initialize the term structure at reasonable values
        r_ = 0.05;
        phi1_ = 0.5;
        phi2_ = 0.5;
        phi3_ = 0.5;
    }

    public TermStructureEstiCIR() {
        init();
    }

    public TermStructureEstiCIR(double r,
                                double phi1, double phi2, double phi3) {
        r_ = r;
        phi1_ = phi1;
        phi2_ = phi2;
        phi3_ = phi3;
    }

    public double discount_factor(double T) {
        return FinRecipes.term_structure_discount_factor_estimated_cir(T, r_, phi1_, phi2_, phi3_);
    }

    public void set_parameters(double[] x) {
        r_ = x[0];
        phi1_ = x[1];
        phi2_ = x[2];
        phi3_ = x[3];
    }

    public double[] get_parameters() {
        double[] x = new double[no_parameters()];
        x[0] = r_;
        x[1] = phi1_;
        x[2] = phi2_;
        x[3] = phi3_;
        return x;
    }

    public double[] parameter_lower_bounds() {
        double[] lb = new double[no_parameters()];
        for (int i = 0; i < no_parameters(); i++) {
            lb[i] = 0.0001;
        }
        return lb;
    }

    public double[] parameter_upper_bounds() {
        double[] ub = new double[no_parameters()];
        for (int i = 0; i < no_parameters(); i++) {
            ub[i] = 10;
        }
        return ub;
    }
}

