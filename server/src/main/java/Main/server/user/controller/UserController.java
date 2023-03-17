package Main.server.user.controller;

import Main.server.user.dto.UserDto;
import Main.server.user.entity.Users;
import Main.server.user.mapper.UserMapper;
import Main.server.user.response.MultiResponse;
import Main.server.user.response.PageInfo;
import Main.server.user.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@Validated
@RequestMapping("/users")
public class UserController {
    private final String url = "http://localhost:8080/users/";
    private final UserService userService;
    private final UserMapper mapper;

    public UserController(UserService userService, UserMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity postUser(@RequestBody @Valid UserDto.Post post) throws Exception {
        Users users = mapper.userDtoPostToUser(post);
        Users createdUsers = userService.createUser(users);
        UserDto.Response response = mapper.userToUserDtoResponse(createdUsers);
        response.setUrl(url + response.getUserId());
        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @PatchMapping("/{user-id}")
    public ResponseEntity patchUser(@PathVariable("user-id") long userId,
                                    @RequestBody @Valid UserDto.Patch patch) throws Exception {
        Users users = mapper.userDtoPatchToUser(patch);
        users.setUserId(userId);
        Users updatedUsers = userService.updateUser(users);
        UserDto.Response response = mapper.userToUserDtoResponse(updatedUsers);
        response.setUrl(url + response.getUserId());
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping("/{user-id}")
    public ResponseEntity getUser(@PathVariable("user-id") @Positive long userId) {
        Users findUsers = userService.getUser(userId);
        UserDto.Response response = mapper.userToUserDtoResponse(findUsers);
        response.setUrl(url + response.getUserId());
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getUsers(@Positive @RequestParam(required = false, defaultValue = "1") int page) throws Exception {
        Page<Users> userPage = userService.getUsers(page);
        PageInfo pageInfo = new PageInfo(userPage.getNumber(), userPage.getSize(), userPage.getTotalElements(), userPage.getTotalPages());
        List<Users> users = userPage.getContent();
        List<UserDto.Response> responses = mapper.usersToUserDtoResponse(users);
        responses.stream().forEach(s -> s.setUrl(url + s.getUserId()));
        return new ResponseEntity<>(
                new MultiResponse<>(responses, pageInfo), HttpStatus.OK);
    }

    @DeleteMapping("/{user-id}")
    public ResponseEntity deleteUser(@PathVariable("user-id") long userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
