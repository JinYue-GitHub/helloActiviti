package cn.itcast.activiti;

import java.util.List;
import java.util.Optional;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.junit.Test;

import groovyjarjarcommonscli.Option;

public class ProcessInstanceAndTask {
	private ProcessEngine pe = ProcessEngines.getDefaultProcessEngine();
	@Test
	public void startProcess() {

		String processDefiKey = "buyBill";
		ProcessInstance pi = pe.getRuntimeService().startProcessInstanceByKey(processDefiKey);
		System.out.println("���̶���id(ִ�ж���id)��"+pi.getId());
		System.out.println("����ʵ��id��"+pi.getProcessInstanceId());
		System.out.println("���̶���id��"+pi.getProcessDefinitionId());
		System.out.println("���̶�������"+pi.getName());
	}
	
	@Test
	public void queryTask(){
		TaskService ts = pe.getTaskService();
		TaskQuery tq = ts.createTaskQuery();
		List<Task> list = tq.list();
		for(Task task:list){
			System.out.println("����id:"+task.getId());
			System.out.println("��������ˣ�"+task.getAssignee());
			System.out.println("��������"+task.getName());
		}
	}
	@Test
	public void compileTask(){
		String taskId = "25002"; 
		TaskService ts = pe.getTaskService();
		ts.complete(taskId);
		System.out.println("����ִ�����!");
	}
	@Test
	public void getProcessInstanceState(){
		String processInstanceId = "20001";
		ProcessInstance pi = pe.getRuntimeService().createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
		if(Optional.ofNullable(pi).isPresent()){
			System.out.println("����ʵ��"+pi+"��������..."+"��ǰ���е������ǣ�"+pi.getActivityId());
		}else{
			System.out.println("����ʵ��"+pi+"����");
		}
	}
	@Test
	public void queryHistoryProcinst(){
		List<HistoricProcessInstance> list = pe.getHistoryService().createHistoricProcessInstanceQuery().list();
		for(HistoricProcessInstance hpi:list){
			System.out.print("��ʷ����ʵ��id:"+hpi.getId());
			System.out.print("��ʷ���̶���id:"+hpi.getProcessDefinitionId());
			System.out.println("��ʷ����ʵ����ʼʱ�䣺"+hpi.getStartTime()+"--->"+"��ʷ���̽���ʱ�䣺"+hpi.getEndTime());
		}
	}
	@Test
	public void queryHistoryTask(){
		String processInstanceId = "20001";
		List<HistoricTaskInstance> list = pe.getHistoryService().createHistoricTaskInstanceQuery().processInstanceId(processInstanceId).list();
		for(HistoricTaskInstance hti:list){
			System.out.print("��ʷ����ʵ������id��"+hti.getId());
			System.out.print("��ʷ����ʵ����������̶���id��"+hti.getProcessDefinitionId());
			System.out.print("��ʷ����ʵ�����������ʵ��id��"+hti.getProcessInstanceId());
			System.out.print("��ʷ����ʵ����������"+hti.getName());
			System.out.println("��ʷ����ʵ����������ˣ�"+hti.getAssignee());
			
		}
	}
	
}

