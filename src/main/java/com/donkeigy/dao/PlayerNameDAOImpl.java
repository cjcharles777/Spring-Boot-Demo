/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.donkeigy.dao;


import com.donkeigy.objects.hibernate.PlayerName;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 * @author cedric
 */
@Repository("nameDAO")
@Transactional
public class PlayerNameDAOImpl implements PlayerNameDAO
{

    private HibernateTemplate hibernateTemplate;
    
    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        hibernateTemplate = new HibernateTemplate(sessionFactory);
    }
    
    @Transactional(readOnly = false)
    @Override
    public void savePlayerName(PlayerName n)
    {
         hibernateTemplate.saveOrUpdate(n);
    }

    @Transactional(readOnly = false)
    @Override
    public void savePlayerNames(List<PlayerName> listN)
    {

        for (PlayerName playerName : listN)
        {
            hibernateTemplate.saveOrUpdate(playerName);
        }
    }

    @Override
    public List<PlayerName> getPlayerNames() {
          return (List<PlayerName>) hibernateTemplate.find("from "
                + PlayerName.class.getName());
    }

    @Override
    public PlayerName gePlayerNameById(int nameId) 
    {
        return hibernateTemplate.get(PlayerName.class, nameId);
    }

    @Override
    @Transactional(readOnly = false)
    public void deletePlayerName(PlayerName n) {
        hibernateTemplate.delete(n);
    }

    @Override
    @Transactional(readOnly = false)
    public void clearPlayerNames() {
         hibernateTemplate.deleteAll(hibernateTemplate.loadAll(PlayerName.class));
    }
    
}
