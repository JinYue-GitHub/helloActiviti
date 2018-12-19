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
		System.out.println("流程对象id(执行对象id)："+pi.getId());
		System.out.println("流程实例id："+pi.getProcessInstanceId());
		System.out.println("流程定义id："+pi.getProcessDefinitionId());
		System.out.println("流程对象名："+pi.getName());
	}
	
	@Test
	public void queryTask(){
		TaskService ts = pe.getTaskService();
		TaskQuery tq = ts.createTaskQuery();
		List<Task> list = tq.list();
		for(Task task:list){
			System.out.println("任务id:"+task.getId());
			System.out.println("任务办理人："+task.getAssignee());
			System.out.println("任务名："+task.getName());
		}
	}
	@Test
	public void compileTask(){
		String taskId = "25002"; 
		TaskService ts = pe.getTaskService();
		ts.complete(taskId);
		System.out.println("任务执行完毕!");
	}
	@Test
	public void getProcessInstanceState(){
		String processInstanceId = "20001";
		ProcessInstance pi = pe.getRuntimeService().createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
		if(Optional.ofNullable(pi).isPresent()){
			System.out.println("流程实例"+pi+"正在运行..."+"当前运行的任务是："+pi.getActivityId());
		}else{
			System.out.println("流程实例"+pi+"结束");
		}
	}
	@Test
	public void queryHistoryProcinst(){
		List<HistoricProcessInstance> list = pe.getHistoryService().createHistoricProcessInstanceQuery().list();
		for(HistoricProcessInstance hpi:list){
			System.out.print("历史流程实例id:"+hpi.getId());
			System.out.print("历史流程定义id:"+hpi.getProcessDefinitionId());
			System.out.println("历史流程实例开始时间："+hpi.getStartTime()+"--->"+"历史流程结束时间："+hpi.getEndTime());
		}
	}
	@Test
	public void queryHistoryTask(){
		String processInstanceId = "20001";
		List<HistoricTaskInstance> list = pe.getHistoryService().createHistoricTaskInstanceQuery().processInstanceId(processInstanceId).list();
		for(HistoricTaskInstance hti:list){
			System.out.print("历史流程实例任务id："+hti.getId());
			System.out.print("历史流程实例任务的流程定义id："+hti.getProcessDefinitionId());
			System.out.print("历史流程实例任务的流程实例id："+hti.getProcessInstanceId());
			System.out.print("历史流程实例任务名："+hti.getName());
			System.out.println("历史流程实例任务办理人："+hti.getAssignee());
			
		}
	}
	
}

