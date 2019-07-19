package co.id.tmli.illustration.model;

@lombok.Data
@lombok.AllArgsConstructor
@lombok.NoArgsConstructor
public class RiskRate {

    private RangeData<Integer> ageRange;
    private Integer risk;
    private Double rate;
}
