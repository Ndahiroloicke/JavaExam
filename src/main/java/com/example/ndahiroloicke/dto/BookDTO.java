package com.example.ndahiroloicke.dto;

import com.example.ndahiroloicke.model.Book.AvailabilityStatus;
import lombok.Data;

@Data
public class BookDTO {
    private Long id;
    private String title;
    private String author;
    private String isbn;
    private AvailabilityStatus availabilityStatus;
} 