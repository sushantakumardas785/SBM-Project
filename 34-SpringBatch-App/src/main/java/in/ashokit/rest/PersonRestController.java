package in.ashokit.rest;

import org.springframework.batch.core.job.Job;
import org.springframework.batch.core.job.parameters.JobParameters;
import org.springframework.batch.core.job.parameters.JobParametersBuilder;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonRestController {
	
	@Autowired
	private JobOperator jobOperator;
	
	@Autowired
	private Job job;
	
	@GetMapping("/data")
	public void loadData() throws Exception{
		
		JobParameters jobParams = new JobParametersBuilder()
										.addLong("startsAt", System.currentTimeMillis())
										.toJobParameters();
		
		jobOperator.start(job, jobParams);
	}

}
