package cn.itcast.activiti;
import java.util.List;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.junit.Test;
public class HelloActiviti {
	private ProcessEngine pe = ProcessEngines.getDefaultProcessEngine();
	@Test
	public void createProcessEngine() {
		
//		ProcessEngineConfiguration pec = ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration();
//		pec.setJdbcDriver("com.mysql.jdbc.Driver");
//		pec.setJdbcUrl("jdbc:mysql://localhost:3306/activitiDB?createDatabaseIfNotExist=true&useUnicode=true&serverTimezone=GMT%2B8");
//		pec.setJdbcUsername("root");
//		pec.setJdbcPassword("jinyue");
//		pec.setDatabaseSchemaUpdate("true");
//		ProcessEngine pe = pec.buildProcessEngine();
		ProcessEngineConfiguration pec = ProcessEngineConfiguration.createProcessEngineConfigurationFromResourceDefault();
		ProcessEngine pe = pec.buildProcessEngine();
		System.out.println(pe+"构建流程引擎成功!");
	}
	@Test
	public void deploy() {
//		ProcessEngineConfiguration pce = ProcessEngineConfiguration.createProcessEngineConfigurationFromResourceDefault();
//		ProcessEngine pe = pce.buildProcessEngine();
		RepositoryService rs = pe.getRepositoryService();
		Deployment deploy = rs.createDeployment()
		.addClasspathResource("diagrams/leaveProcess.bpmn")
		.name("请假单流程")
		.category("办公分类")
		.deploy();
		System.out.println(deploy.getId());
		System.out.println(deploy.getName());
	}
	@Test
	public void startProcess() {
		ProcessInstance pi = pe.getRuntimeService().startProcessInstanceByKey("leaveBill");
		System.out.println("流程实例id"+pi.getId());
		System.out.println("流程定义id"+pi.getProcessDefinitionId());
	}
	@Test
	public void queryTask() {

		String assignee = "李四";
		TaskService ts = pe.getTaskService();
		TaskQuery tq = ts.createTaskQuery();
		List<Task> list = tq.taskAssignee(assignee).list();
		for(Task task:list){
			System.out.println("任务办理人："+task.getAssignee());
			System.out.println("任务id："+task.getId());
			System.out.println("任务名："+task.getName());
		}
	}
	@Test
	public void compileTask() {

		String taskId = "10002";
		TaskService ts = pe.getTaskService();
		ts.complete(taskId);
		System.out.println("任务执行完毕!");
	}
}
