package com.example.backend;

import com.example.backend.api.GradeController;
import com.example.backend.api.QuestionController;
import com.example.backend.api.UserController;
import com.example.backend.dao.QuestionDao;
import com.example.backend.model.Question;
import com.example.backend.model.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.mock.web.MockHttpSession;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BackendApplicationTests {

	@LocalServerPort
	private int port;
	@Autowired
	private QuestionController questionController;
	@Autowired
	private TestRestTemplate restTemplate;
	@MockBean
	private HttpServletRequest request;
	@Autowired
	private QuestionDao questionDao;
	@Autowired
	private UserController userController;
	@Autowired
	GradeController gradeController;
	private <T> String httpRequest(String url, HttpMethod method, HttpEntity<T> entity){
		try {
			//发起一个POST请求
			ResponseEntity<String> result = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
			return result.getBody();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

@Test
	void testAddQuestion_teacher() {
	MockHttpSession session = new MockHttpSession();
	session.setAttribute("type", "teacher");

	when(request.getSession()).thenReturn(session);

		Question question = new Question(); // Create your question object
		question.setExamId(3L);
		question.setNumber(1L);
		question.setMaxScore(10L);
		question.setIsChoice(true);
		question.setDesc("test");
		question.setStdAnswer("test");

		ResponseEntity<String> response = questionController.addQuestion(request, question);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("success", response.getBody());
	}

}
