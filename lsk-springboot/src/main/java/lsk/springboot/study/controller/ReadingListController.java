package lsk.springboot.study.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lsk.springboot.study.domain.Book;
import lsk.springboot.study.domain.Reader;
import lsk.springboot.study.domain.ReadingListRepository;

@Controller
@RequestMapping("/")
public class ReadingListController {

//	private static final String reader="craig";
	
	private ReadingListRepository readingListRepository;
	
	@Autowired
	public ReadingListController(ReadingListRepository readingListRepository) {
		this.readingListRepository = readingListRepository;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String readersBooks(Reader reader, Model model) {
		
		List<Book> readingList = readingListRepository.findByReader(reader);
		if(readingList != null) {
			model.addAttribute("books",readingList);
		}
		
		return "readingList";
	}
	@RequestMapping(method=RequestMethod.POST)
	public String addToReadingList(Reader reader, Book book) {
		book.setReader(reader);
		readingListRepository.save(book);
		return "redirect:/";
	}
}
