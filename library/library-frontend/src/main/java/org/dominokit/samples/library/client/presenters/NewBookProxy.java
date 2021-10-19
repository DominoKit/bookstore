package org.dominokit.samples.library.client.presenters;

import org.dominokit.domino.api.client.annotations.presenter.AutoReveal;
import org.dominokit.domino.api.client.annotations.presenter.AutoRoute;
import org.dominokit.domino.api.client.annotations.presenter.OnReveal;
import org.dominokit.domino.api.client.annotations.presenter.PresenterProxy;
import org.dominokit.domino.api.client.annotations.presenter.Slot;
import org.dominokit.domino.api.client.mvp.presenter.ViewBaseClientPresenter;
import org.dominokit.samples.library.client.views.NewBookView;
import org.dominokit.samples.library.shared.model.Book;
import org.dominokit.samples.library.shared.services.BooksServiceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@PresenterProxy(parent = "shell")
@AutoRoute(token = "new-book")
@Slot("content")
@AutoReveal
public class NewBookProxy extends ViewBaseClientPresenter<NewBookView> implements NewBookView.NewBookUiHandlers {

    private static final Logger LOGGER = LoggerFactory.getLogger(NewBookProxy.class);

    @OnReveal
    public void initBook(){
        view.edit(new Book());
    }

    @Override
    public void onSaveBook() {
        if(view.isValid()) {
            BooksServiceFactory.INSTANCE
                    .create(view.save())
                    .onSuccess(book -> history().fireState("book/" + book.getTitle()))
                    .onFailed(failedResponseBean -> view.onError(failedResponseBean.getBody()))
                    .send();
        }
    }

    @Override
    public void onCancel() {
        history().fireState("books");
    }
}