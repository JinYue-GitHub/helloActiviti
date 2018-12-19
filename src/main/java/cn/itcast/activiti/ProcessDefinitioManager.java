package cn.itcast.activiti;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.zip.ZipInputStream;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
public class ProcessDefinitioManager {
	private ProcessEngine pe = ProcessEngines.getDefaultProcessEngine();
	@Test
	public void deployProcessDefi() {
		RepositoryService rs = pe.getRepositoryService();
		Deployment deploy = rs.createDeployment()
				.category("�칫����")
				.name("��������")
				.addClasspathResource("diagrams/buyProcess.bpmn")
				.addClasspathResource("diagrams/buyProcess.png")
				.deploy();
		System.out.println("����id"+deploy.getId());
		System.out.println("������"+deploy.getName());
	}
	@Test
	public void deployProcessDefiByZip() {
		InputStream in = getClass().getClassLoader().getResourceAsStream("buyProcess.zip");
		RepositoryService rs = pe.getRepositoryService();
		Deployment deploy = rs.createDeployment()
				.category("�칫����")
				.name("��������")
				.addZipInputStream(new ZipInputStream(in))
				.deploy();
		System.out.println("����id"+deploy.getId());
		System.out.println("������"+deploy.getName());
	}
	@Test
	public void queryProcessDetinition() {

		RepositoryService rs = pe.getRepositoryService();
		List<ProcessDefinition> list = rs.createProcessDefinitionQuery()
//		.processDefinitionId(id)			���̶���id
//		.processDefinitionKey(key)			���̶���key
//		.processDefinitionName(name)		���̶�������
//		.processDefinitionVersion(version)	���̶���汾
		.latestVersion()					//���̶������°汾
		.orderByProcessDefinitionVersion()
		.desc()
		.list();
		
		for(ProcessDefinition pd:list){
			System.out.println("���̶���id"+pd.getId());
			System.out.println("���̶���key"+pd.getKey());
			System.out.println("���̶���name"+pd.getName());
			System.out.println("���̶���汾"+pd.getVersion());
			System.out.println("���̶��岿���id"+pd.getDeploymentId());
		}

	}
	@Test
	public void viewImage(){
		String deployId = "15001";
		String imageName = null;
		List<String> list = pe.getRepositoryService().getDeploymentResourceNames(deployId);
		for(String name:list){
			if(name.indexOf(".png")>0){
				imageName = name;
			}
		}
		
		//��ȡ��Դ
		InputStream in = pe.getRepositoryService().getResourceAsStream(deployId, imageName);
		
		File file = new File("d:/"+imageName);
		
		try {
			FileUtils.copyInputStreamToFile(in, file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("ͼƬ���سɹ�!");
		
	}
	@Test
	public void deleteProcessDefinition(){
		String deployId = "2501";
		RepositoryService rs = pe.getRepositoryService();
		rs.deleteDeployment(deployId);
		System.out.println("���ݲ���idɾ�����̶���ɹ�!");
	}
	
}
