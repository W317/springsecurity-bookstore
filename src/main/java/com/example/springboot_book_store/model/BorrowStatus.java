package com.example.springboot_book_store.model;

public enum BorrowStatus {
    ACCEPTED("BORROWED SUCCESSFULLY"),
    REJECTED("REJECTED"),
    PENDING("PENDING"),

    AVAILABLE("AVAILABLE");

    private String status;

    BorrowStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
