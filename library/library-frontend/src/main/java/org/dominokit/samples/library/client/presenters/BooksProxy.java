package org.dominokit.samples.library.client.presenters;

import org.dominokit.domino.api.client.annotations.presenter.AutoReveal;
import org.dominokit.domino.api.client.annotations.presenter.AutoRoute;
import org.dominokit.domino.api.client.annotations.presenter.OnInit;
import org.dominokit.domino.api.client.annotations.presenter.OnReveal;
import org.dominokit.domino.api.client.annotations.presenter.PresenterProxy;
import org.dominokit.domino.api.client.annotations.presenter.Slot;
import org.dominokit.domino.api.client.mvp.presenter.ViewBaseClientPresenter;
import org.dominokit.samples.library.client.views.BooksView;
import org.dominokit.samples.library.shared.model.Book;
import org.dominokit.samples.library.shared.services.BooksServiceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@PresenterProxy(parent = "shell")
@AutoRoute(token = "books")
@Slot("content")
@AutoReveal
public class BooksProxy extends ViewBaseClientPresenter<BooksView> implements BooksView.BooksUiHandlers {

    private static final Logger LOGGER = LoggerFactory.getLogger(BooksProxy.class);

    @OnInit
    public void onBooksInit(){
        LOGGER.info("Books initialized");
    }

    @OnReveal
    public void listBooks() {
        LOGGER.info("Books view revealed, loading books...");
        BooksServiceFactory.INSTANCE
                .list()
                .onSuccess(books -> view.setBooks(books))
                .onFailed(failedResponse -> view.showError("Sadly no response from server.!"))
                .send();
    }

    @Override
    public void onCreate() {
        history().fireState("new-book");
    }

    @Override
    public void onBookSelected(Book book) {
        history().fireState("book/"+book.getTitle());
    }

    @Override
    public void deleteBook(Book book) {
        BooksServiceFactory.INSTANCE
                .delete(book.getTitle())
                .onSuccess(books -> listBooks())
                .onFailed(failedResponse -> view.showError(failedResponse.getBody()))
                .send();
    }
}