package co.id.tmli.illustration.service;

import co.id.tmli.illustration.model.Rider;
import co.id.tmli.illustration.utils.CurrencyEnum;
import java.util.List;

public interface RiderService {

    Rider getRider(String riderPlan);

    List<Rider> getRiders(boolean isSyariahProduct, boolean isInsuredIsHolder, long getInsuredAge, long getHolderAge, CurrencyEnum curr, List<String> riders);

    List<Rider> getRidersByBundledProduct(String productCode);
}
