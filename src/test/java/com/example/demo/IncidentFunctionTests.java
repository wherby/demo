package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Duration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class IncidentFunctionTests {

	@Autowired
	private MockMvc mockMvc;


	String incidentStr = "{\"type\":\"Client\",\"description\":\"Client can't access web\",\"priority\":1,\"reportName\":\"Alice\"}";
	@Test
	void testAddOps(@Autowired MockMvc mvc) throws Exception{
		addOps(mvc);
	}
	@Test
	void testListPage(@Autowired MockMvc mvc) throws Exception{
		int pageNo = 1;
		listPageTest(mvc, pageNo);
	}

	@Test
	void  testAddAndList(@Autowired MockMvc mvc) throws Exception{
		long ADDTIMES = 5000;

		timerForStressTest(mvc, ADDTIMES);
	}

	private void timerForStressTest(MockMvc mvc, long ADDTIMES) throws Exception {
		System.out.println("The stress testing start, please wait...");
		long startTime = System.nanoTime();

		stressTestOnAddAndQuery(mvc, ADDTIMES);
		long endTime = System.nanoTime();
		long elapsedTime = endTime - startTime;
		// Convert nanoseconds to milliseconds for better readability
		long elapsedTimeInMillis = Duration.ofNanos(elapsedTime).toMillis();

		System.out.println("Elapsed time: " + elapsedTimeInMillis + " milliseconds for  " + ADDTIMES + " times record add query");
	}

	private void stressTestOnAddAndQuery(MockMvc mvc, long ADDTIMES) throws Exception {
		for (int i = 0; i < ADDTIMES; i++) {
			addOps(mvc);
		}
		long pageNum = ADDTIMES / 5;
		long queryTime = 5;
		for (int i = 0; i < queryTime; i++) {
			for (int j = 1; j < pageNum; j++) {
				listPageTest(mvc, j);
			}
		}
	}

	private static void listPageTest(MockMvc mvc, int pageNo) throws Exception {
		mvc.perform(get("/api/v1/incident/" + pageNo + "/5"))
				.andExpect(status().isOk());
	}

	private void addOps(MockMvc mvc) throws Exception {
		mvc.perform(post("/api/v1/incident")
						.contentType(MediaType.APPLICATION_JSON)
						.content(incidentStr))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.id").exists());
	}



}
