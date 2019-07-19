package co.id.tmli.illustration.model;

public class ScheduleWithdrawalData {

    private Double scheduleWithdrawalLow;
    private Double scheduleWithdrawalMed;
    private Double scheduleWithdrawalHigh;

    public ScheduleWithdrawalData() {
    }

    public ScheduleWithdrawalData(Double scheduleWithdrawalLow, Double scheduleWithdrawalMed, Double scheduleWithdrawalHigh) {
        this.scheduleWithdrawalLow = scheduleWithdrawalLow;
        this.scheduleWithdrawalMed = scheduleWithdrawalMed;
        this.scheduleWithdrawalHigh = scheduleWithdrawalHigh;
    }

    public Double getScheduleWithdrawalLow() {
        return scheduleWithdrawalLow;
    }

    public void setScheduleWithdrawalLow(Double f) {
        this.scheduleWithdrawalLow = f;
    }

    public Double getScheduleWithdrawalMed() {
        return scheduleWithdrawalMed;
    }

    public void setScheduleWithdrawalMed(Double a) {
        this.scheduleWithdrawalMed = a;
    }

    public Double getScheduleWithdrawalHigh() {
        return scheduleWithdrawalHigh;
    }

    public void setScheduleWithdrawalHigh(Double t) {
        this.scheduleWithdrawalHigh = t;
    }

}
