package mavenTest;

import java.io.File;
import java.util.Date;

import org.apache.commons.io.FileUtils;

import com.thg.utils.FileUtil;

public class DiskScan {

	public static void main(String[] args) {
//		System.out.println("begin Time:"+new Date());
		//扫描硬盘文件夹空间大小，可以指定目录层级
        FileUtil.listDirectorySize("E:\\", 50, 10);
//        System.out.println("end Time:"+new Date());
//		System.out.println(FileUtils.sizeOfDirectory(new File("E:\\dbpool")));
	}

}
