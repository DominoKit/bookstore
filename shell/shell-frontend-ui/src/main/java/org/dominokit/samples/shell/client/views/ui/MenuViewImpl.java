package org.dominokit.samples.shell.client.views.ui;

import elemental2.dom.HTMLDivElement;
import org.dominokit.domino.ui.icons.Icons;
import org.dominokit.domino.ui.tree.Tree;
import org.dominokit.domino.ui.tree.TreeItem;
import org.dominokit.domino.view.BaseElementView;

import org.dominokit.domino.api.client.annotations.UiView;
import org.dominokit.domino.ui.utils.DominoElement;

import org.dominokit.samples.shell.client.presenters.MenuProxy;
import org.dominokit.samples.shell.client.views.MenuView;

import static org.jboss.elemento.Elements.h;

@UiView(presentable = MenuProxy.class)
public class MenuViewImpl extends BaseElementView<HTMLDivElement> implements MenuView{

    private MenuUiHandlers uiHandlers;
    private Tree<String> menu= Tree.create("Menu");

    @Override
    public HTMLDivElement init() {
        menu
                .appendChild(TreeItem.create("Home", Icons.ALL.home_mdi(), "home"))
                .appendChild(TreeItem.create("Books", Icons.ALL.book_mdi(), "books"))
                .addItemClickListener(treeItem -> uiHandlers.onMeuSelected(treeItem.getValue()));
        return menu.element();
    }


    @Override
    public void setUiHandlers(MenuUiHandlers uiHandlers) {
        this.uiHandlers = uiHandlers;
    }
}