package com.aks.Controller;

import com.aks.Entity.Cart;
import com.aks.Exceptions.BadRequestException;
import com.aks.Exceptions.CustomGenericException;
import com.aks.Exceptions.CustomNotFoundException;
import com.aks.Exceptions.DatabaseDownException;
import com.aks.POJO.BookPojo;
import com.aks.Service.CartService;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Rest controller api for cart related requests
 * User id must be passed in header under "user_id" field for api requests
 *
 * url                              HTTP Method  Operation
 * /api/cart/:user_id               GET          Get list of all books in user's cart
 * /api/cart/add/:user_id/:book_id  POST         Add a new book and return success/error message
 * /api/cart/:user_id/:book_id      DELETE       Delete the book in cart
 */
@RestController
@RequestMapping("/api/cart")
public class CartController {
    @Autowired
    CartService cartService;

    /**
     * Get cart for a user
     * @param user_id
     * @return A list of carts corresponding to that user
     */
    @GetMapping("/{user_id}/{type}")
    public @ResponseBody
    List<BookPojo> getCart(@PathVariable("user_id") int user_id,
            @PathVariable("type") int type) {
        try {
            return cartService.getCart(user_id, type);
        }
        catch (HibernateException | CannotCreateTransactionException dbException) {
            throw new DatabaseDownException("Database error. Could not connect at this time.");
        }
        catch (Exception ex){
              throw new CustomGenericException("Could not retrieve cart at this time. Please try again later.", ex);
        }
    }

    /**
     * Add a book to cart of user
     * @param user_id
     * @param book_id
     * @return Success/Error message
     */
    @PostMapping("/add/{user_id}/{book_id}")
    public @ResponseBody
    String addToCart(@PathVariable("user_id") Integer user_id,
                     @PathVariable("book_id") Integer book_id){
        try {
            return cartService.addBook(user_id, book_id);
        }
        catch (HibernateException | CannotCreateTransactionException dbException) {
            throw new DatabaseDownException("Database error. Could not connect at this time.");
        }
        catch (Exception ex){
            throw new CustomGenericException("Could not add book to cart at this time. Please try again later.", ex);
        }
    }

    /**
     * Delete a book from cart of a user
     * @param user_id
     * @param book_id
     * @return Success/Error message
     */
    @DeleteMapping("/{user_id}/{book_id}")
    public @ResponseBody
    String deleteBookFromCart(@PathVariable("user_id") Integer user_id,
                     @PathVariable("book_id") Integer book_id){
        try {
            return cartService.deleteBook(user_id, book_id);
        }
        catch (HibernateException | CannotCreateTransactionException dbException) {
            throw new DatabaseDownException("Database error. Could not connect at this time.");
        }
        catch (Exception ex){
            throw new CustomGenericException("Error deleting book from cart. Please try after sometime.", ex);
        }
    }

    /**
     * @param user_id
     * @return Message whether checkout is successful or not
     */
    @RequestMapping("/checkout/{user_id}")
    public @ResponseBody
    String checkout(@PathVariable("user_id") Integer user_id){
        try{
            return cartService.checkoutBooks(user_id);
        }
        catch (HibernateException | CannotCreateTransactionException dbException) {
            throw new DatabaseDownException("Database error. Could not connect at this time.");
        }
        catch (Exception ex){
            throw new CustomGenericException("Error checking out book. Please try after sometime.", ex);
        }
    }

    /**
     * @param user_id
     * @return Message whether checkout is successful or not
     */
    @RequestMapping("/return/{user_id}/{book_id}")
    public @ResponseBody
    String returnBook(@PathVariable("user_id") Integer user_id,
                    @PathVariable("book_id") Integer book_id){
        try{
            return cartService.returnBook(user_id, book_id);
        }
        catch (HibernateException | CannotCreateTransactionException dbException) {
            throw new DatabaseDownException("Database error. Could not connect at this time.");
        }
        catch (Exception ex){
            throw new CustomGenericException("Error checking out book. Please try after sometime.", ex);
        }
    }
}
