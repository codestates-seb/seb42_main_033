package Main.server.user;

import Main.server.user.controller.UserController;
import Main.server.user.dto.UserDto;
import Main.server.user.entity.Users;
import Main.server.user.mapper.UserMapper;
import Main.server.user.service.UserService;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@SpringBootTest
//@AutoConfigureMockMvc
@WebMvcTest(UserController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureMockMvc(addFilters = false)
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private Gson gson;

    @MockBean
    private UserService userService;
    @MockBean
    private UserMapper mapper;

    @Test
    public void postUserTest() throws Exception {
        //given
        UserDto.Post post = new UserDto.Post("John", "John@gmail.com", "John", "John", "ISTJ");
        String content = gson.toJson(post);

        UserDto.Response responseDto =
                new UserDto.Response(1,
                        "John",
                        "John@gmail.com",
                        "John",
                        "John",
                        "ISTJ",
                        "http://localhost:8080/users/1"
                );


        given(mapper.userDtoPostToUser(Mockito.any(UserDto.Post.class))).willReturn(new Users());
        given(userService.createUser(Mockito.any(Users.class))).willReturn(new Users());
        given(mapper.userToUserDtoResponse(Mockito.any(Users.class))).willReturn(responseDto);


        //when
        ResultActions actions =
                mockMvc.perform(post("/users")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content)
                );

        //then
        actions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nickName").value(post.getNickName()))
                .andExpect(jsonPath("$.email").value(post.getEmail()))
                .andExpect(jsonPath("$.password1").value(post.getPassword1()))
                .andExpect(jsonPath("$.password2").value(post.getPassword2()))
                .andExpect(jsonPath("$.mbti").value(post.getMbti()));
    }

    @Test
    public void patchUserTest() throws Exception {

        long userId = 1L;
        UserDto.Patch patch = new UserDto.Patch(userId, "Paul", "John@gmail.com", "Paul", "Paul", "ESTJ");
        String content1 = gson.toJson(patch);


        UserDto.Response responseDto1 =
                new UserDto.Response(1,
                        "Paul",
                        "John@gmail.com",
                        "Paul",
                        "Paul",
                        "ESTJ",
                        "http://localhost:8080/users/1");

        given(mapper.userDtoPatchToUser(Mockito.any(UserDto.Patch.class))).willReturn(new Users());
        given(userService.updateUser(Mockito.any(Users.class))).willReturn(new Users());
        given(mapper.userToUserDtoResponse(Mockito.any(Users.class))).willReturn(responseDto1);




        ResultActions actions1 =
                mockMvc.perform(patch("/users/{user-id}", userId)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(content1)
                );

        actions1
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(patch.getUserId()))
                .andExpect(jsonPath("$.nickName").value(patch.getNickName()))
                .andExpect(jsonPath("$.email").value(patch.getEmail()))
                .andExpect(jsonPath("$.password1").value(patch.getPassword1()))
                .andExpect(jsonPath("$.password2").value(patch.getPassword2()))
                .andExpect(jsonPath("$.mbti").value(patch.getMbti()));

    }

    @Test
    public void getUserTest() throws Exception {

        long userId = 1L;
        Users users = new Users(userId, "Paul", "John@gmail.com", "Paul", "Paul", "ESTJ");

        UserDto.Response response =
                new UserDto.Response(
                        1L,
                        "Paul",
                        "John@gmail.com",
                        "Paul",
                        "Paul",
                        "ESTJ",
                        "http://localhost:8080/users/1"
                );

        given(userService.getUser(Mockito.any(Long.class))).willReturn(new Users());
        given(mapper.userToUserDtoResponse(Mockito.any(Users.class))).willReturn(response);

        ResultActions actions =
                mockMvc.perform(get("/users/{user-id}", userId)
                        .accept(MediaType.APPLICATION_JSON)
                );

        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(users.getUserId()))
                .andExpect(jsonPath("$.nickName").value(users.getNickName()))
                .andExpect(jsonPath("$.email").value(users.getEmail()))
                .andExpect(jsonPath("$.password1").value(users.getPassword1()))
                .andExpect(jsonPath("$.password1").value(users.getPassword2()))
                .andExpect(jsonPath("$.mbti").value(users.getMbti()));
    }

    @Test
    public void getUsersTest() throws Exception {
        long firstUserId = 1L;
        Users post = new Users(firstUserId,"John", "John@gmail.com", "John", "John", "ISTJ");

        long secondUserId = 2L;
        Users post1 = new Users(secondUserId, "Anne", "Anne@gmail.com", "Anne", "Anne", "ESFP");


        Page<Users> userPages = new PageImpl<>(List.of(
                post, post1), PageRequest.of(0, 10, Sort.by("userId").descending()), 2);

        List<UserDto.Response> responses = List.of(
                new UserDto.Response(
                        firstUserId,
                        "John",
                        "John@gmail.com",
                        "John",
                        "John",
                        "ISTJ",
                        "http://localhost:8080/users/1"
                ),

                new UserDto.Response(
                        secondUserId,
                        "Anne",
                        "Anne@gmail.com",
                        "Anne",
                        "Anne",
                        "ESFP",
                        "http://localhost:8080/users/2"
                )

        );

        given(userService.getUsers(Mockito.anyInt())).willReturn(userPages);
        given(mapper.usersToUserDtoResponse(Mockito.anyList())).willReturn(responses);

        ResultActions actions =
                mockMvc.perform(get("/users")
                                .param("page","1")
                                .accept(MediaType.APPLICATION_JSON)
                );

        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray());
    }

    @Test
    public void deleteUserTest() throws Exception {

        long userId = 1L;
        doNothing().when(userService).deleteUser(userId);

        ResultActions actions =
                mockMvc.perform(delete("/users/{user-id}", userId));

        actions
                .andExpect(status().isNoContent());

    }

}
