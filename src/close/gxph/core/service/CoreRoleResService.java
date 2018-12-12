package close.gxph.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import close.gxph.core.entity.CoreRes;
import close.gxph.core.entity.CoreRoleRes;
import close.gxph.core.repository.CoreResDao;
import close.gxph.core.repository.CoreRoleResDao;
import close.gxph.core.repository.CoreRoleResHessianDao;

/***
 * 角色权限管理
 * @author g
 *
 */
@Service
@Transactional
public class CoreRoleResService implements CoreRoleResHessianDao{
	@Autowired
	private CoreRoleResDao coreRoleResDao;
	@Autowired
	private CoreResDao coreResDao;
	
	/**查询 角色拥有的资源*/
	public List<CoreRes> list(String roleid){
		List<CoreRoleRes> alist=coreRoleResDao.findAllByRoleid(roleid);
		List<CoreRes> blist=coreResDao.findAll();
		for(CoreRes coreRes:blist){
			for(CoreRoleRes coreRoleRes:alist){
				if(coreRes.getId().equals(coreRoleRes.getResourceid())){
					coreRes.setChecked(true);
				}
			}
		}
		return blist;
	}
	/**为角色分配资源*/
	public void save(String roleid,String resourceids){
		try {
			coreRoleResDao.deleteByRoleid(roleid);
			if(null==resourceids){
				return;
			}
			String[] resources=resourceids.split(",");
			for(int i=0;i<resources.length;i++){
				CoreRoleRes crr=new CoreRoleRes();
				crr.setRoleid(roleid);
				crr.setResourceid(resources[i]);
				coreRoleResDao.saveAndFlush(crr);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
