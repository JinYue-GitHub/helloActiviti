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
				.category("办公分类")
				.name("购买流程")
				.addClasspathResource("diagrams/buyProcess.bpmn")
				.addClasspathResource("diagrams/buyProcess.png")
				.deploy();
		System.out.println("部署id"+deploy.getId());
		System.out.println("部署名"+deploy.getName());
	}
	@Test
	public void deployProcessDefiByZip() {
		InputStream in = getClass().getClassLoader().getResourceAsStream("buyProcess.zip");
		RepositoryService rs = pe.getRepositoryService();
		Deployment deploy = rs.createDeployment()
				.category("办公分类")
				.name("购买流程")
				.addZipInputStream(new ZipInputStream(in))
				.deploy();
		System.out.println("部署id"+deploy.getId());
		System.out.println("部署名"+deploy.getName());
	}
	@Test
	public void queryProcessDetinition() {

		RepositoryService rs = pe.getRepositoryService();
		List<ProcessDefinition> list = rs.createProcessDefinitionQuery()
//		.processDefinitionId(id)			流程定义id
//		.processDefinitionKey(key)			流程定义key
//		.processDefinitionName(name)		流程定义名称
//		.processDefinitionVersion(version)	流程定义版本
		.latestVersion()					//流程定义最新版本
		.orderByProcessDefinitionVersion()
		.desc()
		.list();
		
		for(ProcessDefinition pd:list){
			System.out.println("流程定义id"+pd.getId());
			System.out.println("流程定义key"+pd.getKey());
			System.out.println("流程定义name"+pd.getName());
			System.out.println("流程定义版本"+pd.getVersion());
			System.out.println("流程定义部署的id"+pd.getDeploymentId());
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
		
		//获取资源
		InputStream in = pe.getRepositoryService().getResourceAsStream(deployId, imageName);
		
		File file = new File("d:/"+imageName);
		
		try {
			FileUtils.copyInputStreamToFile(in, file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("图片下载成功!");
		
	}
	@Test
	public void deleteProcessDefinition(){
		String deployId = "2501";
		RepositoryService rs = pe.getRepositoryService();
		rs.deleteDeployment(deployId);
		System.out.println("根据部署id删除流程定义成功!");
	}
	
}
