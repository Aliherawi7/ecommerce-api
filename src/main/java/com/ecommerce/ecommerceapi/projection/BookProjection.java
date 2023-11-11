package com.ecommerce.ecommerceapi.projection;

import java.time.LocalDate;

public interface BookProjection {
    //b.bookId AS bookId, b.title AS title, b.author AS author, b.date AS date
    Long getId();
    String getTitle();
    String getAuthor();
    LocalDate getDate();
}
