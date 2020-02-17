package com.harsh.dkatalislabs.di.modules;

import com.harsh.dkatalislabs.presentation.view.activities.FavUsersActivity;
import com.harsh.dkatalislabs.presentation.view.activities.UsersActivity;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityModule {
    @ContributesAndroidInjector
    abstract UsersActivity contributeUserActivity();

    @ContributesAndroidInjector
    abstract FavUsersActivity contributeFavUserActivity();
}