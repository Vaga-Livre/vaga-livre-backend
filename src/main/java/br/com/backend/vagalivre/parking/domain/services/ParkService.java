package br.com.backend.vagalivre.parking.domain.services;

import br.com.backend.vagalivre.parking.domain.dtos.ParkDTO;
import br.com.backend.vagalivre.parking.domain.entities.Park;
import br.com.backend.vagalivre.parking.domain.repositories.ParkRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ParkService {

    private final ParkRepository parkRepository;

    public ParkService(ParkRepository parkRepository) {
        this.parkRepository = parkRepository;
    }

    public List<Park> findAllParks() {
        return parkRepository.findAll();
    }

    public Park createPark(ParkDTO data) {
        Park park = new Park();
        BeanUtils.copyProperties(data, park);

        return parkRepository.save(park);
    }

    public Park updatePark(Integer parkId, ParkDTO data) {
        Park park = getParkOrNull(parkId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.BAD_REQUEST ,"Park with ID " + parkId + " not found")
        );

        BeanUtils.copyProperties(data, park);

        return parkRepository.save(park);
    }

    public Optional<Park> getParkOrNull(Integer parkId) {
        return parkRepository.findById(parkId);
    }
}
