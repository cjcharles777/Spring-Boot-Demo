/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.donkeigy.dao;


import com.donkeigy.objects.hibernate.PlayerName;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @author cedric
 */
@Repository("nameDAO")
public interface PlayerNameDAO 
{
    public void savePlayerName(PlayerName n);
    public void savePlayerNames(List<PlayerName> listN);
    public List<PlayerName> getPlayerNames();
    public PlayerName gePlayerNameById(int nameId);
    public void deletePlayerName(PlayerName n);
    public void clearPlayerNames();
}
