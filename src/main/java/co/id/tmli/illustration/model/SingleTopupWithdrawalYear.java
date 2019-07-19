package co.id.tmli.illustration.model;

public class SingleTopupWithdrawalYear {

    private Integer year = new Integer(0);
    private Double topup = new Double(0);
    private Double withdrawal = new Double(0);

    public SingleTopupWithdrawalYear(int year, double topup, double withdrawal) {
        this.year = year;
        this.topup = topup;
        this.withdrawal = withdrawal;
    }

    public Double getWithdrawal() {
        return withdrawal;
    }

    public void setWithdrawal(Double withdrawal) {
        this.withdrawal = withdrawal;
    }        

    public Double withdrawalProperty() {
        return withdrawal;
    }

    public Double getTopup() {
        return topup;
    }

    public void setTopup(Double topup) {
        this.topup = topup;
    }

    public Double topupProperty() {
        return topup;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer yearProperty() {
        return year;
    }

    @Override
    public String toString() {
        return "SingleTopupWithdrawalYear{" + "year=" + year + ", topup=" + topup + ", withdrawal=" + withdrawal + '}';
    }

}
