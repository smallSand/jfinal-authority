package com.jayqqaa12;

import javax.sql.DataSource;

import com.jayqqaa12.jbase.jfinal.auto.JbaseGenerator;
import com.jayqqaa12.jbase.jfinal.auto.JbaseMappingKitGenerator;
import com.jayqqaa12.jbase.jfinal.auto.JbaseServiceGenerator;
import com.jfinal.ext.plugin.config.ConfigKit;
import com.jfinal.ext.plugin.config.ConfigPlugin;
import com.jfinal.kit.PathKit;
import com.jfinal.plugin.druid.DruidPlugin;

/**
 * 
 * 数据库改变的时候用这个 生成新的 model
 * 
 * 
 * 数据库名称要符合约定
 * 
 */
public class GeneratorModel {

	public static DataSource getDataSource() {

		new ConfigPlugin(".*.txt").reload(false).start();
		DruidPlugin dbPlugin = new DruidPlugin(ConfigKit.getStr("jdbcUrl"), ConfigKit.getStr("user"),
				ConfigKit.getStr("password"));

		dbPlugin.start();
		return dbPlugin.getDataSource();
	}
	public static void main(String[] args) {
		// base model 所使用的包名
		String baseModelPackageName = "com.jayqqaa12.auto.base";
		// base model 文件保存路径
		String baseModelOutputDir = PathKit.getWebRootPath() + "/src/main/auto/com/jayqqaa12/auto/base";

		// model 所使用的包名 (MappingKit 默认使用的包名)
		String modelPackageName = "com.jayqqaa12.model";
		
		String servicePackageName = "com.jayqqaa12.auto.service";
		// model 文件保存路径 (MappingKit 与 DataDictionary 文件默认保存路径)
		String modelOutputDir = PathKit.getWebRootPath() + "/src/main/java/com/jayqqaa12/model";

		
		String serviceOutputDir = PathKit.getWebRootPath() + "/src/main/auto/com/jayqqaa12/auto/service";

		// baseModelPackageName, baseModelOutputDir,
		// modelPackageName,modelOutputDir

		// 创建生成器
		JbaseGenerator gernerator = new JbaseGenerator(getDataSource(), baseModelPackageName, baseModelOutputDir,
				modelPackageName, modelOutputDir);

		// 添加不需要生成的表名
		// 设置是否在 Model 中生成 dao 对象
		gernerator.setGenerateDaoInModel(true);
		// 设置是否生成字典文件
		gernerator.setGenerateDataDictionary(true);
		
		gernerator.setMappingKitGenerator(new JbaseMappingKitGenerator(modelPackageName, modelOutputDir));
//		gernerator.setModelGenerate(null); 
		
		// 设置需要被移除的表名前缀用于生成modelName。例如表名 "osc_user"，移除前缀 "osc_"后生成的model名为
		// "User"而非 OscUser
		gernerator.setRemovedTableNamePrefixes( "system_");
		
		
//		gernerator.setServiceGenerate(new JbaseServiceGenerator(servicePackageName, modelPackageName, serviceOutputDir));
		
		// 生成
		gernerator.generate();
	}
}
