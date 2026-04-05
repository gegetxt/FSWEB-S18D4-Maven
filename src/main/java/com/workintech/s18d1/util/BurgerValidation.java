package com.workintech.s18d1.util;

import com.workintech.s18d1.entity.Burger;
import com.workintech.s18d1.exceptions.BurgerException;
import org.springframework.http.HttpStatus;

public class BurgerValidation {

    private BurgerValidation() {
    }

    public static void validateBurger(Burger burger) {
        if (burger == null) {
            throw new BurgerException("Burger cannot be null", HttpStatus.BAD_REQUEST);
        }
        if (burger.getName() == null || burger.getName().isBlank()) {
            throw new BurgerException("Burger name is required", HttpStatus.BAD_REQUEST);
        }
        if (burger.getPrice() == null || burger.getPrice() <= 0) {
            throw new BurgerException("Burger price must be greater than zero", HttpStatus.BAD_REQUEST);
        }
        if (burger.getIsVegan() == null) {
            throw new BurgerException("Burger vegan information is required", HttpStatus.BAD_REQUEST);
        }
        if (burger.getBreadType() == null) {
            throw new BurgerException("Burger bread type is required", HttpStatus.BAD_REQUEST);
        }
        if (burger.getContents() == null || burger.getContents().isBlank()) {
            throw new BurgerException("Burger contents are required", HttpStatus.BAD_REQUEST);
        }
    }

    public static void validateId(Long id) {
        if (id == null || id <= 0) {
            throw new BurgerException("Burger id must be greater than zero", HttpStatus.BAD_REQUEST);
        }
    }

    public static void validatePrice(int price) {
        if (price < 0) {
            throw new BurgerException("Price cannot be negative", HttpStatus.BAD_REQUEST);
        }
    }

    public static void validateContent(String content) {
        if (content == null || content.isBlank()) {
            throw new BurgerException("Content cannot be blank", HttpStatus.BAD_REQUEST);
        }
    }
}
