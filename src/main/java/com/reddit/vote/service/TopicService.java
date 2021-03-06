package com.reddit.vote.service;

import com.reddit.vote.model.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicService {
	@Autowired
	private CounterService counterService;

	@Autowired
	private CacheService cacheService;

	@Autowired
	private PersistentService persistentService;

	public List<Topic> getTopTopics() {
		return cacheService.getTopTopics();
	}

	public synchronized void save(Topic topic) {
		topic.setId(counterService.generateId());
		persistentService.save(topic);
		cacheService.save(topic);
	}

	public synchronized void upvote(Integer topicId) {
		Topic topic = persistentService.upvote(topicId);
		cacheService.refresh(topic);
	}

	public synchronized void downvote(Integer topicId) {
		persistentService.downvote(topicId);
	}
}
