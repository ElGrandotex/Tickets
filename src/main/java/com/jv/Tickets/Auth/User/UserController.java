package com.jv.Tickets.Auth.User;

import com.jv.Tickets.Utils.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/user")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:4200"})
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<User>>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(ApiResponse.<List<User>>builder()
                .isSuccess(true)
                .message("List successful")
                .data(users)
                .build());
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<ApiResponse<UserDTO>> getUser(@PathVariable int id) {
        UserDTO userDTO = userService.getUser(id);
        if(userDTO == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.<UserDTO>builder()
                            .isSuccess(false)
                            .message("User with ID " + id + " not found")
                            .data(null)
                            .build()
                    );
        }
        return ResponseEntity.ok(ApiResponse.<UserDTO>builder()
                .isSuccess(true)
                .message("User "+userDTO.getId()+" found")
                .data(userDTO)
                .build());
    }

    @PutMapping()
    public ResponseEntity<ApiResponse<UserDTO>> updateUser(@RequestBody UserRequest userRequest) {
        try {
            UserDTO userResponse = userService.updateUser(userRequest);
            return ResponseEntity.ok(ApiResponse.<UserDTO>builder()
                    .isSuccess(true)
                    .message("User updated successfully")
                    .data(userResponse)
                    .build());
        } catch (ConfigDataResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.<UserDTO>builder()
                            .isSuccess(false)
                            .message(ex.getMessage())
                            .data(null)
                            .build());
        }
    }

    @DeleteMapping(value = "delete/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable int id){
        UserDTO userDTO = userService.getUser(id);
        if(userDTO == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.<Void>builder()
                            .isSuccess(false)
                            .message("User with ID " + id + " not found")
                            .data(null)
                            .build()
                    );
        }

        userService.delete(id);
        return ResponseEntity.ok(ApiResponse.<Void>builder()
                .isSuccess(true)
                .message("User successfully deleted")
                .data(null)
                .build());
    }
}
