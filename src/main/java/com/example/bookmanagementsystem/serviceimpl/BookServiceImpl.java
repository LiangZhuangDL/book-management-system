package com.example.bookmanagementsystem.serviceimpl;

import com.example.bookmanagementsystem.dto.*;
import com.example.bookmanagementsystem.entity.authentication.BasicUser;
import com.example.bookmanagementsystem.entity.book.*;
import com.example.bookmanagementsystem.repository.anthentication.BasicUserRepository;
import com.example.bookmanagementsystem.repository.book.*;
import com.example.bookmanagementsystem.service.BasicUserService;
import com.example.bookmanagementsystem.service.BookService;
import com.example.bookmanagementsystem.service.DefaultFileService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.*;

/**
 * @program: book-management-system
 * @description: 图书业务实现类
 * @author: Simon Zhuang
 * @create: 2018-08-15 14:50
 **/
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookSearchRepository bookSearchRepository;

    @Autowired
    private BookBasicTypeRepository bookBasicTypeRepository;

    @Autowired
    private BookShelfRepository bookShelfRepository;
    
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private DefaultFileService defaultFileService;

    @Autowired
    private BasicUserService basicUserService;

    @Autowired
    private BookQuantityRepository bookQuantityRepository;

    @Autowired
    private BorrowedBookRepository borrowedBookRepository;

    @Autowired
    private BasicUserRepository basicUserRepository;

    @Override
    public Map<String, Object> findBooksByTitleContaining(String title) {
        /** 
        * @Description: 按照书名查询图书基本信息 
        * @Param: [title] 
        * @return: java.util.Map<java.lang.String,java.lang.Object> 
        * @Author: Simon Zhuang
        * @Date: 2018/8/16 
        **/ 
        Pageable pageable = PageRequest.of(1, 20);
        Page<BookSearch> bookSearches = bookSearchRepository.findBookSearchesByTitleContaining(title, pageable);
        Map<String, Object> map = new HashMap<>();
        map.put("data", bookSearches);
        return map;
    }

    @Override
    public Map<String, Object> findBooksByAuthorContaining(String author) {
        /** 
        * @Description: 按照作者查询图书基本信息 
        * @Param: [author] 
        * @return: java.util.Map<java.lang.String,java.lang.Object> 
        * @Author: Simon Zhuang
        * @Date: 2018/8/16 
        **/ 
        Pageable pageable = PageRequest.of(1, 20);
        Page<BookSearch> bookSearches = bookSearchRepository.findBookSearchesByAuthorContaining(author, pageable);
        Map<String, Object> map = new HashMap<>();
        map.put("data", bookSearches);
        return map;
    }

    @Override
    public Map<String, Object> findBooksByPublishingHouseContaining(String publishingHouse) {
        /** 
        * @Description: 按照出版社查询图书基本信息 
        * @Param: [publishingHouse] 
        * @return: java.util.Map<java.lang.String,java.lang.Object> 
        * @Author: Simon Zhuang
        * @Date: 2018/8/16 
        **/ 
        Pageable pageable = PageRequest.of(1, 20);
        Page<BookSearch> bookSearches = bookSearchRepository.findBookSearchesByPublishingHouseContaining(publishingHouse, pageable);
        Map<String, Object> map = new HashMap<>();
        map.put("data", bookSearches);
        return map;
    }

    @Override
    public Map<String, Object> findBooksByIsbnContaining(String isbn) {
        /** 
        * @Description: 按照isbn查询图书基本信息 
        * @Param: [isbn] 
        * @return: java.util.Map<java.lang.String,java.lang.Object> 
        * @Author: Simon Zhuang
        * @Date: 2018/8/16 
        **/ 
        Pageable pageable = PageRequest.of(1, 20);
        Page<BookSearch> bookSearches = bookSearchRepository.findBookSearchesByIsbnContaining(isbn, pageable);
        Map<String, Object> map = new HashMap<>();
        map.put("data", bookSearches);
        return map;
    }

    @Override
    public Map<String, Object> saveBook(BookDTO bookDTO) {
        /** 
        * @Description: 保存图书信息 
        * @Param: [bookDTO] 
        * @return: java.util.Map<java.lang.String,java.lang.Object> 
        * @Author: Simon Zhuang
        * @Date: 2018/8/16 
        **/
        BookShelf bookShelf = new BookShelf();
        BeanUtils.copyProperties(bookDTO, bookShelf);
        Book book = bookDTO.convert(bookDTO);
        String bookTypeSign = bookDTO.getSign();
        Map<String, Object> map = new HashMap<>();
        BookBasicType bookBasicType = bookBasicTypeRepository.findBookBasicTypeBySign(bookTypeSign);
        if(!ObjectUtils.isEmpty(bookBasicType)){
            BookShelf returnBookShelf = bookShelfRepository.save(bookShelf);
            if(!ObjectUtils.isEmpty(returnBookShelf)){
                book.setBookBasicType(bookBasicType);
                book.setBookShelf(returnBookShelf);
                Book returnBook = bookRepository.save(book);
                BookSearch bookSearch = new BookSearch();
                BookQuantity bookQuantity = new BookQuantity(book.getQuantity(), book);
                BeanUtils.copyProperties(returnBook, bookSearch, "id");
                BookSearch returnBookSearch = bookSearchRepository.save(bookSearch);
                BookQuantity returnBookQuantity = bookQuantityRepository.save(bookQuantity);
                if(!ObjectUtils.isEmpty(returnBook) && !ObjectUtils.isEmpty(returnBookSearch) && !ObjectUtils.isEmpty(returnBookQuantity)){
                    map.put("success", true);
                    return map;
                }else{
                    map.put("success", false);
                    return map;
                }
            }else {
                map.put("success", false);
                return map;
            }
        }else {
            map.put("success", false);
            return map;
        }
    }

    @Override
    public Map<String, Object> getBookByBookSearch(SingleBookSearchDTO singleBookSearchDTO) {
        /** 
        * @Description: 查询单条图书详细信息和图书封面图片并返回 
        * @Param: [singleBookSearchDTO] 
        * @return: java.util.Map<java.lang.String,java.lang.Object> 
        * @Author: Simon Zhuang
        * @Date: 2018/8/17 
        **/ 
        Book book = bookRepository.findBookByTitleAndAuthorAndPublishingHouseAndIsbn(singleBookSearchDTO.getTitle(), singleBookSearchDTO.getAuthor(), singleBookSearchDTO.getPublishingHouse(), singleBookSearchDTO.getIsbn());
        Map<String, Object> map = new HashMap<>();
        if(!ObjectUtils.isEmpty(book)){
            String coverImage = defaultFileService.findCoverBase64ById(book.getCover());
            if(!ObjectUtils.isEmpty(coverImage)){
                map.put("success", true);
                map.put("data", book);
                map.put("cover", coverImage);
                return map;
            }else {
                map.put("success", false);
                return map;
            }
        }else {
            map.put("success", false);
            return map;
        }
    }

    @Override
    public Map<String, Object> getBookListSearch(Integer page, Integer size, BookListSearchDTO bookListSearchDTO) {
        /** 
        * @Description: 根据查询类型查询批量查询图书 
        * @Param: [page, size, bookListSearchDTO] 
        * @return: java.util.Map<java.lang.String,java.lang.Object> 
        * @Author: Simon Zhuang
        * @Date: 2018/8/17 
        **/ 
        Pageable pageable = PageRequest.of(page, size);
        Map<String, Object> map = new HashMap<>();
        String type = bookListSearchDTO.getSearchType();
        String text = bookListSearchDTO.getText();
        switch (type) {
            case "title":
                Page<Book> booksByTitleContaining = bookRepository.findBooksByTitleContaining(text, pageable);
                return getStringObjectMap(map, booksByTitleContaining);
            case "author":
                Page<Book> booksByAuthorContaining = bookRepository.findBooksByAuthorContaining(text, pageable);
                return getStringObjectMap(map, booksByAuthorContaining);
            case "publishingHouse":
                Page<Book> booksByPublishingHouseContaining = bookRepository.findBooksByPublishingHouseContaining(text, pageable);
                return getStringObjectMap(map, booksByPublishingHouseContaining);
            default:
                map.put("success", false);
                return map;
        }
    }

    @Override
    public Map<String, Object> borrowBooks(BorrowedBookListDTO borrowedBookListDTO) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        List<Book> books = new ArrayList<>();
        List<String> successBorrowedBook = new ArrayList<>();
        BasicUser basicUser = basicUserService.findBasicUserByUsername(username);
        Map<String, Object> map = new HashMap<>();
        if(!ObjectUtils.isEmpty(basicUser) && !basicUser.getBorrowed()){
            List<BorrowedBookDTO> borrowedBookDTOS = borrowedBookListDTO.getBorrowedBookDTOS();
            BorrowedBook borrowedBook = borrowedBookRepository.findBorrowedBookByBasicUser(basicUser);
            if(ObjectUtils.isEmpty(borrowedBook)){
                borrowedBook = new BorrowedBook();
            }
            if(borrowedBookDTOS.size() <= borrowedBook.getMaxBorrowedQuantity()){
                for(BorrowedBookDTO borrowedBookDTO : borrowedBookDTOS){
                    Book book = bookRepository.findBookByTitleAndAuthorAndIsbnAndNumber(borrowedBookDTO.getTitle(), borrowedBookDTO.getAuthor(), borrowedBookDTO.getIsbn(), borrowedBookDTO.getNumber());
                    BookQuantity bookQuantity = bookQuantityRepository.findBookQuantityByBook(book);
                    if(!ObjectUtils.isEmpty(book) && bookQuantity.getLeftQuantities() >= 1){
                        book.setBorrowedDate(new Date());
                        book.setMaxHoldingDays(30);
                        Book returnBook = bookRepository.save(book);
                        books.add(returnBook);
                        Integer leftQuantity = bookQuantity.getLeftQuantities() - 1;
                        bookQuantity.setLeftQuantities(leftQuantity);
                        bookQuantityRepository.save(bookQuantity);
                        successBorrowedBook.add(book.getTitle());
                    }
                }
                Integer maxBorrowNumber = borrowedBook.getMaxBorrowedQuantity() - borrowedBookDTOS.size();
                BorrowedBook borrowedBooks = new BorrowedBook(basicUser, books, books);
                borrowedBooks.setMaxBorrowedQuantity(maxBorrowNumber);
                BorrowedBook result = borrowedBookRepository.save(borrowedBooks);
                basicUser.setBorrowed(true);
                BasicUser returnUser = basicUserRepository.save(basicUser);
                if(!ObjectUtils.isEmpty(result) && !ObjectUtils.isEmpty(returnUser)){
                    map.put("success", true);
                    map.put("borrowedBook", successBorrowedBook);
                    return map;
                }else {
                    map.put("success", false);
                    return map;
                }
            }else{
                map.put("success", false);
                return map;
            }
        }else{
            map.put("success", false);
            return map;
        }
    }

    @Override
    public Map<String, Object> returnBooks(BorrowedBookListDTO borrowedBookListDTO) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        BasicUser basicUser = basicUserService.findBasicUserByUsername(username);
        Map<String, Object> map = new HashMap<>();
        List<Book> successList = new ArrayList<>();
        if(!ObjectUtils.isEmpty(basicUser) && basicUser.getBorrowed()){
            BorrowedBook borrowedBook = borrowedBookRepository.findBorrowedBookByBasicUser(basicUser);
            List<BorrowedBookDTO> borrowedBookDTOS = borrowedBookListDTO.getBorrowedBookDTOS();
            if(!ObjectUtils.isEmpty(borrowedBook) && borrowedBookDTOS.size() <= borrowedBook.getMaxBorrowedQuantity()){
                List<Book> books = borrowedBook.getBooks();
                double price = 0.0;
                for (Book book: books){
                    for(BorrowedBookDTO borrowedBookDTO : borrowedBookDTOS){
                        Book returnBook = bookRepository.findBookByTitleAndAuthorAndIsbnAndNumber(borrowedBookDTO.getTitle(), borrowedBookDTO.getAuthor(), borrowedBookDTO.getIsbn(), borrowedBookDTO.getNumber());
                        if(returnBook.equals(book)){
                            BookQuantity bookQuantity = bookQuantityRepository.findBookQuantityByBook(book);
                            books.remove(book);
                            Date date = book.getBorrowedDate();
                            Date now = new Date();
                            Long days = ((now.getTime() - date.getTime())/1000*60*60*24 + 1);
                            if(days > book.getMaxHoldingDays()){
                                int overdueDays = (int)(book.getMaxHoldingDays() - days);
                                price = price + overdueDays * book.getPrice() * 0.1;
                            }
                            book.setMaxHoldingDays(null);
                            book.setBorrowedDate(null);
                            Integer leftQuantity = bookQuantity.getLeftQuantities() + 1;
                            bookQuantity.setLeftQuantities(leftQuantity);
                            bookQuantityRepository.save(bookQuantity);
                            successList.add(book);
                        }
                    }
                }
                BorrowedBook result;
                if(books.size() == 0){
                    borrowedBook.setFinished(true);
                    result = borrowedBookRepository.save(borrowedBook);
                    basicUser.setBorrowed(false);
                    basicUserRepository.save(basicUser);
                }else {
                    borrowedBook.setFinished(true);
                    result = borrowedBookRepository.save(borrowedBook);
                    if(!ObjectUtils.isEmpty(result)){
                        BorrowedBook borrowedBooks = new BorrowedBook(basicUser, books);
                        result = borrowedBookRepository.save(borrowedBooks);
                    }
                }
                if(!ObjectUtils.isEmpty(result)){
                    map.put("success", true);
                    map.put("price", price);
                    map.put("successList", successList);
                    return map;
                }else{
                    map.put("success", false);
                    return map;
                }
            }else{
                map.put("success", false);
                return map;
            }
        }else {
            map.put("success", false);
            return map;
        }
    }

    @Override
    public Map<String, Object> renewBooks(BorrowedBookListDTO borrowedBookListDTO) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        BasicUser basicUser = basicUserService.findBasicUserByUsername(username);
        Map<String, Object> map = new HashMap<>();
        List<Book> bookList = new ArrayList<>();
        List<Book> successList = new ArrayList<>();
        List<Book> failureList = new ArrayList<>();
        if(!ObjectUtils.isEmpty(basicUser) && basicUser.getBorrowed()){
            BorrowedBook borrowedBook = borrowedBookRepository.findBorrowedBookByBasicUser(basicUser);
            List<BorrowedBookDTO> borrowedBookDTOS = borrowedBookListDTO.getBorrowedBookDTOS();
            if(!ObjectUtils.isEmpty(borrowedBook) && borrowedBookDTOS.size() <= borrowedBook.getMaxBorrowedQuantity()){
                List<Book> books = borrowedBook.getBooks();
                for(Book book: books){
                    for(BorrowedBookDTO borrowedBookDTO : borrowedBookDTOS){
                        Book returnBook = bookRepository.findBookByTitleAndAuthorAndIsbnAndNumber(borrowedBookDTO.getTitle(), borrowedBookDTO.getAuthor(), borrowedBookDTO.getIsbn(), borrowedBookDTO.getNumber());
                        if(returnBook.equals(book)){
                            book.setRenew(true);
                            book.setMaxHoldingDays(book.getMaxHoldingDays() + 30);
                            Book result = bookRepository.save(book);
                            if(!ObjectUtils.isEmpty(result)){
                                bookList.add(result);
                                successList.add(result);
                            }
                        }else {
                            bookList.add(book);
                            failureList.add(returnBook);
                        }
                    }
                }
                borrowedBook.setBooks(bookList);
                BorrowedBook borrowedBookResult = borrowedBookRepository.save(borrowedBook);
                if(!ObjectUtils.isEmpty(borrowedBookResult)){
                    map.put("success", true);
                    map.put("successList", successList);
                    map.put("failureList", failureList);
                    return map;
                }else{
                    map.put("success", false);
                    return map;
                }
            }else {
                map.put("success", false);
                return map;
            }
        }
        else {
            map.put("success", false);
            return map;
        }
    }

    private Map<String, Object> getStringObjectMap(Map<String, Object> map, Object object) {
        if (!ObjectUtils.isEmpty(object)) {
            map.put("success", true);
            map.put("data", object);
            return map;
        } else {
            map.put("success", false);
            return map;
        }
    }
}
