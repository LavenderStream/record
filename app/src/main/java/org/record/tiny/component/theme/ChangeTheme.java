package org.record.tiny.component.theme;


@SuppressWarnings("All")
public interface ChangeTheme
{
    void themeChanged(Model themeMode);

    enum Model {
        DAY, NIGHT
    }
}