package org.dominokit.samples.shell.client.views.ui;

import elemental2.dom.HTMLDivElement;
import org.dominokit.domino.api.client.annotations.UiView;
import org.dominokit.domino.api.client.mvp.slots.IsSlot;
import org.dominokit.domino.ui.layout.Layout;
import org.dominokit.domino.view.BaseElementView;
import org.dominokit.domino.view.slots.SingleElementSlot;
import org.dominokit.samples.shell.client.presenters.ShellProxy;
import org.dominokit.samples.shell.client.views.ShellView;

@UiView(presentable = ShellProxy.class)
public class ShellViewImpl extends BaseElementView<HTMLDivElement> implements ShellView{

    private Layout layout= Layout.create("Book store");

    @Override
    public HTMLDivElement init() {
        layout.autoFixLeftPanel();
        return layout.element();
    }

    @Override
    public IsSlot<?> getLeftPanelSlot() {
        return SingleElementSlot.of(layout.getLeftPanel());
    }

    @Override
    public IsSlot<?> getContentSlot() {
        return SingleElementSlot.of(layout.getContentPanel());
    }
}