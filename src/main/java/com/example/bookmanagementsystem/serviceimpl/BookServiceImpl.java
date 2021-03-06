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

    @Autowired
    private BorrowedBookHistoryRepository borrowedBookHistoryRepository;

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
        BasicUser basicUser = basicUserRepository.findBasicUserByUsername(username);
        List<BorrowedBookHistory> failureBorrowedBookHistories = new ArrayList<>();
        List<Book> failureBooks = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        if(!ObjectUtils.isEmpty(basicUser)){
            BorrowedBook borrowedBook = borrowedBookRepository.findBorrowedBookByBasicUser(basicUser);
            if(!ObjectUtils.isEmpty(borrowedBook)){
                List<Book> books = borrowedBook.getBooks();
                int canBorrowedBooksNumber = borrowedBook.getMaxBorrowedQuantity() - books.size();
                List<BorrowedBookDTO> borrowedBookDTOS = borrowedBookListDTO.getBorrowedBookDTOS();
                if(canBorrowedBooksNumber >= borrowedBookDTOS.size()){
                    for(BorrowedBookDTO borrowedBookDTO: borrowedBookDTOS){
                        Book book = bookRepository.findBookByTitleAndAuthorAndIsbnAndNumber(borrowedBookDTO.getTitle(), borrowedBookDTO.getAuthor(), borrowedBookDTO.getIsbn(), borrowedBookDTO.getNumber());
                        if(!ObjectUtils.isEmpty(book)){
                            books.add(book);
                            BorrowedBookHistory borrowedBookHistory = new BorrowedBookHistory(new Date(), basicUser, book);
                            BorrowedBookHistory borrowedBookHistoryResult = borrowedBookHistoryRepository.save(borrowedBookHistory);
                            if(ObjectUtils.isEmpty(borrowedBookHistoryResult)){
                                failureBorrowedBookHistories.add(borrowedBookHistoryResult);
                            }
                        }else{
                            failureBooks.add(book);
                        }
                    }
                    borrowedBook.setBooks(books);
                    BorrowedBook borrowedBookResult = borrowedBookRepository.save(borrowedBook);
                    if(!ObjectUtils.isEmpty(borrowedBookResult)){
                        map.put("success", true);
                        map.put("failureBooks" , failureBooks);
                        map.put("failureBorrowedBookHistories", failureBorrowedBookHistories);
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
        }else{
            map.put("success", false);
            return map;
        }
    }

    @Override
    public Map<String, Object> returnBooks(BorrowedBookListDTO borrowedBookListDTO) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        BasicUser basicUser = basicUserRepository.findBasicUserByUsername(username);
        List<BorrowedBookHistory> failureBorrowedBookHistories = new ArrayList<>();
        List<Book> returnBooks = new ArrayList<>();
        List<Book> failureBooks = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        double price = 0;
        if(!ObjectUtils.isEmpty(basicUser)){
            BorrowedBook borrowedBook = borrowedBookRepository.findBorrowedBookByBasicUser(basicUser);
            if(!ObjectUtils.isEmpty(borrowedBook)){
                List<Book> books = borrowedBook.getBooks();
                List<BorrowedBookDTO> borrowedBookDTOS = borrowedBookListDTO.getBorrowedBookDTOS();
                findBooksNeedChange(failureBooks, books, returnBooks, borrowedBookDTOS);
                if(books.removeAll(returnBooks)){
                    for(Book historyBook : returnBooks){
                        BorrowedBookHistory borrowedBookHistory = borrowedBookHistoryRepository.findBorrowedBookHistoryByBookAndBasicUserAndFinished(historyBook, basicUser, false);
                        Date borrowedDate = borrowedBookHistory.getCreateTime();
                        Date now = new Date();
                        Calendar borrowedCalender = new GregorianCalendar();
                        Calendar nowCalender = new GregorianCalendar();
                        borrowedCalender.setTime(borrowedDate);
                        nowCalender.setTime(now);
                        int holdingDays;
                        if(historyBook.getRenew()){
                            holdingDays = 60;
                        }else {
                            holdingDays = 30;
                        }
                        int days = nowCalender.get(Calendar.DAY_OF_YEAR) - borrowedCalender.get(Calendar.DAY_OF_YEAR) - holdingDays;
                        price = price + days * historyBook.getPrice() * 0.1;
                        borrowedBookHistory.setFinishDate(new Date());
                        borrowedBookHistory.setFinished(true);
                        if(ObjectUtils.isEmpty(borrowedBookHistoryRepository.save(borrowedBookHistory))){
                            failureBorrowedBookHistories.add(borrowedBookHistoryRepository.save(borrowedBookHistory));
                        }
                    }
                    borrowedBook.setBooks(books);
                    BorrowedBook result = borrowedBookRepository.save(borrowedBook);
                    if(!ObjectUtils.isEmpty(result)){
                        map.put("success", true);
                        map.put("failureBooks", failureBooks);
                        map.put("failureBorrowedBookHistories", failureBorrowedBookHistories);
                        map.put("price", price);
                        return map;
                    }else {
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
        }else {
            map.put("success", false);
            return map;
        }
    }

    @Override
    public Map<String, Object> renewBooks(BorrowedBookListDTO borrowedBookListDTO) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        BasicUser basicUser = basicUserRepository.findBasicUserByUsername(username);
        List<Book> failureBooks = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        if(!ObjectUtils.isEmpty(basicUser)){
            BorrowedBook borrowedBook = borrowedBookRepository.findBorrowedBookByBasicUser(basicUser);
            if(!ObjectUtils.isEmpty(borrowedBook)){
                List<Book> books = borrowedBook.getBooks();
                List<Book> returnBooks = new ArrayList<>();
                List<BorrowedBookDTO> borrowedBookDTOS = borrowedBookListDTO.getBorrowedBookDTOS();
                findBooksNeedChange(failureBooks, books, returnBooks, borrowedBookDTOS);
                if(books.removeAll(returnBooks)){
                    for(Book book : returnBooks){
                        book.setRenew(true);
                    }
                    if(books.addAll(returnBooks)){
                        borrowedBook.setBooks(books);
                        BorrowedBook result = borrowedBookRepository.save(borrowedBook);
                        if(!ObjectUtils.isEmpty(result)){
                            map.put("success", true);
                            map.put("failureBooks", failureBooks);
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
            }else {
                map.put("success", false);
                return map;
            }
        }else{
            map.put("success", false);
            return map;
        }
    }

    private void findBooksNeedChange(List<Book> failureBooks, List<Book> books, List<Book> returnBooks, List<BorrowedBookDTO> borrowedBookDTOS) {
        for(BorrowedBookDTO borrowedBookDTO : borrowedBookDTOS){
            Book returnBook = bookRepository.findBookByTitleAndAuthorAndIsbnAndNumber(borrowedBookDTO.getTitle(), borrowedBookDTO.getAuthor(), borrowedBookDTO.getIsbn(), borrowedBookDTO.getNumber());
            if(books.contains(returnBook)){
                returnBooks.add(returnBook);
            }else {
                failureBooks.add(returnBook);
            }
        }
    }


    @Override
    public Map<String, Object> borrowedBookInformation() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        BasicUser basicUser = basicUserRepository.findBasicUserByUsername(username);
        List<Book> failureBookList = new ArrayList<>();
        Map<Book, Date> bookDateMap = new HashMap<>();
        Map<String, Object> map = new HashMap<>();
        if(!ObjectUtils.isEmpty(basicUser)){
            BorrowedBook borrowedBook = borrowedBookRepository.findBorrowedBookByBasicUser(basicUser);
            List<Book> books = borrowedBook.getBooks();
            for(Book book: books){
                BorrowedBookHistory borrowedBookHistory = borrowedBookHistoryRepository.findBorrowedBookHistoryByBookAndBasicUserAndFinished(book, basicUser, false);
                if(!ObjectUtils.isEmpty(borrowedBookHistory)){
                    Date createDate = borrowedBookHistory.getCreateTime();
                    bookDateMap.put(book, createDate);
                }else{
                    failureBookList.add(book);
                }
            }
            map.put("success", true);
            map.put("failureBookList", failureBookList);
            map.put("bookDateMap", bookDateMap);
            map.put("books", books);
            return map;
        }else{
            map.put("success", false);
        }
        return null;
    }

    @Override
    public Map<String, Object> borrowedBookHistory(Integer size, Integer page) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        BasicUser basicUser = basicUserRepository.findBasicUserByUsername(username);
        Map<String, Object> map = new HashMap<>();
        if(!ObjectUtils.isEmpty(basicUser)){
            Pageable pageable = PageRequest.of(page, size);
            Page<BorrowedBookHistory> borrowedBookHistories = borrowedBookHistoryRepository.findBorrowedBookHistoriesByBasicUser(basicUser, pageable);
            if(!ObjectUtils.isEmpty(borrowedBookHistories)){
                map.put("success", true);
                map.put("borrowedBookHistories", borrowedBookHistories);
                return map;
            }else {
                map.put("success", false);
                return map;
            }
        }else{
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
