package services;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import model.TotalPixel;

import java.util.List;

@Stateless
public class TotalPixelService {
    @PersistenceContext
    EntityManager em;

    public List<TotalPixel> findPageByFieldId(int id){
        List<TotalPixel> pageList = em.createNamedQuery("c.findTotalPixelByFiledId", TotalPixel.class)
                .setParameter("id", id).getResultList();
        return pageList;
    }
}
