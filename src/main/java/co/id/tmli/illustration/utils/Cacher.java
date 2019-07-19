/**
 * 
 */
package co.id.tmli.illustration.utils;

import java.util.function.Supplier;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@RequiredArgsConstructor
public class Cacher<T> {
	
	private T data;
	@Setter
	@NonNull
	private Supplier<T> supplier;
	
	public T get() {
        if (!hasData()) {
            data = supplier.get();
        }
        return data;
    }
	
	public void clear() {
        data = null;
    }

    public boolean hasData() {
        return data != null;
    }

    @Override
    public String toString() {
        return "Cacher{" + "data=" + data + '}';
    }

}
