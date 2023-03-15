package com.coderocket.sportscomp.core.services;

import com.coderocket.sportscomp.core.ports.in.AddPlayerToCompetitionUseCase;
import com.coderocket.sportscomp.core.ports.out.AddPlayerToCompetitionPort;
import com.coderocket.sportscomp.domain.competitionPlayer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AddPlayerToCompetitionService implements AddPlayerToCompetitionUseCase {
    private final AddPlayerToCompetitionPort addPlayerToCompetitionPort;
    @Override
    public void addPlayerToCompetitionByCompetitionId(competitionPlayer competitionPlayer, Integer competitionId) {
        addPlayerToCompetitionPort.addPlayerToCompetition(competitionPlayer, competitionId);
    }
}
