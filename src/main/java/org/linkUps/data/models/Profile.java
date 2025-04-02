package org.linkUps.data.models;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;



    @Document(collection = "profiles")
    @Data
    @NoArgsConstructor
    public class Profile {
        @Id
        String id;
        Gender gender;
        String location;
        Height height;
        Weight weight;
        LocalDate dateOfBirth;
    }


