package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.model.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

	private AuthorRepository authorRepository;
	private BookRepository bookRepository;
	private PublisherRepository publisherRepository;

	public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
		this.authorRepository = authorRepository;
		this.bookRepository = bookRepository;
		this.publisherRepository = publisherRepository;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
		initData();
	}

	private void initData() {

		Publisher publisher1 = new Publisher("Jon Snow", "Winterfell");
		Publisher publisher2 = new Publisher("Arya Stark", "Winterfell");

		publisherRepository.save(publisher1);
		publisherRepository.save(publisher2);

		// Jeriel
		Author jeriel = new Author("Jeriel", "Gallofin");
		Book ddd = new Book("Domain Driven Design", "1234", publisher1);
		jeriel.getBooks().add(ddd);
		ddd.getAuthors().add(jeriel);

		authorRepository.save(jeriel);
		bookRepository.save(ddd);

		Author abie = new Author("Abie", "Alcudia");
		Book noEJP = new Book("J2EE Development without EJB", "2344", publisher2);
		abie.getBooks().add(noEJP);

		authorRepository.save(abie);
		bookRepository.save(noEJP);

	}

}
