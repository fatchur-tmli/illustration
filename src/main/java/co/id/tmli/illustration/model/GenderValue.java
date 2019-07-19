package co.id.tmli.illustration.model;

@lombok.Data
@lombok.AllArgsConstructor
public class GenderValue<T> {

    private final T maleValue, femaleValue;

    public T getValue(Gender g) {
        return g.isMale() ? maleValue : femaleValue;
    }
}
