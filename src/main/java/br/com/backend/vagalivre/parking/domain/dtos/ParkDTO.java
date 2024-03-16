package br.com.backend.vagalivre.parking.domain.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ParkDTO {
    @NotNull
    @NotBlank(message = "O campo de nome nao pode esta vazio")
    String name;

    @NotNull
    @NotBlank(message = "O campo de descrição não pode esta vazio")
    String description;

    @NotNull
    @Positive
    Double hourlyRate;

    @NotNull
    @NotBlank(message = "O campo de contato nao pode está vazio")
    String contactInfo;

    @NotNull
    LocalDateTime startTime;

    @NotNull
    LocalDateTime endDateTime;
}
