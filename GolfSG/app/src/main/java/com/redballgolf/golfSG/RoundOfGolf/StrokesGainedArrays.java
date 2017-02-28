package com.redballgolf.golfSG.RoundOfGolf;


public class StrokesGainedArrays {
    static double[] tee = {2.5, 2.6, 2.7, 2.8, 2.92, 2.99, 2.97, 2.99, 3.05, 3.12, 3.17, 3.25, 3.45, 3.65, 3.71, 3.79, 3.86, 3.92, 3.96, 3.99, 4.02, 4.08, 4.17, 4.28, 4.41, 4.54, 4.65, 4.74, 4.79, 4.82};
    static double[] fairway = {2.40, 2.60, 2.70, 2.75, 2.80, 2.85, 2.91, 2.98, 3.08, 3.19, 3.32, 3.45, 3.58, 3.69, 3.78, 3.84, 3.88, 3.95, 4.03, 4.11, 4.15, 4.20, 4.29, 4.40, 4.53, 4.66, 4.78, 4.86, 4.91, 4.94};
    static double[] rough = {2.59, 2.78, 2.91, 2.96, 3.02, 3.08, 3.15, 3.23, 3.31, 3.42, 3.53, 3.64, 3.74, 3.83, 3.90, 3.95, 4.02, 4.11, 4.21, 4.30, 4.34, 4.39, 4.48, 4.59, 4.72, 4.85, 4.97, 5.05, 5.10, 5.13};
    static double[] sand = {2.53, 2.82, 3.15, 3.24, 3.23, 3.21, 3.22, 3.28, 3.40, 3.55, 3.70, 3.84, 3.93, 4.00, 4.04, 4.12, 4.26, 4.41, 4.55, 4.69, 4.73, 4.78, 4.87, 4.98, 5.11, 5.24, 5.36, 5.44, 5.49, 5.52};
    static double[] recovery = {3.40, 3.50, 3.60, 3.70, 3.80, 3.78, 3.80, 3.81, 3.82, 3.87, 3.92, 3.97, 4.03, 4.10, 4.20, 4.31, 4.44, 4.56, 4.66, 4.75, 4.79, 4.84, 4.93, 5.04, 5.17, 5.30, 5.42, 5.50, 5.55, 5.58};
    static double[] putt = {1.04, 1.13, 1.23, 1.34, 1.42, 1.50, 1.56, 1.61, 1.78, 1.87, 1.98, 2.06, 2.14, 2.21, 2.40};

    public static double getTeeDifficulty(int index) {
        return tee[index];
    }

    public static double getFairwayDifficulty(int index) {
        return fairway[index];
    }

    public static double getRoughDifficulty(int index) {
        return rough[index];
    }

    public static double getSandDifficulty(int index) {
        return sand[index];
    }

    public static double getRecoveryDifficulty(int index) {
        return recovery[index];
    }

    public static double getPuttDifficulty(int index) {
        return putt[index];
    }
}
