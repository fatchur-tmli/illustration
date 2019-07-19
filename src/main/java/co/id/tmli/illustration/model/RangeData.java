package co.id.tmli.illustration.model;

@lombok.Getter
@lombok.AllArgsConstructor
@lombok.EqualsAndHashCode
@lombok.ToString
public class RangeData<T extends Comparable> implements Comparable<RangeData<T>> {

    private final T from, to;

    public boolean check(T v) {
        return v.compareTo(from) >= 0 && v.compareTo(to) <= 0;
    }

    @Override
    public int compareTo(RangeData<T> o) {
        return this.from.compareTo(o.from);
    }

}
