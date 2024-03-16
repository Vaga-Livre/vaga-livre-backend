package br.com.backend.vagalivre.parking.api;

import br.com.backend.vagalivre.parking.domain.dtos.ParkDTO;
import br.com.backend.vagalivre.parking.domain.entities.Park;
import br.com.backend.vagalivre.parking.domain.entities.ParkSpace;
import br.com.backend.vagalivre.parking.domain.services.ParkService;
import br.com.backend.vagalivre.parking.domain.services.ParkSpaceService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parks")
public class ParkingController {
    private final ParkSpaceService parkSpaceService;
    private final ParkService parkService;

    public ParkingController(
            ParkSpaceService parkSpaceService,
            ParkService parkService
    ) {
        this.parkSpaceService = parkSpaceService;
        this.parkService = parkService;
    }

    @GetMapping("")
    public ResponseEntity<List<Park>> listAllParks() {
        List<Park> parks = parkService.findAllParks();

        return ResponseEntity.ok(parks);
    }

    @PostMapping("/create")
    public ResponseEntity<Park> createPark(@Valid @RequestBody ParkDTO data) {
        Park createdPark = parkService.createPark(data);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdPark);
    }

    @PutMapping("/{parkId}/update")
    public ResponseEntity<Park> updatePark(@PathVariable Integer parkId, @Valid @RequestBody ParkDTO parkDTO) {
        Park updatedPark = parkService.updatePark(parkId, parkDTO);

        return ResponseEntity.ok(updatedPark);
    }

    @GetMapping("/{parkId}/parkSpaces")
    public ResponseEntity<List<ParkSpace>> listAllParkSpaces(@PathVariable Integer parkId){
        List<ParkSpace> parkSpaces = parkSpaceService.getAllParkSpacesInPark(parkId);

        return ResponseEntity.ok(parkSpaces);
    }

    @PostMapping("/{parkId}/parkSpaces/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ParkSpace> createNewParkSpace(@PathVariable Integer parkId) {
        ParkSpace newParkSpace = parkSpaceService.createNewParkSpace(parkId);

        if (newParkSpace == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        return ResponseEntity.status(HttpStatus.CREATED).body(newParkSpace);
    }

    @DeleteMapping("/{parkId}/parkSpaces/{parkSpaceId}/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteParkSpaceById(
            @PathVariable Integer parkId,
            @PathVariable Integer parkSpaceId
    ) {
        parkSpaceService.removeParkSpace(parkSpaceId,parkId);
        return ResponseEntity.noContent().build();
    }
}