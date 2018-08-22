package com.example.bookmanagementsystem.repository.book;

import com.example.bookmanagementsystem.entity.authentication.BasicUser;
import com.example.bookmanagementsystem.entity.book.BorrowedBookHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowedBookHistoryRepository extends JpaRepository<BorrowedBookHistory, Long> {

    Page<BorrowedBookHistory> findBorrowedBookHistoriesByBasicUser(BasicUser basicUser, Pageable pageable);
}
