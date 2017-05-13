package com.reddit.vote.service;

import com.reddit.vote.domain.Topic;
import com.reddit.vote.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicService {
	@Autowired
	private CounterService counterService;

	@Autowired
	private TopicRepository topicRepository;

	public List<Topic> getTopics() {
		return topicRepository.getTopics();
	}

	public void saveTopic(Topic topic) {
		topic.setId(counterService.generate());
		topicRepository.addTopic(topic);
	}

	public void increaseUp(int topicId) {
		topicRepository.increaseUp(topicId);
	}

	public void increaseDown(int topicId) {
		topicRepository.increaseDown(topicId);
	}
}
