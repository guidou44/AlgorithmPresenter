package com.AlgorithmPresenter.Application.ControllerTests;

import com.AlgorithmPresenter.Application.Controllers.SortingController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = SortingController.class)
public class SortingControllerTests {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testReturnAppropriateViewWhenProperGet() throws Exception {
        mockMvc.perform(get("/SortingAlgorithm"))
                .andExpect(status().isOk())
                .andExpect(view().name("SortingView"));
    }

}
