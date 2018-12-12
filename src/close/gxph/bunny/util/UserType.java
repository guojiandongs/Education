package close.gxph.bunny.util;

import java.sql.Timestamp;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;

import close.gxph.bunny.entity.LoginUser;
import close.gxph.bunny.entity.User;
import close.gxph.bunny.entity.Usinesses;
import close.gxph.core.constant.Contants;

/**
 * 登陆用户类型
 * @author Administrator
 *
 */
public class UserType {
	public LoginUser getUserType(){
		LoginUser lu = new LoginUser();
		Session session = SecurityUtils.getSubject().getSession();
		User currenter = (User) session.getAttribute(Contants.CURRENT_USER);
		String recorduserid="";
		String recordusername = "";
		if(currenter!=null){
			recorduserid = currenter.getId();
			recordusername = currenter.getName();
		}else{
			Usinesses us = (Usinesses) session.getAttribute(Contants.CURRENT_US);
			recorduserid = us.getId();
			recordusername = us.getName();
		}
		lu.setRecordtime(new Timestamp(System.currentTimeMillis()));
		lu.setRecorduserid(recorduserid);
		lu.setRecordusername(recordusername);
		return lu;
	}
}
