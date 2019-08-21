//package ru.stacy.controllers;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import ru.stacy.dto.UserDTO;
//import ru.stacy.entities.User;
//import ru.stacy.services.UserService;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.hamcrest.Matchers.containsInAnyOrder;
//import static org.hamcrest.Matchers.equalTo;
//import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
//import static org.mockito.ArgumentMatchers.eq;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@RunWith(SpringRunner.class)
//@WebMvcTest
//public class UserControllerTest {
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @MockBean
//    private UserService userService;
//
//    private final List<UserDTO> users = new ArrayList<>();
//
//    @Test
//    public void shouldSaveUser() throws Exception {
//        User user = new User();
//        user.setId(1L);
//        user.setUsername("stacy.cmd");
//        user.setEmail("stacy@gmail.com");
//        user.setPassword("poweru087");
//        user.setUserState(true);
//        // user.setCreated(LocalDateTime.now());
//
//        when(userService.createUser(user)).thenReturn(1L);
//
//        String json = objectMapper.writeValueAsString(user);
//
//        mockMvc.perform(post("/api/users")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(json)
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isCreated());
//    }
//
//    @Test
//    public void shouldReturnUserWhenFound() throws Exception {
//        UserDTO user1 = new UserDTO();
//        user1.setId(1L);
//        user1.setUsername("stacy.cmd");
//        user1.setEmail("stacy@gmail.com");
//        user1.setUserState(true);
//
//        when(userService.getUserById(1L)).thenReturn(Optional.of(user1));
//
//        mockMvc.perform(get("/api/users/1"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id", equalTo(1)))
//                .andExpect(jsonPath("$.username", equalTo("stacy.cmd")))
//                .andExpect(jsonPath("$.email", equalTo("stacy@gmail.com")))
//                .andExpect(jsonPath("$.userState", equalTo(true)));
//    }
//
//    @Test
//    public void shouldReturn404WhenUserNotFound() throws Exception {
//        when(userService.getUserById(29L)).thenReturn(Optional.empty());
//        mockMvc.perform(get("/users/1")).andExpect(status().isNotFound());
//    }
//
//    @Test
//    public void shouldReturnListOfUsers() throws Exception {
//        UserDTO user1 = new UserDTO();
//        user1.setId(1L);
//        user1.setUsername("stacy.cmd");
//        user1.setEmail("stacy@gmail.com");
//        user1.setUserState(true);
//
//        UserDTO user2 = new UserDTO();
//        user2.setId(2L);
//        user2.setUsername("lera.arel");
//        user2.setEmail("lera@gmail.com");
//        user2.setUserState(false);
//
//        users.add(user1);
//        users.add(user2);
//
//        when(userService.getAllUsers()).thenReturn(users);
//
//        mockMvc.perform(get("/api/users/statistics"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(2)))
//                .andExpect(jsonPath("$[*].id", containsInAnyOrder(1, 2)))
//                .andExpect(jsonPath("$[*].username", containsInAnyOrder("stacy.cmd", "lera.arel")))
//                .andExpect(jsonPath("$[*].email", containsInAnyOrder("stacy@gmail.com", "lera@gmail.com")))
//                .andExpect(jsonPath("$[*].userState", containsInAnyOrder(true, false)));
//    }
//}