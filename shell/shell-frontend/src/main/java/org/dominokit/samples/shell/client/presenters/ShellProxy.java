package org.dominokit.samples.shell.client.presenters;

import org.dominokit.domino.api.client.annotations.presenter.AutoReveal;
import org.dominokit.domino.api.client.annotations.presenter.AutoRoute;
import org.dominokit.domino.api.client.annotations.presenter.OnReveal;
import org.dominokit.domino.api.client.annotations.presenter.PresenterProxy;
import org.dominokit.domino.api.client.annotations.presenter.RegisterSlots;
import org.dominokit.domino.api.client.annotations.presenter.RevealCondition;
import org.dominokit.domino.api.client.annotations.presenter.Slot;
import org.dominokit.domino.api.client.mvp.presenter.ViewBaseClientPresenter;
import org.dominokit.domino.api.shared.extension.PredefinedSlots;
import org.dominokit.samples.shell.client.views.ShellView;
import org.dominokit.samples.shell.shared.Slots;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@PresenterProxy(name = "shell")
@AutoRoute()
@Slot(PredefinedSlots.BODY_SLOT)
@AutoReveal
@RegisterSlots({Slots.LEFT_PANEL, Slots.CONTENT})
public class ShellProxy extends ViewBaseClientPresenter<ShellView> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShellProxy.class);

    @OnReveal
    public void fireInitialState(){
        if(history().currentToken().isEmpty()){
            history().fireState("home");
        }
    }
}