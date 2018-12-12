package close.gxph.bunny.service;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;


//@ContextConfiguration(locations = {"/applicationContext.xml"})
public class ScheduledTaskManagerTest extends AbstractJUnit4SpringContextTests {
	
	/*@Autowired
	private OrderReturnService orderReturnService;*/

	@Test
	public void findlist() throws Exception {
//		Map<String, Object> searchParams=new TreeMap<String, Object>();
//		Page<Ticket> lists = ticketService.getTicket(searchParams,
//				1, 10,null, null);
//		System.out.println(lists.getContent().size());
//		SpecialCommend specialCommend = specialCommendService.getSpecialCommendByState("1");
//		String url = fileService.getImageView(specialCommend.getScenery().getPicurl(), "150", "200");
//		specialCommend.setPicurl(url);
//		System.out.println(specialCommend.getTitle()+","+specialCommend.getPicurl());
//		List<Guest> guests = guestServie.findAll();
//		for(int i = 0;i<guests.size();i++){
//			System.out.println(guests.get(i).getName()+"***************");
//		}
//		List<User> users = userService.listAll();
//		for(User user : users){
//			System.out.println(user.getName()+"$$$$$$$$$$$$$");
//		}
		
	}
	
//	@Test
//	public void delfile() throws Exception {
//		sceneryService.delfilebyrid("402881034f062cc8014f0636d5ca0000");
//	}
	
	public static void main(String args[]){
		//System.out.println(DateUtil.getCurrCodeDateStr()+(int)(Math.random() * 900000000));
		System.out.println((int)(Math.random() * 900000)+100000);
	}
}
