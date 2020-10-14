package edu.ptu.androidtest._30_storage._40_byte;

import android.os.Parcel;
import android.os.Parcelable;

public class _002_Parcelable {
    //Parcelable优于Serializable不需要大量反射，edu.ptu.javatest._80_storage._80_file._04_ObjectStream.ObjectStreamTest
    public static class UserParcelable implements Parcelable {
        int id;
        public UserParcelable(int id){
            this.id=id;
        }
        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(id);
        }

        private static final Creator<UserParcelable> CREATOR = new Creator<UserParcelable>() {
            @Override
            public UserParcelable createFromParcel(Parcel source) {
                return new UserParcelable(source.readInt());
//                return new UserParcelable(source);
            }

            @Override
            public UserParcelable[] newArray(int size) {
                return new UserParcelable[size];
            }
        };
    }
}
