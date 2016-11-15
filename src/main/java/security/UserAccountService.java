package security;

import domain.Actor;
import domain.Administrator;
import domain.Nutritionist;
import domain.Sponsor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import services.*;

@Service
@Transactional
public class UserAccountService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private UserAccountRepository userAccountRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private AdministratorService administratorService;

	@Autowired
	private CookService cookService;

	@Autowired
	private SponsorService sponsorService;


	@Autowired
	private NutritionistService nutritionistService;





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
		Actor actor = userService.findByPrincipal();
		String[] roles = role.split(",");
		for (String e : roles) {
			switch(e){
				case "USER":
					Assert.notNull(userService.findByPrincipal());
					break;
				case "COOK":
					Assert.notNull(cookService.findByPrincipal());
					break;
				case "NUTRITIONIST":
					Assert.notNull(nutritionistService.findByPrincipal());
					break;
				case "SPONSOR":
					Assert.notNull(sponsorService.findByPrincipal());
					break;
				case "ADMINISTRATOR":
					Assert.notNull(administratorService.findByPrincipal());
					break;
			}
		}
	}
	
	// Other business methods -------------------------------------------------

}