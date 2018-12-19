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

public class UserTask {

	ProcessEngine pe = ProcessEngines.getDefaultProcessEngine();
	@Test
	public void deploy(){
		Deployment deploy = pe.getRepositoryService()
		.createDeployment()
		.category("�칫����")
		.name("֧������")
		.addClasspathResource("diagrams/appayProcess.bpmn")
		.deploy();
		System.out.println("����ɹ���"+deploy);
	}
	@Test
	public void startProcess(){
		String bpmnId = "appayBill";
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("userId", "лĳĳ");
		ProcessInstance pi = pe.getRuntimeService()
				.startProcessInstanceByKey(bpmnId, params);
		System.out.println("�������̣�"+pi);
	}
	@Test
	public void queryTask(){
		String assigne = "лĳĳ";
		List<Task> list = pe.getTaskService()
				.createTaskQuery()
				.taskAssignee(assigne)
				.list();
		for(Task task:list){
			System.out.println("����id��"+task.getId());
			System.out.println("��������"+task.getName());
			System.out.println("��������ˣ�"+task.getAssignee());
			System.out.println("���̶���id��"+task.getProcessDefinitionId());
			System.out.println("����ʵ��id��"+task.getProcessInstanceId());
		}
	}
	@Test
	public void compileTask(){
		String taskId = "130003";
//		pe.getTaskService().complete(taskId);
		Map<String, Object> param = new HashMap<String,Object>();
		param.put("visitor", 6);
		pe.getTaskService().complete(taskId, param);
		System.out.println("��ǰ����ִ�����!");
	}
}
