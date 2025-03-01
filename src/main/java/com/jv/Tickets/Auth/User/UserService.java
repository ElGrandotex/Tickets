package com.jv.Tickets.Auth.User;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public UserDTO getUser(int id) {
        User user = userRepository.findById(id).orElse(null);

        if(user != null) {
            UserDTO userDTO = UserDTO.builder()
                    .id(user.id)
                    .username(user.username)
                    .firstname(user.firstname)
                    .lastname(user.lastname)
                    .phone(user.phone)
                    .build();
            return userDTO;
        }
        return null;
    }

    @Transactional
    public UserResponse updateUser(UserRequest userRequest) {
        User user = User.builder()
                .id(userRequest.id)
                .firstname(userRequest.getFirstname())
                .lastname(userRequest.lastname)
                .phone(userRequest.getPhone())
                .role(Role.USER)
                .build();

        userRepository.updateUser(user.id, user.firstname, user.lastname, user.phone);

        return new UserResponse("El usuario se ha actualizado correctamente");
    }
}
