package com.example.bookmanagementsystem.utils;

public interface DTOConvert<T, S> {

    T convert(S s);
}
