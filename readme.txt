1. �� Maven ģ�崴��һ����Ŀ:
   ʹ�����E:\tmp>mvn archetype:generate -DgroupId=com.thg -DartifactId=maven4java -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false������һ����Ŀ
   
2. ����eclipse��Ŀ��������
 �л������ɵĹ���Ŀ¼�£�ʹ�����E:\tmp\maven4java>mvn eclipse:eclipse��
 
3.�޸�pom.xml�ļ�����ӱ���������汾���޸�junit�汾

4.���ҵ���߼�

5.maven�����Ŀjar��
  �л�������Ŀ¼�£�ʹ�����E:\tmp\maven4java>mvn package�����д��
  
6.����ʾ��
 ʹ�����E:\tmp\maven4java>java -cp target/maven4java-1.0-SNAPSHOT.jar com.thg.App������