package org.linkUps.services.Dtos;


import lombok.Data;
import org.linkUps.data.models.Profile;
import org.linkUps.data.models.User;

@Data
public class UserRegisterationRequest {
    private User user;
    private Profile profile;
}
