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

public class SequenceFlow {

	ProcessEngine pe = ProcessEngines.getDefaultProcessEngine();
	@Test
	public void deploy(){
		Deployment deploy = pe.getRepositoryService()
		.createDeployment()
		.category("�칫����")
		.name("��������")
		.addClasspathResource("diagrams/sequenceProcess.bpmn")
		.deploy();
		System.out.println("����ɹ���"+deploy);
	}
	@Test
	public void startProcess(){
		String bpmnId = "sequenceBill";
		ProcessInstance pi = pe.getRuntimeService().startProcessInstanceByKey(bpmnId);
		System.out.println("�������̣�"+pi);
	}
	@Test
	public void queryTask(){
		List<Task> list = pe.getTaskService().createTaskQuery().list();
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
		String taskId = "82504";
//		pe.getTaskService().complete(taskId);
		Map<String, Object> param = new HashMap<String,Object>();
		param.put("message", "��֪���ز���Ҫ");
		pe.getTaskService().complete(taskId, param);
		System.out.println("��ǰ����ִ�����!");
	}
}
