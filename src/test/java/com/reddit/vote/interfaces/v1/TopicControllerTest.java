package com.reddit.vote.interfaces.v1;

import com.reddit.vote.common.QueryString;
import com.reddit.vote.common.Uri;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TopicControllerTest {

	private MockMvc mvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setup() throws Exception {
		mvc = webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void getTopics() throws Exception {
		mvc.perform(get(Uri.V1_REDDIT_TOPICS + QueryString.TOP20))
			.andExpect(status().isOk());
	}

	@Test
	public void showForm() throws Exception {
		mvc.perform(get(Uri.V1_REDDIT_TOPICS_NEW))
			.andExpect(status().isOk());
	}

	@Test
	public void submit() throws Exception {
		mvc.perform(post(Uri.V1_REDDIT_TOPICS)
			.param("text", "text1"))
			.andExpect(status().is3xxRedirection());
	}

	@Test
	public void submitWithInvalidData() throws Exception {
		mvc.perform(post(Uri.V1_REDDIT_TOPICS))
			.andExpect(status().isOk());
	}
}
