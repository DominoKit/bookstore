package org.dominokit.samples.shell.client.presenters;

import org.dominokit.domino.api.client.annotations.presenter.AutoReveal;
import org.dominokit.domino.api.client.annotations.presenter.AutoRoute;
import org.dominokit.domino.api.client.annotations.presenter.PresenterProxy;
import org.dominokit.domino.api.client.annotations.presenter.Slot;
import org.dominokit.domino.api.client.mvp.presenter.ViewBaseClientPresenter;
import org.dominokit.samples.shell.client.views.MenuView;
import org.dominokit.samples.shell.shared.Slots;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@PresenterProxy(parent = "shell")
@AutoRoute()
@Slot(Slots.LEFT_PANEL)
@AutoReveal
public class MenuProxy extends ViewBaseClientPresenter<MenuView> implements MenuView.MenuUiHandlers {

    private static final Logger LOGGER = LoggerFactory.getLogger(MenuProxy.class);

    @Override
    public void onMeuSelected(String token) {
        history().fireState(token);
    }
}