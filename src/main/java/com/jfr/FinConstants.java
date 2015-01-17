package com.jfr;

public interface FinConstants {

    int PAYOFF_EUROPEAN_CALL = 0;
    int PAYOFF_EUROPEAN_PUT = 1;
    int PAYOFF_ARITHMETRIC_AVERAGE = 2;
    int PAYOFF_GEOMETRIC_AVERAGE = 3;
    int PAYOFF_MAX = 4;
    int PAYOFF_MIN = 5;

    double ACCURACY = 1.0e-6;
    double ERROR = -1e30;

}
