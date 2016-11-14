package security;

import domain.Actor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import services.ActorService;
import services.UserService;

@Service
@Transactional
public class UserAccountService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private UserAccountRepository userAccountRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private ActorService actorService;
	
	// Supporting services ----------------------------------------------------
		
	// Constructors -----------------------------------------------------------

	public UserAccountService() {
		super();
	}
	
	// Simple CRUD methods ----------------------------------------------------
	
	public UserAccount findByActor(Actor actor) {
		Assert.notNull(actor);
		
		UserAccount result;
		
		result = userAccountRepository.findByActorId(actor.getId());		
		
		return result;
	}

	public void assertRole(String role) {
		Actor actor = actorService.findByPrincipal();
		String[] roles = role.split(",");
		for (String e : roles) {
			Authority authority = new Authority();
			authority.setAuthority(e);
			Assert.isTrue(findByActor(actor).getAuthorities().contains(authority));
		}
	}
	
	// Other business methods -------------------------------------------------

}