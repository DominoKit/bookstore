package org.dominokit.samples.shell.client.views.ui;

import elemental2.dom.HTMLDivElement;
import org.dominokit.domino.ui.grid.Column;
import org.dominokit.domino.ui.grid.Row;
import org.dominokit.domino.view.BaseElementView;

import org.dominokit.domino.api.client.annotations.UiView;
import org.dominokit.domino.ui.utils.DominoElement;

import org.dominokit.samples.shell.client.presenters.HomeProxy;
import org.dominokit.samples.shell.client.views.HomeView;

import static org.jboss.elemento.Elements.h;

@UiView(presentable = HomeProxy.class)
public class HomeViewImpl extends BaseElementView<HTMLDivElement> implements HomeView{

    private HomeUiHandlers uiHandlers;
    private DominoElement<HTMLDivElement> root = DominoElement.div();

    @Override
    public HTMLDivElement init() {
        root.appendChild(Row.create()
                .appendChild(Column.span6().offset3()
                        .appendChild(DominoElement.of(h(2)
                                .textContent("Welcome to domino book store."))
                                .setTextAlign("center")
                        )
                )
        );
        return root.element();
    }

    @Override
    public void setUiHandlers(HomeUiHandlers uiHandlers) {
        this.uiHandlers = uiHandlers;
    }
}