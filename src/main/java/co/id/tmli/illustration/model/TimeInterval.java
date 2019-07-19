package co.id.tmli.illustration.model;

import java.time.LocalDate;

import co.id.tmli.illustration.utils.Constants.AgeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TimeInterval {

    private final int value;
//    private LocalDate date;
    private final AgeUnit timeUnit;

    public TimeInterval(String str) {
        String[] split = str.split("_");
        value = Integer.parseInt(split[0]);
        if (AgeUnit.years.name().equalsIgnoreCase(split[1])) {
            timeUnit = AgeUnit.years;
        } else if (AgeUnit.months.name().equalsIgnoreCase(split[1])) {
            timeUnit = AgeUnit.months;
        } else {
            timeUnit = AgeUnit.days;
        }
    }

    public TimeInterval(int v, AgeUnit u) {
        this.value = v;
        this.timeUnit = u;
    }

//    public TimeInterval(LocalDate d, TimeUnit u) {
//        this.date = d;
//        this.timeUnit = u;
//    }
    public boolean isLessOrEqualsThan(TimeInterval other) {
//        int f = 0;
//        switch (timeUnit) {
//            case days: {
//                long h = ChronoUnit.DAYS.between(other.getDate(), LocalDate.now());
//                return value <= h;
//            }
//            case months: {
//                long k = ChronoUnit.MONTHS.between(other.getDate(), LocalDate.now());
//                return value <= k;
//            }
//            case years:
//                f = other.getValue();
//                break;
//        }
//        return value <= f;
        if (this.timeUnit == other.timeUnit) {
            boolean r = this.value <= other.value;
            LOGGER.debug("[isLessOrEqualsThan]-{} <= {} ? {}", this.value, other.value, r);
            return r;
        }
        LOGGER.warn("[isLessOrEqualsThan]-comparing different timeunit {} <= {}", this, other);
        return this.value <= 0;
    }

    static final Logger LOGGER = LoggerFactory.getLogger(TimeInterval.class);

    public static TimeInterval countIntervalToNow(LocalDate d, AgeUnit u) {
        int v = (int) u.getChronoUnit().between(d, LocalDate.now());
        TimeInterval t = new TimeInterval(v, u);
        LOGGER.debug("[countIntervalToNow]-date={}, intervalToNow={}", d, t);
        return t;
    }

    public boolean isLessThan(TimeInterval other) {
//        int et = 0;
//        switch (timeUnit) {
//            case days: {
//                long ft = ChronoUnit.DAYS.between(other.getDate(), LocalDate.now());
//                return value < ft;
//            }
//            case months: {
//                long pl = ChronoUnit.MONTHS.between(other.getDate(), LocalDate.now());
//                return value < pl;
//            }
//            case years:
//                et = other.getValue();
//                break;
//        }
//        return value < et;
        if (this.timeUnit == other.timeUnit) {
            boolean r = this.value < other.value;
            LOGGER.debug("[isLessThan]-{} < {} ? {}", this.value, other.value, r);
            return r;
        }
        LOGGER.warn("[isLessThan]-comparing different timeunit {} < {}", this, other);
        return this.value < 0;
    }

    public int getValue() {
        return value;
    }

    public AgeUnit getTimeUnit() {
        return timeUnit;
    }

//    public LocalDate getDate() {
//        return date;
//    }
    @Override
    public String toString() {
        return "";
        //return value + " " + MainApp.getInstance().getResourceBundle().getString(timeUnit.name());
    }
}
