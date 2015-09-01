/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.donkeigy.dao;


import com.donkeigy.objects.hibernate.LeaguePlayer;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 * @author cedric
 */

@Repository("LeaguePlayersDAO")
@Transactional
public class LeaguePlayersDAOImpl implements LeaguePlayersDAO
{
    private HibernateTemplate hibernateTemplate;
 
    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        hibernateTemplate = new HibernateTemplate(sessionFactory);
    }
    
    @Transactional(readOnly = false)
    public void saveLeaguePlayer(LeaguePlayer player) {
        hibernateTemplate.saveOrUpdate(player);
    }
        
    @Transactional(readOnly = false)
    public void saveLeaguePlayers(List<LeaguePlayer> players) 
    {
        for (LeaguePlayer leaguePlayer : players)
        {
            hibernateTemplate.saveOrUpdate(leaguePlayer);
        }
    }

    
    public List<LeaguePlayer> getAllLeaguePlayers() {
         return (List<LeaguePlayer>) hibernateTemplate.find("from "
                + LeaguePlayer.class.getName());
    }
    
    public List<LeaguePlayer> getLeaguePlayers(int firstResult, int maxResults) 
    {
       DetachedCriteria criteria;
       criteria =  DetachedCriteria.forClass(LeaguePlayer.class);
       criteria.addOrder(Order.asc("player_id"));
       return (List<LeaguePlayer>) hibernateTemplate.findByCriteria(criteria, firstResult, maxResults);
      
    



    }
    
    public List<LeaguePlayer> getLeaguePlayers(LeaguePlayer p)
    {
        DetachedCriteria exampleCriteria = DetachedCriteria.forClass(LeaguePlayer.class).add(Example.create(p));
        if(p.getName()!= null)
        {
            exampleCriteria.createCriteria("name")
                    .add(Example.create(p.getName()));
        }
        return (List<LeaguePlayer>) hibernateTemplate.findByCriteria(exampleCriteria);
    
    }

    @SuppressWarnings("unchecked")
    public LeaguePlayer getLeaguePlayerById(int playerId) {
        return hibernateTemplate.get(LeaguePlayer.class, playerId);
    }
    
    @SuppressWarnings("unchecked")
    public LeaguePlayer getLeaguePlayerbyYahooId(int yahooId)
    {
        LeaguePlayer result = null;
        
        List<LeaguePlayer> tempList =  (List<LeaguePlayer>) hibernateTemplate.findByCriteria(
        DetachedCriteria.forClass(LeaguePlayer.class)
        .add(Restrictions.eq("player_id", new Integer(yahooId).toString())));
        
        if (tempList != null && tempList.size() > 0)
        {
            result = tempList.get(0);
        }
        else
        {
            result = null;
        }
        return result;
    }
    
    @Transactional(readOnly = false)
    public void deleteLeaguePlayer(LeaguePlayer player)
    {
       hibernateTemplate.delete(player);
    }
    
    @Transactional(readOnly = false)
    public void clearLeaguePlayers() 
    {
       hibernateTemplate.deleteAll(hibernateTemplate.loadAll(LeaguePlayer.class));
    }



}
