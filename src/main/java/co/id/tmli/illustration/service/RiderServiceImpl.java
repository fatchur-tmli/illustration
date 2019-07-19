package co.id.tmli.illustration.service;

import co.id.tmli.illustration.utils.Constants;
import co.id.tmli.illustration.model.Rider;
import co.id.tmli.illustration.service.repo.RiderRepo;
import co.id.tmli.illustration.utils.CurrencyEnum;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RiderServiceImpl implements RiderService {

    public RiderServiceImpl(RiderRepo riderRepo) {
        this.riderRepo = riderRepo;
    }

    @lombok.Setter
    private RiderRepo riderRepo;

    @Override
    public Rider getRider(String riderPlan) {
        for (Rider r : riderRepo.getRiders()) {
            if (r.getRiderCode().equals(riderPlan)) {
                return r;
            }
        }
        return null;
    }

    @Override
    public List<Rider> getRiders(boolean isSyariahProduct, boolean isInsuredIsHolder, long insuredAge, long holderAge, CurrencyEnum curr, List<String> limitedRiders) {
        List<Rider> list;
        if (limitedRiders != null) {
            list = limitedRiders.stream()
                    .map(s -> getRider(s))
                    .collect(Collectors.toList());
        } else {
            list = riderRepo.getRiders();
        }
        List<Rider> result = new ArrayList<>();
        for (Rider rider : list) {
            if (Constants.RIDERGROUP_EMBEDDED.equalsIgnoreCase(rider.getGroupCode()) 
                    || Constants.RIDERGROUP_HSR_NEW.equalsIgnoreCase(rider.getGroupCode())
                    || Constants.RIDERGROUP_HSR_OLD.equalsIgnoreCase(rider.getGroupCode())) {
                continue;
            }            
            if (isInsuredIsHolder
                    && rider.getValidationForAdd() != null
                    && rider.getValidationForAdd().contains(Constants.INSURED_ISNOT_HOLDER)) {
            } else if (!isInsuredIsHolder
                    && rider.getValidationForAdd() != null
                    && rider.getValidationForAdd().contains(Constants.INSURED_IS_HOLDER)) {
            } else if (rider.isSyariahProduct() == isSyariahProduct
                    && rider.getReleasedDate() != null
                    && rider.getCurrency().contains(curr.name())) {
                result.add(rider);
            }
        }
        return result;
    }

    @Override
    public List<Rider> getRidersByBundledProduct(String productCode) {
        List<Rider> items = Arrays.asList();
        for (Rider rider : riderRepo.getRiders()) {
            if (Constants.RIDERGROUP_EMBEDDED.equalsIgnoreCase(rider.getGroupCode())
                    && rider.getBundledProduct().equals(productCode)) {
//                items.add(rider.createNew());
                items.add(rider);
            }
        }
        return items;
    }

}
