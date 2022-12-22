package com.example.tgJNI;


import static com.example.tgJNI.tgnet.NativeByteBuffer.native_setJava;

import android.os.Environment;
import android.util.Log;

import com.example.tgJNI.SQLite.SQLiteCursor;
import com.example.tgJNI.SQLite.SQLiteDatabase;
//import com.example.tgJNI.SQLite.SQLiteException;
//import com.example.tgJNI.tgnet.NativeByteBuffer;
import com.example.tgJNI.SQLite.SQLiteException;
import com.example.tgJNI.tgnet.NativeByteBuffer;
import com.example.tgJNI.tgnet.TLRPC;

import java.util.Locale;

public class MessageStorage {
    private static final String TAG = "tgJNI";
    public static SQLiteDatabase database = null;
    private String mCurrApkPath = Environment.getExternalStorageDirectory().getPath() + "/";
    private static final String COPY_WX_DATA_DB = "mycache4.db";
    String copyFilePath = mCurrApkPath + COPY_WX_DATA_DB;
//    public void openDatabase() {
//        try {
//            database = SQLiteDatabase.openDatabase(copyFilePath, null, SQLiteDatabase.OPEN_READWRITE);
//            database.rawQuery("PRAGMA secure_delete = ON",null);
//            database.rawQuery("PRAGMA temp_store = MEMORY",null);
//            database.rawQuery("PRAGMA journal_mode = WAL",null);
//            database.rawQuery("PRAGMA journal_size_limit = 10485760",null);
//        } catch (Exception exception) {
//            Log.d("cxaErr",exception.getMessage());
//
//        }
//    }

    public void ReadHistoryData() {
        Log.d(TAG, "in ReadHistoryData");
        String fid = "0";
        String off = "0";
        String cnt = "1000";
        // region
        //        Cursor cursor = null;
//        database = SQLiteDatabase.openDatabase(copyFilePath, null, SQLiteDatabase.OPEN_READWRITE);
//        database.rawQuery("PRAGMA secure_delete = ON", null);
//        database.rawQuery("PRAGMA temp_store = MEMORY", null);
//        database.rawQuery("PRAGMA journal_mode = WAL", null);
//        database.rawQuery("PRAGMA journal_size_limit = 10485760", null);
//        try {
////            String sql ="SELECT d.did, d.last_mid, d.unread_count, d.date, m.data, m.read_state, m.mid, m.send_state, s.flags, m.date, d.pts, d.inbox_max, d.outbox_max, m.replydata, d.pinned, d.unread_count_i, d.flags, d.folder_id, d.data, d.unread_reactions, d.last_mid_group FROM dialogs as d LEFT JOIN messages_v2 as m ON d.last_mid = m.mid AND d.did = m.uid AND d.last_mid_group IS NULL LEFT JOIN dialog_settings as s ON d.did = s.did WHERE d.folder_id = ? ORDER BY d.pinned DESC, d.date DESC LIMIT ?,?";
////            String[] selectionArgs = {fid, off, cnt};
////            cursor= database.rawQuery(sql,selectionArgs);
//            String sql = "Select * from messages_v2";
//            cursor = database.rawQuery(sql, null);
//            while (cursor.moveToNext()) {
//                int dataIndex = cursor.getColumnIndex("data");
//                byte[] messageBytes = cursor.getBlob(dataIndex);
//
////                NativeByteBuffer data = cursor.byteBufferValue(4);
////                if (data != null) {
////                    TLRPC.Message message = TLRPC.Message.TLdeserialize(data, data.readInt32(false), false);
////                    if (message != null) {
////
////                    } else {
////                        data.reuse();
////                    }
////                }
//            }
//
//            cursor.close();
//            cursor = null;
//        } catch (Exception e) {
//        } finally {
//            if (cursor != null) {
////                cursor.dispose();
//            }
//        }
        //endregion
        SQLiteCursor cursor = null;
        native_setJava(true);


        try {
            database = new SQLiteDatabase(copyFilePath);
            database.executeFast("PRAGMA secure_delete = ON").stepThis().dispose();
            database.executeFast("PRAGMA temp_store = MEMORY").stepThis().dispose();
            database.executeFast("PRAGMA journal_mode = WAL").stepThis().dispose();
            database.executeFast("PRAGMA journal_size_limit = 10485760").stepThis().dispose();
            cursor = database.queryFinalized("select mid,uid,data,date from messages_v2 ORDER BY date limit 50 offset 0");
            while (cursor.next()) {
                long did = cursor.longValue(0);
                NativeByteBuffer data = cursor.byteBufferValue(2);
                int dataInt = data.readInt32(false);
                Log.d(TAG,Integer.toHexString(dataInt));

                switch (dataInt) {
                    case 0x1d86f70e:

                        break;
                    case 0xa7ab1991:

                        break;
                    case 0xc3060325:

                        break;
                    case 0x555555fa:

                        break;
                    case 0x555555f9:

                        break;
                    case 0x90dddc11:

                        break;
                    case 0xc09be45f:

                        break;
                    case 0xc992e15c:

                        break;
                    case 0x5ba66c13:

                        break;
                    case 0xc06b9607:

                        break;
                    case 0x83e5de54:

                        break;
                    case 0x2bebfa86:

                        break;
                    case 0x44f9b43d:

                        break;
                    case 0x90a6ca84:

                        break;
                    case 0x1c9b1027:

                        break;
                    case 0xa367e716:

                        break;
                    case 0x5f46804:

                        break;
                    case 0x567699b3:

                        break;
                    case 0x9f8d60bb:

                        break;
                    case 0x22eb6aba:

                        break;
                    case 0x555555F8:

                        break;
                    case 0x9789dac4:

                        break;
                    case 0x452c0e65:

                        break;
                    case 0xf52e6b7f:

                        break;
                    case 0x58ae39c9:

                        break;
                    case 0xbce383d2:

                        break;
                    case 0x85d6cbe2:

                        break;
                    case 0x38116ee0:
                        //constructor 940666592

                        break;
                    case 0x9e19a1f6:

                        break;
                    case 0x286fa604:

                        break;
                    case 0x2b085862:

                        break;
                    case 0xf07814c8:

                        break;
                }
                data.reuse();

                System.out.println("data");

            }
        } catch (SQLiteException e) {
            e.printStackTrace();
        }

    }
}
