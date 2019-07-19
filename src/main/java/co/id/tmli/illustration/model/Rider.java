package co.id.tmli.illustration.model;

import java.util.Comparator;

public interface Rider {

    public static Comparator<Rider> sorter = (o1, o2) -> ((String) o1.getDesc()).compareTo((String) o2.getDesc());

    String getRiderCode();

    String getDesc();

    String getDescKolomW();

    String getDescShort();

    String getGroupCode();

    String getMinHolderEntryAge();

    String getMaxInsuredEntryAge();

    Double getMinSA();

    String getReleasedDate();

    Double getMaxSumInsured();

    boolean isSyariahProduct();

    Integer getCoveredAges();

    String getValidationForAdd();

    String getCurrency();

    String getBundledProduct();

    String getCorFormula();

    String getRiderSA();

    String getColN();

    String getIllustrationOutput();

    String getDescLong();

    String getCorType();

    String getMinInsuredEntryAge();

    String getMaxHolderEntryAge();

    Double getMinBasicSA();

    String getRateType();

    Double getRate(Gender gender, Integer age, String rateType, Integer y, Integer riskClass);

}
