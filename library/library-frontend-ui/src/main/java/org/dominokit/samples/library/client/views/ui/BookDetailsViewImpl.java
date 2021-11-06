package org.dominokit.samples.library.client.views.ui;

import elemental2.dom.HTMLDivElement;
import org.dominokit.domino.api.client.annotations.UiView;
import org.dominokit.domino.ui.button.Button;
import org.dominokit.domino.ui.cards.Card;
import org.dominokit.domino.ui.grid.Column;
import org.dominokit.domino.ui.grid.Row;
import org.dominokit.domino.ui.grid.flex.FlexItem;
import org.dominokit.domino.ui.grid.flex.FlexLayout;
import org.dominokit.domino.ui.icons.Icons;
import org.dominokit.domino.ui.notifications.Notification;
import org.dominokit.domino.ui.utils.DominoElement;
import org.dominokit.domino.view.BaseElementView;
import org.dominokit.samples.library.client.presenters.BookDetailsProxy;
import org.dominokit.samples.library.client.views.BookDetailsView;
import org.dominokit.samples.library.shared.model.Book;

@UiView(presentable = BookDetailsProxy.class)
public class BookDetailsViewImpl extends BaseElementView<HTMLDivElement> implements BookDetailsView{

    private BookDetailsUiHandlers uiHandlers;
    private DominoElement<HTMLDivElement> root = DominoElement.div();
    private BookComponent bookComponent;
    private FlexItem<HTMLDivElement> editElement;
    private FlexItem<HTMLDivElement> saveElement;
    private FlexItem<HTMLDivElement> cancelElement;
    private FlexItem<HTMLDivElement> backToListElement;

    @Override
    public HTMLDivElement init() {
        bookComponent = new BookComponent();

        editElement = FlexItem.create();
        saveElement = FlexItem.create();
        cancelElement = FlexItem.create();
        backToListElement = FlexItem.create();
        root
                .appendChild(Card.create("Book details")
                        .appendChild(bookComponent)
                        .appendChild(Row.create()
                                .appendChild(Column.span6()
                                        .appendChild(FlexLayout.create()
                                                .setGap("10px")
                                                .appendChild(editElement
                                                        .appendChild(Button.createPrimary(Icons.ALL.pencil_mdi())
                                                                .setContent("Edit")
                                                                .setMinWidth("120px")
                                                                .addClickListener(evt -> uiHandlers.onEdit()))
                                                )
                                                .appendChild(saveElement
                                                        .appendChild(Button.createPrimary(Icons.ALL.floppy_mdi())
                                                                .setContent("Save")
                                                                .setMinWidth("120px")
                                                                .addClickListener(evt -> uiHandlers.onSave()))
                                                )
                                                .appendChild(cancelElement
                                                        .appendChild(Button.create(Icons.ALL.floppy_mdi())
                                                                .setContent("Cancel")
                                                                .linkify()
                                                                .setMinWidth("120px")
                                                                .addClickListener(evt -> uiHandlers.onCancel()))
                                                )
                                                .appendChild(backToListElement
                                                        .appendChild(Button.create(Icons.ALL.view_list_mdi())
                                                                .setContent("Books list")
                                                                .linkify()
                                                                .setMinWidth("120px")
                                                                .addClickListener(evt -> uiHandlers.onBackToList())))
                                        )
                                )
                        )
                );
        return root.element();
    }

    @Override
    public void edit(Book book) {
        bookComponent.edit(book);
    }

    @Override
    public void onError(String message) {
        Notification.createDanger(message).show();
    }

    @Override
    public Book save() {
        return bookComponent.save();
    }

    public void setEditable(boolean editable){
        bookComponent.setEditable(editable);
        editElement.toggleDisplay(!editable);
        backToListElement.toggleDisplay(!editable);
        saveElement.toggleDisplay(editable);
        cancelElement.toggleDisplay(editable);
    }

    @Override
    public void setUiHandlers(BookDetailsUiHandlers uiHandlers) {
        this.uiHandlers = uiHandlers;
    }
}