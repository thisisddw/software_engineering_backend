package com.example.backend;

import com.example.backend.dao.ExamDao;
import com.example.backend.model.Exam;
import com.example.backend.model.Question;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ExamControllerTest {

    @LocalServerPort
    private int port;
    @MockBean
    private ExamDao examDao;
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testGetAllExams() {
        when(examDao.getAllExams()).thenReturn(Arrays.asList(
                new Exam(1L, "考试1", "考试1描述", 100.0f),
                new Exam(2L, "考试2", "考试2描述", 200.0f),
                new Exam(3L, "考试3", "考试3描述", 300.0f)
        ));
        String url = "http://localhost:" + port + "/api/exam/all";
        ResponseEntity<Exam[]> response = restTemplate.getForEntity(url, Exam[].class);

        // 验证HTTP状态码
        assertEquals(HttpStatus.OK, response.getStatusCode());
        // 验证响应体内容
        Exam[] exams = response.getBody();

        assert (exams.length == 3);
        // 根据实际情况编写断言，验证返回的考试列表是否符合预期
    }

    @Test
    public void testGetExamContent() {
        Long examId = 1L; // 根据实际情况设置要请求的考试ID

        String url = "http://localhost:" + port + "/api/exam/content?id=" + examId;
        ResponseEntity<Question[]> response = restTemplate.getForEntity(url, Question[].class);

        // 验证HTTP状态码
        assertEquals(HttpStatus.OK, response.getStatusCode());

        // 验证响应体内容
        Question[] questions = response.getBody();
        assert(questions[0].getDesc().equals("cet4 choice test 1:\n" +
                "A: \n" +
                "B: \n" +
                "C: "));
        // 根据实际情况编写断言，验证返回的问题列表是否符合预期
    }
}
