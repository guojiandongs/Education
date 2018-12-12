package close.gxph.bunny.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CommonDaoImplJPA {
	private static Logger logger = LoggerFactory
			.getLogger(CommonDaoImplJPA.class);
	
    private EntityManagerFactory entityManagerFactory;

    @Resource(name="entityManagerFactory")
    public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }
    
    @SuppressWarnings("unchecked")
	public List<Map<String, Object>> execCommonSQL(String sql,Object ... objs){
    	List<Map<String, Object>> rows =null;
    	try {
			EntityManager em = entityManagerFactory.createEntityManager();
			Query query = em.createNativeQuery(sql);
			
			if(null!=objs&&objs.length>0){
				for (int i = 0; i < objs.length; i++) {
					query.setParameter(i+1,objs[i]);
				}
			}
			query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP); 
			List<Map<String, Object>> rslist = query.getResultList();
			if(null!=rslist&&rslist.size()>0){
				rows=new ArrayList<Map<String,Object>>();
				for (int i = 0; i < rslist.size(); i++) {
					Map<String,Object> map=new HashMap<String, Object>();
					Map<String, Object> rsmap=rslist.get(i);
					Iterator<Map.Entry<String, Object>> it = rsmap.entrySet().iterator();
					while (it.hasNext()) {
						Map.Entry<String, Object> entry = it.next();
						map.put(entry.getKey().toLowerCase(), entry.getValue());
					}
					rows.add(map);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("error", e);
		}
    	return rows;
    }
    
    public void executeSql(String sql){
    	try {
    		EntityManager em = entityManagerFactory.createEntityManager();
    		em.getTransaction().begin();
    		Query query = em.createNativeQuery(sql);
    		query.executeUpdate();
    		em.flush();
    		em.clear();
    		em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
