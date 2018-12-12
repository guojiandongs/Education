package close.gxph.bunny.common;

import java.util.Random;

public class ValidFactory {

	//生成一个取票验证码
	public static String get(){
		Random random=new Random();
		int r=random.nextInt(10000000);
		while(r<1000000){
			r=random.nextInt(10000000);
		}
		return r+"";
	}
}
