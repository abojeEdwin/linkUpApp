package org.linkUps.data.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class User {
    @Id
    String id;
    String username;

    @Indexed(unique = true)
    String email;
    String password;
    Profile profile;
    Gender gender;
    Weight weight;
    Height height;
}
