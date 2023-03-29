//package Main.server.security;
//
//
//import Main.server.auth.JwtTokenizer;
//import Main.server.auth.filter.JwtAuthenticationFilter;
//import Main.server.user.Users;
//import com.google.gson.Gson;
//import com.google.gson.JsonObject;
//import com.google.gson.JsonParser;
//import org.aspectj.lang.annotation.Before;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultActions;
//import org.springframework.test.web.servlet.ResultHandler;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//public class SecurityConfigTest {
//    @Autowired
//    MockMvc mockMvc;
//    @Autowired
//    PasswordEncoder passwordEncoder;
//    @Autowired
//    Gson gson;
//    @Autowired
//    JwtTokenizer jwtTokenizer;
//
//    JsonObject obj = new JsonObject();
//
//    @BeforeEach
//    public void init() throws Exception{
//        String nickName = "aa";
//        String email = "fkdltj2@gmail.com";
//        String password1 = "1111";
//        String password2 = "1111";
//        String mbti = "intp";
//
//        obj.addProperty("nickName", nickName);
//        obj.addProperty("email", email);
//        obj.addProperty("password1", password1);
//        obj.addProperty("password2", password2);
//        obj.addProperty("mbti", mbti);
//
//        mockMvc.perform(post("/users")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(obj.toString()));
//    }
//
//    @Test
//    @DisplayName("로그인 테스트")
//    public void loginTest() throws Exception{
//
//        JsonObject obj1 = new JsonObject();
//
//        String email1 = "fkdltj2@gmail.com";
//        String password1 = "1111";
//
//        obj1.addProperty("email", email1);
//        obj1.addProperty("password1", password1);
//
//        mockMvc.perform(post("/user/login")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(obj1.toString()))
//                .andExpect(status().isOk())
//                .andDo(print());
//    }
//}
