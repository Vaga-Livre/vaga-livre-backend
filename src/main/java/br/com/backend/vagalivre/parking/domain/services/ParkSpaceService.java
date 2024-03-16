package br.com.backend.vagalivre.parking.domain.services;

import br.com.backend.vagalivre.parking.domain.entities.Park;
import br.com.backend.vagalivre.parking.domain.entities.ParkSpace;
import br.com.backend.vagalivre.parking.domain.repositories.ParkSpaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ParkSpaceService {

    private final ParkSpaceRepository parkSpaceRepository;
    private final ParkService parkService;

    @Autowired
    public ParkSpaceService(
            ParkSpaceRepository parkSpaceRepository,
            ParkService parkService
    ){
        this.parkSpaceRepository = parkSpaceRepository;
        this.parkService = parkService;
    }

    public List<ParkSpace> getAllParkSpacesInPark(Integer parkId) {
        Park park = parkService.getParkOrNull(parkId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND ,"Park with ID " + parkId + " not found")
        );

        return parkSpaceRepository.findParkSpaceByPark(park);
    }

    public ParkSpace createNewParkSpace(Integer parkId) {
        Park park = parkService.getParkOrNull(parkId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.BAD_REQUEST, "Park with ID " + parkId + " not found")
        );

        ParkSpace parkSpace = new ParkSpace(null, null, Collections.emptyList(), park);

        return parkSpaceRepository.save(parkSpace);
    }

    public void removeParkSpace(Integer parkSpaceId, Integer parkId){
        Park park = parkService.getParkOrNull(parkId).orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Park with Id" + parkId + "not found")
        );

        ParkSpace parkSpace =  getParkSpaceOrNull(parkSpaceId).orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_FOUND ,"Park Space with ID " + parkSpaceId + " not found")
        );

        parkSpaceRepository.delete(parkSpace);
    }

    public Optional<ParkSpace> getParkSpaceOrNull(Integer parkSpaceId){
        return parkSpaceRepository.findById(parkSpaceId);
    }
}
