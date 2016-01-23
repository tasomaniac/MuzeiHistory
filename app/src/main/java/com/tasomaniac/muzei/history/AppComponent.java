package com.tasomaniac.muzei.history;


import com.tasomaniac.muzei.history.artwork.ArtworkService;
import com.tasomaniac.muzei.history.data.DataModule;
import com.tasomaniac.muzei.history.settings.SettingsFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, DataModule.class})
public interface AppComponent {

    void inject(App app);

    void inject(SettingsFragment fragment);

    void inject(ArtworkService service);


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
