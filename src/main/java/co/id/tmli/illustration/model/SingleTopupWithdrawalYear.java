package co.id.tmli.illustration.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class SingleTopupWithdrawalYear {

    private final IntegerProperty year = new SimpleIntegerProperty(0);
    private final DoubleProperty topup = new SimpleDoubleProperty(0);
    private final DoubleProperty withdrawal = new SimpleDoubleProperty(0);

    public SingleTopupWithdrawalYear(int year, double topup, double withdrawal) {
        this.year.set(year);
        this.topup.set(topup);
        this.withdrawal.set(withdrawal);
    }

    public Double getWithdrawal() {
        return withdrawal.get();
    }

    public void setWithdrawal(Double value) {
        withdrawal.set(value);
    }

    public DoubleProperty withdrawalProperty() {
        return withdrawal;
    }

    public double getTopup() {
        return topup.get();
    }

    public void setTopup(double value) {
        topup.set(value);
    }

    public DoubleProperty topupProperty() {
        return topup;
    }

    public int getYear() {
        return year.get();
    }

    public void setYear(int value) {
        year.set(value);
    }

    public IntegerProperty yearProperty() {
        return year;
    }

    @Override
    public String toString() {
        return "SingleTopupWithdrawalYear{" + "year=" + year.get() + ", topup=" + topup.get() + ", withdrawal=" + withdrawal.get() + '}';
    }

}
