package ec.edu.uce.services;

import ec.edu.uce.jpa.FactureDetail;
import jakarta.ejb.Stateless;

@Stateless
public class FactureDetailService extends GenericServiceImpl<FactureDetail> {
    public FactureDetailService() {
        super(FactureDetail.class);
    }
}