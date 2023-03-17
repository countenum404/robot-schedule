package com.rshu.schedule;

import com.rshu.schedule.api.controllers.GroupController;
import com.rshu.schedule.api.controllers.ScheduleController;
import com.rshu.schedule.api.controllers.StudentController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ScheduleApplicationTests {

	@Autowired
	private StudentController studentController;
	@Autowired
	private ScheduleController scheduleController;
	@Autowired
	private GroupController groupController;

	@Test
	public void verifyThatControllersIsNotNull(){
		assertThat(studentController).isNotNull();
		assertThat(scheduleController).isNotNull();
		assertThat(groupController).isNotNull();
	}

}
