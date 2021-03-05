package fivePoints.spring.GestionDeStock;

import fivePoints.spring.GestionDeStock.models.ERole;
import fivePoints.spring.GestionDeStock.models.Role;
import fivePoints.spring.GestionDeStock.models.User;
import fivePoints.spring.GestionDeStock.repositories.RoleRepository;
import fivePoints.spring.GestionDeStock.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
@EnableSwagger2
public class GestionDeStockApplication implements ApplicationRunner {
	@Autowired
	private ApplicationContext applicationContext;
	@Autowired
	UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;


	public static void main(String[] args) {
		SpringApplication.run(GestionDeStockApplication.class, args);
	}

	// this bean used to crypt the password
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder passwordEncoderBean = applicationContext.getBean(BCryptPasswordEncoder.class);
		return passwordEncoderBean;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		// Clean up database tables
//		this.roleRepository.deleteAllInBatch();
//		this.userRepository.deleteAllInBatch();
//		this.userDetailsRepository.deleteAllInBatch();
//		this.postRepository.deleteAllInBatch();
//		this.tagRepository.deleteAllInBatch();
//		this.commentRepository.deleteAllInBatch();

		// Save roles
		Role superAdminRole = this.roleRepository.save(new Role(ERole.SUPER_ADMIN));
		Role adminRole = this.roleRepository.save(new Role(ERole.ADMIN));
		Role userRole = this.roleRepository.save(new Role(ERole.USER));
		Role guestRole = this.roleRepository.save(new Role(ERole.GUEST));



		// Save users
		User user1 = new User("marwen", "sghair",78965412,"korba",
				"marwen@gmail.com",
				this.passwordEncoder().encode("123456789"),18);



		// ManyToMany Relations
		Set<Role> roles = new HashSet<>();
		roles.add(adminRole);;
		user1.setRoles(roles);
		this.userRepository.save(user1);


	}

}
