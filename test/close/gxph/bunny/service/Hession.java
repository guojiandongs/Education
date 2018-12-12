package close.gxph.bunny.service;

import java.net.MalformedURLException;
import java.sql.Timestamp;
import java.util.Date;

import com.caucho.hessian.client.HessianProxyFactory;

import close.gxph.bunny.entity.AdPic;
import close.gxph.bunny.repository.AdPicDao;
import close.gxph.bunny.repository.AdPicHessianDao;

/** 
* 客户端调用（会依赖服务接口） 
* 
* @author leizhimin 2009-8-14 12:29:33 
*/ 
public class Hession { 
        public static void main(String[] args) throws MalformedURLException { 
                String url = "http://localhost:8080/SxLyHessian/wxweb/service/adService"; 
                HessianProxyFactory factory = new HessianProxyFactory(); 
                AdPicHessianDao hello = (AdPicHessianDao) factory.create(AdPicHessianDao.class, url); 
                AdPic ad = new AdPic();
                factory.setOverloadEnabled(true);
                ad.setId("121313fergrg31231lkk3n12");
                ad.setName("个人哥啊");
                ad.setMemo("个");
                ad.setSeq(4);
                ad.setUrl("/frefer");
                Integer seq = 2;
                ad.setRecordtime(new Timestamp(new Date().getTime()));
                System.out.println(hello.addAdPic(ad));
        } 
}
