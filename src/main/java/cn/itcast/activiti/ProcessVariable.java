package cn.itcast.activiti;

import java.util.Date;
import java.util.List;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

public class ProcessVariable {

	ProcessEngine pe = ProcessEngines.getDefaultProcessEngine();
	@Test
	public void deploy(){
		Deployment deploy = pe.getRepositoryService()
		.createDeployment()
		.category("办公分类")
		.name("支付流程")
		.addClasspathResource("diagrams/appayProcess.bpmn")
		.addClasspathResource("diagrams/appayProcess.png")
		.deploy();
		System.out.println("部署成功："+deploy);
	}
	@Test
	public void startProcess(){
		String bpmnId = "appayBill";
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
		String taskId = "40002";
		pe.getTaskService().complete(taskId);
		System.out.println("当前任务执行完成!");
	}
	@Test
	public void setVariable(){
		String taskId = "47504";
		TaskService ts = pe.getTaskService();
		AppayBillBean appayBillBean = new AppayBillBean();
		appayBillBean.setId(1);
		appayBillBean.setCost(7000);
		appayBillBean.setAppayPerson("王五");
		appayBillBean.setDate(new Date());
		ts.setVariable(taskId, "appayBillBean", appayBillBean);
//		ts.setVariable(taskId, "cost", 5000);
//		ts.setVariable(taskId, "申请时间", new Date());
//		ts.setVariableLocal(taskId, "申请人", "李某某");
//		ts.setVariableLocal(taskId, "申请人_", "何某某");
		System.out.println("设置成功!");
	}
	@Test
	public void getVariable(){
		/*String taskId = "47504";
		TaskService ts = pe.getTaskService();
		Integer cost = (Integer) ts.getVariable(taskId, "cost");
		Date date = (Date) ts.getVariable(taskId, "申请时间");
//		Date date = (Date) ts.getVariableLocal(taskId, "申请时间");
		String personStr = (String) ts.getVariableLocal(taskId, "申请人");
//		String personStr_ = (String) ts.getVariableLocal(taskId, "申请人_");
//		String personStr_ = (String) ts.getVariable(taskId, "申请人_");
		System.out.println("金额："+cost);
		System.out.println("申请时间："+date);
		System.out.println("申请人："+personStr);
//		System.out.println("申请人_:"+personStr_);
*/		
		String taskId = "47504";
		TaskService ts = pe.getTaskService();
		AppayBillBean appayBillBean = (AppayBillBean) ts.getVariable(taskId, "appayBillBean");
		System.out.println("金额："+appayBillBean.getCost());
	}
	@Test
	public void getAndSetProcessVariable(){
		TaskService ts = pe.getTaskService();
		RuntimeService rs = pe.getRuntimeService();
//		1.通過runtimeService 設置流程變量
//		executionId：执行对象id
//		variable：变量名‘
//		values:變量值
//		rs.setVariable(executionId, variableName, values);
//		rs.setVariableLocal(executionId, variableName, values);//設置本執行對象的變量，該變量的作用域只在當前的execution對象
//		rs.setVariables(executionId, variables);//map<String,Object>
		
//		2.通過taskService 設置流程變量
//		taskId：执行对象id
//		variable：变量名‘
//		values:變量值
//		ts.setVariable(taskId, variableName, values);
//		ts.setVariableLocal(taskId, variableName, values);//設置本執行對象的變量，該變量的作用域只在當前的execution對象
//		ts.setVariables(taskId, variables);//map<String,Object>
		
//		3.当流程开始的时候
//		rs.startProcessInstanceByKey(processDefiKey, variables)
		
//		4.当执行任务的时候
//		ts.complete(taskId, variables);
		
//		5.通过runtimeservice取变量值
//		rs.getVariable(executionId, variableName)取某个执行对象的变量
//		rs.getVariableLocal(executionId, variableName)取本执行对象的变量
//		rs.getVariables(executionId)取变量值
		
//		6.通过taskservice取变量值
//		rs.getVariable(taskId, variableName)取某个执行对象的变量
//		rs.getVariableLocal(taskId, variableName)取本执行对象的变量
//		rs.getVariables(taskId)取变量值

	}
}
