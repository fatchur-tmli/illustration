package co.id.tmli.illustration.model;

import java.io.Serializable;

@lombok.Data
@lombok.AllArgsConstructor
public class Pair7<K> implements Serializable {

    private final K value1, value2, value3, value4, value5, value6, value7;
}
