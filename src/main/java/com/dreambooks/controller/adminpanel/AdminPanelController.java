package com.dreambooks.controller.adminpanel;

import com.dreambooks.model.Book;
import com.dreambooks.model.Category;
import com.dreambooks.model.User;
import com.dreambooks.repository.UserRepository;
import com.dreambooks.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.validation.Valid;

@Controller
public class AdminPanelController {

    private BookService bookService;
    private AuthorService authorService;
    private PublisherService publisherService;
    private CategoryService categoryService;
    private UserRepository userRepository;

    public AdminPanelController(BookService bookService, AuthorService authorService, PublisherService publisherService, CategoryService categoryService, UserRepository userRepository) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.publisherService = publisherService;
        this.categoryService = categoryService;
        this.userRepository = userRepository;
    }

    @GetMapping(value = "/adminpanel")
    public String getAdminPanel(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("user", new User());
        model.addAttribute("category", new Category());
        model.addAttribute("authors", authorService.getAllAuthors());
        model.addAttribute("publishers", publisherService.getAllPublishers());
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("countBooks", bookService.countAllBooks());
        model.addAttribute("countUsers", userRepository.countUsers());
        model.addAttribute("users", userRepository.getMaxFiveNewUsers());
        model.addAttribute("countCategories", categoryService.countCategories());
        return "/adminpanel/index";
    }

    @GetMapping(value = "/adminpanel/error")
    public String getAdminPanelWithError(Model model) {
        if(!model.containsAttribute("book"))
            model.addAttribute("book", new Book());
        if(!model.containsAttribute("user"))
            model.addAttribute("user", new User());
        if(!model.containsAttribute("category"))
            model.addAttribute("category", new Category());
        model.addAttribute("authors", authorService.getAllAuthors());
        model.addAttribute("publishers", publisherService.getAllPublishers());
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("countBooks", bookService.countAllBooks());
        model.addAttribute("countUsers", userRepository.countUsers());
        model.addAttribute("users", userRepository.getMaxFiveNewUsers());
        model.addAttribute("countCategories", categoryService.countCategories());
        return "/adminpanel/index";
    }


}
