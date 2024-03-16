package br.com.backend.vagalivre.parking.domain.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table
public class Park {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @NotBlank(message = "O campo de nome nao pode esta vazio")
    private String name;

    @NotNull
    @NotBlank(message = "O campo de descrição não pode esta vazio")
    private String description;

    @Positive
    private Double hourlyRate;

    @NotNull
    @NotBlank(message = "O campo de contato nao pode está vazio")
    private String contactInfo;

    @NotNull
    private LocalDateTime startTime;

    @NotNull
    private LocalDateTime endDateTime;

    public Park(String name, String description, Double hourlyRate, String contactInfo, LocalDateTime startTime, LocalDateTime endDateTime){
        this.name = name;
        this.description = description;
        this.hourlyRate = hourlyRate;
        this.contactInfo = contactInfo;
        this.startTime = startTime;
        this.endDateTime = endDateTime;
    }

}