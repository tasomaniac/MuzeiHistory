package com.tasomaniac.muzei.history;


import com.tasomaniac.muzei.history.ui.SettingsFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    void inject(App app);

    void inject(SettingsFragment fragment);

    /**
     * An initializer that creates the graph from an application.
     */
    final class Initializer {
        static AppComponent init(App app) {
            return DaggerAppComponent.builder()
                    .appModule(new AppModule(app))
                    .build();
        }

        private Initializer() {
        } // No instances.
    }
}