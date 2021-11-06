package org.dominokit.samples.library.client.views;

import org.dominokit.domino.api.client.mvp.view.HasUiHandlers;
import org.dominokit.domino.api.client.mvp.view.ModalView;
import org.dominokit.domino.api.client.mvp.view.UiHandlers;
import org.dominokit.samples.library.shared.model.Book;

public interface NewBookView extends ModalView, HasUiHandlers<NewBookView.NewBookUiHandlers> {

    Book save();

    void onError(String message);

    void edit(Book book);

    boolean isValid();

    interface NewBookUiHandlers extends UiHandlers {
        void onSaveBook();

        void onCancel();
    }
}