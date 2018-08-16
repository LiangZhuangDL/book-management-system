package com.example.bookmanagementsystem.serviceimpl;

import com.example.bookmanagementsystem.dto.BookDTO;
import com.example.bookmanagementsystem.entity.book.Book;
import com.example.bookmanagementsystem.entity.book.BookBasicType;
import com.example.bookmanagementsystem.entity.book.BookSearch;
import com.example.bookmanagementsystem.entity.book.BookShelf;
import com.example.bookmanagementsystem.repository.book.BookBasicTypeRepository;
import com.example.bookmanagementsystem.repository.book.BookRepository;
import com.example.bookmanagementsystem.repository.book.BookSearchRepository;
import com.example.bookmanagementsystem.repository.book.BookShelfRepository;
import com.example.bookmanagementsystem.service.BookService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.HashMap;
import java.util.Map;

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
        Page<BookSearch> bookSearches = bookSearchRepository.findBookSearchByTitleContaining(title, pageable);
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
        Page<BookSearch> bookSearches = bookSearchRepository.findBookSearchByAuthorContaining(author, pageable);
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
        Page<BookSearch> bookSearches = bookSearchRepository.findBookSearchByPublishingHouseContaining(publishingHouse, pageable);
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
        Page<BookSearch> bookSearches = bookSearchRepository.findBookSearchByIsbnContaining(isbn, pageable);
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
                BeanUtils.copyProperties(returnBook, bookSearch, "id");
                BookSearch returnBookSearch = bookSearchRepository.save(bookSearch);
                if(!ObjectUtils.isEmpty(returnBook) && !ObjectUtils.isEmpty(returnBookSearch)){
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
}
