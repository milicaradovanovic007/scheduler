package com.milicaradovanovic.sap;

import com.milicaradovanovic.sap.entity.ScheduleTaskEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SapApiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SapApiApplicationTests {
	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	private String getRootUrl() {
		return "http://localhost:" + port;
	}

	@Test
	void contextLoads() {
	}

	@Test
	public void testGetAllTasks() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/task",
				HttpMethod.GET, entity, String.class);
		Assertions.assertNotNull(response.getBody());
	}

	@Test
	public void testGetTaskById() {
		ScheduleTaskEntity scheduleTaskEntity = restTemplate.getForObject(getRootUrl() + "/task/3", ScheduleTaskEntity.class);
		System.out.println(scheduleTaskEntity.getName());
		Assertions.assertNotNull(scheduleTaskEntity);
	}

	@Test
	public void testCreateTask() {
		ScheduleTaskEntity scheduleTaskEntity = new ScheduleTaskEntity();
		scheduleTaskEntity.setName("name");
		scheduleTaskEntity.setRecurrency("rec");
		scheduleTaskEntity.setCode("code");
		ResponseEntity<ScheduleTaskEntity> postResponse = restTemplate.postForEntity(getRootUrl() + "/task",
				scheduleTaskEntity, ScheduleTaskEntity.class);
		Assertions.assertNotNull(postResponse);
		Assertions.assertNotNull(postResponse.getBody());
	}

	@Test
	public void testUpdateTask() {
		int id = 3;
		ScheduleTaskEntity scheduleTaskEntity = restTemplate.getForObject(getRootUrl() + "/task/" + id,
				ScheduleTaskEntity.class);
		scheduleTaskEntity.setName("name2");
		scheduleTaskEntity.setRecurrency("rec2");
		restTemplate.put(getRootUrl() + "/task/" + id, scheduleTaskEntity);
		ScheduleTaskEntity updatedScheduleTaskEntity = restTemplate.getForObject(getRootUrl() + "/task/" + id,
				ScheduleTaskEntity.class);
		Assertions.assertNotNull(updatedScheduleTaskEntity);
	}

	@Test
	public void testDeleteTask() {
		int id = 3;
		ScheduleTaskEntity scheduleTaskEntity = restTemplate.getForObject(getRootUrl() + "/task/" + id, ScheduleTaskEntity.class);
		Assertions.assertNotNull(scheduleTaskEntity);
		restTemplate.delete(getRootUrl() + "/task/" + id);
		try {
			scheduleTaskEntity = restTemplate.getForObject(getRootUrl() + "/task/" + id, ScheduleTaskEntity.class);
		} catch (final HttpClientErrorException e) {
			Assertions.assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
		}
	}

}