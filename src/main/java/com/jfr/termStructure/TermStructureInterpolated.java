package com.jfr.termStructure;


import com.jfr.FinRecipes;

public class TermStructureInterpolated extends TermStructure {
    public double[] times_;
    public double[] yields_;

    public int no_observations() {
        return times_.length;
    }

    public String name() {
        return "interpolated";
    }

    public double time_of_last_observation() {
        if (times_.length > 0) {
            return times_[times_.length - 1];
        }
        return 0;
    }

    public void set_last_observation(double r) {
        if (times_.length > 0) {
            yields_[yields_.length - 1] = r;
        }
    }

    public void clear() {
        // reset everything to null
        if (times_ != null) {
            for (int i = 0; i < times_.length; i++) {
                times_[i] = 0;
                yields_[i] = 0;
            }
        }
    }

    public TermStructureInterpolated() {
        super();
    }

    public TermStructureInterpolated(TermStructureInterpolated term) {
        times_ = new double[term.no_observations()];
        yields_ = new double[term.no_observations()];
        for (int i = 0; i < term.no_observations(); i++) {
            times_[i] = term.times_[i];
            yields_[i] = term.yields_[i];
        }
    }

    public TermStructureInterpolated clone(TermStructureInterpolated term) {
        TermStructureInterpolated t = new TermStructureInterpolated(term);
        return t;
    }

    public double yield(double T) {
        return FinRecipes.term_structure_yield_linearly_interpolated(T, times_, yields_);
    }

    // put into sorted order.
    public void add_interpolated_observation(double time, double yield) {
        times_[times_.length] = time;
        yields_[yields_.length] = yield;
    }

    public void set_interpolated_observations(double[] in_times, double[] in_yields) {
        clear();
        if (in_times.length != in_yields.length) {
            return;
        }
        times_ = new double[in_times.length];
        yields_ = new double[in_yields.length];
        for (int i = 0; i < in_times.length; i++) {
            times_[i] = in_times[i];
            yields_[i] = in_yields[i];
        }
    }
}
