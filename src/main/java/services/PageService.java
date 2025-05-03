package services;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import model.Couvertures;
import model.Page;

import java.util.List;

@Stateless
public class PageService {

    @PersistenceContext
    EntityManager em;

    public List<Page> findPageByFieldId(int id){
        List<Page> pageList = em.createNamedQuery("c.findPageByFiledId", Page.class)
                .setParameter("id", id).getResultList();
        return pageList;
    }
}
