package com.tasomaniac.muzei.history.artwork;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.text.TextUtils;

import java.net.URISyntaxException;

import nl.qbusict.cupboard.convert.EntityConverter.ColumnType;
import nl.qbusict.cupboard.convert.FieldConverter;

public class IntentFieldConverter implements FieldConverter<Intent> {

    @Override
    public Intent fromCursorValue(Cursor cursor, int index) {
        try {
            String viewIntent = cursor.getString(index);
            if (!TextUtils.isEmpty(viewIntent)) {
                return Intent.parseUri(viewIntent, Intent.URI_INTENT_SCHEME);
            }
        } catch (URISyntaxException ignored) {
        }
        return null;
    }

    @Override
    public void toContentValue(Intent value, String key, ContentValues values) {
        values.put(key, value != null ? value.toUri(Intent.URI_INTENT_SCHEME) : null);
    }

    @Override
    public ColumnType getColumnType() {
        return ColumnType.TEXT;
    }
}
