package org.dominokit.samples.shell.client.presenters;

import org.dominokit.domino.api.client.annotations.presenter.AutoReveal;
import org.dominokit.domino.api.client.annotations.presenter.AutoRoute;
import org.dominokit.domino.api.client.annotations.presenter.PresenterProxy;
import org.dominokit.domino.api.client.annotations.presenter.Slot;
import org.dominokit.domino.api.client.mvp.presenter.ViewBaseClientPresenter;
import org.dominokit.samples.shell.client.views.HomeView;
import org.dominokit.samples.shell.shared.Slots;

@PresenterProxy(parent = "shell")
@AutoRoute(token = "home")
@Slot(Slots.CONTENT)
@AutoReveal
public class HomeProxy extends ViewBaseClientPresenter<HomeView> {

}