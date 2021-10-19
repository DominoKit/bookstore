package org.dominokit.samples.shell.client.views;

import org.dominokit.domino.api.client.mvp.view.ContentView;
import org.dominokit.domino.api.client.mvp.view.HasUiHandlers;
import org.dominokit.domino.api.client.mvp.view.UiHandlers;

public interface MenuView extends ContentView, HasUiHandlers<MenuView.MenuUiHandlers> {

    interface MenuUiHandlers extends UiHandlers {
        void onMeuSelected(String token);
    }
}