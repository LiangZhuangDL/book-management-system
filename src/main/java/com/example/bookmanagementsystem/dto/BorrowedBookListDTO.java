package com.example.bookmanagementsystem.dto;

import java.util.List;

/**
 * @program: book-management-system
 * @description: 借书列表DTO
 * @author: Simon Zhuang
 * @create: 2018-08-17 17:38
 **/
public class BorrowedBookListDTO {

    private List<BorrowedBookDTO> borrowedBookDTOS;

    public List<BorrowedBookDTO> getBorrowedBookDTOS() {
        return borrowedBookDTOS;
    }

    public void setBorrowedBookDTOS(List<BorrowedBookDTO> borrowedBookDTOS) {
        this.borrowedBookDTOS = borrowedBookDTOS;
    }

    public BorrowedBookListDTO() {
    }

    public BorrowedBookListDTO(List<BorrowedBookDTO> borrowedBookDTOS) {
        this.borrowedBookDTOS = borrowedBookDTOS;
    }

}
