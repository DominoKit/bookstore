package org.dominokit.samples.library.client.views.ui;

import elemental2.dom.HTMLDivElement;
import org.dominokit.domino.ui.forms.DoubleBox;
import org.dominokit.domino.ui.forms.FieldsGrouping;
import org.dominokit.domino.ui.forms.Select;
import org.dominokit.domino.ui.forms.SelectOption;
import org.dominokit.domino.ui.forms.TextBox;
import org.dominokit.domino.ui.forms.validations.ValidationResult;
import org.dominokit.domino.ui.grid.Column;
import org.dominokit.domino.ui.grid.Row;
import org.dominokit.domino.ui.utils.BaseDominoElement;
import org.dominokit.domino.ui.utils.DominoElement;
import org.dominokit.samples.library.shared.model.Book;
import org.gwtproject.editor.client.Editor;
import org.gwtproject.editor.client.SimpleBeanEditorDriver;
import org.gwtproject.editor.client.annotation.IsDriver;

import java.util.stream.IntStream;

public class BookComponent extends BaseDominoElement<HTMLDivElement, BookComponent> implements Editor<Book> {

    @IsDriver
    interface Driver extends SimpleBeanEditorDriver<Book, BookComponent> {
    }

    TextBox title;
    TextBox author;
    Select<Integer> year;
    TextBox publisher;
    DoubleBox cost;

    private DominoElement<HTMLDivElement> root = DominoElement.div();

    private FieldsGrouping fieldsGrouping = FieldsGrouping.create();
    private Driver driver;
    private Book book;

    public BookComponent() {
        init(this);
        title = TextBox.create("Title")
                .setFixErrorsPosition(true)
                .groupBy(fieldsGrouping);

        author = TextBox.create("Author")
                .setFixErrorsPosition(true)
                .groupBy(fieldsGrouping);

        year = Select.<Integer>create("Year")
                .apply(self -> {
                    IntStream.range(1500, 2022)
                            .forEach(value -> self.appendChild(SelectOption.<Integer>create(value, value + "", value + "")));
                })
                .groupBy(fieldsGrouping);

        publisher = TextBox.create("Publisher")
                .setFixErrorsPosition(true)
                .groupBy(fieldsGrouping);

        cost = DoubleBox.create("Price")
                .addValidator(() -> {
                    if (cost.getValue() < 0) {
                        return ValidationResult.invalid("Price should not be negative");
                    }
                    return ValidationResult.valid();
                })
                .groupBy(fieldsGrouping);

        fieldsGrouping.setRequired(true);

        root.appendChild(DominoElement.div()
                .appendChild(Row.create()
                        .appendChild(Column.span12()
                                .appendChild(title)
                        )
                )
                .appendChild(Row.create()
                        .appendChild(Column.span12()
                                .appendChild(author)
                        )
                )
                .appendChild(Row.create()
                        .appendChild(Column.span12()
                                .appendChild(year)
                        )
                )
                .appendChild(Row.create()
                        .appendChild(Column.span12()
                                .appendChild(publisher)
                        )
                )
                .appendChild(Row.create()
                        .appendChild(Column.span12()
                                .appendChild(cost)
                        )
                )

        );

        driver = new BookComponent_Driver_Impl();
        driver.initialize(this);
    }

    public boolean isValid(){
        return fieldsGrouping.validate().isValid();
    }

    public void edit(Book book) {
        driver.edit(book);
    }

    public Book save() {
        return driver.flush();
    }

    public void setEditable(boolean editable){
        fieldsGrouping.setReadOnly(!editable);
    }
    @Override
    public HTMLDivElement element() {
        return root.element();
    }
}
