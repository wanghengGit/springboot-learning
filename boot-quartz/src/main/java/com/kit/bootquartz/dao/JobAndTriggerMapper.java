package com.kit.bootquartz.dao;

import java.util.List;

import com.example.quartz.entity.JobAndTrigger;

public interface JobAndTriggerMapper {
	public List<JobAndTrigger> getJobAndTriggerDetails();
}
