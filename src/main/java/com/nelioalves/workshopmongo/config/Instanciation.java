package com.nelioalves.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.CommandLineRunner;

import com.nelioalves.workshopmongo.domain.Post;
import com.nelioalves.workshopmongo.domain.User;
import com.nelioalves.workshopmongo.dto.AuthorDTO;
import com.nelioalves.workshopmongo.dto.CommentDTO;
import com.nelioalves.workshopmongo.repository.PostRepository;
import com.nelioalves.workshopmongo.repository.UserRepository;


@Configurable
public class Instanciation implements CommandLineRunner {

	@Autowired
	private UserRepository userReposiroty;
	
	@Autowired
	private PostRepository postReposiroty;
	
	
	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy"); 
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userReposiroty.deleteAll();
		postReposiroty.deleteAll();
		
		
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		userReposiroty.saveAll( Arrays.asList(maria, alex, bob) );
		
		Post post1 = new Post(null, sdf.parse("21/03/2018"), "Partiu Viagem", "Vou Viajar para Sao Paulo!! Abraços", new AuthorDTO(maria) );
		Post post2 = new Post(null, sdf.parse("23/03/2018"), "Bom Dia", "Acordei Feliz Hoje!!", new AuthorDTO(maria) );
	
		CommentDTO c1 = new CommentDTO("Boa Viagem Mano!!", sdf.parse("21/03/2018"), new AuthorDTO(alex) );
		CommentDTO c2 = new CommentDTO("Aproveite meu amigoo!!", sdf.parse("22/03/2018"), new AuthorDTO(bob) );
		CommentDTO c3 = new CommentDTO("Tenha Um Bom Dia !!", sdf.parse("23/03/2018"), new AuthorDTO(alex) );
		
		post1.getComments().addAll(Arrays.asList(c1,c2));
		post1.getComments().addAll(Arrays.asList(c3));
		
		
		postReposiroty.saveAll( Arrays.asList(post1, post2) );
		
		maria.getPosts().addAll(Arrays.asList(post1, post2));
		userReposiroty.save(maria);
	}

}
