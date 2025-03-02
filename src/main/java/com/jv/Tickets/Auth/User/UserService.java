package com.jv.Tickets.Auth.User;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
            return UserDTO.builder()
                    .id(user.id)
                    .username(user.username)
                    .firstname(user.firstname)
                    .lastname(user.lastname)
                    .phone(user.phone)
                    .build();
        }
        return null;
    }

    @Transactional
    public UserDTO updateUser(UserRequest userRequest) {
        User user = userRepository.findById(userRequest.id)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        user.setFirstname(userRequest.getFirstname());
        user.setLastname(userRequest.getLastname());
        user.setPhone(userRequest.getPhone());
        user.setRole(userRequest.isOrganizer ? Role.ORGANIZER : Role.USER);

        User updatedUser = userRepository.save(user);

        return mapToUserResponse(updatedUser);
    }

    private UserDTO mapToUserResponse(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .phone(user.getPhone())
                .role(String.valueOf(user.getRole()))
                .build();
    }

    public void delete(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
        userRepository.delete(user);
    }
}
