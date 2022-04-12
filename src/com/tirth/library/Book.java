/*
 * Copyright 2022 Tirth Patel
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.tirth.library;

import java.io.Serializable;

/**
 * The {@code Book} class represents a book. It stores the book's title, author,
 * price and the number of copies of the book(quantity). Every {@code Book} class
 * is supposed to have a unique bookId number. The bookId is generated by the
 * {@code Book} class. The bookId is used to uniquely identify the book. The
 * quantity of book can be increased by the {@link #addCopies(int)} method and
 * decreased by the {@link #removeCopies(int)} method.
 *
 * @author Tirth Patel
 */
public class Book implements Serializable {

    private static final long serialVersionUID = 4174440598488135434L;

    private final String title;
    private final String author;
    private final float price;
    private final int bookId;
    private int quantity;

    public Book(String title, String author, float price, int quantity) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.quantity = quantity;

        this.bookId = BookIdGenerator.generateBookId();
    }

    /**
     * Returns the title of the book.
     *
     * @return The title of the book
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns the author of the book.
     *
     * @return The author of the book
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Returns the price of the book.
     *
     * @return The price of the book
     */
    public float getPrice() {
        return price;
    }

    /**
     * Returns the bookId of the book.
     *
     * @return The bookId of the book
     */
    public int getBookId() {
        return bookId;
    }

    /**
     * Returns the quantity of the book.
     *
     * @return The quantity of the book
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Increases the quantity of the book by the specified number of copies.
     *
     * @param quantity The number of copies to be added
     */
    public void addCopies(int quantity) {
        this.quantity += quantity;
    }

    /**
     * Decreases the quantity of the book by the specified number of copies.
     *
     * @param quantity The number of copies to be removed
     */
    public void removeCopies(int quantity) {
        if (quantity > this.quantity) {
            throw new IllegalArgumentException("Not enough copies");
        }
        this.quantity -= quantity;
    }
}