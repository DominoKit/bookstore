package org.dominokit.samples.library.client.views;

import org.dominokit.domino.api.client.mvp.view.ContentView;
import org.dominokit.domino.api.client.mvp.view.HasUiHandlers;
import org.dominokit.domino.api.client.mvp.view.UiHandlers;
import org.dominokit.samples.library.shared.model.Book;

public interface BookDetailsView extends ContentView, HasUiHandlers<BookDetailsView.BookDetailsUiHandlers> {

    void edit(Book book);

    void onError(String message);

    Book save();

    void setEditable(boolean editable);

    interface BookDetailsUiHandlers extends UiHandlers {
        void onSave();

        void onCancel();

        void onEdit();

        void onBackToList();
    }
}