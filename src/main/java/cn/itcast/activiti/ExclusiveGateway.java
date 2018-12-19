package cn.itcast.activiti;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

public class ExclusiveGateway {

	ProcessEngine pe = ProcessEngines.getDefaultProcessEngine();
	@Test
	public void deploy(){
		Deployment deploy = pe.getRepositoryService()
		.createDeployment()
		.category("办公分类")
		.name("排他网关流程")
		.addClasspathResource("diagrams/bankProcess.bpmn")
		.deploy();
		System.out.println("部署成功："+deploy);
	}
	@Test
	public void startProcess(){
		String bpmnId = "bankBill";
		ProcessInstance pi = pe.getRuntimeService().startProcessInstanceByKey(bpmnId);
		System.out.println("启动流程："+pi);
	}
	@Test
	public void queryTask(){
		List<Task> list = pe.getTaskService().createTaskQuery().list();
		for(Task task:list){
			System.out.println("任务id："+task.getId());
			System.out.println("任务名："+task.getName());
			System.out.println("任务办理人："+task.getAssignee());
			System.out.println("流程定义id："+task.getProcessDefinitionId());
			System.out.println("流程实例id："+task.getProcessInstanceId());
		}
	}
	@Test
	public void compileTask(){
		String taskId = "110004";
//		pe.getTaskService().complete(taskId);
		Map<String, Object> param = new HashMap<String,Object>();
		param.put("visitor", 6);
		pe.getTaskService().complete(taskId, param);
		System.out.println("当前任务执行完成!");
	}
}
