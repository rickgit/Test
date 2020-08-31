package edu.ptu.androidtest.di.dagger2._00_app.mvpview;

import javax.inject.Inject;

import edu.ptu.androidtest.di.dagger2._02_mvp.IPresenter;
import edu.ptu.androidtest.di.dagger2._02_mvp.IView;

public class MvpFragmentView<P extends IPresenter> implements IView {
    @Inject
    public P presenter;

}
