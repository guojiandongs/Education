package close.gxph.core.common.security;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import close.gxph.bunny.entity.User;
import close.gxph.bunny.entity.Usinesses;
import close.gxph.bunny.repository.UserDao;
import close.gxph.bunny.repository.UsinessesDao;
import close.gxph.core.common.util.StringX;

public class ShiroDbRealm extends AuthorizingRealm {
	
	@Autowired
	private UserDao userdao;
	@Autowired
	private UsinessesDao usinessesDao;

	public ShiroDbRealm() {
		super();
		this.setAuthenticationTokenClass(UsernamePasswordToken.class);
		this.setCredentialsMatcher(new CredentialsMatcher() {
			public boolean doCredentialsMatch(AuthenticationToken token,
					AuthenticationInfo info) {
				UsernamePasswordToken upToken = (UsernamePasswordToken) token;
				String pwd = new String(upToken.getPassword());
				if (StringX.equals((String) info.getCredentials(),
						StringX.md5(pwd.getBytes()).toLowerCase())) {
					return true;
				}
				return false;
			}
		});
	}

	/**
	 * 认证回调函数
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authcToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		User user = userdao.findByCode(token.getUsername());
		if (user != null) {
			return new SimpleAuthenticationInfo(user.getCode(), user.getPwd(),
					getName());
		} else {
			Usinesses us = usinessesDao.findByUsername(token.getUsername());
			if(us != null){
				return new SimpleAuthenticationInfo(us.getUsername(), us.getUpassword(),
						getName());
			}else{
				return null;
			}
		}
	}

	/**
	 * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		String usercode = (String) principals.fromRealm(getName()).iterator()
				.next();
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		/*List<String> roles = getUserRoles(usercode);
		List<String> auths = getUserAuths(usercode);
		info.addStringPermissions(auths);
		info.addRoles(roles);*/
		return info;
	}

	/**
	 * 获取用户角色
	 */
//	public List<String> getUserRoles(String usercode) {
//		User user = userdao.findByCode(usercode);
//		Post post = user.getPost();
//		List<String> list = new ArrayList<String>();
//		for (Role role : post.getRoles()) {
//			list.add(role.getCode());
//		}
//		return list;
//	}

	/**
	 * 获取用户权限
	 */
//	public List<String> getUserAuths(String usercode) {
//		User user = userdao.findByCode(usercode);
//		Post post = user.getPost();
//		List<String> list = new ArrayList<String>();
//		for (Role role : post.getRoles()) {
//			for (Auth auth : role.getAuths()) {
//				list.add(auth.getCode());
//			}
//		}
//		return list;
//	}

}
