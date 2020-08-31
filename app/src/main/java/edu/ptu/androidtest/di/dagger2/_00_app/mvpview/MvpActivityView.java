package edu.ptu.androidtest.di.dagger2._00_app.mvpview;

import android.app.Activity;

import javax.inject.Inject;

import edu.ptu.androidtest.di.dagger2._02_mvp.IPresenter;
import edu.ptu.androidtest.di.dagger2._02_mvp.IView;

public class MvpActivityView<P extends IPresenter> implements IView {
    @Inject
    public P presenter;

}
