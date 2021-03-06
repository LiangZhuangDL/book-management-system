package com.example.bookmanagementsystem.repository.book;

import com.example.bookmanagementsystem.entity.authentication.BasicUser;
import com.example.bookmanagementsystem.entity.book.BorrowedBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowedBookRepository extends JpaRepository<BorrowedBook, Long> {

    BorrowedBook findBorrowedBookByBasicUser(BasicUser basicUser);
}
