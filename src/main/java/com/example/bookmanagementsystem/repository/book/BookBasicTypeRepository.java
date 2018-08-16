package com.example.bookmanagementsystem.repository.book;

import com.example.bookmanagementsystem.entity.book.BookBasicType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookBasicTypeRepository extends JpaRepository<BookBasicType, Long> {

    BookBasicType findBookBasicTypeBySign(String sign);
}
