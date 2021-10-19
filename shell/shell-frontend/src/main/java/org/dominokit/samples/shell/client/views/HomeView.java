package org.dominokit.samples.shell.client.views;

import org.dominokit.domino.api.client.mvp.view.ContentView;
import org.dominokit.domino.api.client.mvp.view.HasUiHandlers;
import org.dominokit.domino.api.client.mvp.view.UiHandlers;

public interface HomeView extends ContentView, HasUiHandlers<HomeView.HomeUiHandlers> {

    interface HomeUiHandlers extends UiHandlers {
    }
}