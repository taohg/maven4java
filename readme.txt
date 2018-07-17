1. 从 Maven 模板创建一个项目:
   使用命令【E:\tmp>mvn archetype:generate -DgroupId=com.thg -DartifactId=maven4java -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false】创建一个项目
   
2. 生成eclipse项目所需配置
 切换到生成的工程目录下，使用命令【E:\tmp\maven4java>mvn eclipse:eclipse】
 
3.修改pom.xml文件：添加编译器插件版本；修改junit版本

4.添加业务逻辑

5.maven打包项目jar包
  切换到工程目录下，使用命令【E:\tmp\maven4java>mvn package】进行打包
  
6.运行示例
 使用命令【E:\tmp\maven4java>java -cp target/maven4java-1.0-SNAPSHOT.jar com.thg.App】运行