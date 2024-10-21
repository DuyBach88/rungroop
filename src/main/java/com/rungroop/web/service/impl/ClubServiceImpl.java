package com.rungroop.web.service.impl;

import com.rungroop.web.dto.ClubDto;
import com.rungroop.web.models.Club;
import com.rungroop.web.repository.ClubRepository;
import com.rungroop.web.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.rungroop.web.mapper.ClubMapper.mapToClub;
import static com.rungroop.web.mapper.ClubMapper.mapToClubDto;

@Service
public class ClubServiceImpl implements ClubService {
    private ClubRepository clubRepository;
    @Autowired
    public ClubServiceImpl(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }

    @Override
    public List<ClubDto> findAllClubs() {
        List<Club> clubs = clubRepository.findAll();
        return clubs.stream()
                .map(club -> mapToClubDto(club)) // Make sure to fix the method name if needed
                .collect(Collectors.toList());

    }

    @Override
    public Club saveClub(Club club) {
        return clubRepository.save(club);
    }

    // get by id
    @Override
    public ClubDto findClubById(long clubId) {

       Club club = clubRepository.findById(clubId).get();
       return mapToClubDto(club);

    }
// update
    @Override
    public void updateClub(ClubDto clubDto) {
        Club club = mapToClub(clubDto);
        clubRepository.save(club);

    }

    @Override
    public void delete(long clubId) {
        clubRepository.deleteById(clubId);
    }

    @Override
    public List<ClubDto> searchClubs(String query) {
        List<Club> clubs = clubRepository.searchClubs(query);
        return  clubs.stream().map((club) -> mapToClubDto(club)).collect(Collectors.toList());

    }
//    private Club mapToClub(ClubDto club){
//        Club clubDto = Club.builder()
//                .id(club.getId())
//                .title(club.getTitle())
//                .photoUrl(club.getPhotoUrl())
//                .content(club.getContent())
//                .createdOn(club.getCreatedOn())
//                .updatedOn(club.getUpdatedOn())
//                .build();
//        return  clubDto;
//    }
//
//
//
//
//    private ClubDto mapToClubDto(Club club) {
//        ClubDto clubDto = ClubDto.builder()
//                .id(club.getId())
//                .title(club.getTitle())
//                .photoUrl(club.getPhotoUrl())
//                .content(club.getContent())
//                .createdOn(club.getCreatedOn())
//                .updatedOn(club.getUpdatedOn())
//                .build();
//        return clubDto;
//    }

}
