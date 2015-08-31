/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.donkeigy.dao;

import com.donkeigy.objects.hibernate.ByeWeek;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 * @author cedric
 */

@Repository("ByeWeekDAO")
@Transactional
public class ByeWeekDAOImpl implements ByeWeekDAO
{
        private HibernateTemplate hibernateTemplate;
    
    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        hibernateTemplate = new HibernateTemplate(sessionFactory);
    }
    
    @Transactional(readOnly = false)
    @Override
    public void saveByeWeek(ByeWeek bw)
    {
         hibernateTemplate.saveOrUpdate(bw);
    }

    @Transactional(readOnly = false)
    @Override
    public void saveByeWeeks(List<ByeWeek> listBW) 
    {
         hibernateTemplate.saveOrUpdateAll(listBW);
    }

    @Override
    public List<ByeWeek> getByeWeeks() {
          return (List<ByeWeek>) hibernateTemplate.find("from "
                + ByeWeek.class.getName());
    }

    @Override
    public ByeWeek getByeWeekById(int byeWeekId) 
    {
        return hibernateTemplate.get(ByeWeek.class, byeWeekId);
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteByeWeek(ByeWeek bw) 
    {
        hibernateTemplate.delete(bw);
    }

    @Override
    @Transactional(readOnly = false)
    public void clearByeWeeks() 
    {
         hibernateTemplate.deleteAll(hibernateTemplate.loadAll(ByeWeek.class));
    }
    
}
