package com.rungroop.web.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class ClubDto {
    private long id;
    @NotEmpty(message = "Club title should not empty")
    private String title;
    @NotEmpty(message = "photo link should not empty")
    private String photoUrl;
    @NotEmpty(message = "Content should not empty")
    private String content;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
    private List<EventDto> events ;
}
