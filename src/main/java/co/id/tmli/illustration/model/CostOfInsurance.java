package co.id.tmli.illustration.model;

@lombok.Data
@lombok.AllArgsConstructor
public class CostOfInsurance {

    private final int age;
    private final GenderValue<Double> value;
}
