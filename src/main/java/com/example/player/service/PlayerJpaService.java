/*
 * You can use the following import statements
 * 
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.http.HttpStatus;
 * import org.springframework.stereotype.Service;
 * import org.springframework.web.server.ResponseStatusException;
 * import java.util.*;
 * 
 */

// Write your code here
package com.example.player.service;

import com.example.player.model.Player;
import com.example.player.repository.PlayerRepository;
import com.sun.xml.bind.annotation.OverrideAnnotationOf;
import com.example.player.repository.PlayerJpaRepository;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PlayerJpaService implements PlayerRepository {
    @Autowired
    private PlayerJpaRepository playerJpaRepository;

    @Override
    public ArrayList<Player> getPlayers() {
        return (ArrayList<Player>) playerJpaRepository.findAll();
    }

    @Override
    public Player getPlayerById(int playerId) {
        try {
            return playerJpaRepository.findById(playerId).get();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }

    @Override
    public Player addPlayer(Player player) {
        playerJpaRepository.save(player);
        return player;
    }

    @Override
    public Player updatePlayer(int playerId, Player player) {
        try {
            Player existPlayer = playerJpaRepository.findById(playerId).get();
            if (player.getPlayerName() != null) {
                existPlayer.setPlayerName(player.getPlayerName());
            }
            if (player.getJerseyNumber() != 0) {
                existPlayer.setJerseyNumber(player.getJerseyNumber());
            }
            if (player.getRole() != null) {
                existPlayer.setRole(player.getRole());
            }
            playerJpaRepository.save(existPlayer);
            return existPlayer;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public void deletePlayer(int playerId) {
        try{
            playerJpaRepository.deleteById(playerId);
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        
    }
}