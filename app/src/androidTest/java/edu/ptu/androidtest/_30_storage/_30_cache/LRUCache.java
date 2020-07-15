package edu.ptu.androidtest._30_storage._30_cache;

import android.graphics.Bitmap;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.LruCache;

import org.junit.Test;

public class LRUCache {
    @Test
    public void testLru(){
        LruCache<Object, Object> lruCache = new LruCache (10){
            @Override
            protected int sizeOf(@NonNull Object key, @NonNull Object value) {
                return super.sizeOf(key, value);
            }

            @Override
            protected void entryRemoved(boolean evicted, @NonNull Object key, @NonNull Object oldValue, @Nullable Object newValue) {
                super.entryRemoved(evicted, key, oldValue, newValue);
            }
        };

    }
}
