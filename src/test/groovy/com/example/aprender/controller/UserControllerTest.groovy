package com.example.aprender.controller

import com.example.aprender.Entity.User
import com.example.aprender.usecases.CreateUser
import com.example.aprender.usecases.DeleteUser
import com.example.aprender.usecases.SearchById
import com.example.aprender.usecases.UpdateUser
import com.fasterxml.jackson.databind.ObjectMapper
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MvcResult
import spock.lang.Specification


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest extends Specification {

    @Autowired
    MockMvc mockMvc

    @SpringBean
    SearchById searchById = Mock()

    @SpringBean
    CreateUser createUser = Mock()

    @SpringBean
    UpdateUser updateUser = Mock()

    @SpringBean
    DeleteUser deleteUser = Mock()

    def "Metodo Get funciona corretamente"() {
        given: "A requisição é feita"

        ObjectMapper objectMapper = new ObjectMapper();
        String nome = "Hiago"
        int id = 1
        String telefone = "19999999999"
        User user =  new User(id, nome, telefone)

        when: "Chama o entrypoint"
        MvcResult result = mockMvc.perform(get("/user/1")).andReturn();

        then: "Retorna uma lista de usuários"
        searchById.execute(id)>>{
            return  user
        }

        User userFinal = objectMapper.readValue(result.response.contentAsString, User)

        and: "Comparar os resultados"
        result.response.status == 201
        userFinal.id == id
        userFinal.name == nome
        userFinal.telefone == telefone
    }

    def "Metodo POST funciona corretamente"() {
        given: "A requisição é feita"

        ObjectMapper objectMapper = new ObjectMapper();
        String nome = "Hiago"
        int id = 1
        String telefone = "19999999999"
        User user =  new User(id, nome, telefone)

        when: "Chama o entrypoint"
        MvcResult result = mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(user))).andReturn();

        then: "Retorna uma lista de usuários"
        createUser.execute(_ as User)>>{
            return user
        }

        User userFinal = objectMapper.readValue(result.response.contentAsString, User)

        and: "Comparar os resultados"
        result.response.status == 201
    }

    def "Metodo PUT funciona corretamente"() {
        given: "A requisição é feita"

        ObjectMapper objectMapper = new ObjectMapper();
        String nome = "Hiago"
        int id = 1
        String telefone = "19999999999"
        User user =  new User(id, nome, telefone)

        when: "Chama o entrypoint"
        mockMvc.perform(put("/user/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user))).andExpect(status().isOk());


        then: "Não retorna uma exception"

    }

    def "Metodo DELETE funciona corretamente"() {
        given: "A requisição é feita"

        when: "Chama o entrypoint"
        mockMvc.perform(delete("/user/1"))
        .andExpect(status().isOk());

        then:"Não retorna uma exception"

    }

}
