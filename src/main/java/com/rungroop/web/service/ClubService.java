package com.rungroop.web.service;

import com.rungroop.web.dto.ClubDto;
import com.rungroop.web.models.Club;
import org.springframework.stereotype.Controller;

import java.util.List;

public interface ClubService {
    List<ClubDto> findAllClubs();


    Club saveClub(Club club);

    ClubDto findClubById(long clubId);

    void updateClub(ClubDto clubDto);

    void delete(long clubId);
    List<ClubDto> searchClubs (String query);
}
