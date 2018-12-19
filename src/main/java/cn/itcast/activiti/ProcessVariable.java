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
		.category("�칫����")
		.name("֧������")
		.addClasspathResource("diagrams/appayProcess.bpmn")
		.addClasspathResource("diagrams/appayProcess.png")
		.deploy();
		System.out.println("����ɹ���"+deploy);
	}
	@Test
	public void startProcess(){
		String bpmnId = "appayBill";
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
		String taskId = "40002";
		pe.getTaskService().complete(taskId);
		System.out.println("��ǰ����ִ�����!");
	}
	@Test
	public void setVariable(){
		String taskId = "47504";
		TaskService ts = pe.getTaskService();
		AppayBillBean appayBillBean = new AppayBillBean();
		appayBillBean.setId(1);
		appayBillBean.setCost(7000);
		appayBillBean.setAppayPerson("����");
		appayBillBean.setDate(new Date());
		ts.setVariable(taskId, "appayBillBean", appayBillBean);
//		ts.setVariable(taskId, "cost", 5000);
//		ts.setVariable(taskId, "����ʱ��", new Date());
//		ts.setVariableLocal(taskId, "������", "��ĳĳ");
//		ts.setVariableLocal(taskId, "������_", "��ĳĳ");
		System.out.println("���óɹ�!");
	}
	@Test
	public void getVariable(){
		/*String taskId = "47504";
		TaskService ts = pe.getTaskService();
		Integer cost = (Integer) ts.getVariable(taskId, "cost");
		Date date = (Date) ts.getVariable(taskId, "����ʱ��");
//		Date date = (Date) ts.getVariableLocal(taskId, "����ʱ��");
		String personStr = (String) ts.getVariableLocal(taskId, "������");
//		String personStr_ = (String) ts.getVariableLocal(taskId, "������_");
//		String personStr_ = (String) ts.getVariable(taskId, "������_");
		System.out.println("��"+cost);
		System.out.println("����ʱ�䣺"+date);
		System.out.println("�����ˣ�"+personStr);
//		System.out.println("������_:"+personStr_);
*/		
		String taskId = "47504";
		TaskService ts = pe.getTaskService();
		AppayBillBean appayBillBean = (AppayBillBean) ts.getVariable(taskId, "appayBillBean");
		System.out.println("��"+appayBillBean.getCost());
	}
	@Test
	public void getAndSetProcessVariable(){
		TaskService ts = pe.getTaskService();
		RuntimeService rs = pe.getRuntimeService();
//		1.ͨ�^runtimeService �O������׃��
//		executionId��ִ�ж���id
//		variable����������
//		values:׃��ֵ
//		rs.setVariable(executionId, variableName, values);
//		rs.setVariableLocal(executionId, variableName, values);//�O�ñ����Ќ����׃����ԓ׃����������ֻ�ڮ�ǰ��execution����
//		rs.setVariables(executionId, variables);//map<String,Object>
		
//		2.ͨ�^taskService �O������׃��
//		taskId��ִ�ж���id
//		variable����������
//		values:׃��ֵ
//		ts.setVariable(taskId, variableName, values);
//		ts.setVariableLocal(taskId, variableName, values);//�O�ñ����Ќ����׃����ԓ׃����������ֻ�ڮ�ǰ��execution����
//		ts.setVariables(taskId, variables);//map<String,Object>
		
//		3.�����̿�ʼ��ʱ��
//		rs.startProcessInstanceByKey(processDefiKey, variables)
		
//		4.��ִ�������ʱ��
//		ts.complete(taskId, variables);
		
//		5.ͨ��runtimeserviceȡ����ֵ
//		rs.getVariable(executionId, variableName)ȡĳ��ִ�ж���ı���
//		rs.getVariableLocal(executionId, variableName)ȡ��ִ�ж���ı���
//		rs.getVariables(executionId)ȡ����ֵ
		
//		6.ͨ��taskserviceȡ����ֵ
//		rs.getVariable(taskId, variableName)ȡĳ��ִ�ж���ı���
//		rs.getVariableLocal(taskId, variableName)ȡ��ִ�ж���ı���
//		rs.getVariables(taskId)ȡ����ֵ

	}
}
