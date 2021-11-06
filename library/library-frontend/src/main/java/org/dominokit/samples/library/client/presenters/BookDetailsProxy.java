package org.dominokit.samples.library.client.presenters;

import org.dominokit.domino.api.client.annotations.presenter.AutoReveal;
import org.dominokit.domino.api.client.annotations.presenter.AutoRoute;
import org.dominokit.domino.api.client.annotations.presenter.OnReveal;
import org.dominokit.domino.api.client.annotations.presenter.PathParameter;
import org.dominokit.domino.api.client.annotations.presenter.PresenterProxy;
import org.dominokit.domino.api.client.annotations.presenter.QueryParameter;
import org.dominokit.domino.api.client.annotations.presenter.Slot;
import org.dominokit.domino.api.client.mvp.presenter.ViewBaseClientPresenter;
import org.dominokit.samples.library.client.views.BookDetailsView;
import org.dominokit.samples.library.shared.model.Book;
import org.dominokit.samples.library.shared.services.BooksServiceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static java.util.Objects.nonNull;

@PresenterProxy(parent = "books")
@AutoRoute(token = "books/:title")
@Slot("content")
@AutoReveal
public class BookDetailsProxy extends ViewBaseClientPresenter<BookDetailsView> implements BookDetailsView.BookDetailsUiHandlers {

    private Book book;

    @PathParameter
    String title;

    @QueryParameter
    List<String> editable;

    @OnReveal
    public void onBookDetailsRevealed() {
        BooksServiceFactory.INSTANCE
                .get(title)
                .onSuccess(book -> editBook(book, isEditableState()))
                .onFailed(failedResponseBean -> view.onError(failedResponseBean.getBody()))
                .send();
    }

    private void editBook(Book book, boolean editableState) {
        this.book= book;
        view.edit(book);
        setEditable(editableState);
    }

    private void setEditable(boolean editableState) {
        view.setEditable(editableState);
        if (history().currentToken().hasQueryParameter("editable")) {
            history().pushState(history().currentToken().replaceParameter("editable", "editable", editableState + ""));
        } else {
            history().pushState(history().currentToken().addQueryParameter("editable", editableState + ""));
        }
    }

    private boolean isEditableState() {
        return nonNull(editable) && !editable.isEmpty() && Boolean.parseBoolean(editable.get(0));
    }

    @Override
    public void onSave() {
        BooksServiceFactory.INSTANCE
                .update(title, view.save())
                .onSuccess(book -> editBook(book, false))
                .onFailed(failedResponseBean -> view.onError(failedResponseBean.getBody()))
                .send();
    }

    @Override
    public void onEdit() {
        setEditable(true);
    }

    @Override
    public void onCancel() {
        editBook(book, false);
    }

    @Override
    public void onBackToList() {
        history().fireState("books");
    }
}