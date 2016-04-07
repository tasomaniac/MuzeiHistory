package com.tasomaniac.muzei.history.data.artwork;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

import nl.qbusict.cupboard.convert.EntityConverter.ColumnType;
import nl.qbusict.cupboard.convert.FieldConverter;

public class UriFieldConverter implements FieldConverter<Uri> {

    @Override
    public Uri fromCursorValue(Cursor cursor, int index) {
        String uriString = cursor.getString(index);
        return uriString != null ? Uri.parse(uriString) : null;
    }

    @Override
    public void toContentValue(Uri value, String key, ContentValues values) {
        values.put(key, (value != null) ? value.toString() : null);
    }

    @Override
    public ColumnType getColumnType() {
        return ColumnType.TEXT;
    }

}
