package services;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import model.Couvertures;


import java.util.List;

@Stateless
@LocalBean
public class CouvertureService {

    @PersistenceContext
    EntityManager em;

    public List <Couvertures> findPageByFieldId(int id){
        List<Couvertures> pageList = em.createNamedQuery("c.findCouvertureByFiledId", Couvertures.class)
                .setParameter("id", id).getResultList();
        return pageList;
    }
}
